package integration.impl;

import integration.CarDAO;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import model.Car;
import model.CarId;

public final class DefaultCarDAO implements CarDAO {

    Map<CarId, Car> cars = new HashMap<>();
    private Integer counter = 2;

    public DefaultCarDAO() {
    }

    @Override
    public Collection<Car> getAll() {
        return new ArrayList<>(cars.values());
    }

    @Override
    public void create(String brand, String model, String owner) {
        Car b = new Car(new CarId(counter++), brand, model, owner);
        cars.put(b.getId(), b);
    }

    @Override
    public void delete(CarId id) {
        cars.remove(id);
    }
}
