package com.ig.api.fix.otc.trading.app.quickfixj;

import java.time.LocalDateTime;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import lombok.extern.slf4j.Slf4j;
import quickfix.Session;
import quickfix.SessionID;
import quickfix.SessionNotFound;
import quickfix.SystemTime;
import quickfix.field.Account;
import quickfix.field.BeginString;
import quickfix.field.ClOrdID;
import quickfix.field.Currency;
import quickfix.field.OrdType;
import quickfix.field.OrderQty;
import quickfix.field.SecurityID;
import quickfix.field.SecurityIDSource;
import quickfix.field.SenderCompID;
import quickfix.field.Side;
import quickfix.field.Symbol;
import quickfix.field.TargetCompID;
import quickfix.field.TimeInForce;
import quickfix.field.TransactTime;
import quickfix.fix50sp2.NewOrderSingle;

@Slf4j
@Component
public class NewOrderSender {

    private final String igAccount;
    private final SessionID sessionID;

    public NewOrderSender(@Value("${IGAccount}") String igAccount,
            @Value("${SenderCompID}") String senderCompID,
            @Value("${TargetCompID}") String targetCompID) {
        this.igAccount = igAccount;
        this.sessionID = new SessionID(
                new BeginString("FIXT.1.1"),
                new SenderCompID(senderCompID),
                new TargetCompID(targetCompID));
    }

    public void sendNewOrder() {
        NewOrderSingle nos = newOrderSingle();
        try {
            Session.sendToTarget(nos, sessionID);
        } catch (SessionNotFound sessionNotFound) {
            log.error("Session [{}] not found in qfj when sending order {}", sessionID, nos);
        }
    }

    private NewOrderSingle newOrderSingle() {
        NewOrderSingle nos = new NewOrderSingle();
        nos.set(new Account(igAccount));
        nos.set(new ClOrdID(getNewClientOrderId()));
        nos.set(new OrdType(OrdType.MARKET));
        nos.set(new TimeInForce(TimeInForce.FILL_OR_KILL));
        nos.set(new Side(Side.BUY));
        nos.set(new OrderQty(1));
        nos.set(new SecurityIDSource(SecurityIDSource.IG_ID)); //Custom IG Value
        nos.set(new SecurityID("IX.D.FTSE.CFD.IP"));
        nos.set(new Currency("GBP"));
        nos.set(new Symbol("N/A"));
        nos.set(new TransactTime(LocalDateTime.now()));

        return nos;
    }

    private String getNewClientOrderId() {
        return String.valueOf(SystemTime.currentTimeMillis());
    }
}
