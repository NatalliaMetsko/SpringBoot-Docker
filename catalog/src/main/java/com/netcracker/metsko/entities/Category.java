package com.netcracker.metsko.entities;

public class Category {
    public String category;

    public String getCategory() {
        return category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public Category(String category) {

        this.category = category;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;

        Category category1 = (Category) o;

        return getCategory().equals(category1.getCategory());
    }

    @Override
    public int hashCode() {
        return getCategory().hashCode();
    }

    @Override
    public String toString() {
        return "Category: " +category + " ";
    }
}
