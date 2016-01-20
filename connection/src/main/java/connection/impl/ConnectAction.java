package connection.impl;

import richclient.AbstractCarAction;
import static utils.Messages.*;

public class ConnectAction extends AbstractCarAction {

    public static final ConnectAction instance = new ConnectAction();

    private ConnectAction() {
        super(Connect.createMess());
    }

    @Override
    public void execute() {
        new ConnectDialog().execute();
    }

    @Override
    public boolean testDisable() {
        return connection.Connection.instance.isConnected();
    }

}
