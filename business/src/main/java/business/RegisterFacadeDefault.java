package business;

import java.util.Collection;
import integration.RegDAOFactory;
import model.owner.Owner;
import model.car.Car;
import utils.RegException;

public class RegisterFacadeDefault extends RegisterFacade {

    @Override
    public void createCar(String brand, String model, int idOwner) throws RegException {
        RegDAOFactory.service().getRegDAO().createCar(brand, model, idOwner);
    }

    @Override
    public Collection<Car> getAllCars() throws RegException {
        return RegDAOFactory.service().getRegDAO().getAllCars();
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public void deleteCars(Collection<Car> cars) throws RegException {
        for (Car car : cars) {
            RegDAOFactory.service().getRegDAO().deleteCar(car.getId());
        }
    }

    @Override
    public void createOwner(String name, String surname) throws RegException {
        RegDAOFactory.service().getRegDAO().createOwner(name, surname);
    }

    @Override
    public Collection<Owner> getAllOwners() throws RegException {
        return RegDAOFactory.service().getRegDAO().getAllOwners();
    }

    @Override
    public void deleteOwners(Collection<Owner> owners) throws RegException {
        for (Owner w : owners) {
            RegDAOFactory.service().getRegDAO().deleteOwner(w.getId());
        }
    }

    @Override
    public Collection<Car> getOwnersCars(int idOwner) throws RegException{
        return RegDAOFactory.service().getRegDAO().getOwnersCars(idOwner);
    }

    @Override
    public Owner getOwner(int ownerId) throws RegException {
        return RegDAOFactory.service().getRegDAO().getOwner(ownerId);
    }

}
