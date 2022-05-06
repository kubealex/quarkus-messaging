package org.acme;

import java.util.UUID;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import io.smallrye.mutiny.Multi;

@Path("/orders")
public class OrderProducer {



    @Channel("order-requests")
    Emitter<String> orderEmitter;
    
    @POST
    @Path("/request")
    @Produces(MediaType.TEXT_PLAIN)
    public String createOrder() {
        UUID uuid = UUID.randomUUID();
        orderEmitter.send(uuid.toString());
        return uuid.toString();
    }    

    @Channel("order-receipts")
    Multi<Order> orders;

    @GET
    @Path("")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Multi<Order> consumer() {
        return orders;
    }    

}