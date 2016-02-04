package im.jss.carAPI;

import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Collection;

public interface CarRepository extends JpaRepository<Car, Long> {
	Collection<Car> findByBrandId(Long id);
	Collection<Car> findByColorId(Long id);
	Collection<Car> findByEngineTypeId(Long id);
	Collection<Car> findByTypeId(Long id);
	Collection<Car> findByYearId(Long id);
}