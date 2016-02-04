package im.jss.carAPI;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.OneToMany;

import java.util.HashSet;
import java.util.Set;

@Entity
public class Type {
	@JsonIgnore
	@OneToMany(mappedBy = "type")
	public Set<Car> cars = new HashSet();

	@Id 
	@GeneratedValue
	private Long id;

	public String name;

	Type() {

	}

	public Type(String name) {
		this.name = name;
	}

	public Long getId() {
		return id;
	}

	public String getName() {
		return name;
	}
}