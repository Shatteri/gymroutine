package gymroutine.domain;

import java.util.List;

import org.springframework.data.repository.CrudRepository;

public interface WeekRepository extends CrudRepository<Week, Long>{
    List<Week> findByNumber(Long number);
}
