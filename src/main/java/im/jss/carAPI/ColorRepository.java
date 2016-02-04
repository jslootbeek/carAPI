package im.jss.carAPI;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface ColorRepository extends JpaRepository<Color, Long> {
	Color findByName(@Param("name") String name);

	@Query("SELECT c.color.id, c.color.name, AVG(c.price) FROM Car c GROUP BY c.color.id ORDER BY AVG(c.price)")
    List<AverageDTO> getAverage();
}