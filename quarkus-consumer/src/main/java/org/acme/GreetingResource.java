package org.acme;

import javax.inject.Inject;
import javax.ws.rs.GET;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import org.jboss.resteasy.reactive.RestStreamElementType;
import io.smallrye.mutiny.Multi;

@Path("/")
public class GreetingResource {

    @Inject
    @Channel("demo-out")
    Emitter<String> demoEmitter;

    @Inject
    @Channel("demo-in")
    Multi<String> demoReceiver;

    @GET
    @Path("hello")
    @Produces(MediaType.TEXT_PLAIN)
    public String hello() {
        return "Hello from RESTEasy Reactive";
    }

    @GET
    @Path("kafka-out")
    @Produces(MediaType.TEXT_PLAIN)
    public String producer() {
        demoEmitter.send("test");
        return "Hello from RESTEasy Reactive";
    }    

    @GET
    @Path("kafka-in")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    @RestStreamElementType(MediaType.TEXT_PLAIN)
    public Multi<String> consumer() {
        return demoReceiver;
    }    

}