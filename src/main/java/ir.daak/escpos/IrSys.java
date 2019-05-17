package ir.daak.escpos;

import ir.daak.escpos.enums.Direction;
import ir.daak.escpos.model.WordParts;
import org.apache.commons.lang3.ArrayUtils;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Matcher;
import java.util.regex.Pattern;
import java.util.stream.Collectors;

import static ir.daak.escpos.maps.IrSysMap.*;
import static org.apache.commons.lang3.ArrayUtils.*;

public class IrSys {
    private final byte MAX_CHAR_EACH_LINE = 88;

    private Direction direction = Direction.RIGHT_TO_LEFT;

    private Boolean isFirstLetter(Integer index){
        return index == 0;
    }

    private Boolean isNoAlphabet(char cIn){
        return OTHER_LETTERS.get(cIn) != null;
    }

    private Boolean isMiddleLetter(String text, Integer index){
        return index > 0 && index < text.length() - 1;
    }

    private Boolean isLastLetters(String text, Integer index){
        return index == text.length() - 1;
    }

    private boolean isPersian(char c){
        return (c >= 0x0600 && c <= 0x06FF) /*|| (c >= 0xFB50 && c <= 0xFDFF) || (c >= 0xFE70 && c <= 0xFEFF)*/;
    }

    private Boolean isSeparateLetter(String text, Integer previousLetterIndex){
        if (previousLetterIndex < 0) {
                return true;
        }

        char c = text.charAt(previousLetterIndex);
        return SEPARATE_LETTERS.indexOf(c) > 0 || !isPersian(c) || isNoAlphabet(c);
    }

    private Boolean isAleph(char cIn){
        return ALEPH.equals(cIn);
    }

    private Byte charAt(String text, Integer index){
        Byte c = null;
        char cIn = text.charAt(index);

        if (isNoAlphabet(cIn)){
            c = OTHER_LETTERS.get(cIn);
        }
        else if (isFirstLetter(index)){
            c = FIRST_LETTERS.get(cIn);
        }
        else if(isMiddleLetter(text, index)){
            if (isAleph(cIn) && isSeparateLetter(text, index - 1)){
                c = FIRST_LETTERS.get(cIn);
            }
            else if(isSeparateLetter(text, index + 1) &&
                    (!isSeparateLetter(text, index - 1))){
                c = LAST_LETTERS.get(cIn);
            }
            else {
                c = MIDDLE_LETTERS.get(cIn);
            }
        }
        else if(isLastLetters(text, index) && isSeparateLetter(text, index - 1)){
            c = EXCEPTION_LETTERS.get(cIn);
        }
        else if (isLastLetters(text, index)){
            c = LAST_LETTERS.get(cIn);
        }

        return c;
    }

    private List<WordParts> wordPartsList(String word, String regex){
        List<WordParts> irsysWordList = new ArrayList<>();

        final Pattern pattern = Pattern.compile(regex);
        Matcher matcherRequest = pattern.matcher(word);
        while (matcherRequest.find()) {
            WordParts wordParts = new WordParts(matcherRequest.group(), matcherRequest.start(), matcherRequest.end());
            irsysWordList.add(wordParts);
        }

        return irsysWordList;
    }

    private List<WordParts> lineSpaces(String line){
        return wordPartsList(line, "[ ]+");
    }

    private List<WordParts> lineWords(String line){
        return wordPartsList(line, "[^\\s]+");
    }

    private List<WordParts> persianLines(String word){
        return wordPartsList(word, "[\\u060C-\\uFEFC\\s]+");
    }

    private List<WordParts> otherLines(String word){
        return wordPartsList(word, "[^،-ﻼ\\s]+[^،-ﻼ]+[^،-ﻼ\\s]+"/*"[^\\u060C-\\uFEFC\\s]+"*/);
    }

    private List<WordParts> persianWords(String word){
        return wordPartsList(word, "[\\u060C-\\uFEFC]+");
    }

    private List<WordParts> persianAlphabets(String word){
        return wordPartsList(word, "[\\u0621-\\u0652\\u067E-\\u06CC\\u200C-\\uFEFC]+");
    }

    private List<WordParts> persianWithoutAlphabets(String word){//number and other
        return wordPartsList(word, "[\\u0660-\\u0669\\u06F0-\\u06F9\\u060C-\\u061F\\u0640]+");
    }

    private List<WordParts> otherWords(String word){
        return wordPartsList(word, "[^\\u060C-\\uFEFC]+"/*"[a-zA-z0-9]+"*/);
    }

