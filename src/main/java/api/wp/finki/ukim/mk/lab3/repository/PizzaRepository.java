package api.wp.finki.ukim.mk.lab3.repository;

import api.wp.finki.ukim.mk.lab3.models.entities.Ingredient;
import api.wp.finki.ukim.mk.lab3.models.entities.Pizza;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface PizzaRepository extends JpaRepository<Pizza, String> {
    @Query("SELECT p " +
            "FROM Pizza p " +
            "WHERE (SELECT COUNT(j) FROM Pizza p1 JOIN p1.ingredients j WHERE p.name = p1.name) < :totalIng")
    List<Pizza> myQuery(@Param("totalIng") long count);

    @Query("SELECT ING \n" +
            "FROM Ingredient ING\n" +
            "JOIN ING.pizzas p\n" +
            "WHERE p.name = :pizza1 AND ING.name IN (\n" +
            "SELECT ING1 \n" +
            "FROM Ingredient ING1\n" +
            "JOIN ING1.pizzas PI1\n" +
            "WHERE PI1.name = :pizza2\n" +
            ")")
    List<Ingredient> commonIngredients(@Param("pizza1") String p1, @Param("pizza2") String p2);
}
