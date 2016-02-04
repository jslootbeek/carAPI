package im.jss.carAPI;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import java.util.List;

public interface BrandRepository extends JpaRepository<Brand, Long> {
	Brand findByName(@Param("name") String name);

    @Query("SELECT c.brand.id, c.brand.name, AVG(c.price) FROM Car c GROUP BY c.brand.id ORDER BY AVG(c.price)")
    List<AverageDTO> getAverage();
}