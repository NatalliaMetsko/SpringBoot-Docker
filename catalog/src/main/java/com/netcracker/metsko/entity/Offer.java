package com.netcracker.metsko.entity;


import java.util.*;

public class Offer {

    private long id;

    private String name;

    private String description;

    private boolean availability;

    private Price price;

    private ArrayList<Tag> tag;

    private Category category;

    public Offer() {
    }

    public Offer(long id, String name, String description,
                 boolean availability, Price price, ArrayList<Tag> tag, Category category) {
        this.id = id;
        this.name = name;
        this.description = description;
        this.availability = availability;
        this.price = price;
        this.tag = tag;
        this.category = category;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDescription() {
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

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public ArrayList<Tag> getTag() {
        return tag;
    }

    public void setTag(ArrayList<Tag> tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Offer)) return false;
        Offer offer = (Offer) o;
        return getId() == offer.getId() &&
                isAvailability() == offer.isAvailability() &&
                Objects.equals(getName(), offer.getName()) &&
                Objects.equals(getDescription(), offer.getDescription()) &&
                Objects.equals(getPrice(), offer.getPrice()) &&
                Objects.equals(getTag(), offer.getTag()) &&
                Objects.equals(getCategory(), offer.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), isAvailability(), getPrice(), getTag(), getCategory());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Offer{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", availability=").append(availability);
        sb.append(", price=").append(price);
        sb.append(", tag=").append(tag);
        sb.append(", category=").append(category);
        sb.append('}');
        return sb.toString();
    }
}
