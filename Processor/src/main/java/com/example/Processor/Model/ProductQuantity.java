package com.example.Processor.Model;

public class ProductQuantity {

    private String name;
    private long quantity;

    public ProductQuantity() {
    }

    public ProductQuantity(String name, long quantity) {
        this.name = name;
        this.quantity = quantity;
    }

    public String getName() {
        return this.name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public long getQuantity() {
        return this.quantity;
    }

    public void setQuantity(long quantity) {
        this.quantity = quantity;
    }

    @Override
    public String toString() {
        return "{" +
            " name='" + getName() + "'" +
            ", quantity='" + getQuantity() + "'" +
            "}";
    }

    
}
