package ir.daak.irsys.maps;

import java.util.HashMap;

public class IrSysMap {
    //TODO from davood akbari: Reduce the code by merging the maps

    public static final String separateLetters = "٠١٢٣٤٥٦٧٨٩۰۱۲۳۴۵۶۷۸۹،؛؟ـدذرزاژ";

    public static final HashMap<Character, Byte> firstLetters = new HashMap<Character, Byte>(){{

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

    public static final HashMap<Character, Byte> middleLetters = new HashMap<Character, Byte>(){{
        put('آ', (byte) 145);

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

    public static final HashMap<Character, Byte> lastLetters = new HashMap<Character, Byte>(){{
        put('آ', (byte) 144);

        put('ا', (byte) 144);

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

    public static final HashMap<Character, Byte>  exceptionLetters = new HashMap<Character, Byte>(lastLetters){{
        put('ع', (byte) 226);
        put('غ', (byte) 230);
        put('ی', (byte) 252);
    }};

    public static final HashMap<Character, Byte> otherLetters = new HashMap<Character, Byte>(){{
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

    public static final HashMap<Character, Byte> unicodeIrsys = new HashMap<Character, Byte>(){{
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

        put('آ', (byte) 141);
        put('ﺁ', (byte) 141);

        put('ﺋ', (byte) 142);
        put('ﺌ', (byte) 142);

        put('ء', (byte) 143);

        put('ا', (byte) 144);
        put('ﺍ', (byte) 144);

        put('ﺎ', (byte) 145);

        put('ب', (byte) 146);
        put('ﺏ', (byte) 146);
        put('ﺐ', (byte) 146);
        put('ﺑ', (byte) 147);
        put('ﺒ', (byte) 147);

        put('پ', (byte) 148);
        put('ﭖ', (byte) 148);
        put('ﭗ', (byte) 148);
        put('ﭘ', (byte) 149);
        put('ﭙ', (byte) 149);

        put('ت', (byte) 150);
        put('ﺕ', (byte) 150);
        put('ﺖ', (byte) 150);
        put('ﺗ', (byte) 151);
        put('ﺘ', (byte) 151);

        put('ث', (byte) 152);
        put('ﺙ', (byte) 152);
        put('ﺚ', (byte) 152);
        put('ﺛ', (byte) 153);
        put('ﺜ', (byte) 153);

        put('ج', (byte) 154);
        put('ﺝ', (byte) 154);
        put('ﺞ', (byte) 154);
        put('ﺟ', (byte) 155);
        put('ﺠ', (byte) 155);

        put('چ', (byte) 156);
        put('ﭺ', (byte) 156);
        put('ﭻ', (byte) 156);
        put('ﭼ', (byte) 157);
        put('ﭽ', (byte) 157);

        put('ح', (byte) 158);
        put('ﺡ', (byte) 158);
        put('ﺢ', (byte) 158);
        put('ﺣ', (byte) 159);
        put('ﺤ', (byte) 159);

        put('خ', (byte) 160);
        put('ﺥ', (byte) 160);
        put('ﺦ', (byte) 160);
        put('ﺧ', (byte) 161);
        put('ﺨ', (byte) 161);

        put('د', (byte) 162);
        put('ﺩ', (byte) 162);
        put('ﺪ', (byte) 162);

        put('ذ', (byte) 163);
        put('ﺫ', (byte) 163);
        put('ﺬ', (byte) 163);

        put('ر', (byte) 164);
        put('ﺭ', (byte) 164);
        put('ﺮ', (byte) 164);

        put('ز', (byte) 165);
        put('ﺯ', (byte) 165);
        put('ﺰ', (byte) 165);

        put('ژ', (byte) 166);
        put('ﮊ', (byte) 166);
        put('ﮋ', (byte) 166);

        put('س', (byte) 167);
        put('ﺱ', (byte) 167);
        put('ﺲ', (byte) 167);
        put('ﺳ', (byte) 168);
        put('ﺴ', (byte) 168);

        put('ش', (byte) 169);
        put('ﺵ', (byte) 169);
        put('ﺶ', (byte) 169);
        put('ﺷ', (byte) 170);
        put('ﺸ', (byte) 170);

        put('ص', (byte) 171);
        put('ﺹ', (byte) 171);
        put('ﺺ', (byte) 171);
        put('ﺻ', (byte) 172);
        put('ﺼ', (byte) 172);

        put('ض', (byte) 173);
        put('ﺽ', (byte) 173);
        put('ﺾ', (byte) 173);
        put('ﺿ', (byte) 174);
        put('ﻀ', (byte) 174);

        put('ط', (byte) 175);
        put('ﻁ', (byte) 175);
        put('ﻂ', (byte) 175);
        put('ﻃ', (byte) 175);
        put('ﻄ', (byte) 175);

        put('ظ', (byte) 224);
        put('ﻅ', (byte) 224);
        put('ﻆ', (byte) 224);
        put('ﻇ', (byte) 224);
        put('ﻈ', (byte) 224);

        put('ع', (byte) 225);
        put('ﻉ', (byte) 225);
        put('ﻊ', (byte) 226);
        put('ﻌ', (byte) 227);
        put('ﻋ', (byte) 228);

        put('غ', (byte) 229);
        put('ﻍ', (byte) 229);
        put('ﻎ', (byte) 230);
        put('ﻐ', (byte) 231);
        put('ﻏ', (byte) 232);

        put('ف', (byte) 233);
        put('ﻑ', (byte) 233);
        put('ﻒ', (byte) 233);
        put('ﻓ', (byte) 234);
        put('ﻔ', (byte) 234);

        put('ق', (byte) 235);
        put('ﻕ', (byte) 235);
        put('ﻖ', (byte) 235);
        put('ﻗ', (byte) 236);
        put('ﻘ', (byte) 236);

        put('ک', (byte) 237);
        put('ﻙ', (byte) 237);
        put('ﻚ', (byte) 237);
        put('ﻛ', (byte) 238);
        put('ﻜ', (byte) 238);

        put('ﮎ', (byte) 237);
        put('ﮏ', (byte) 237);
        put('ﮐ', (byte) 238);
        put('ﮑ', (byte) 238);

        put('گ', (byte) 239);
        put('ﮒ', (byte) 239);
        put('ﮓ', (byte) 239);
        put('ﮔ', (byte) 240);
        put('ﮕ', (byte) 240);

        put('ل', (byte) 241);
        put('ﻝ', (byte) 241);
        put('ﻞ', (byte) 241);
        put('ﻻ', (byte) 242);
        put('ﻟ', (byte) 243);
        put('ﻠ', (byte) 243);

        put('م', (byte) 244);
        put('ﻡ', (byte) 244);
        put('ﻢ', (byte) 244);
        put('ﻣ', (byte) 245);
        put('ﻤ', (byte) 245);

        put('ن', (byte) 246);
        put('ﻥ', (byte) 246);
        put('ﻦ', (byte) 246);
        put('ﻧ', (byte) 247);
        put('ﻨ', (byte) 247);

        put('و', (byte) 248);
        put('ﻭ', (byte) 248);
        put('ﻮ', (byte) 248);

        put('ه', (byte) 249);
        put('ﻩ', (byte) 249);
        put('ﻪ', (byte) 249);
        put('ﻬ', (byte) 250);
        put('ﻫ', (byte) 251);

        put('ﻰ', (byte) 252);
        put('ﻲ', (byte) 252);
        put('ﯽ', (byte) 252);
        put('ﺊ', (byte) 252);
        put('ئ', (byte) 253);
        put('ى', (byte) 253);
        put('ي', (byte) 253);
        put('ﻯ', (byte) 253);
        put('ﻱ', (byte) 253);
        put('ﺉ', (byte) 253);
        put('ﯼ', (byte) 253);
        put('ﻳ', (byte) 254);
        put('ﻴ', (byte) 254);
        put('ﯾ', (byte) 254);
        put('ﯿ', (byte) 254);
    }};
}
