package ir.daak;


import ir.daak.escpos.EscPosBuilder;
import ir.daak.escpos.command.Align;
import org.junit.Test;

import static org.junit.Assert.*;

import java.io.UnsupportedEncodingException;
import static ir.daak.escpos.map.IrSysMap.*;


public class SlipTest
{
    @Test
    public void print() throws UnsupportedEncodingException {
        EscPosBuilder escPos = new EscPosBuilder();
        byte[] data = escPos.initialize()
//                .font(Font.EMPHASIZED)
                .align(Align.CENTER)
////                .text("HELLO WORLD")
//                .feed(5)
//                .cut(Cut.PART)
                .getBytes();

        PrinterService printerService = new PrinterService();

        System.out.println(printerService.getPrinters());

        String num = "!%()*+,-./0123456789:=[]\n" +
                "{}«·»×÷˙،؛؟ءآأؤإئابت\n" +
                "ثجحخدذرزسشصضطظعغـفقكلمنه\n" +
                "وىي٠١٢٣٤٥٦٧٨٩پچژک\n" +
                "گی۰۱۲۳۴۵۶۷۸۹ﭖﭗﭘﭙﭺﭻ\n" +
                "ﭼﭽﮊﮋﮎﮏﮐﮑﮒﮓﮔﮕﯼﯽﯽﯾﺁﺃ\n" +
                "ﺉﺊﺋﺌﺍﺎﺏﺐﺑﺒﺕﺖﺗﺘﺙﺚﺛﺜﺝ\n" +
                "ﺞﺟﺠﺡﺢﺣﺤﺥﺦﺧﺨﺩﺪﺫﺬﺭﺮﺯﺰﺱ\n" +
                "ﺲﺳﺴﺵﺶﺷﺸﺹﺺﺻﺼﺽﺾﺿﻀﻁﻂﻃﻄﻅﻆﻇﻈ" +
                "\nﻉﻊﻋﻌﻍﻎﻏﻐﻑﻒﻓﻔﻕﻖﻗﻘﻙﻚﻛﻜﻝ" ;

        byte[] out = new byte[num.length()];

        for (int i = 0; i< num.length(); i++) {
            Byte irSYS = unicodeIrsys.get(num.charAt(i));
            out[i] = irSYS != null ? irSYS : (byte)num.charAt(i);
        }

        printerService.printBytes("OLIVETTI PR4 SL Slip", out);

        //print some stuff
        printerService.printString("OLIVETTI PR4 SL Slip", "\n");

        // cut that paper!
        byte[] cutP = new byte[] { 0x1d, 'V', 1 };

        printerService.printBytes("OLIVETTI PR4 SL Slip", cutP);

        assertTrue( true );
    }
}
