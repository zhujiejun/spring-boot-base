package com.zhujiejun.java.akka.greet;

import akka.actor.UntypedAbstractActor;

public class GreetPrinter extends UntypedAbstractActor {
    @Override
    public void onReceive(Object message) throws Throwable, Throwable {
        if (message instanceof Greeting) {
            System.out.println(((Greeting) message).message);
        }
    }
}
