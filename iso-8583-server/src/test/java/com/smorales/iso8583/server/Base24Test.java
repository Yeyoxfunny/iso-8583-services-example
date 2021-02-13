
package com.smorales.iso8583.server;

import static com.smorales.iso8583.server.util.TestUtil.hex2ByteArray;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.packager.GenericPackager;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class Base24Test {

    private GenericPackager packager;

    @BeforeEach
    void setUp() throws ISOException {
        packager = new GenericPackager("src/dist/cfg/base24-ascii-packager.xml");
    }

    @Test
    void unpackIsoMessage() throws Exception {
        String message = "";
        ISOMsg isoMsg = new ISOMsg();
        isoMsg.setPackager(packager);
        packager.unpack(isoMsg, hex2ByteArray(message));

        for (int i = 1; i <= isoMsg.getMaxField(); i++) {
            if (isoMsg.hasField(i)) {
                System.out.printf("Field: %03d, Value: %s%n", i, isoMsg.getString(i));
            }
        }
    }

}
