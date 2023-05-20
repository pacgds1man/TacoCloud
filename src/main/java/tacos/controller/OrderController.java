package tacos.controller;

import org.springframework.dao.EmptyResultDataAccessException;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.http.HttpStatus;
import org.springframework.security.core.annotation.AuthenticationPrincipal;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.Errors;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.bind.support.SessionStatus;
import tacos.Order;
import tacos.User;
import tacos.repository.OrderRepository;
import tacos.OrderProps;

import javax.validation.Valid;


@Controller
@RequestMapping("/orders")
@SessionAttributes("order")
public class OrderController {
    public OrderController(OrderRepository repo) {
        this.repo = repo;
    }

    private OrderRepository repo;

    @PutMapping(path = "/{orderId}", consumes = "application/json")
    public Order putOrder(@RequestBody Order order) {
        return repo.save(order);
    }

    @PatchMapping(path = "/{orderId}", consumes = "application/json")
    public Order patchOrder(@PathVariable("orderId") Long orderId,
                            @RequestBody Order patch) {

        Order order = repo.findById(orderId).get();
        if (patch.getDeliveryName() != null) {
            order.setDeliveryName(patch.getDeliveryName());
        }
        if (patch.getDeliveryStreet() != null) {
            order.setDeliveryStreet(patch.getDeliveryStreet());
        }
        if (patch.getDeliveryCity() != null) {
            order.setDeliveryCity(patch.getDeliveryCity());
        }
        if (patch.getDeliveryState() != null) {
            order.setDeliveryState(patch.getDeliveryState());
        }
        if (patch.getDeliveryZip() != null) {
            order.setDeliveryZip(patch.getDeliveryState());
        }
        if (patch.getCcNumber() != null) {
            order.setCcNumber(patch.getCcNumber());
        }
        if (patch.getCcExpiration() != null) {
            order.setCcExpiration(patch.getCcExpiration());
        }
        if (patch.getCcCVV() != null) {
            order.setCcCVV(patch.getCcCVV());
        }
        return repo.save(order);
    }

    @DeleteMapping("/{orderId}")
    @ResponseStatus(code = HttpStatus.NO_CONTENT)
    public void deleteOrder(@PathVariable("orderId") Long orderId) {
        try {
            repo.deleteById(orderId);
        } catch (EmptyResultDataAccessException e) {
        }
    }

//    public OrderController(OrderRepository orderRepo, OrderProps props) {
//        this.orderRepo = orderRepo;
//        this.props = props;
//    }
//
//
//    @GetMapping("/current")
//    public String orderForm(Model model) {
//        model.addAttribute("order", new Order());
//        return "orderForm";
//    }
//
//    @PostMapping
//    public String processOrder(@Valid Order order, Errors errors, @AuthenticationPrincipal User user, SessionStatus sessionStatus) {
//        if (errors.hasErrors()) {
//            return "orderForm";
//        }
//        order.setUser(user);
//        orderRepo.save(order);
//        sessionStatus.setComplete();
//        return "redirect:/";
//    }
//
//    @GetMapping
//    public String ordersForUser(@AuthenticationPrincipal User user, Model model) {
//        Pageable pageable =  PageRequest.of(0, props.getPageSize());
//        model.addAttribute("orders",orderRepo.findByUserOrderByPlacedAtDesc(user, pageable));
//        return "orderList";
//    }
}

