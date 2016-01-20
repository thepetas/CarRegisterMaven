package protocol;

import business.RegisterFacade;
import utils.RegException;

public class CreateCar extends Command {

    private final String brand;
    private final String model;
    private final String owner;

    public CreateCar(String brand, String model, String owner) {
        this.brand = brand;
        this.model = model;
        this.owner = owner;
    }

    @Override
    public Object execute(RegisterFacade f) throws RegException {
        f.createCar(brand, model, owner);
        return OK;
    }

}
