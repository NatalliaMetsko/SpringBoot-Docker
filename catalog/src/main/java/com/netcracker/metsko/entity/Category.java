package com.netcracker.metsko.entity;


import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String category;

    @OneToMany
    private List<Offer> offerList;

    public Category() {

    }

    public Category(String category, List<Offer> offerList) {
        this.category = category;
        this.offerList = offerList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<Offer> getOfferList() {
        return offerList;
    }

    public void setOfferList(List<Offer> offerList) {
        this.offerList = offerList;
    }

    public void addOffer(Offer offer)
    {
        this.offerList.add(offer);
        offer.setCategory(this);
    }

    public void removeOffer(Offer offer)
    {
        this.offerList.remove(offer);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Category)) return false;
        Category category1 = (Category) object;
        return id == category1.id &&
                Objects.equals(category, category1.category) &&
                Objects.equals(offerList, category1.offerList);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, category, offerList);
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Category{");
        sb.append("id=").append(id);
        sb.append(", category='").append(category).append('\'');
        sb.append(", offerList=").append(offerList);
        sb.append('}');
        return sb.toString();
    }
}
