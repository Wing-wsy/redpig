package com.redpig;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

@SpringBootApplication
public class App {
    public static void main(String[] args) {
        ConfigurableApplicationContext run = SpringApplication.run(App.class, args);
        String[] names = run.getBeanDefinitionNames();
//        int i = 1;
//        for (String name : names) {
//            System.out.println(i + "-加载："+ name);
//            i++;
//        }
    }
}
