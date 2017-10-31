package com.netcracker.metsko.entity;


import java.util.Objects;

public class Price {

    private double price;

    private String currency;

    public Price() {
    }

    public Price(double price, String currency) {
        this.price = price;
        this.currency=currency;
    }

    public Double getPrice() {

        return price;
    }

    public void setPrice(double price) {
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
        return Double.compare(price1.getPrice(), getPrice()) == 0 &&
                Objects.equals(getCurrency(), price1.getCurrency());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getPrice(), getCurrency());
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Price{");
        sb.append("price=").append(price);
        sb.append(", currency='").append(currency).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
