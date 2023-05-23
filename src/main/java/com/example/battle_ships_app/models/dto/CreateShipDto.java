package com.example.battle_ships_app.models.dto;

import com.example.battle_ships_app.models.enums.Name;
import jakarta.validation.constraints.*;
import org.springframework.format.annotation.DateTimeFormat;

import java.time.LocalDate;

public class CreateShipDto {
    @NotBlank
    @Size(min = 2, max = 10)
    private String name;


    @Positive
    private Long power;


    @Positive
    private Long health;

    @DateTimeFormat(pattern = "yyyy-MM-dd")
    @PastOrPresent
    private LocalDate created;

    @NotNull
    private Integer category;

    public CreateShipDto() {
    }

    public String getName() {
        return name;
    }

    public CreateShipDto setName(String name) {
        this.name = name;
        return this;
    }

    public Long getPower() {
        return power;
    }

    public CreateShipDto setPower(Long power) {
        this.power = power;
        return this;
    }

    public Long getHealth() {
        return health;
    }

    public CreateShipDto setHealth(Long health) {
        this.health = health;
        return this;
    }

    public LocalDate getCreated() {
        return created;
    }

    public CreateShipDto setCreated(LocalDate created) {
        this.created = created;
        return this;
    }

    public Integer getCategory() {
        return category;
    }

    public CreateShipDto setCategory(Integer category) {
        this.category = category;
        return this;
    }
}
