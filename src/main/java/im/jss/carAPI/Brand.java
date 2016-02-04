package im.jss.carAPI;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "brands")
public class Brand {

	@JsonIgnore
	@OneToMany(mappedBy = "brand")
	public Set<Car> cars = new HashSet();

	@Id
	@GeneratedValue
	private Long id;
	public String name;

	Brand() {

	}

	public Brand(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}