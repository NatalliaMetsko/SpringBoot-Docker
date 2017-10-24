package com.netcracker.metsko.entities;

public class Price {

    public Double price;

    public Price(Double price) {
        this.price = price;
    }

    public Double getPrice() {

        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Price)) return false;

        Price price1 = (Price) o;

        return getPrice().equals(price1.getPrice());
    }

    @Override
    public int hashCode() {
        return getPrice().hashCode();
    }

    @Override
    public String toString() {
        return "Price= " + price;
    }

    /*реализация цены в валюте*/

}
