package im.jss.carAPI;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;
import java.util.Map;

public interface YearRepository extends JpaRepository<Year, Long> {
    Year findByName(@Param("name") String name);

    @Query("SELECT c.year.id, c.year.name, AVG(c.price) FROM Car c GROUP BY c.year.id ORDER BY AVG(c.price)")
    List<AverageDTO> getAverage();
}