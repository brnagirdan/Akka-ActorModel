package org.berna.akka;

import akka.actor.Actor;
import akka.actor.IndirectActorProducer;
import org.springframework.context.ApplicationContext;


// Implements Akka’s IndirectActorProducer interface which allows overriding the instantiation process for an actor
public class SpringActorProducer implements IndirectActorProducer{

    private ApplicationContext applicationContext;

    private String beanActorName;

    public SpringActorProducer(ApplicationContext applicationContext, String beanActorName) {
        this.applicationContext=applicationContext;
        this.beanActorName=beanActorName;
    }

    // As we’ve made the actor a prototype-scoped bean, every call to the produce method will return a new instance of the actor
    // instead of direct instantiation, it will always retrieve an actor instance from the Spring’s ApplicationContext
    public Actor produce() {
        return (Actor) applicationContext.getBean(beanActorName);
    }

    public Class<? extends Actor> actorClass() {
        return (Class<? extends Actor>) applicationContext.getType(beanActorName);
    }
}
