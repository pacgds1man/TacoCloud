package tacos;

import lombok.Data;
import lombok.RequiredArgsConstructor;

@Data
@RequiredArgsConstructor
public class Ingredient {

    private final String id;
    private final String name;
    private final Type type;

    public static enum Type {
        WRAP, PROTEIN, VEGGIES, CHEESE, SAUCE
    }
}
//@Controller
//@RequestMapping("/orders")
//@SessionAttributes("order")
//public class OrderController {
//    private OrderRepository orderRepo;
//
//    public OrderController(OrderRepository orderRepo) {
//        this.orderRepo = orderRepo;
//    }
//
//    @GetMapping("/current")
//    public String orderForm() {
//        return "orderForm";
//    }
//
//    @PostMapping
//    public String processOrder(@Valid Order order, Errors errors,SessionStatus sessionStatus) {
//        if (errors.hasErrors()) {
//            return "orderForm";
//        }
//        orderRepo.save(order);
//        sessionStatus.setComplete();
//        return "redirect:/";
//    }
//}