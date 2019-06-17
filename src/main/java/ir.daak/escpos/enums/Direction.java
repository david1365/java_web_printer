package ir.daak.escpos.enums;

/**
 * @author Davood Akbari - 1398
 * daak1365@gmail.com
 * daak1365@yahoo.com
 * 09125188694
 */

import ir.daak.escpos.model.WordParts;

import java.util.Comparator;

public enum Direction {
    LEFT_TO_RIGHT,
    RIGHT_TO_LEFT;

    public Comparator<WordParts> comparator(){
       return Direction.RIGHT_TO_LEFT.equals(this) ? (first, second) -> second.getStart() - first.getStart() : Comparator.comparingInt(WordParts::getStart);
    }
}
