package im.jss.carAPI;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface TypeRepository extends JpaRepository<Type, Long> {
	Type findByName(@Param("name") String name);

	@Query("SELECT c.type.id, c.type.name, AVG(c.price) FROM Car c GROUP BY c.type.id ORDER BY AVG(c.price)")
	List<AverageDTO> getAverage();
}