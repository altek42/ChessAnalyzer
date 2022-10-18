package pl.altek.chessanalizer.common;

import org.apache.commons.lang3.RandomStringUtils;

public class RefSymbol {
    private final String value;

    private static final int REF_SYMBOL_LENGTH = 8;

    public RefSymbol(String value) {
        this.value = value;
    }

    public static RefSymbol create(){
        String random = RandomStringUtils.random(REF_SYMBOL_LENGTH, true, true);
        return new RefSymbol(random);
    }

    @Override
    public String toString() {
        return "[" + value  +']';
    }
}
