package api.wp.finki.ukim.mk.lab3.repository;

import api.wp.finki.ukim.mk.lab3.models.entities.Ingredient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface IngredientRepository extends JpaRepository<Ingredient, String> {
    Page<Ingredient> findAllBySpicy(boolean spicy, Pageable pageable);

    List<Ingredient> findAllBySpicy(boolean spicy);
}
