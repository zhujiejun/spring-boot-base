package com.zhujiejun.java.akka.greet;

import akka.actor.ActorRef;
import akka.actor.ActorSystem;
import akka.actor.Inbox;
import akka.actor.Props;
import scala.concurrent.duration.Duration;

import java.util.concurrent.TimeUnit;

public class GreetApp001 {
    public static void main(String[] args) throws Exception {
        final ActorSystem actorSystem = ActorSystem.create("actorSystem");

        //创建一个到greeter Actor的管道
        final ActorRef greeter = actorSystem.actorOf(Props.create(Greeter.class), "greeter");

        //创建邮箱
        final Inbox inbox = Inbox.create(actorSystem);

        //先发第一个消息,消息类型为Greetee
        greeter.tell(new Greetee("world"), ActorRef.noSender());
        //真正的发送消息,消息体为Greet
        inbox.send(greeter, new Greet());

        //等待5秒尝试接收Greeter返回的消息
        Greeting greeting1 = (Greeting) inbox.receive(Duration.create(5, TimeUnit.SECONDS));
        System.out.println("1.Greeting: " + greeting1.message);

        //发送第三个消息,修改名字
        greeter.tell(new Greetee("akka"), ActorRef.noSender());
        //发送第四个消息
        inbox.send(greeter, new Greet());

        //等待5秒尝试接收Greeter返回的消息
        Greeting greeting2 = (Greeting) inbox.receive(Duration.create(5, TimeUnit.SECONDS));
        System.out.println("2.Greeting: " + greeting2.message);

        //新创建一个Actor的管道
        ActorRef greetPrinter = actorSystem.actorOf(Props.create(GreetPrinter.class));
        //使用schedule每一秒发送一个Greet消息给greeterActor,然后把greeterActor的消息返回给greetPrinterActor
        actorSystem.scheduler().schedule(Duration.Zero(), Duration.create(1, TimeUnit.SECONDS), greeter,
                new Greet(), actorSystem.dispatcher(), greetPrinter);
        //actorSystem.shutdown();
    }
}
