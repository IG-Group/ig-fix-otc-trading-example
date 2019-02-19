ig-fix-otc-trading-example
==========

This is a example java application that sends an order to the IG FIX OTC Trading API. 

The example can be run by a counter party who has been allocated an IG FIX OTC session. 

This example application is an initiator for the FIX connection. Once the session is logged on to IG's FIX API, it immediately sends a `NewOrderSingle` message. It is listening for response messages and once an ExecutionReport or PositionReport is received it will log the incoming messages and log "Received execution report for client order: XYZ order status: X" or "Received position report for client order: XYZ on account: ABC" 


**Before running**

Before running various properties require updating in  
_quickfixj-client50sp2.cfg_

A counter party needs to update the following property values with values allocated by IG.

``` 
IGAccount=REPLACE_ME
SenderCompID=REPLACE_ME
SocketConnectHost=demo-fix.marketdatasystems.com
SocketConnectPort=55000
```

**How to Build**

This application uses a custom-built version of _quickfixj_ which allows you to refer to custom fields and message types by their names, rather than simply their number, reducing scope of errors and increasing readability. This can be obtained here: https://github.com/IG-Group/quickfixj. Building this library is required before running the example application. Please check out the latest OTC branch, currently this is QFJ_RELEASE_2_1_0_IG_OTC.

This branch will need to be built; please run

`mvn versions:set -DnewVersion=ig.custom-2.1.0`

`mvn clean install -DskipTests`

in the QFJ base directory. This will change the default `2.1.0` version to `ig.custom-2.1.0` and then install it in your local maven repository. You can then include it in this project by changing the value in the <quickfixj.version> tags to `ig.custom-2.1.0` in the root pom.xml of this project.

It should be noted that using `-DskipTests` is generally not advisable but is suggested in this case to reduce build time.


This example application is written with spring boot. After building the QFJ library above the example can be run on command line by using the spring boot maven plugin e.g. 
 
 `mvn spring-boot:run`

Other ways of running a spring boot application can be found here 
https://docs.spring.io/spring-boot/docs/current/reference/html/using-boot-running-your-application.html


**License and Acknowledgement**

The ig-fix-otc-trading-example is released under version 2.0 of the [Apache License](http://www.apache.org/licenses/LICENSE-2.0).

This code utilises the following [quickfixj spring boot starter library](https://github.com/esanchezros/quickfixj-spring-boot-starter).
