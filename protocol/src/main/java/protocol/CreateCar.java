package protocol;

import business.RegisterFacade;
import utils.RegException;

public class CreateCar extends Command {

    private final String brand;
    private final String model;
    private final int idOwner;

    public CreateCar(String brand, String model, int idOwner) {
        this.brand = brand;
        this.model = model;
        this.idOwner = idOwner;
    }

    @Override
    public Object execute(RegisterFacade f) throws RegException {
        f.createCar(brand, model, idOwner);
        return OK;
    }

}
