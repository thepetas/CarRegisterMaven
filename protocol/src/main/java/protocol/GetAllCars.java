package protocol;

import java.util.Collection;
import business.RegisterFacade;
import model.car.Car;
import utils.RegException;

public class GetAllCars extends Command {


    public GetAllCars() {
    }

    @Override
    public Collection<Car> execute(RegisterFacade f) throws RegException {
        return f.getAllCars();
    }

}
