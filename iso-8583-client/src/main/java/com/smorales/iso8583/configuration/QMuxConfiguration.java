package com.smorales.iso8583.configuration;

import org.jpos.q2.iso.QMUX;
import org.jpos.util.NameRegistrar;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class QMuxConfiguration {

    @Bean
    public QMUX qmux() throws NameRegistrar.NotFoundException {
        return NameRegistrar.get("mux.jpos-client");
    }

}
