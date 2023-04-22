package com.example.battle_ships_app.seeders;

import com.example.battle_ships_app.models.Category;
import com.example.battle_ships_app.models.enums.Name;
import com.example.battle_ships_app.repositories.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.stereotype.Component;
import java.util.Arrays;
import java.util.List;


@Component
public class CategorySeeder implements CommandLineRunner {
    private final CategoryRepository categoryRepository;

    @Autowired
    public CategorySeeder(CategoryRepository categoryRepository) {
        this.categoryRepository = categoryRepository;
    }

    @Override
    public void run(String... args) throws Exception {

        if (categoryRepository.count()==0){
            List<Category> categoryList = Arrays.stream(Name.values())
                    .map(type -> new Category(type))
                    .toList();

            categoryRepository.saveAll(categoryList);

        }
    }
}
