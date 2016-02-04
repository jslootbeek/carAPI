package im.jss.carAPI;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;
import java.util.HashSet;
import java.util.Set;


@Entity
@Table(name = "cars")
public class Car {
	@Id 
	@GeneratedValue
	private Long id;

	private String vin;

	@ManyToOne
	private Type type;

	@ManyToOne
	private Brand brand;

	@ManyToOne
	private Color color;

	@ManyToOne
	private EngineType engineType;

	private int price;

    @ManyToOne
	private Year year;

	Car() {

	}

	public Car(Type type, String vin, Brand brand, Color color, EngineType engineType, int price, Year year) {
		this.vin = vin;
		this.type = type;
		this.brand = brand;
		this.color = color;
		this.engineType = engineType;
		this.price = price;
		this.year = year;
	}

	public Long getId() {
		return id;
	}

	public Type getType() {
		return type;
	}

	public String getVin() {
		return vin;
	}

	public Brand getBrand() {
		return brand;
	}

	public Color getColor() {
		return color;
	}

	public EngineType getEngineType() {
		return engineType;
	}

	public int getPrice() {
		return price;
	}

	public Year getYear() {
		return year;
	}
}