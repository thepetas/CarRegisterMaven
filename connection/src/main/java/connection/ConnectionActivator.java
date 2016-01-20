package connection;

import java.util.logging.Logger;
import javafx.application.Platform;
import connection.impl.ConnectAction;
import connection.impl.ConnectionMenu;
import connection.impl.ConnectionStatus;
import connection.impl.DisconnectAction;
import richclient.ActionsState;
import richclient.MainWindow;
import utils.RegException;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class ConnectionActivator implements BundleActivator {

    private static final Logger LOG = Logger.getLogger(ConnectionActivator.class.getName());
    public static ConnectionStatus status = new ConnectionStatus();

    @Override
    public void start(BundleContext context) throws Exception {
        LOG.info("");
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                MainWindow.instance.getRegMenuBar().getMenus().add(new ConnectionMenu());
                MainWindow.instance.getRegToolBar().getItems().addAll(
                        ConnectAction.instance.genButton(),
                        DisconnectAction.instance.genButton(),
                        status
                );
                ActionsState.instance.dateChanged();
            }
        });
    }

    @Override
    public void stop(BundleContext context) throws RegException {
        LOG.info("");
        Connection.instance.disconnect();
    }

}
