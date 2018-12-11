package com.ig.api.fix.otc.trading.app.quickfixj;

import org.springframework.beans.factory.annotation.Autowired;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import quickfix.Application;
import quickfix.DoNotSend;
import quickfix.FieldNotFound;
import quickfix.IncorrectDataFormat;
import quickfix.IncorrectTagValue;
import quickfix.Message;
import quickfix.RejectLogon;
import quickfix.SessionID;
import quickfix.UnsupportedMessageType;
import quickfix.fix50sp2.MessageCracker;

@Slf4j
@Component("clientApplication")
public class QFJApplication implements Application {

    private final MessageCracker messageCracker;
    private final NewOrderSender newOrderSender;

    public QFJApplication(@Autowired MessageCracker messageCracker,
            @Autowired NewOrderSender newOrderSender) {
        log.info("Starting QuickJ Application");
        this.messageCracker = messageCracker;
        this.newOrderSender = newOrderSender;
    }


    public void fromAdmin(Message message, SessionID sessionId)
            throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, RejectLogon {
    }

    public void fromApp(Message message, SessionID sessionId)
            throws FieldNotFound, IncorrectDataFormat, IncorrectTagValue, UnsupportedMessageType {
        messageCracker.crack(message, sessionId);
    }

    public void onCreate(SessionID sessionId) {
        log.info("Session created {}", sessionId);
    }

    public void onLogon(SessionID sessionId) {
        log.info("Session successfully logged on [{}]", sessionId);
        log.info("Placing order");
        newOrderSender.sendNewOrder();
        log.info("Sent order");
    }

    public void onLogout(SessionID sessionId) {
        log.info("{} Logged out", sessionId);
    }

    public void toAdmin(Message message, SessionID sessionId) {
    }

    public void toApp(Message message, SessionID sessionId) throws DoNotSend {
    }
}
