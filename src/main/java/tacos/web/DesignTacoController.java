package tacos.web;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import tacos.Ingredient;
import tacos.Order;
import tacos.Taco;
import tacos.TacoForm;
import tacos.data.IngredientRepository;
import tacos.data.TacoRepository;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/design")
@SessionAttributes("order")
public class DesignTacoController {
    private final IngredientRepository ingredientRepo;
    private final TacoRepository designRepo;

    @Autowired
    public DesignTacoController(IngredientRepository ingredientRepo, TacoRepository designRepo) {
        this.ingredientRepo = ingredientRepo;
        this.designRepo = designRepo;
    }
    @ModelAttribute(name = "order")
    public Order order() {//а для чего нам эти методы?
        return new Order();
    }

    @ModelAttribute(name = "taco")
    public Taco taco() {
        return new Taco();
    }

    @GetMapping
    public String showDesignForm(Model model) {
        List<Ingredient> ingredients = new ArrayList<>();
        ingredientRepo.findAll().forEach(i -> ingredients.add(i));
        Ingredient.Type[] types = Ingredient.Type.values();
        for (Ingredient.Type type : types) {
            model.addAttribute(type.toString().toLowerCase(), filterByType(ingredients, type));
        }
        model.addAttribute("taco", new Taco());
        return "design";
    }

    private List<Ingredient> filterByType(List<Ingredient> ingredients, Ingredient.Type type) {
        return ingredients.stream()
                .filter(x -> x.getType().equals(type))
                .collect(Collectors.toList());
    }

    @PostMapping
    public String processDesign(@Valid TacoForm form, Errors errors, @ModelAttribute Order order) {
        if (errors.hasErrors()) {
            return "design";
        }
        Taco createdTaco = createTaco(form);

        Taco saved=designRepo.save(createdTaco);
        order.addDesign(saved);
        return "redirect:/orders/current";
    }

    private Taco createTaco(TacoForm form) {
        List<Ingredient> ingredients = form.getIngredients().stream()
                .map(ingredientRepo::findById)
                .collect(Collectors.toList());

        Taco taco = new Taco();
        taco.setCreatedAt(form.getCreatedAt());
        taco.setName(form.getName());
        taco.setIngredients(ingredients);
        return taco;
    }

}
//@Controller
//@RequestMapping("/design")
//@SessionAttributes("order")
//public class DesignTacoController {
//    private final IngredientRepository ingredientRepo;
//
//    @Autowired
//    public DesignTacoController(IngredientRepository ingredientRepo) {
//        this.ingredientRepo = ingredientRepo;
//    }
//
//    @GetMapping
//    public String showDesignForm(Model model) {
//        List<Ingredient> ingredients = new ArrayList<>();
//        ingredientRepo.findAll().forEach(i -> ingredients.add(i));
//        Type[] types = Ingredient.Type.values();
//        for (Type type : types) {
//            model.addAttribute(type.toString().toLowerCase(),
//                    filterByType(ingredients, type));
//        }
//        return "design";
//    }
// ...
//}