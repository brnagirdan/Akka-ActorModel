package org.berna.akka;


import akka.actor.AbstractExtensionId;
import akka.actor.ExtendedActorSystem;
import akka.actor.Extension;
import akka.actor.Props;
import org.springframework.context.ApplicationContext;


// The easiest way to integrate Spring with Akka is through an Akka extension
public class SpringExtension extends AbstractExtensionId<SpringExtension.SpringExt> {

    //An extension is a singleton instance created per actor system
    public static final SpringExtension SPRING_EXTENSION_PROVIDER = new SpringExtension();


    public SpringExt createExtension(ExtendedActorSystem system) {
        return new SpringExt();
    }

    // our extensşon class that is implements the marker Extension class
    public static class SpringExt implements Extension {
        // The Java volatile keyword is used to mark a Java variable as "being stored in main memory". Not from CPU cache
        private volatile ApplicationContext applicationContext;

        public void initialize(ApplicationContext applicationContext)
        {
            this.applicationContext=applicationContext;
        }

        // Props specify options while creating an actor. It is immutable, so it is thread-safe and shareable
        // Props instance is a blueprint for an actor
        public Props props(String actorBeanName){
            return Props.create(SpringActorProducer.class, applicationContext , actorBeanName);
        }
    }
}
