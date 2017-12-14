package com.netcracker.metsko.entity;

import java.util.List;
import java.util.Objects;

public class OfferFilter {

    private String category;

    private List<String> tagList;

    private double price;

    public OfferFilter() {
    }

    public OfferFilter(String category, List<String> tagList, double price) {
        this.category = category;
        this.tagList = tagList;
        this.price = price;
    }

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public List<String> getTagList() {
        return tagList;
    }

    public void setTagList(List<String> tagList) {
        this.tagList = tagList;
    }

    public double getPrice() {
        return price;
    }

    public void setPrice(double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof OfferFilter)) return false;
        OfferFilter that = (OfferFilter) object;
        return Double.compare(that.getPrice(), getPrice()) == 0 &&
                Objects.equals(getCategory(), that.getCategory()) &&
                Objects.equals(getTagList(), that.getTagList());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategory(), getTagList(), getPrice());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("OfferFilter{");
        sb.append("category='").append(category).append('\'');
        sb.append(", tagList=").append(tagList);
        sb.append(", price=").append(price);
        sb.append('}');
        return sb.toString();
    }
}
