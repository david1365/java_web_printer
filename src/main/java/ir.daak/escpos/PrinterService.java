package ir.daak.escpos;

import ir.daak.escpos.enums.Direction;
import ir.daak.escpos.maps.CommandList;
import ir.daak.escpos.model.Command;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortException;

import java.io.ByteArrayOutputStream;
import java.util.HashMap;

public class PrinterService extends SerialPort {
    private CommandList commandsList = new CommandList();

    public PrinterService(String portName) {
        super(portName);
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

    private byte[] commands2Bytes(HashMap<String, Object> CommandsIn) {
        ByteArrayOutputStream output = new ByteArrayOutputStream();
        Direction direction;

        for (String commandIn : CommandsIn.keySet()) {
            Command command =  commandsList.get(commandIn);
            Byte[] hexCommand = command.getCommand();

            if (command.isHasParameter()){
                if (command.isNotPrintableCode()){

                }
                else {
                    hexCommand[hexCommand.length - 1] = (Byte) CommandsIn.get(commandIn);
                }
            }
        }

        return output.toByteArray();
    }

    public void print(HashMap<String, Object> commands) throws SerialPortException {
        open();

        writeBytes(commands2Bytes(commands));

        closePort();
    }


}
