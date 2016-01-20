package business;

import java.util.Collection;
import model.Car;
import utils.RegException;
import org.osgi.util.tracker.ServiceTracker;

public abstract class RegisterFacade {

    private static RegisterFacade service;
    private static ServiceTracker<RegisterFacade, RegisterFacade> st;

    public static RegisterFacade getService() {
        if (service == null) {
            service = st.getService();
            if (service == null) {
                service = new LibraryRegisterDefault();
            }
        }
        return service;
    }

    public static void setSt(ServiceTracker<RegisterFacade, RegisterFacade> aSt) {
        st = aSt;
    }

    public abstract void createCar(String brand, String model, String owner) throws RegException;

    public abstract Collection<Car> getAllCars() throws RegException;

    public abstract boolean isAvailable();

    public abstract void deleteCars(Collection<Car> cars) throws RegException;
}
