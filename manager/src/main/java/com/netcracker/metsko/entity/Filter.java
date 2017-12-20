package com.netcracker.metsko.entity;

import java.util.Objects;

public class Filter {

    private String category;

    private String tagList;

    private Double price;

    public Filter() {
    }

    public Filter(String category, String tagList, Double price) {
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

    public String getTagList() {
        return tagList;
    }

    public void setTagList(String tagList) {
        this.tagList = tagList;
    }

    public Double getPrice() {
        return price;
    }

    public void setPrice(Double price) {
        this.price = price;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Filter)) return false;
        Filter filter = (Filter) object;
        return Objects.equals(getCategory(), filter.getCategory()) &&
                Objects.equals(getTagList(), filter.getTagList()) &&
                Objects.equals(getPrice(), filter.getPrice());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategory(), getTagList(), getPrice());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Filter{");
        sb.append("category='").append(category).append('\'');
        sb.append(", tagList=").append(tagList);
        sb.append(", price='").append(price).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
