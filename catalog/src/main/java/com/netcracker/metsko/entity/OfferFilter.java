package com.netcracker.metsko.entity;

import java.util.List;
import java.util.Objects;

public class OfferFilter {

    private Category category;

    private List<Tag> tagList;

    private Price price;

    public OfferFilter() {
    }

    public OfferFilter(Category category, List<Tag> tagList, Price price) {
        this.category = category;
        this.tagList = tagList;
        this.price = price;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public List<Tag> getTagList() {
        return tagList;
    }

    public void setTagList(List<Tag> tagList) {
        this.tagList = tagList;
    }

    public Price getPrice() {
        return price;
    }

    public void setPrice(Price price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof OfferFilter)) return false;
        OfferFilter that = (OfferFilter) object;
        return Objects.equals(getCategory(), that.getCategory()) &&
                Objects.equals(getTagList(), that.getTagList()) &&
                Objects.equals(getPrice(), that.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategory(), getTagList(), getPrice());
    }


    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OfferFilter{");
        sb.append("category=").append(category);
        sb.append(", tagList=").append(tagList);
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}
