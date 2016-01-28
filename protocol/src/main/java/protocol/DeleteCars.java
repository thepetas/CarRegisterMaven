package protocol;

import java.util.Collection;
import business.RegisterFacade;
import model.car.Car;
import utils.RegException;

public class DeleteCars extends Command {

    private final Collection<Car> cars;

    public DeleteCars(Collection<Car> cars) {
        this.cars = cars;
    }

    @Override
    public String execute(RegisterFacade f) throws RegException {
        f.deleteCars(cars);
        return OK;
    }

}
