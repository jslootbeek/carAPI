package im.jss.carAPI;

import com.fasterxml.jackson.annotation.JsonIgnore;

import javax.persistence.*;

import java.util.HashSet;
import java.util.Set;

@Entity
@Table(name = "years")
public class Year {

    @JsonIgnore
    @OneToMany(mappedBy = "year")
    public Set<Car> cars = new HashSet();

    @Id
    @GeneratedValue
    private Long id;
    public String name;

    Year() {

    }

    public Year(String name) {
        this.name = name;
    }

    public Long getId() {
        return id;
    }

    public String getName() {
        return name;
    }
}