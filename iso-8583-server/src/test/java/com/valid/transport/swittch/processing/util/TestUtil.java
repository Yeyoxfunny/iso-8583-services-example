
package com.valid.transport.swittch.processing.util;

import javax.xml.bind.DatatypeConverter;

public final class TestUtil {

    private TestUtil() {
        throw new UnsupportedOperationException();
    }

    public static byte[] hex2ByteArray(String hex) {
        return DatatypeConverter.parseHexBinary(hex);
    }

}
