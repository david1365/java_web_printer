package ir.daak;


import ir.daak.escpos.EscPosBuilder;
import ir.daak.escpos.command.Align;
import ir.daak.escpos.command.Cut;
import ir.daak.escpos.command.Font;
import ir.daak.irsys.enums.Direction;
import jssc.SerialPort;
import jssc.SerialPortEvent;
import jssc.SerialPortEventListener;
import jssc.SerialPortException;
import org.junit.Test;
import sun.reflect.generics.tree.ByteSignature;

import static org.junit.Assert.*;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.io.UnsupportedEncodingException;

import static ir.daak.irsys.IrSysUtil.*;


public class SlipTest
{

   static SerialPort serialPort;

    PrinterService printerService = new PrinterService();

    @Test
    public void print() throws UnsupportedEncodingException {
        EscPosBuilder escPos = new EscPosBuilder();
//        byte[] data = escPos.initialize()
//                .font(Font.EMPHASIZED)
//                .align(Align.CENTER).feed(10)
//                .text("HELLO WORLD with com port")
//                .feed(3)
//                .cut(Cut.FULL)
//                .getBytes();


        serialPort = new SerialPort("COM1");
        try {
            serialPort.openPort();//Open serial port
            serialPort.setParams(SerialPort.BAUDRATE_9600,
                    SerialPort.DATABITS_8,
                    SerialPort.STOPBITS_1,
                    SerialPort.PARITY_NONE);//Set params. Also you can set params by this string: serialPort.setParams(9600, 8, 1, 0);
            serialPort.setFlowControlMode(SerialPort.FLOWCONTROL_NONE);

            int mask = SerialPort.MASK_RXCHAR + SerialPort.MASK_CTS + SerialPort.MASK_DSR;//Prepare mask
            serialPort.setEventsMask(mask);//Set mask
            serialPort.addEventListener(new SerialPortReader());//Add SerialPortEventListener

            ByteArrayOutputStream output = new ByteArrayOutputStream();
            byte[] init =  { 0x1b, 0x40}; //Print and eject slip paper
            byte[] eject =  { 0x0c }; //Print and eject slip paper

            byte[] sensor =  { 0x1b, 0x63, 0x33, 2}; //Select paper sensor(s) to stop printing
            byte[] sensor2 =  { 0x1b, 0x76}; //Transmit paper sensor status
            byte[] sensor3 =  { 0x10, 0x04, 0x05}; //Real-time status transmission

            byte[] CD_KICK_5 = {0x1b, 0x70, 0x01}; // Sends a pulse to pin 5 []
            byte[] FRONT =  { 0x1C, 0x61, 0x31}; //Load/check paper to print starting position

            byte[] printBuffer =  { 0x0D }; //Carriage return.  print buffer and does not feed the pape

            byte[] feedLines =  {0x1B, 0x64, 12};
            byte[] newLine =  {0x0A};

//            byte[] alignRight =  {0x1B, 0x61, 0x02};
            byte[] rightToLeftJ =   {0x1b, 0x61, 0x02};
//            String text = "٠١٢٣٤٥ds٦٧٨٩045asdزمین٠١df٢d٤شس٥٦٧٨٩خاکزمین۰۱۲۳۴gf۵۶g۷۸۹ سلام بر حسین david akbari  ٠١٢٣٤٥ فداییان اسلام";
//            String text = " ٠١٢٣٤٥ds٦٧٨٩045asdزمین٠١df٢d٤شس٥٦٧٨٩خاکزمین۰۱۲۳۴gf۵۶g۷۸۹ ali bgvh gfg gbvfسلام بر حسین david akbari  ٠١٢٣٤٥ فداییان اسلام ddsa fdasd fdsaasf سیبیسب  سبسیب dafasf fdfa asd dsfa asdf sdf fsadf asfd sfd fdfas سیسش بسیبشس بیبش سیب بیستل سبیسیب بسی سیب 3234324 324234 324 23423 324 یبن aaaa123 7686767 545 بلیل 343 gf 242 dds5452 dsd5 5 dsبیسا ٠١٢٣٤٥ ٠١٢٣٤٥ ٠١٢٣٤٥";
            String text = "در سال 1395 50 راس از گاو های حسنی در iran bank به خاطر عدم توجه نابود شد این 50 گاه  و بیگاه ماندند  واین شدن  بودن یا نبودن بامقدار واضح برای مردم ایران.";
//            String text1 = "123456789-123456789-123456789-123456789";
//            String text2 = "123456789-123456789-123456789-123456789";
//            output.write(data);
            output.write(init);
//            output.write(sensor);
//            output.write(sensor2);
            output.write(CD_KICK_5);
            output.write(FRONT);
            output.write(printBuffer);
//            output.write("ali mamad".getBytes());
            output.write(feedLines);
            output.write(rightToLeftJ);
//            output.write(alignRight);
            output.write(getBytes(text, Direction.RIGHT_TO_LEFT));
//            output.write(newLine);
//            output.write(getBytes(text2, Direction.RIGHT_TO_LEFT));
            output.write(eject);

            byte[] data = output.toByteArray();

            serialPort.writeBytes(data);//Write data to port

            serialPort.closePort();//Close serial port
        }
        catch (SerialPortException | IOException ex) {
            System.out.println(ex);
        }

//        System.out.println(printerService.getPrinters());

//        String num = "!%()*+,-./0123456789:=[]\n" +
//                "{}«·»×÷˙،؛؟ءآأؤإئابت\n" +
//                "ثجحخدذرزسشصضطظعغـفقكلمنه\n" +
//                "وىي٠١٢٣٤٥٦٧٨٩پچژک\n" +
//                "گی۰۱۲۳۴۵۶۷۸۹ﭖﭗﭘﭙﭺﭻ\n" +
//                "ﭼﭽﮊﮋﮎﮏﮐﮑﮒﮓﮔﮕﯼﯽﯽﯾﺁﺃ\n" +
//                "ﺉﺊﺋﺌﺍﺎﺏﺐﺑﺒﺕﺖﺗﺘﺙﺚﺛﺜﺝ\n" +
//                "ﺞﺟﺠﺡﺢﺣﺤﺥﺦﺧﺨﺩﺪﺫﺬﺭﺮﺯﺰﺱ\n" +
//                "ﺲﺳﺴﺵﺶﺷﺸﺹﺺﺻﺼﺽﺾﺿﻀﻁﻂﻃﻄﻅﻆﻇﻈ" +
//                "\nﻉﻊﻋﻌﻍﻎﻏﻐﻑﻒﻓﻔﻕﻖﻗﻘﻙﻚﻛﻜﻝ" ;

//        String text = "٠١٢٣٤٥ds٦٧٨٩045asdزمین٠١df٢d٤شس٥٦٧٨٩خاکزمین۰۱۲۳۴gf۵۶g۷۸۹ سلام بر حسین david akbari  ٠١٢٣٤٥ فداییان اسلام";
//        String text = "٠١٢٣٤٥ds٦٧٨٩045asdزمینdf٢d٤شس٥٦٧٨٩خاکزمین۰۱۲۳۴gf۵۶g۷۸۹";
//        String text = "سسسسسسسسسسس";
//        byte[] out = new byte[num.length()];
//
//        for (int i = 0; i< num.length(); i++) {
//            Byte irSYS = unicodeIrsys.get(num.charAt(i));
//            out[i] = irSYS != null ? irSYS : (byte)num.charAt(i);
//        }

//        byte[] out = getBytes(text, Direction.LEFT_To_RIGHT);

//        printerService.printString("OLIVETTI PR4 SL Slip", "\n\n");

//        printerService.printBytes("OLIVETTI PR4 SL Slip", out);

        //print some stuff
//        printerService.printString("OLIVETTI PR4 SL Slip", "\n");

        // cut that paper!
//        byte[] cutP =  { 0x1d, 'V', 1 };

//        printerService.printBytes("OLIVETTI PR4 SL Slip", data);

        assertTrue( true );
    }


    static class SerialPortReader implements SerialPortEventListener {

        public void serialEvent(SerialPortEvent event) {
            if(event.isRXCHAR()){//If data is available
                if(event.getEventValue() == 10){//Check bytes count in the input buffer
                    //Read data, if 10 bytes available
                    try {
                        byte buffer[] = serialPort.readBytes(10);
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
    }
}
