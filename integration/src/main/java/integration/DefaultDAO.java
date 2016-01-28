package integration;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import model.car.Car;
import model.car.CarId;
import model.owner.Owner;
import model.owner.OwnerId;
import utils.RegException;

public final class DefaultDAO implements RegDAO {

    Map<CarId, Car> cars = new HashMap<>();
    Map<OwnerId, Owner> owners = new HashMap<>();

    private Integer carCnt = 1;
    private Integer ownerCnt = 1;

    public DefaultDAO() {
    }

    @Override
    public Collection<Car> getAllCars() {
        return new ArrayList<>(cars.values());
    }

    @Override
    public void createCar(String brand, String model, int idOwner) {
        Car b = new Car(new CarId(carCnt++), brand, model, idOwner);
        cars.put(b.getId(), b);
    }

    @Override
    public void deleteCar(CarId id) {
        cars.remove(id);
    }

    @Override
    public void createOwner(String name, String surname) throws RegException {
        Owner o = new Owner(new OwnerId(ownerCnt++), name, surname);
    }

    @Override
    public Collection<Owner> getAllOwners() {
        return new ArrayList<>(owners.values());
    }

    @Override
    public void deleteOwner(OwnerId id) throws RegException {
        owners.remove(id);
    }

    @Override
    public Collection<Car> getOwnersCars(int idOwner) {

        ArrayList<Car> owned = new ArrayList<>();
        for (Map.Entry<CarId, Car> entrySet : cars.entrySet()) {
            Car c = entrySet.getValue();
            if (c.getIdOwner() == idOwner) {
                owned.add(c);
            }
        }
        return owned;
    }

    @Override
    public Owner getOwner(int ownerId) throws RegException {
        return owners.get(ownerId);
    }
}
