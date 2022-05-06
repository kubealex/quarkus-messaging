package org.acme;

public class Order {
    private String id;
    public Integer price;


    public Order() {}

    public Order(String id, Integer price) {
        this.id=id;
        this.price=price;
    }
    @Override
    public String toString() {
        return "Quote{"
        +"id='"
        +id
        +'\''
        +", price="
        +price 
        + '}';
    }
}