package tacos.data;

import org.springframework.data.repository.CrudRepository;
import tacos.Ingredient;

import java.util.List;

public interface IngredientRepository extends CrudRepository<Ingredient, String> {

    Ingredient getById(String s);
}
