package org.acme;

import java.util.UUID;
import javax.ws.rs.GET;
import javax.ws.rs.POST;
import javax.ws.rs.Path;
import javax.ws.rs.Produces;
import javax.ws.rs.core.MediaType;
import org.eclipse.microprofile.reactive.messaging.Channel;
import org.eclipse.microprofile.reactive.messaging.Emitter;
import io.quarkus.logging.Log;
import io.smallrye.mutiny.Multi;

@Path("/orders-amq")
public class OrderProducerAMQ {

    @Channel("order-requests-queue")
    Emitter<String> orderEmitterAmq;

    @POST
    @Path("/request")
    @Produces(MediaType.TEXT_PLAIN)
    public String createOrderAMQ() {
        UUID uuid = UUID.randomUUID();
        Log.info("Customer created a new order on AMQ " + uuid
                + ", delivered to AMQ Queue and preparing event for processing");
        orderEmitterAmq.send(uuid.toString());
        return uuid.toString();
    }

    @Channel("order-receipts-queue")
    Multi<Order> ordersAmq;

    @GET
    @Path("")
    @Produces(MediaType.SERVER_SENT_EVENTS)
    public Multi<Order> consumerAMQ() {
        return ordersAmq;
    }

}
