package org.acme;

public class Order {
    private String id;
    public Integer price;


    public Order() {}

    public Order(String id, Integer price) {
        this.id=id;
        this.price=price;
    }

    public String jsonify() {
        return "Quote{"
        +"id='"
        +id
        +'\''
        +", price="
        +price 
        + '}';
    }
}