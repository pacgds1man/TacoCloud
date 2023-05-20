package tacos.repository;

import org.springframework.data.repository.CrudRepository;
import tacos.entity.Order;

public interface OrderRepository extends CrudRepository<Order, Long> {
}
