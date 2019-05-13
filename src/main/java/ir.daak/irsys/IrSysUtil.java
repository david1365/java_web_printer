package ir.daak.irsys;

import ir.daak.irsys.enums.Direction;
import org.apache.commons.lang3.ArrayUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static ir.daak.irsys.maps.IrSysMap.*;
import static org.apache.commons.lang3.ArrayUtils.*;

public class IrSysUtil {
    private static final byte MAX_CHAR_EACH_LINE = 88;

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

    private static boolean isPersian(char c){
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

    private static List<WordParts> lineSpaces(String line){
        return wordPartsList(line, "[ ]+");
    }

    private static List<WordParts> lineWords(String line){
        return wordPartsList(line, "[^\\s]+");
    }

    private static List<WordParts> persianLines(String word){
        return wordPartsList(word, "[\\u060C-\\uFEFC\\s]+");
    }

    private static List<WordParts> otherLines(String word){
        return wordPartsList(word, "[^،-ﻼ\\s]+[^،-ﻼ]+[^،-ﻼ\\s]+"/*"[^\\u060C-\\uFEFC\\s]+"*/);
    }

    private static List<WordParts> persianWords(String word){
        return wordPartsList(word, "[\\u060C-\\uFEFC]+");
    }

    private static List<WordParts> persianAlphabets(String word){
        return wordPartsList(word, "[\\u0621-\\u0652\\u067E-\\u06CC\\u200C-\\uFEFC]+");
    }

    private static List<WordParts> persianWithoutAlphabets(String word){//number and other
        return wordPartsList(word, "[\\u0660-\\u0669\\u06F0-\\u06F9\\u060C-\\u061F\\u0640]+");
    }

    private static List<WordParts> otherWords(String word){
        return wordPartsList(word, "[^\\u060C-\\uFEFC]+"/*"[a-zA-z0-9]+"*/);
    }

    private static byte[] unicodeToIrSys(String word){
        byte[] out = new byte[word.length()];

        for (int index = 0; index< word.length(); index++) {
            Byte irSYS = charAt(word, index);
            out[index] = irSYS != null ? irSYS : (byte)word.charAt(index);
        }

        return out;
    }

    private static List<WordParts> concat(List<WordParts> ...wordPartsLists) {
        List<WordParts> allWord = new ArrayList<>();

        for (List<WordParts> wordPartsList : wordPartsLists) {
            allWord.addAll(wordPartsList);
        }

        return allWord;
    }

    private static byte[] getBytes(Direction direction, List<WordParts> ...wordPartsLists){
        List<WordParts> allWords = concat(wordPartsLists);

        return allWords.size() > 0 ? allWords.stream().sorted(direction.comparator()).map(WordParts::getIrSysWord).reduce(ArrayUtils::addAll).get() : null;
    }

    private static byte[] getBytes(List<WordParts> ...wordPartsLists){
        return getBytes(Direction.LEFT_To_RIGHT, wordPartsLists);
    }

    private static List<WordParts> converOther(String word){
        return otherWords(word).stream().map(wordParts -> {

            byte[] out = unicodeToIrSys(wordParts.getWord());

            wordParts.setIrSysWord(out);

            return wordParts;
        }).collect(Collectors.toList());
    }

    private static List<WordParts> converPersianString(String word){
        return persianAlphabets(word).stream().map(wordParts -> {
            byte[] out = unicodeToIrSys(wordParts.getWord());

            reverse(out);

            wordParts.setIrSysWord(out);

            return wordParts;
        }).collect(Collectors.toList());
    }

    private static List<WordParts> converOtherPersian(String word){
        return persianWithoutAlphabets(word).stream().map(wordParts -> {
            byte[] out = unicodeToIrSys(wordParts.getWord());

            wordParts.setIrSysWord(out);

            return wordParts;
        }).collect(Collectors.toList());
    }

    private static List<WordParts> converPersian(String word){
        return persianWords(word).stream().map(wordParts -> {
            List<WordParts> persianStringList = converPersianString(wordParts.getWord());
            List<WordParts> otehrPersianList = converOtherPersian(wordParts.getWord());

            byte[] out = (otehrPersianList.size() > 0 && persianStringList.size() > 0) ?
                    getBytes(Direction.RIGHT_TO_LEFT, persianStringList, otehrPersianList) :
                    getBytes(persianStringList, otehrPersianList);

            wordParts.setIrSysWord(out);

            return wordParts;
        }).collect(Collectors.toList());
    }

    private static byte[] convertWord(String word){
        List<WordParts> persianWordList = converPersian(word);
        List<WordParts> otherWordList = converOther(word);

        return getBytes(persianWordList, otherWordList);
    }

    private static List<WordParts> convertWordList(String line){
        return lineWords(line).stream().map(wordParts -> {

            wordParts.setIrSysWord(convertWord(wordParts.getWord()));

            return wordParts;

        }).collect(Collectors.toList());
    }

    private static List<WordParts> convertSpaceList(String line){
        return lineSpaces(line).stream().map(wordParts -> {
            byte[] out = unicodeToIrSys(wordParts.getWord());

            wordParts.setIrSysWord(out);

            return wordParts;

        }).collect(Collectors.toList());
    }

    private static byte[] convertLine(String line, Direction direction){
        List<WordParts> wordList = convertWordList(line);
        List<WordParts> spaceList = convertSpaceList(line);

        return getBytes(direction, wordList, spaceList);
    }

    private static List<WordParts> convertPersianLines(String line){
        return persianLines(line).stream().map(wordParts -> {

            wordParts.setIrSysWord(convertLine(wordParts.getWord(), Direction.RIGHT_TO_LEFT));

            return wordParts;

        }).collect(Collectors.toList());
    }

    private static List<WordParts> convertOtherLines(String line){
        return otherLines(line).stream().map(wordParts -> {

            wordParts.setIrSysWord(convertLine(wordParts.getWord(), Direction.LEFT_To_RIGHT));

            return wordParts;

        }).collect(Collectors.toList());
    }

    private static byte[] addNewLine(byte[] bytes, Direction direction) throws IOException {
        if (Direction.RIGHT_TO_LEFT.equals(direction)){
            if (bytes.length > MAX_CHAR_EACH_LINE){
                ByteArrayOutputStream output = new ByteArrayOutputStream();
                int count = (int) Math.ceil(bytes.length / MAX_CHAR_EACH_LINE);

                int end = bytes.length;
                for(int i = 0; i <= count; i++){
                    int begin = end - MAX_CHAR_EACH_LINE;
                    begin = begin < 0 ? 0 : begin;

                    byte[] subarray = ArrayUtils.subarray(bytes, begin, end);
                    output.write(subarray);

                    end = begin - 1;
                }

                return output.toByteArray();
            }
        }

        return bytes;
    }

    public static byte[] getBytes(String line, Direction direction) throws IOException {
        List<WordParts> persianLines = convertPersianLines(line);
        List<WordParts> otherLines = convertOtherLines(line);

        return addNewLine(getBytes(direction, persianLines, otherLines), direction);
    }

    public static byte[] getBytes(String line) throws IOException {
        return getBytes(line, Direction.LEFT_To_RIGHT);
    }
}
