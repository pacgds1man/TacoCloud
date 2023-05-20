package tacos.repository;

import org.springframework.data.repository.PagingAndSortingRepository;
import tacos.entity.Taco;

public interface TacoRepository extends PagingAndSortingRepository<Taco, Long> {
}
