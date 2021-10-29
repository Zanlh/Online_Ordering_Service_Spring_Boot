package com.example.Processor.Model;

public class TotalAmount {
    private Long id;
    private int totalAmount;


    public TotalAmount() {
    }


    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public int getTotalAmount() {
        return this.totalAmount;
    }

    public void setTotalAmount(int totalAmount) {
        this.totalAmount = totalAmount;
    }

    @Override
    public String toString() {
        return "{" +
            " id='" + getId() + "'" +
            ", totalAmount='" + getTotalAmount() + "'" +
            "}";
    }
    
}
