package com.ipi.jva324;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.web.servlet.WebMvcAutoConfiguration;

@SpringBootApplication
public class Jva324Application {
    public static void main(String[] args) {
        SpringApplication.run(Jva324Application.class, args);
    }

    public class TriggeringWebMvcAutoConfiguration extends WebMvcAutoConfiguration {
        public TriggeringWebMvcAutoConfiguration() {
        }
    }
}
