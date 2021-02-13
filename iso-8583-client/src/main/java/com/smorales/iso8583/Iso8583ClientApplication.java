package com.smorales.iso8583;

import org.jpos.iso.ISOUtil;
import org.jpos.q2.Q2;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

@SpringBootApplication
public class Iso8583ClientApplication {

    public static void main(String[] args) {
        Q2 q2 = new Q2();
        q2.start();
        ISOUtil.sleep(5 * 1000);
        SpringApplication.run(Iso8583ClientApplication.class, args);
    }

}
