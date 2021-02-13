package com.smorales.iso8583.service;

import com.smorales.iso8583.dto.FinancialRequestDTO;
import com.smorales.iso8583.dto.FinancialResponseDTO;
import org.jpos.iso.ISOException;
import org.jpos.iso.ISOMsg;
import org.jpos.q2.iso.QMUX;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import static com.smorales.iso8583.util.Constants.*;

@Service
public class PurchaseService {

    private final QMUX mux;

    public PurchaseService(QMUX mux) {
        this.mux = mux;
    }

    public FinancialResponseDTO purchase(FinancialRequestDTO purchaseRequest) {
        try {
            ISOMsg msg = new ISOMsg();
            msg.setMTI("0200");
            msg.set(2, purchaseRequest.getPan());
            msg.set(7, purchaseRequest.getDateTime().format(DATE_10));
            msg.set(11, purchaseRequest.getDateTime().format(DATE_4));
            msg.set(4, purchaseRequest.getAmount().toString().replace(".", ""));

            if (!mux.isConnected()) {
                throw new ResponseStatusException(HttpStatus.SERVICE_UNAVAILABLE, "Service is unavailable");
            }

            ISOMsg responseMsg = mux.request(msg, 3_000);
            if (responseMsg == null) {
                throw new ResponseStatusException(HttpStatus.GATEWAY_TIMEOUT, "Service timeout");
            }

            FinancialResponseDTO responseDTO = new FinancialResponseDTO();
            responseDTO.setCode(responseMsg.getString(39));
            responseDTO.setMessage("Approved");
            responseDTO.setAuthorization(responseMsg.getString(38));
            return responseDTO;
        } catch (ISOException e) {
            throw new RuntimeException(e.getMessage(), e);
        }
    }

}
