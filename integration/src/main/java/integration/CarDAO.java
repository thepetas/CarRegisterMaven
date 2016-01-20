package integration;

import java.util.Collection;
import model.Car;
import model.CarId;
import utils.RegException;

public interface CarDAO {

    void create(String brand, String model, String owner) throws RegException;

    Collection<Car> getAll() throws RegException;

    void delete(CarId id) throws RegException;

}
