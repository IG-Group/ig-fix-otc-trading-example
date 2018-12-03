package com.ig.api.fix.dma.trading.app.quickfixj;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import quickfix.FieldNotFound;
import quickfix.IncorrectTagValue;
import quickfix.SessionID;
import quickfix.UnsupportedMessageType;
import quickfix.fix50sp2.ExecutionReport;
import quickfix.fix50sp2.MessageCracker;

@Component
@Profile("fix50sp2")
@Slf4j
public class Fix50SP2MessageCracker extends MessageCracker {

    @Override
    public void onMessage(ExecutionReport message, SessionID sessionID)
            throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {

        log.info("Received execution report for client order: {} order status: {}",
                message.getClOrdID().getValue(), message.getOrdStatus().getValue());
    }

}
