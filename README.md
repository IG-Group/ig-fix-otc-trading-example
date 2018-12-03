ig-fix-otc-trading-example
==========

This is a example java application that sends an order to the IG FIX OTC Trading API.

The application can be run by a counter party who has been allocated a FIX OTC session. The FIX Comp IDs will require updating in 
_quickfixj-client50sp2.cfg_
and IG account values in 
_NewOrderSenderFix50SP2.java_.

This example is written as a spring boot application.  The example can be run on command line by using the spring boot maven plugin e.g. 
 
 `mvn spring-boot:run`

Other ways of running a spring boot application can be found here 
https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-running-your-application.html


This example application is an initiator for the FIX connection. Once the session is logged on to IG's FIX API, it immediately sends a `NewOrderSingle` message. It is listening for response messages and once an ExecutionReport or PositionReport is received it will log the incoming messages and log "Received execution report for client order: XYZ order status: X" or "Received position report for client order: XYZ on account: ABC"  


