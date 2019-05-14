package ir.daak.escpos;

import ir.daak.escpos.dto.CommandDto;
import ir.daak.escpos.maps.CommandList;
import ir.daak.escpos.model.Command;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortException;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

public class PrinterService extends SerialPort {
    private CommandList commandsList = new CommandList();

    public PrinterService(String portName) {
        super(portName);
    }

    public PrinterService() {
        super("COM1");
    }

    private void open() throws SerialPortException {
        this.openPort();

        this.setParams(SerialPort.BAUDRATE_9600,
                SerialPort.DATABITS_8,
                SerialPort.STOPBITS_1,
                SerialPort.PARITY_NONE);//Set params. Also you can set params by this string: serialPort.setParams(9600, 8, 1, 0);
        this.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);

        int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;//Prepare mask
        this.setEventsMask(mask);//Set mask
        this.addEventListener((event)-> serialEvent(event));
    }

    //TODO from davood akbari: handle events
    private void serialEvent(SerialPortEvent event) {
        if(event.isRXCHAR()){//If data is available
            if(event.getEventValue() == 10){//Check bytes count in the input buffer
                //Read data, if 10 bytes available
                try {
                    byte buffer[] = this.readBytes(10);
                }
                catch (SerialPortException ex) {
                    System.out.println(ex);
                }
            }
        }
        else if(event.isCTS()){//If CTS line has changed state
            if(event.getEventValue() == 1){//If line is ON
                System.out.println("CTS - ON");
            }
            else {
                System.out.println("CTS - OFF");
            }
        }
        else if(event.isDSR()){///If DSR line has changed state
            if(event.getEventValue() == 1){//If line is ON
                System.out.println("DSR - ON");
            }
            else {
                System.out.println("DSR - OFF");
            }
        }
    }

    private byte[] commands2Bytes(ArrayList<CommandDto> CommandsIn) throws IOException {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        IrSys irSys = IrSys.initialize();

        for (CommandDto commandIn : CommandsIn) {
            Command command =  commandsList.get(commandIn.getName());
            byte[] hexCommand = command.getCommand();

            if (command.isHasParameter()){
                String param = commandIn.getParam();

                if (command.isNotPrintableCode()){
                    if (command.isTexted()){
                        output.write(irSys.getBytes(param));
                    }
                    else {
                        irSys.direction(param);
                    }
                }
                else {
                    hexCommand[hexCommand.length - 1] = Byte.parseByte(param);
                    output.write(hexCommand);
                }
            }
            else {
                output.write(hexCommand);
            }
        }

        return output.toByteArray();
    }

    public void print(ArrayList<CommandDto> commands) throws SerialPortException, IOException {
        open();
        ByteArrayOutputStream output = new ByteArrayOutputStream();

        output.write(CommandList.initialize);
        output.write(commands2Bytes(commands));
        output.write(CommandList.eject);

        writeBytes(output.toByteArray());

        closePort();
    }
}
