package ir.daak.irsys.enums;

import ir.daak.irsys.WordParts;

import java.util.Comparator;

public enum Direction {
    LEFT_To_RIGHT,
    RIGHT_TO_LEFT;

    public Comparator<WordParts> comparator(){
       return Direction.RIGHT_TO_LEFT.equals(this) ? (first, second) -> second.getStart() - first.getStart() : Comparator.comparingInt(WordParts::getStart);
    }
}
