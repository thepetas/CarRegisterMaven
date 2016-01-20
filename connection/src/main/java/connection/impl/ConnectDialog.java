package connection.impl;

import java.io.IOException;
import java.net.InetAddress;
import java.net.UnknownHostException;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import connection.ConnectionActivator;
import richclient.AbstractCarDialog;
import richclient.ActionsState;
import richclient.MyAlert;
import richclient.PersistentDateState;
import richclient.ValidatedTextField;
import utils.Messages;
import static utils.Messages.*;

public class ConnectDialog extends AbstractCarDialog {

    private ValidatedTextField host;
    private ValidatedTextField port;

    public ConnectDialog() {
        super(Connect.createMess());
    }

    @Override
    protected Node createContent() {
        GridPane gp = new GridPane();
        gp.setVgap(5);
        gp.setHgap(5);
        gp.setPadding(new Insets(5));
        gp.add(new Label(Host.createMess()), 0, 0);
        gp.add(host = new ValidatedTextField(this, "localhost"), 1, 0);
        gp.add(new Label(Port.createMess()), 0, 1);
        gp.add(port = new ValidatedTextField(this, "8000"), 1, 1);
        return gp;
    }

    @Override
    protected void ok() {
        try {
            connection.Connection.instance.connect(InetAddress.getByName(host.getText()),
                    Integer.parseInt(port.getText()));
            PersistentDateState.instance.dateChanged();
            ActionsState.instance.dateChanged();
            ConnectionActivator.status.setIsConnected(connection.Connection.instance.getPort());
        } catch (IOException ex) {
            MyAlert.error(Messages.No_connection.createMess());
        }

    }

    @Override
    public void validate() {
        boolean error = false;
        try {
            Integer.parseInt(port.getText());
        } catch (NumberFormatException ex) {
            error = true;
            errorPanel.setError(Invalid_port.createMess());
        }
        try {
            InetAddress.getByName(host.getText());
        } catch (UnknownHostException ex) {
            error = true;
            errorPanel.setError(Invalid_host.createMess());
        }
        if (!error) {
            errorPanel.clearError();
        }
        getDialogPane().lookupButton(ButtonType.OK).setDisable(error);
    }

}
