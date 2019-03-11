package ir.daak.irsys;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static ir.daak.irsys.map.IrSysMap.*;
import static org.apache.commons.lang3.ArrayUtils.*;

public class IrSysUtil {
    private static Boolean isFirstLetter(Integer index){
        return index == 0;
    }

    private static Boolean isNoAlphabet(char cIn){
        return otherLetters.get(cIn) != null;
    }

    private static Boolean isMiddleLetter(String text, Integer index){
        return index > 0 && index < text.length() - 1;
    }

    private static Boolean isLastLetters(String text, Integer index){
        return index == text.length() - 1;
    }

    public static boolean isPersian(char c) {
        return (c >= 0x0600 && c <= 0x06FF) /*|| (c >= 0xFB50 && c <= 0xFDFF) || (c >= 0xFE70 && c <= 0xFEFF)*/;
    }

    private static Boolean isSeparateLetter(String text, Integer previousLetterIndex){
        if (previousLetterIndex <= 0){
                return true;
        }

        char c = text.charAt(previousLetterIndex);
        return separateLetters.indexOf(c) > 0 || !isPersian(c) || isNoAlphabet(c);
    }

    private static Byte charAt(String text, Integer index){
        Byte c = null;
        char cIn = text.charAt(index);

        if (isNoAlphabet(cIn)){
            c = otherLetters.get(cIn);
        }
        else if (isFirstLetter(index)){
            c = firstLetters.get(cIn);
        }
        else if(isMiddleLetter(text, index)){
            if(isSeparateLetter(text, index + 1)){
                c = lastLetters.get(cIn);
            }
            else {
                c = middleLetters.get(cIn);
            }
        }
        else if(isLastLetters(text, index) && isSeparateLetter(text, index - 1)){
            c = exceptionLetters.get(cIn);
        }
        else if (isLastLetters(text, index)){
            c = lastLetters.get(cIn);
        }

        return c;
    }

    private static List<WordParts> persianWord(String word){
        return wordPartsList(word, "[\\u060C-\\u06F9]+");
    }

    private static List<WordParts> persianString(String word){
        return wordPartsList(word, "[\\u0621-\\u064A\\u067E-\\u06CC]+");
    }

    private static List<WordParts> persianOther(String word){//number and other
        return wordPartsList(word, "[\\u0660-\\u0669\\u06F0-\\u06F9\\u060C-\\u061F\\u0640]+");
    }

    private static List<WordParts> englishWord(String word){
        return wordPartsList(word, "[a-zA-z0-9]+");
    }

    private static List<WordParts> wordPartsList(String word, String regex){
        List<WordParts> irsysWordList = new ArrayList<>();

        final Pattern pattern = Pattern.compile(regex);
        Matcher matcherRequest = pattern.matcher(word);
        while (matcherRequest.find()) {
            WordParts wordParts = new WordParts(matcherRequest.group(), matcherRequest.start(), matcherRequest.end());
            irsysWordList.add(wordParts);
        }

        return irsysWordList;
    }

    private static byte[] getBytes(Comparator<WordParts> comparator, List<WordParts> ...wordPartsLists){
        List<WordParts> allWord = new ArrayList<>();

        for (List<WordParts> wordPartsList: wordPartsLists) {
            allWord.addAll(wordPartsList);
        }

        return allWord.size() > 0 ? allWord.stream().sorted(comparator).map(WordParts::getIrSysWord).reduce((first, second) -> addAll(first, second)).get() : null;
    }

    private static byte[] getBytes(List<WordParts> ...wordPartsLists){
        return getBytes(Comparator.comparingInt(WordParts::getStart), wordPartsLists);
    }

    private static List<WordParts> converEnglish(String word){
        return englishWord(word).stream().map(wordParts -> {

            byte[] out = unicodeToIrSys(wordParts.getWord());

            wordParts.setIrSysWord(out);

            return wordParts;
        }).collect(Collectors.toList());
    }

    private static List<WordParts> converPersianString(String word){
        return persianString(word).stream().map(wordParts -> {
            byte[] out = unicodeToIrSys(wordParts.getWord());

            reverse(out);

            wordParts.setIrSysWord(out);

            return wordParts;
        }).collect(Collectors.toList());
    }

    private static List<WordParts> converOtherPersian(String word){
        return persianOther(word).stream().map(wordParts -> {
            byte[] out = unicodeToIrSys(wordParts.getWord());

            wordParts.setIrSysWord(out);

            return wordParts;
        }).collect(Collectors.toList());
    }

    private static List<WordParts> converPersian(String word){
        return persianWord(word).stream().map(wordParts -> {
            List<WordParts> persianStringList = converPersianString(wordParts.getWord());
            List<WordParts> otehrPersianList = converOtherPersian(wordParts.getWord());

            byte[] out = (otehrPersianList.size() > 0 && persianStringList.size() > 0) ?
                    getBytes(((first, second) -> second.getStart() - first.getStart()), persianStringList, otehrPersianList) :
                    getBytes(persianStringList, otehrPersianList);

            wordParts.setIrSysWord(out);

            return wordParts;
        }).collect(Collectors.toList());
    }

    public static byte[] convertWord(String word){
        List<WordParts> persianWordList = converPersian(word);
        List<WordParts> englishWordList = converEnglish(word);

        return getBytes(persianWordList, englishWordList);
    }

    private static byte[] unicodeToIrSys(String word){
        byte[] out = new byte[word.length()];

        for (int index = 0; index< word.length(); index++) {
            Byte irSYS = charAt(word, index);
            out[index] = irSYS != null ? irSYS : (byte)word.charAt(index);
        }

        return out;
    }
}
