package com.netcracker.metsko.entity;

import java.util.Objects;

public class Tag {
    private String tag;

    public Tag(String tag) {
        this.tag = tag;
    }

    public Tag() {
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
        return Objects.equals(getTag(), tag1.getTag());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getTag());
    }

    @Override
    public String toString() {
        final StringBuilder sb = new StringBuilder("Tag{");
        sb.append("tag='").append(tag).append('\'');
        sb.append('}');
        return sb.toString();
    }
}
