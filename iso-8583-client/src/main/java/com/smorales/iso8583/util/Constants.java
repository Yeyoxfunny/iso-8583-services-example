package com.smorales.iso8583.util;

import java.text.DecimalFormat;
import java.time.format.DateTimeFormatter;

public final class Constants {

    public static final DateTimeFormatter DATE_4 = DateTimeFormatter.ofPattern("MMdd");
    public static final DateTimeFormatter DATE_10 = DateTimeFormatter.ofPattern("MMddHHmmss");

    public Constants() {
        throw new UnsupportedOperationException();
    }
}
