package com.netcracker.metsko.entities;


import javax.persistence.*;



public class Offer {

    @Id
    @GeneratedValue
    private  long id;

    public String name;

    public String description;

    public boolean availability;

    @ManyToMany
    Order order;

    @OneToMany
    public Price price;

    @ManyToMany
    public Tag tag;

    @ManyToOne
    public Category category;

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

    public double getPrice() {
        return price.price;
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
                "name='" + name + '\'' +
                ", description='" + description + '\'' +
                ", availability=" + availability +
                ", price=" + price +
                ", tag=" + tag +
                ", category=" + category +
                '}';
    }


}
