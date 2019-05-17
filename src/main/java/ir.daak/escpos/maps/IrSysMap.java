package ir.daak.escpos.maps;

import java.util.HashMap;

public class IrSysMap {
    //TODO from davood akbari: Reduce the code by merging the maps

    public static final String MIDDLE_SEPARATE_LETTERS = "ا";

    public static final String SEPARATE_LETTERS = "٠١٢٣٤٥٦٧٨٩۰۱۲۳۴۵۶۷۸۹،؛؟ـدذرزاژو";

    public static final HashMap<Character, Byte> FIRST_LETTERS = new HashMap<Character, Byte>(){{

        put('آ', (byte) 141);

        put('ا', (byte) 144);

        put('ب', (byte) 147);

        put('پ', (byte) 149);

        put('ت', (byte) 151);

        put('ث', (byte) 153);

        put('ج', (byte) 155);

        put('چ', (byte) 157);

        put('ح', (byte) 159);

        put('خ', (byte) 161);

        put('د', (byte) 162);

        put('ذ', (byte) 163);

        put('ر', (byte) 164);

        put('ز', (byte) 165);

        put('ژ', (byte) 166);

        put('س', (byte) 168);

        put('ش', (byte) 170);

        put('ص', (byte) 172);

        put('ض', (byte) 174);

        put('ط', (byte) 175);

        put('ظ', (byte) 224);

        put('ع', (byte) 228);

        put('غ', (byte) 232);

        put('ف', (byte) 234);

        put('ق', (byte) 236);

        put('ک', (byte) 238);

        put('گ', (byte) 240);

        put('ل', (byte) 243);

        put('م', (byte) 245);

        put('ن', (byte) 247);

        put('و', (byte) 248);

        put('ه', (byte) 251);

        put('ی', (byte) 254);
    }};

    public static final HashMap<Character, Byte> MIDDLE_LETTERS = new HashMap<Character, Byte>(){{
        put('آ', (byte) 141);

        put('ا', (byte) 145);

        put('ب', (byte) 147);

        put('پ', (byte) 149);

        put('ت', (byte) 151);

        put('ث', (byte) 153);

        put('ج', (byte) 155);

        put('چ', (byte) 157);

        put('ح', (byte) 159);

        put('خ', (byte) 161);

        put('د', (byte) 162);

        put('ذ', (byte) 163);

        put('ر', (byte) 164);

        put('ز', (byte) 165);

        put('ژ', (byte) 166);

        put('س', (byte) 168);

        put('ش', (byte) 170);

        put('ص', (byte) 172);

        put('ض', (byte) 174);

        put('ط', (byte) 175);

        put('ظ', (byte) 224);

        put('ع', (byte) 227);

        put('غ', (byte) 231);

        put('ف', (byte) 234);

        put('ق', (byte) 236);

        put('ک', (byte) 238);

        put('گ', (byte) 240);

        put('ل', (byte) 243);

        put('م', (byte) 245);

        put('ن', (byte) 247);

        put('و', (byte) 248);

        put('ه', (byte) 250);

        put('ی', (byte) 254);
    }};

    public static final HashMap<Character, Byte> LAST_LETTERS = new HashMap<Character, Byte>(){{
        put('آ', (byte) 141);

        put('ا', (byte) 145);

        put('ب', (byte) 146);

        put('پ', (byte) 148);

        put('ت', (byte) 150);

        put('ث', (byte) 152);

        put('ج', (byte) 154);

        put('چ', (byte) 156);

        put('ح', (byte) 158);

        put('خ', (byte) 160);

        put('د', (byte) 162);

        put('ذ', (byte) 163);

        put('ر', (byte) 164);

        put('ز', (byte) 165);

        put('ژ', (byte) 166);

        put('س', (byte) 167);

        put('ش', (byte) 169);

        put('ص', (byte) 171);

        put('ض', (byte) 173);

        put('ط', (byte) 175);

        put('ظ', (byte) 224);

        put('ع', (byte) 225);

        put('غ', (byte) 229);

        put('ف', (byte) 233);

        put('ق', (byte) 235);

        put('ک', (byte) 237);

        put('گ', (byte) 239);

        put('ل', (byte) 241);

        put('م', (byte) 244);

        put('ن', (byte) 246);

        put('و', (byte) 248);

        put('ه', (byte) 249);

        put('ی', (byte) 253);
    }};

    public static final HashMap<Character, Byte> TWO_PHASED_LETTERS = new HashMap<Character, Byte>(LAST_LETTERS){{
        put('ع', (byte) 226);
        put('غ', (byte) 230);
        put('ی', (byte) 252);
        put('ا', (byte) 144);
    }};

    public static final HashMap<Character, Byte> OTHER_LETTERS = new HashMap<Character, Byte>(){{
        put('٠', (byte) 128);
        put('١', (byte) 129);
        put('٢', (byte) 130);
        put('٣', (byte) 131);
        put('٤', (byte) 132);
        put('٥', (byte) 133);
        put('٦', (byte) 134);
        put('٧', (byte) 135);
        put('٨', (byte) 136);
        put('٩', (byte) 137);

        put('۰', (byte) 128);
        put('۱', (byte) 129);
        put('۲', (byte) 130);
        put('۳', (byte) 131);
        put('۴', (byte) 132);
        put('۵', (byte) 133);
        put('۶', (byte) 134);
        put('۷', (byte) 135);
        put('۸', (byte) 136);
        put('۹', (byte) 137);

        put('،', (byte) 138);
        put('ـ', (byte) 139);
        put('؟', (byte) 140);
    }};
}
