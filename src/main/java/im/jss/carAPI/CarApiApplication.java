// tag::runner[]
package im.jss.carAPI;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.domain.Sort;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@Configuration
@ComponentScan
@EnableAutoConfiguration
public class CarApiApplication {

	String[] input = {
			"Truck, 1234, GMC, Red, Gas, 24000, 2016",
			"Car, 1235, Toyota, Green, Hybrid, 27000, 2015",
			"Car, 1236, Toyota, Red, Gas, 19000, 2015",
			"Car, 1237, Toyota, Blue, Gas, 21000, 2014",
			"Motorcycle, 1238, Honda, Blue, Hybrid, 17000, 2016",
			"Truck, 1239, Honda, Blue, Hybrid, 17000, 2016",
			"Car, 1240, Hyundai, Red, Gas, 17000, 2014",
			"Car, 1241, Tesla, Blue, Electric, 85000, 2016",
			"Bicycle, 1242, Cervelo, White, None, 2000, 2015",
			"Bicycle, 1243, Huffy, White, None, 150, 2014",
			"Bicycle, 1244, Trek, Orange, None, 1200, 2016",
			"Car, 1245, Hyundai, White, Electric, 25000, 2009",
			"Car, 1246, Ford Pinto, Light Blue, Gas, 1000, 1984",
			"Car, 1247, Chevy Vega, Dark Blue, Gas, 500, 1981",
			"Car, 1248, AMC Hornet, Tan, Gas, 100, 1977",
			"Car, 1249, Yugo, Gray, Gas, 10, 1985",
			"Car, 1250, Bugatti Veyron, Black/Red, Gas, 2500000, 2016",
			"Car, 1251, Tesla model-S, Red, Electric, 84000, 2016",
			"Car, 1252, Honda, Blue, Gas, 5000, 2002",
			"Car, 1253, Honda, Silver, Gas, 13000, 2015",
			"Car, 1254, Hyundai, Gold, Gas, 13000, 2014",
			"Car, 1255, Subaru, Blue, Gas, 13000, 2014"};

	@Bean
	CommandLineRunner init(CarRepository carRepo, TypeRepository typeRepo, final BrandRepository brandRepo, ColorRepository colorRepo, final EngineTypeRepository engineTypeRepo, final YearRepository yearRepo) {
		return (evt) -> Arrays.asList(input).forEach(
				a -> {
					String[] items = a.split(",");

					Type type = null;
					String typeString = items[0].trim();
					type = typeRepo.findByName(typeString);
					if (type == null) {
						type = typeRepo.save(new Type(typeString));
					}

					String vin = items[1].trim();

                    Brand brand = null;
					String brandString = items[2].trim();
					brand = brandRepo.findByName(brandString);
                    if (brand == null) {
                        brand = brandRepo.save(new Brand(brandString));
                    }

                    Color color = null;
					String colorString = items[3].trim();
					color = colorRepo.findByName(colorString);
                    if (color == null) {
                        color = colorRepo.save(new Color(colorString));
                    }

                    EngineType engineType = null;
                    String engineTypeString = items[4].trim();
					engineType = engineTypeRepo.findByName(engineTypeString);
                    if (engineType == null) {
                        engineType = engineTypeRepo.save(new EngineType(engineTypeString));
                    }

					Integer price = Integer.parseInt(items[5].trim());
                    
                    Year year = null;
                    String yearString = items[6].trim();
                    year = yearRepo.findByName(yearString);
                    if (year == null) {
                        year = yearRepo.save(new Year(yearString));
                    }
					Car car = carRepo.save(new Car(type, vin, brand, color, engineType, price, year));
				});
		}
		public static void main(String[] args) {
			SpringApplication.run(CarApiApplication.class, args);
		}
}
// end::runner[]

@RestController
@RequestMapping("/engineTypes")
class EngineTypeRestController {
    private final EngineTypeRepository engineTypeRepo;
    private final CarRepository carRepo;

    @RequestMapping(value = "/average", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    // Return a custom object that contains the ID, name and average
    public List<AverageDTO> average() {
        return this.engineTypeRepo.getAverage();
    }

    @RequestMapping(value = "/{engineTypeId}", method = RequestMethod.GET)
    Collection<Car> getCarsByEngineTypeId(@PathVariable Long engineTypeId) {
        return this.carRepo.findByEngineTypeId(engineTypeId);
    }

    @RequestMapping(method = RequestMethod.GET)
    Collection<EngineType> getAllCars() {
        return this.engineTypeRepo.findAll();
    }

    @Autowired
    EngineTypeRestController(EngineTypeRepository engineTypeRepo, CarRepository carRepo) {
        this.engineTypeRepo = engineTypeRepo;
        this.carRepo = carRepo;
    }
}

@RestController
@RequestMapping("/types")
class TypeRestController {
    private final TypeRepository typeRepo;
    private final CarRepository carRepo;

