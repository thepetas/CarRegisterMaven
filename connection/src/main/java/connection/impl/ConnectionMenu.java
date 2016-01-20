package connection.impl;

import javafx.scene.control.Menu;
import utils.Messages;

public class ConnectionMenu extends Menu {

    public ConnectionMenu() {
        super(Messages.Connection.createMess());
        this.getItems().addAll(ConnectAction.instance.genMenuItem(),
                DisconnectAction.instance.genMenuItem()
        );
    }

}
