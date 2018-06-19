package org.berna.akka;


import akka.actor.UntypedActor;
import org.springframework.beans.factory.config.ConfigurableBeanFactory;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.Component;

@Component
@Scope(ConfigurableBeanFactory.SCOPE_PROTOTYPE)
// every bean retrieval request should result in a newly created instance, as this behavior matches Akka’s actor lifecycle
public class GreetingActor extends UntypedActor {

    private GreetingService greetingService;

    public GreetingActor(GreetingService greetingService) {
        this.greetingService = greetingService;
    }

    public void onReceive(Object message) throws Throwable {
        if(message instanceof Greet){
            // it takes name of person from the Greet instance.
            String name=((Greet)message).getName();
            // then uses the greeting service to receive a greeting for this person
            getSender().tell(greetingService.greet(name),getSelf());
        } else{
            unhandled(message);
        }
    }

// Accepted message types should be defined as close to an actor as possible to avoid confusion
    public static class Greet{
        private String name;

        public Greet(String name) {
            this.name = name;
        }

        public String getName() {
            return name;
        }
    }
}
