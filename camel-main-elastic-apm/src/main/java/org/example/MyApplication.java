package org.example;

import org.apache.camel.main.Main;

// Packages for tracing
import org.apache.camel.opentracing.OpenTracingTracer;
import co.elastic.apm.opentracing.ElasticApmTracer;
import io.opentracing.Tracer;
import co.elastic.apm.attach.ElasticApmAttacher; // You can remove this if you are going to use -javaagent

public final class MyApplication {

    private MyApplication() {
    }

    public static void main(String[] args) throws Exception {
        Main main = new Main();

        // Make camel traces in opentracing format
        OpenTracingTracer ottracer = new OpenTracingTracer();
        main.bind("ottracer", ottracer);

        // Adapt opentracing traces for elastic APM 
        Tracer tracer = new ElasticApmTracer();
        main.bind("tracer", tracer);

        // Attach the elastic APM agent,you can remove it if you are going to use -javaagent
        ElasticApmAttacher.attach();

        // We can add routes in java like this
        main.addRouteBuilder(MyRouteBuilder.class);
        // or we can add xml routes putting them in /src/main/resources/camel

        main.run(args);
    }

}
