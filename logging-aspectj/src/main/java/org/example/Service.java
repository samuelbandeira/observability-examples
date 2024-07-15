package org.example;

import org.springframework.stereotype.Component;

@Component
public class Service {

    public void doSomething() {
        System.out.println("Doing something");
    }

    public String doSomethingElse() {
        return "Doing something else";
    }
}
