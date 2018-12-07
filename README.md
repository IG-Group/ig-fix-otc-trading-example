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

**How to Build**
This application uses a custom-built version of _quickfixj_ which allows you to refer to fields and message types by their names, rather than simply their number, reducing scope of errors and increasing readability. This can be obtained here: https://github.com/IG-Group/quickfixj. Please check out the latest OTC branch, currently this is QFJ_RELEASE_2_1_0_IG_OTC.

This will need to be built locally; please run
`mvn versions:set -DnewVersion=ig.custom-2.1.0`
`mvn clean install -DskipTests`
in the QFJ base directory. This will change all versions to _ig.custom-2.1.0_ and then install it in your local maven repository. You can then include it in this project by changing the value in the <quickfixj.version> tags to _ig.custom-2.1.0_ in the _pom.xml_ of this project.

This example will then build and run.

It should be noted that using `-DskipTests` is generally not advisable but is suggested in this case to reduce build time.


