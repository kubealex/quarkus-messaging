package org.acme;

import java.util.Random;
import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import io.quarkus.logging.Log;
import io.smallrye.common.annotation.Blocking;

@ApplicationScoped
public class OrderProcessorAMQ {

    private Random random = new Random();

    @Incoming("order-requests-queue")
    @Outgoing("order-receipts-queue")
    @Blocking
    public Order process(String orderRequest) throws InterruptedException {
        Log.info("Order: " + orderRequest + " received on AMQ queue, attaching a price");
        Thread.sleep(150);
        return new Order(orderRequest, random.nextInt(100));
    }  
}