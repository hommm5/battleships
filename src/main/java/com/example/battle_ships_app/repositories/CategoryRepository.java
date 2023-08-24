package com.example.battle_ships_app.repositories;

import com.example.battle_ships_app.models.Category;
import com.example.battle_ships_app.models.enums.Name;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CategoryRepository extends JpaRepository<Category, Long> {

    Category findByName(Name name);
}
