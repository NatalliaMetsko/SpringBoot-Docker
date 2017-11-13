package com.netcracker.metsko.entity;


import javax.persistence.*;
import java.util.Objects;

@Entity
public class Category {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private long id;

    @Column
    private String category;

    public Category() {

    }
    public Category(String category) {

        this.category = category;
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

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Category)) return false;
        Category category1 = (Category) o;
        return Objects.equals(getCategory(), category1.getCategory());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getCategory());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Category{");
        sb.append("category='").append(category).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
