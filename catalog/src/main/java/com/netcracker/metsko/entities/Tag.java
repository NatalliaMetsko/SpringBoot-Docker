package com.netcracker.metsko.entities;

public class Tag {
    private String tag;

    public Tag(String tag) {
        this.tag = tag;
    }

    public Tag() {
    }

    private String getTag() {
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
        return "Tag{" +
                "tag='" + tag + '\'' +
                '}';
    }
}
