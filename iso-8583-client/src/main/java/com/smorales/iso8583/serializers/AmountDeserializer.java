package com.smorales.iso8583.serializers;

import com.fasterxml.jackson.core.JsonParser;
import com.fasterxml.jackson.databind.DeserializationContext;
import com.fasterxml.jackson.databind.deser.std.NumberDeserializers;

import java.io.IOException;
import java.math.BigDecimal;

public class AmountDeserializer extends NumberDeserializers.BigDecimalDeserializer {

    /** The serialVersionUID constant */
    private static final long serialVersionUID = -3857592938821486329L;

    @Override
    public BigDecimal deserialize(JsonParser p, DeserializationContext ctxt) throws IOException {
        BigDecimal value = super.deserialize(p, ctxt);

        if (value != null) {
            value = value.setScale(2, BigDecimal.ROUND_DOWN);
        }

        return value;
    }

}