    @RequestMapping(value = "/average", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    // Return a custom object that contains the ID, name and average
    public List<AverageDTO> average() {
        return this.typeRepo.getAverage();
    }

    @RequestMapping(value = "/{typeId}", method = RequestMethod.GET)
    Collection<Car> getCarsByTypeId(@PathVariable Long typeId) {
        return this.carRepo.findByTypeId(typeId);
    }

    @RequestMapping(method = RequestMethod.GET)
    Collection<Type> getAllCars() {
        return this.typeRepo.findAll();
    }

    @Autowired
    TypeRestController(TypeRepository typeRepo, CarRepository carRepo) {
        this.typeRepo = typeRepo;
        this.carRepo = carRepo;
    }
}


@RestController
@RequestMapping("/colors")
class ColorRestController {
    private final ColorRepository colorRepo;
    private final CarRepository carRepo;

    @RequestMapping(value = "/average", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    // Return a custom object that contains the ID, name and average
    public List<AverageDTO> average() {
        return this.colorRepo.getAverage();
    }

    @RequestMapping(value = "/{brandId}", method = RequestMethod.GET)
    Collection<Car> getCarsByBrandId(@PathVariable Long brandId) {

        return this.carRepo.findByColorId(brandId);
    }

    @RequestMapping(method = RequestMethod.GET)
    Collection<Color> getAllCars() {

        return this.colorRepo.findAll();
    }

    @Autowired
    ColorRestController(ColorRepository colorRepo, CarRepository carRepo) {
        this.colorRepo = colorRepo;
        this.carRepo = carRepo;
    }
}

@RestController
@RequestMapping("/brands")
class BrandRestController {
    private final BrandRepository brandRepo;
    private final CarRepository carRepo;

    @RequestMapping(value = "/average", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    // Return a custom object that contains the ID, name and average
    public List<AverageDTO> average() {
        return this.brandRepo.getAverage();
    }

    @RequestMapping(value = "/{brandId}", method = RequestMethod.GET)
    Collection<Car> getCarsByBrandId(@PathVariable Long brandId) {

        return this.carRepo.findByBrandId(brandId);
    }

    @RequestMapping(method = RequestMethod.GET)
    Collection<Brand> getAllCars() {

        return this.brandRepo.findAll();
    }

    @Autowired
    BrandRestController(BrandRepository brandRepo, CarRepository carRepo) {
        this.brandRepo = brandRepo;
        this.carRepo = carRepo;
    }
}

@RestController
@RequestMapping("/years")
class YearRestController {
    private final YearRepository yearRepo;
    private final CarRepository carRepo;

    @RequestMapping(value = "/average", method = RequestMethod.GET, produces = MediaType.APPLICATION_JSON_VALUE)
    // Return a custom object that contains the ID, name and average
    public List<AverageDTO> average() {
        return this.yearRepo.getAverage();
    }

    @RequestMapping(value = "/{yearId}", method = RequestMethod.GET)
    Collection<Car> getCarsByYearId(@PathVariable Long yearId) {

        return this.carRepo.findByYearId(yearId);
    }

    @RequestMapping(method = RequestMethod.GET)
    Collection<Year> getAllCars() {

        return this.yearRepo.findAll();
    }

    @Autowired
    YearRestController(YearRepository yearRepo, CarRepository carRepo) {
        this.yearRepo = yearRepo;
        this.carRepo = carRepo;
    }
}


@RestController
@RequestMapping("/cars")
class CarRestController {
	private final CarRepository carRepo;

    @RequestMapping(value = "/sort/{value}/{dir}", method = RequestMethod.GET)
    Collection<Car> readCarSortedByValue(@PathVariable String value, @PathVariable String dir) {
        Sort.Direction direction = Sort.Direction.ASC;
        if (dir.equals("desc")) {
            direction = Sort.Direction.DESC;
        }
        Sort sort = new Sort(new Sort.Order(direction, value));
        return this.carRepo.findAll(sort);
    }

	@RequestMapping(value = "/{carId}", method = RequestMethod.GET)
	Car readCar(@PathVariable Long carId) {
		return this.carRepo.findOne(carId);
	}

	@RequestMapping(method = RequestMethod.GET)
	Collection<Car> getCars() {
		return this.carRepo.findAll();
	}

	@Autowired
	CarRestController(CarRepository carRepo) {
		this.carRepo = carRepo;
	}
}

@ResponseStatus(HttpStatus.NOT_FOUND)
class CarNotFoundException extends RuntimeException {
	public CarNotFoundException(Long carId) {
		super("could not find car '" + carId + "'.");
	}
}