package org.acme;

import java.util.Random;
import javax.enterprise.context.ApplicationScoped;
import org.eclipse.microprofile.reactive.messaging.Incoming;
import org.eclipse.microprofile.reactive.messaging.Outgoing;
import io.smallrye.common.annotation.Blocking;

@ApplicationScoped
public class OrderProcessor {

    private Random random = new Random();

    @Incoming("order-requests")
    @Outgoing("order-receipts")
    @Blocking
    public Order process(String orderRequest) throws InterruptedException {
        Thread.sleep(150);
        return new Order(orderRequest, random.nextInt());
    }

}