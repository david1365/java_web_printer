package ir.daak.escpos.enums;

import ir.daak.escpos.model.WordParts;

import java.util.Comparator;

public enum Direction {
    LEFT_TO_RIGHT,
    RIGHT_TO_LEFT;

    public Comparator<WordParts> comparator(){
       return Direction.RIGHT_TO_LEFT.equals(this) ? (first, second) -> second.getStart() - first.getStart() : Comparator.comparingInt(WordParts::getStart);
    }
}
