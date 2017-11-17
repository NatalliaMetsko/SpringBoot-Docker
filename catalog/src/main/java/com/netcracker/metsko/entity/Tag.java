package com.netcracker.metsko.entity;

import javax.persistence.*;
import java.util.List;
import java.util.Objects;

@Entity
public class Tag {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String tag;

    @ManyToMany
    private List<Offer> offerList;

    public Tag() {
    }

    public Tag(String tag, List<Offer> offerList) {
        this.tag = tag;
        this.offerList = offerList;
    }

    public long getId() {
        return id;
    }

    public void setId(long id) {
        this.id = id;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
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
        offer.getTagList().add(this);
    }

    public void removeOffer(Offer offer)
    {
        this.offerList.remove(offer);
        offer.getTagList().remove(this);
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Tag)) return false;
        Tag tag1 = (Tag) object;
        return getId() == tag1.getId() &&
                Objects.equals(getTag(), tag1.getTag()) &&
                Objects.equals(getOfferList(), tag1.getOfferList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getTag(), getOfferList());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tag{");
        sb.append("id=").append(id);
        sb.append(", tag='").append(tag).append('\'');
        sb.append(", offerList=").append(offerList);
        sb.append('}');
        return sb.toString();
    }
}
