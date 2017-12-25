package com.netcracker.metsko.entity;

import javax.validation.constraints.NotNull;
import java.util.Objects;

public class Filter {

    @NotNull
    private String category;

    private String tagList;

    private Double min;

    private Double max;

    public Filter() {
    }

    public Filter(String category, String tagList, Double min, Double max) {
        this.category = category;
        this.tagList = tagList;
        this.min = min;
        this.max = max;
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

    public Double getMin() {
        return min;
    }

    public void setMin(Double min) {
        this.min = min;
    }

    public Double getMax() {
        return max;
    }

    public void setMax(Double max) {
        this.max = max;
    }

    @Override
    public boolean equals(Object object) {
        if (this == object) return true;
        if (!(object instanceof Filter)) return false;
        Filter filter = (Filter) object;
        return Objects.equals(getCategory(), filter.getCategory()) &&
                Objects.equals(getTagList(), filter.getTagList()) &&
                Objects.equals(getMin(), filter.getMin()) &&
                Objects.equals(getMax(), filter.getMax());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategory(), getTagList(), getMin(), getMax());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Filter{");
        sb.append("category='").append(category).append('\'');
        sb.append(", tagList='").append(tagList).append('\'');
        sb.append(", min=").append(min);
        sb.append(", max=").append(max);
        sb.append('}');
        return sb.toString();
    }
}
