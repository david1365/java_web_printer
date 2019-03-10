package ir.daak.irsys;

import org.apache.commons.lang3.ArrayUtils;
import static ir.daak.irsys.map.IrSysMap.*;

public class IrSysUtil {
    private static Boolean isFirstLetter(Integer index){
        return index == 0;
    }

    private static Boolean isMiddleLetter(String text, Integer index){
        return index > 0 && index < text.length() - 1;
    }

    private static Boolean isLastLetters(String text, Integer index){
        return index == text.length() - 1;
    }

    private static Boolean isSeparateLetter(String text, Integer previousLetterIndex){
        if (previousLetterIndex <= 0){
                return false;
        }

        char c = text.charAt(previousLetterIndex);
        return separateLetters.indexOf(c) > 0;
    }

    private static Byte charAt(String text, Integer index){
        Byte c = null;
        char cIn = text.charAt(index);

        if (isFirstLetter(index)){
            c = firstLetters.get(cIn);
        }
        else if(isMiddleLetter(text, index)){
            c = middleLetters.get(cIn);
        }
        else if(isLastLetters(text, index) && isSeparateLetter(text, index - 1)){
            c = exceptionLetters.get(cIn);
        }
        else if (isLastLetters(text, index)){
            c = lastLetters.get(cIn);
        }

        return c;
    }

    public static byte[] unicodeToIrSys(String text){
        byte[] out = new byte[text.length()];

        for (int index = 0; index< text.length(); index++) {
            Byte irSYS = charAt(text, index);
            out[index] = irSYS != null ? irSYS : (byte)text.charAt(index);
        }

        ArrayUtils.reverse(out);

        return out;
    }
}
