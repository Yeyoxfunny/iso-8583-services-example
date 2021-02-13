package com.smorales.iso8583.server.listener;

import java.io.IOException;
import java.util.concurrent.TimeUnit;

import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.iso.ISORequestListener;
import org.jpos.iso.ISOSource;

public class ServerRequestListener implements ISORequestListener {

    @Override
    public boolean process(ISOSource source, ISOMsg m) {
        try {
            ISOMsg response = (ISOMsg) m.clone();
            String mti = m.getMTI();
            if ("0800".equals(mti)) {
                response.setMTI("0810");
            } else if ("0200".equals(mti)) {
                response.setMTI("0210");
                response.set(38, "000000");
            }
            response.set(39, "00");
            source.send(response);
        } catch (ISOException | IOException e) {
            e.printStackTrace();
        }
        return false;
    }

}
