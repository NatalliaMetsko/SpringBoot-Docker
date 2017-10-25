package com.netcracker.metsko.entities;


import javax.persistence.*;

public class Offer {

    private  long id;

    private String name;

    private String description;

    private boolean availability;

    private Price price;

    private Tag tag;

    private Category category;

    private long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    private String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    private String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public boolean isAvailability() {
        return availability;
    }

    public void setAvailability(boolean availability) {
        this.availability = availability;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    public Tag getTag() {
        return tag;
    }

    public void setTag(Tag tag) {
        this.tag = tag;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public Offer(String name, String description, boolean availability, Price price, Tag tag, Category category) {
        this.name = name;
        this.description = description;
        this.availability = availability;
        this.price = price;
        this.tag = tag;
        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Offer)) return false;

        Offer offer = (Offer) o;

        if (getId() != offer.getId()) return false;
        if (!getName().equals(offer.getName())) return false;
        return getDescription() != null ? getDescription().equals(offer.getDescription()) : offer.getDescription() == null;
    }

    @Override
    public int hashCode() {
        int result = (int) (getId() ^ (getId() >>> 32));
        result = 31 * result + getName().hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Offer{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", availability=" + availability +
                ", price=" + price +
                ", tag=" + tag +
                ", category=" + category +
                '}';
    }
}
