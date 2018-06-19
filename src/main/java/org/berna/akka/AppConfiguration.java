package org.berna.akka;

import akka.actor.ActorSystem;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;

import static org.berna.akka.SpringExtension.SPRING_EXTENSION_PROVIDER;

@Configuration
@ComponentScan
public class AppConfiguration{

    @Autowired
    private ApplicationContext applicationContext;

    @Bean
    public ActorSystem actorSystem(){
        ActorSystem system=ActorSystem.create("akka-spring-demo"); // actor systemi yarat.
        // springdeki beanlerle actoru entegre eder. Actor producer çağrılır.
        // SpringActorProducer actor beanı oluşturur (scope= prototype)
        // systemi initialize ettik.Providerın props fonk kullanıp processi tamamlamıyoruz. Initialize edip bıraktık.
        SPRING_EXTENSION_PROVIDER.get(system).initialize(applicationContext);

        return system;
    }
}
