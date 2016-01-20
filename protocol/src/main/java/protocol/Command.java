package protocol;

import java.io.Serializable;
import business.RegisterFacade;
import utils.RegException;

public abstract class Command implements Serializable {
    
    public static final String OK = "ok";
    
    public abstract Object execute(RegisterFacade f) throws RegException;
    
}
