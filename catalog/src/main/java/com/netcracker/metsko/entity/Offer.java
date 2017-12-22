package com.netcracker.metsko.entity;


import com.fasterxml.jackson.annotation.JsonBackReference;
import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import java.util.List;
import java.util.Objects;

@Entity
public class Offer {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column(unique = true)
    @NotNull
    @Size(min = 3, max = 50)
    private String name;

    @Column
    @Size(min = 5, max = 100)
    private String description;

    @Column
    private boolean availability;

    @OneToOne
    private Price price;

    @ManyToMany
    private List<Tag> tagList;

    @Column
    @JsonIgnore
    private String tags;

    @ManyToOne
    @JsonBackReference
    private Category category;

    public Offer() {
    }

    public Offer(String name, String description, boolean availability, Price price,
                 List<Tag> tagList, String tags, Category category) {
        this.name = name;
        this.description = description;
        this.availability = availability;
        this.price = price;
        this.tagList = tagList;
        this.tags = tags;
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

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public void setTags(String tags) {
        this.tags = tags;
    }

    public String getTags() {
        return tags;
    }

    public void addPrice(Price price) {
        this.price = price;
        price.setOffer(this);
    }

    public void addTag(Tag tag) {
        this.tagList.add(tag);
        this.tags = tags.concat(tag.getTag()+" ");
        tag.getOfferList().add(this);
    }

    public void removeTag(Tag tag) {
        this.tagList.remove(tag);
        tag.getOfferList().remove(this);
    }


    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Offer)) return false;
        Offer offer = (Offer) object;
        return getId() == offer.getId() &&
                isAvailability() == offer.isAvailability() &&
                Objects.equals(getName(), offer.getName()) &&
                Objects.equals(getDescription(), offer.getDescription()) &&
                Objects.equals(getPrice(), offer.getPrice()) &&
                Objects.equals(getTagList(), offer.getTagList()) &&
                Objects.equals(getTags(), offer.getTags()) &&
                Objects.equals(getCategory(), offer.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getDescription(), isAvailability(), getPrice(), getTagList(), getTags(), getCategory());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Offer{");
        sb.append("id=").append(id);
        sb.append(", name='").append(name).append('\'');
        sb.append(", description='").append(description).append('\'');
        sb.append(", availability=").append(availability);
        sb.append(", price=").append(price);
        sb.append(", tagList=").append(tagList);
        sb.append(", tags='").append(tags).append('\'');
        sb.append(", category=").append(category);
        sb.append('}');
        return sb.toString();
    }
}
