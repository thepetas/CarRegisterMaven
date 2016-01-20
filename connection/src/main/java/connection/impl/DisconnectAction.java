package connection.impl;

import connection.Connection;
import connection.ConnectionActivator;
import richclient.AbstractCarAction;
import richclient.ActionsState;
import richclient.MyAlert;
import utils.RegException;
import utils.Messages;

public class DisconnectAction extends AbstractCarAction {

    public static final DisconnectAction instance = new DisconnectAction();

    private DisconnectAction() {
        super(Messages.Disconnect.createMess());
    }

    @Override
    public void execute() {
        try {
            Connection.instance.disconnect();
            ConnectionActivator.status.setIsDisconnected();
        } catch (RegException ex) {
            MyAlert.error(ex);
        }
        ActionsState.instance.dateChanged();
    }

    @Override
    public boolean testDisable() {
        return !Connection.instance.isConnected();
    }

}
