package com.netcracker.metsko.entities;

import javax.persistence.ManyToMany;

public class Tag {
    public String tag;

    @ManyToMany
    public Offer offer;

    public Tag(String tag) {
        this.tag = tag;
    }

    public String getTag() {
        return tag;
    }

    public void setTag(String tag) {
        this.tag = tag;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Tag)) return false;

        Tag tag1 = (Tag) o;

        return getTag().equals(tag1.getTag());
    }

    @Override
    public int hashCode() {
        return getTag().hashCode();
    }

    @Override
    public String toString() {
        return "Tag: " + tag + " ";
    }
}
