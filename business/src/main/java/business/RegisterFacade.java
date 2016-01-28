package business;

import model.owner.Owner;
import java.util.Collection;
import model.car.Car;
import utils.RegException;
import org.osgi.util.tracker.ServiceTracker;

public abstract class RegisterFacade {

    private static RegisterFacade service;
    private static ServiceTracker<RegisterFacade, RegisterFacade> st;

    public static RegisterFacade getService() {
        if (service == null) {
            service = st.getService();
            if (service == null) {
                service = new RegisterFacadeDefault();
            }
        }
        return service;
    }

    public static void setSt(ServiceTracker<RegisterFacade, RegisterFacade> aSt) {
        st = aSt;
    }

    public abstract void createCar(String brand, String model, int idOwner) throws RegException;

    public abstract Collection<Car> getAllCars() throws RegException;

    public abstract boolean isAvailable();

    public abstract void deleteCars(Collection<Car> cars) throws RegException;
    
    public abstract void createOwner(String name, String surname) throws RegException;
    public abstract Collection<Owner> getAllOwners() throws RegException;
    public abstract void deleteOwners(Collection<Owner> owners) throws RegException;
    public abstract Collection<Car> getOwnersCars(int idOwner) throws RegException;
    public abstract Owner getOwner(int ownerId) throws RegException;
    
    
}
