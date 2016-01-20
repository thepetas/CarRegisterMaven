
package utils;

import java.text.MessageFormat;
import java.util.MissingResourceException;
import java.util.ResourceBundle;
import java.util.logging.Logger;

public enum Messages {

    Brand,
    Cars,
    Connect,
    Connected,
    Connection,
    Create_Car,
    Delete_Car,
    Disconnect,
    Disconnected,
    Empty_brand,
    Empty_model,
    Empty_owner,
    Error,
    Exit,
    File,
    Host,
    Id,
    Invalid_host,
    Invalid_port,
    Model,
    No_connection,
    Not_connected,
    Owner,
    Port,
    Refresh_Cars,
    Register;

    public String createMess(Object... args) {
        try {
            ResourceBundle rb = ResourceBundle.getBundle("utils.locale_cs_CZ");
            return MessageFormat.format(rb.getString(name()), args);
        } catch (MissingResourceException mrex) {
            Logger.getLogger(getClass().getName()).fine(mrex.toString());
            return name().replaceAll("_", " ");
        }
    }

}
