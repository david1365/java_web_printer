package ir.daak.escpos.model;

/**
 * @author Davood Akbari - 1398
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

public class WordParts implements Comparable{
    private String word;
    private byte[] irSysWord;
    private int start;
    private int end;

    public WordParts() {
    }

    public WordParts(String word, int start, int end) {
        this.word = word;
        this.start = start;
        this.end = end;
    }

    public String getWord() {
        return word;
    }

    public void setWord(String word) {
        this.word = word;
    }

    public byte[] getIrSysWord() {
        return irSysWord;
    }

    public void setIrSysWord(byte[] irSysWord) {
        this.irSysWord = irSysWord;
    }

    public int getStart() {
        return start;
    }

    public void setStart(int start) {
        this.start = start;
    }

    public int getEnd() {
        return end;
    }

    public void setEnd(int end) {
        this.end = end;
    }


    @Override
    public int compareTo(Object o) {
        WordParts otherWordParts = (WordParts) o;
        return start - otherWordParts.getStart();
    }
}
