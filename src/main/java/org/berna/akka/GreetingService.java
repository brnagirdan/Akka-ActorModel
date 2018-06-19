package org.berna.akka;

import org.springframework.stereotype.Component;

// @Component annotation to it (with default singleton scope)
@Component
public class GreetingService {

    public String greet(String name){
        return "Hello" + name;
    }
}
