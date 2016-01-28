package proxy;

import protocol.DeleteCars;
import java.util.Collection;
import business.RegisterFacade;
import connection.Connection;
import protocol.CreateCar;
import protocol.GetAllCars;
import utils.RegException;
import static connection.Connection.instance;
import model.car.Car;
import model.owner.Owner;

public class RegisterFacadeProxy extends RegisterFacade {

    @Override
    public void createCar(String brand, String model, int idOwner) throws RegException {
        instance.send(new CreateCar(brand, model, idOwner));
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

    @Override
    public void createOwner(String name, String surname) throws RegException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Owner> getAllOwners() throws RegException {
        return null;
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public void deleteOwners(Collection<Owner> owners) throws RegException {
//        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Collection<Car> getOwnersCars(int idOwner) throws RegException {
        throw new UnsupportedOperationException("Not supported yet."); //To change body of generated methods, choose Tools | Templates.
    }

    @Override
    public Owner getOwner(int ownerId) throws RegException {
        return null;
    }

}
