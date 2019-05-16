package ir.daak;


import ir.daak.escpos.InvalidCommandEntryException;
import ir.daak.escpos.PrinterService;
import ir.daak.escpos.dto.CommandDto;
import jssc.SerialPort;
import jssc.SerialPortException;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.IOException;
import java.util.ArrayList;


public class SlipTest
{

   static SerialPort serialPort;

    PrinterService printerService = new PrinterService();

    @Test
    public void print() throws InvalidCommandEntryException /*throws UnsupportedEncodingException*/ {
//        EscPosBuilder escPos = new EscPosBuilder();
//        byte[] data = escPos.initialize()
//                .font(Font.EMPHASIZED)
//                .align(Align.CENTER).feed(10)
//                .text("HELLO WORLD with com port")
//                .feed(3)
//                .kick(DrawerKick.PIN2)
//                .cut(Cut.FULL)
//                .getBytes();


//        serialPort = new SerialPort("COM1");
        try {
            PrinterService printerService = new PrinterService();
            printerService.print(new ArrayList<CommandDto>(){{
                add(new CommandDto("DIRECTION_LEFT_TO_RIGHT", "LEFT_TO_RIGHT"));
                add(new CommandDto("TEXT", "hello iran"));

                add(new CommandDto("FEED_LINES", "1"));

                add(new CommandDto("DIRECTION_RIGHT_TO_LEFT", "RIGHT_TO_LEFT"));
                add(new CommandDto("TEXT", "سلام ایران و ایرانی"));

                add(new CommandDto("FEED_LINES", "1"));

                add(new CommandDto("DIRECTION_LEFT_TO_RIGHT", "LEFT_TO_RIGHT"));
                add(new CommandDto("ALIGN_LEFT", "ALIGN_LEFT"));
                add(new CommandDto("TEXT", "hello iran"));

                add(new CommandDto("FEED_LINES", "1"));

                add(new CommandDto("DIRECTION_RIGHT_TO_LEFT", "RIGHT_TO_LEFT"));
                add(new CommandDto("ALIGN_RIGHT", "ALIGN_RIGHT"));
                add(new CommandDto("TEXT", "سلام ایران و ایرانی"));

                add(new CommandDto("FEED_LINES", "1"));

                add(new CommandDto("ALIGN_CENTER", "ALIGN_CENTER"));
                add(new CommandDto("TEXT", "سلام ایران و ایرانی"));

                add(new CommandDto("FEED_LINES", "10"));

                add(new CommandDto("TEXT", "سلام ایران و ایرانی"));
            }});

//            ByteArrayOutputStream output = new ByteArrayOutputStream();
//            byte[] init =  { 0x1b, 0x40}; //Initialize printer
//            byte[] eject =  { 0x0c }; //Print and eject slip paper
//
//            byte[] sensor =  { 0x1b, 0x63, 0x33, 2}; //Select paper sensor(s) to stop printing
//            byte[] sensor2 =  { 0x1b, 0x76}; //Transmit paper sensor status
//            byte[] sensor3 =  { 0x10, 0x04, 0x05}; //Real-time status transmission
//
//            byte[] CD_KICK_5 = {0x1b, 0x70, 0x01}; // Sends a pulse to pin 5 []
//            byte[] FRONT =  { 0x1C, 0x61, 0x31}; //Load/check paper to print starting position
//
//            byte[] printBuffer =  { 0x0D }; //Carriage return.  print buffer and does not feed the pape
//
//            byte[] feedLines =  {0x1B, 0x64, 12};
//            byte[] newLine =  {0x0A};
//
////            byte[] alignRight =  {0x1B, 0x61, 0x02};
//            byte[] rightToLeftJ =   {0x1b, 0x61, 0x02};
////            String text = "٠١٢٣٤٥ds٦٧٨٩045asdزمین٠١df٢d٤شس٥٦٧٨٩خاکزمین۰۱۲۳۴gf۵۶g۷۸۹ سلام بر حسین david akbari  ٠١٢٣٤٥ فداییان اسلام";
////            String text = " ٠١٢٣٤٥ds٦٧٨٩045asdزمین٠١df٢d٤شس٥٦٧٨٩خاکزمین۰۱۲۳۴gf۵۶g۷۸۹ ali bgvh gfg gbvfسلام بر حسین david akbari  ٠١٢٣٤٥ فداییان اسلام ddsa fdasd fdsaasf سیبیسب  سبسیب dafasf fdfa asd dsfa asdf sdf fsadf asfd sfd fdfas سیسش بسیبشس بیبش سیب بیستل سبیسیب بسی سیب 3234324 324234 324 23423 324 یبن aaaa123 7686767 545 بلیل 343 gf 242 dds5452 dsd5 5 dsبیسا ٠١٢٣٤٥ ٠١٢٣٤٥ ٠١٢٣٤٥";
//            String text = "در سال 1395 50 راس از گاو های حسنی در iran bank به خاطر عدم توجه نابود شد این 50 گاه  و بیگاه ماندند  واین شدن  بودن یا نبودن بامقدار واضح برای مردم ایران.";
////            String text1 = "123456789-123456789-123456789-123456789";
////            String text2 = "123456789-123456789-123456789-123456789";
////            output.write(data);
//            output.write(init);
////            output.write(sensor);
////            output.write(sensor2);
//            output.write(CD_KICK_5);
//            output.write(FRONT);
//            output.write(printBuffer);
////            output.write("ali mamad".getBytes());
//            output.write(feedLines);
//            output.write(rightToLeftJ);
////            output.write(alignRight);
//            output.write(getBytes(text, Direction.RIGHT_TO_LEFT));
////            output.write(newLine);
////            output.write(getBytes(text2, Direction.RIGHT_TO_LEFT));
//            output.write(eject);
//
//            byte[] data = output.toByteArray();

//            serialPort.writeBytes(data);//Write data to port
//
//            serialPort.closePort();//Close serial port
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

//        byte[] out = getBytes(text, Direction.LEFT_TO_RIGHT);

//        printerService.printString("OLIVETTI PR4 SL Slip", "\n\n");

//        printerService.printBytes("OLIVETTI PR4 SL Slip", out);

        //print some stuff
//        printerService.printString("OLIVETTI PR4 SL Slip", "\n");

        // cut that paper!
//        byte[] cutP =  { 0x1d, 'V', 1 };

//        printerService.printBytes("OLIVETTI PR4 SL Slip", data);

        assertTrue( true );
    }
}
