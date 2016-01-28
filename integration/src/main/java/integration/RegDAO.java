package integration;

import java.util.Collection;
import model.car.Car;
import model.car.CarId;
import model.owner.Owner;
import model.owner.OwnerId;
import utils.RegException;

public interface RegDAO {

    void createCar(String brand, String model, int idOwner) throws RegException;

    Collection<Car> getAllCars() throws RegException;

    void deleteCar(CarId id) throws RegException;
    
    
    void createOwner(String name, String surname) throws RegException;

    Collection<Owner> getAllOwners() throws RegException;

    void deleteOwner(OwnerId id) throws RegException;
    
    Collection<Car> getOwnersCars(int idOwner) throws RegException;
    
    Owner getOwner(int ownerId) throws RegException;

}
