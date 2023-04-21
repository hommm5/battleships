package com.example.battle_ships_app.models;

import com.example.battle_ships_app.models.enums.Name;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;

@Entity
@Table(name = "categories")
public class Category extends BaseEntity {

    @Column(unique = true, nullable = false)
    @Enumerated(EnumType.ORDINAL)
    private Name name;

    @Nullable
    @Column(columnDefinition = "TEXT")
    private String description;

    public Category() {
    }

    public Category(Name name) {
        this.name = name;
    }

    public Name getName() {
        return name;
    }

    public Category setName(Name name) {
        this.name = name;
        return this;
    }

    public String getDescription() {
        return description;
    }

    public Category setDescription(String description) {
        this.description = description;
        return this;
    }
}
