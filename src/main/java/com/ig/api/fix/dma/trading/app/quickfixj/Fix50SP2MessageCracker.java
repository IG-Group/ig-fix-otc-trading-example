package com.ig.api.fix.dma.trading.app.quickfixj;

import java.util.List;

import org.springframework.context.annotation.Profile;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import quickfix.FieldNotFound;
import quickfix.Group;
import quickfix.IncorrectTagValue;
import quickfix.SessionID;
import quickfix.UnsupportedMessageType;
import quickfix.field.NoPositions;
import quickfix.fix50sp2.ExecutionReport;
import quickfix.fix50sp2.MessageCracker;
import quickfix.fix50sp2.PositionReport;

@Component
@Slf4j
public class Fix50SP2MessageCracker extends MessageCracker {

    @Override
    public void onMessage(ExecutionReport message, SessionID sessionID)
            throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {

        log.info("Received execution report for client order: {} order status: {}",
                message.getClOrdID().getValue(), message.getOrdStatus().getValue());
    }
    
    @Override
    public void onMessage(PositionReport message, SessionID sessionID)
            throws FieldNotFound, UnsupportedMessageType, IncorrectTagValue {

    	final List<Group> noPositions = message.getGroups(NoPositions.FIELD);
    	
        log.info("Received position report for client order: {} on account: {}",
                noPositions.get(0).getString(NoPositions.FIELD), message.getAccount().getValue());
    }

}
