package org.example.domain;

public interface Entity<ID> {
    void setId(ID id);
    ID getId();
}