    private byte[] unicodeToIrSys(String word){
        byte[] out = new byte[word.length()];

        for (int index = 0; index< word.length(); index++) {
            Byte irSYS = charAt(word, index);
            out[index] = irSYS != null ? irSYS : (byte)word.charAt(index);
        }

        return out;
    }

    private List<WordParts> concat(List<WordParts> ...wordPartsLists) {
        List<WordParts> allWord = new ArrayList<>();

        for (List<WordParts> wordPartsList : wordPartsLists) {
            allWord.addAll(wordPartsList);
        }

        return allWord;
    }

    private byte[] getBytes(Direction direction, List<WordParts> ...wordPartsLists) {
        List<WordParts> allWords = concat(wordPartsLists);

        return allWords.size() > 0 ? allWords.stream().sorted(direction.comparator()).map(WordParts::getIrSysWord).reduce(ArrayUtils::addAll).get() : null;
    }

    private List<WordParts> converOther(String word) {
        return otherWords(word).stream().map(wordParts -> {

            byte[] out = unicodeToIrSys(wordParts.getWord());

            wordParts.setIrSysWord(out);

            return wordParts;
        }).collect(Collectors.toList());
    }

    private List<WordParts> converPersianString(String word) {
        return persianAlphabets(word).stream().map(wordParts -> {
            byte[] out = unicodeToIrSys(wordParts.getWord());

            reverse(out);

            wordParts.setIrSysWord(out);

            return wordParts;
        }).collect(Collectors.toList());
    }

    private List<WordParts> converOtherPersian(String word) {
        return persianWithoutAlphabets(word).stream().map(wordParts -> {
            byte[] out = unicodeToIrSys(wordParts.getWord());

            wordParts.setIrSysWord(out);

            return wordParts;
        }).collect(Collectors.toList());
    }

    private List<WordParts> converPersian(String word) {
        return persianWords(word).stream().map(wordParts -> {
            List<WordParts> persianStringList = converPersianString(wordParts.getWord());
            List<WordParts> otehrPersianList = converOtherPersian(wordParts.getWord());

            byte[] out = (otehrPersianList.size() > 0 && persianStringList.size() > 0) ?
                    getBytes(Direction.RIGHT_TO_LEFT, persianStringList, otehrPersianList) :
                    getBytes(Direction.LEFT_TO_RIGHT, persianStringList, otehrPersianList);

            wordParts.setIrSysWord(out);

            return wordParts;
        }).collect(Collectors.toList());
    }

    private byte[] convertWord(String word){
        List<WordParts> persianWordList = converPersian(word);
        List<WordParts> otherWordList = converOther(word);

        return getBytes(Direction.LEFT_TO_RIGHT, persianWordList, otherWordList);
    }

    private List<WordParts> convertWordList(String line) {
        return lineWords(line).stream().map(wordParts -> {

            wordParts.setIrSysWord(convertWord(wordParts.getWord()));

            return wordParts;

        }).collect(Collectors.toList());
    }

    private List<WordParts> convertSpaceList(String line) {
        return lineSpaces(line).stream().map(wordParts -> {
            byte[] out = unicodeToIrSys(wordParts.getWord());

            wordParts.setIrSysWord(out);

            return wordParts;

        }).collect(Collectors.toList());
    }

    private byte[] convertLine(String line, Direction direction) {
        List<WordParts> wordList = convertWordList(line);
        List<WordParts> spaceList = convertSpaceList(line);

        return getBytes(direction, wordList, spaceList);
    }

    private List<WordParts> convertPersianLines(String line) {
        return persianLines(line).stream().map(wordParts -> {

            wordParts.setIrSysWord(convertLine(wordParts.getWord(), Direction.RIGHT_TO_LEFT));

            return wordParts;

        }).collect(Collectors.toList());
    }

    private List<WordParts> convertOtherLines(String line) {
        return otherLines(line).stream().map(wordParts -> {

            wordParts.setIrSysWord(convertLine(wordParts.getWord(), Direction.LEFT_TO_RIGHT));

            return wordParts;

        }).collect(Collectors.toList());
    }

    private byte[] makeLines(byte[] bytes) throws IOException {
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

    public static IrSys initialize(){
        return  new IrSys();
    }

    public IrSys direction(String direction) {
        this.direction = Direction.valueOf(direction);
        return this;
    }

    public IrSys direction(Direction direction) {
        this.direction = direction;
        return this;
    }

    public byte[] getBytes(String line) throws IOException {
        List<WordParts> persianLines = convertPersianLines(line);
        List<WordParts> otherLines = convertOtherLines(line);

        return makeLines(getBytes(direction, persianLines, otherLines));
    }
}
