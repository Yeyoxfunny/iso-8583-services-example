package com.smorales.iso8583.dto;

public class FinancialResponseDTO extends  GatewayResponseDTO {

    private String authorization;

    public String getAuthorization() {
        return authorization;
    }

    public void setAuthorization(String authorization) {
        this.authorization = authorization;
    }
}
