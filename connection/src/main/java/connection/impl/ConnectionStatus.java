package connection.impl;

import javafx.scene.control.Label;
import javafx.scene.paint.Color;
import utils.Messages;

public class ConnectionStatus extends Label {

    public ConnectionStatus() {
        setIsDisconnected();
    }

    public final void setIsConnected(int port) {
        setText(Messages.Connected.createMess() + "(port:" + String.valueOf(port) + ")");
        setTextFill(Color.GREEN);
    }

    public final void setIsDisconnected() {
        setText(Messages.Disconnected.createMess());
        setTextFill(Color.RED);
    }
}
