package org.example;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.ImportResource;
import org.springframework.context.annotation.Bean;

// Packages for tracing
import org.apache.camel.opentracing.starter.CamelOpenTracing;
import co.elastic.apm.opentracing.ElasticApmTracer;
import io.opentracing.Tracer;
import co.elastic.apm.attach.ElasticApmAttacher; // You can remove this if you are going to use -javaagent

@SpringBootApplication
@CamelOpenTracing // Make camel do traces in OpenTraging format
@ImportResource("classpath:camel-context.xml")// load the spring xml file from classpath
public class Application {

    public static void main(String[] args) {
        // Self attach the APM agent, you can remove it if you are going to use -javaagent
        ElasticApmAttacher.attach();
        SpringApplication.run(Application.class, args);
    }

    // Add elastiapmtracer to opentracing, so it will transform the tracers for APM
    @Bean
    public Tracer tracer() {
        return new ElasticApmTracer();
    }

}
