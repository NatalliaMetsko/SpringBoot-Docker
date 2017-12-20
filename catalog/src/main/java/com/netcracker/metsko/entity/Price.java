package com.netcracker.metsko.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;

import javax.persistence.*;
import java.util.Objects;

@Entity
public class Price {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private double price;

    @Column
    private String currency;

    @OneToOne
    @JsonBackReference
    private Offer offer;

    public Price() {
    }

    public Price(double price, String currency, Offer offer) {
        this.price = price;
        this.currency = currency;
        this.offer = offer;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public double getPrice() {
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

    public Offer getOffer() {
        return offer;
    }

    public void setOffer(Offer offer) {
        this.offer = offer;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Price)) return false;
        Price price1 = (Price) object;
        return getId() == price1.getId() &&
                Double.compare(price1.getPrice(), getPrice()) == 0 &&
                Objects.equals(getCurrency(), price1.getCurrency()) &&
                Objects.equals(getOffer(), price1.getOffer());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getPrice(), getCurrency(), getOffer());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Price{");
        sb.append("id=").append(id);
        sb.append(", price=").append(price);
        sb.append(", currency='").append(currency).append('\'');
        sb.append(", offer=").append(offer);
        sb.append('}');
        return sb.toString();
    }
}
