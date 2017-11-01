package com.netcracker.metsko.entities;

public class Price {

    public Double price;

    public String currency;

    public Price(Double price, String currency) {
        this.price = price;
        this.currency=currency;
    }

    public Double getPrice() {

        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    public String getCurrency() {
        return currency;
    }

    public void setCurrency(String currency) {
        this.currency = currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Price)) return false;

        Price price1 = (Price) o;

        if (!getPrice().equals(price1.getPrice())) return false;
        return getCurrency().equals(price1.getCurrency());
    }

    @Override
    public int hashCode() {
        int result = getPrice().hashCode();
        result = 31 * result + getCurrency().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Price= " + price + currency;
    }
}
