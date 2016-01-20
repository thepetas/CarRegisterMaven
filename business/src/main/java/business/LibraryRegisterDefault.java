package business;

import java.util.Collection;
import integration.DAOFactory;
import model.Car;
import utils.RegException;

public class LibraryRegisterDefault extends RegisterFacade {

    @Override
    public void createCar(String brand, String model, String owner) throws RegException {
        DAOFactory.service().getCarDAO().create(brand, model, owner);
    }

    @Override
    public Collection<Car> getAllCars() throws RegException {
        return DAOFactory.service().getCarDAO().getAll();
    }

    @Override
    public boolean isAvailable() {
        return true;
    }

    @Override
    public void deleteCars(Collection<Car> cars) throws RegException {
        for (Car car : cars) {
            DAOFactory.service().getCarDAO().delete(car.getId());
        }
    }

}
