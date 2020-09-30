package com.zhujiejun.java.akka.greet;

import java.io.Serializable;

public class Greetee implements Serializable {
    public final String who;

    public Greetee(String who) {
        this.who = who;
    }
}
