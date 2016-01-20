package proxy;

import protocol.DeleteCars;
import java.util.Collection;
import business.RegisterFacade;
import connection.Connection;
import model.Car;
import protocol.CreateCar;
import protocol.GetAllCars;
import utils.RegException;
import static connection.Connection.instance;

public class RegisterFacadeProxy extends RegisterFacade {

    @Override
    public void createCar(String brand, String model, String owner) throws RegException {
        instance.send(new CreateCar(brand, model, owner));
    }

    @Override
    public Collection<Car> getAllCars() throws RegException {
        return instance.send(new GetAllCars());
    }

    @Override
    public boolean isAvailable() {
        return Connection.instance.isConnected();
    }

    @Override
    public void deleteCars(Collection<Car> cars) throws RegException {
        instance.send(new DeleteCars(cars));
    }

}
