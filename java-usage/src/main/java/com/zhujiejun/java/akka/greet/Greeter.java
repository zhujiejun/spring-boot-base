package com.zhujiejun.java.akka.greet;

import akka.actor.UntypedAbstractActor;
import org.apache.commons.lang3.StringUtils;

public class Greeter extends UntypedAbstractActor {
    private String greeting = StringUtils.EMPTY;

    @Override
    public void onReceive(Object message) throws Throwable, Throwable {
        if (message instanceof Greetee) {
            greeting = "hello, " + ((Greetee) message).who;
        } else if (message instanceof Greet) {
            //发送招呼消息给发送消息给这个Actor的Actor
            getSender().tell(new Greeting(greeting), getSelf());
        } else {
            unhandled(message);
        }
    }
}
