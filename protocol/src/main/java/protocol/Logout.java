package protocol;

import business.RegisterFacade;
import utils.RegException;

public class Logout extends Command {

    @Override
    public Object execute(RegisterFacade f) throws RegException {
        throw new RuntimeException();
    }

}
