package im.jss.carAPI;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface EngineTypeRepository extends JpaRepository<EngineType, Long> {
	EngineType findByName(@Param("name") String name);

	@Query("SELECT c.engineType.id, c.engineType.name, AVG(c.price) FROM Car c GROUP BY c.engineType.id ORDER BY AVG(c.price)")
	List<AverageDTO> getAverage();
}