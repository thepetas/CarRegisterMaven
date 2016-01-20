package richclient;

import richclient.impl.CarPanel;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.MenuBar;
import javafx.scene.control.SplitPane;
import javafx.scene.control.ToolBar;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import richclient.impl.CarMenu;
import richclient.impl.CreateCarAction;
import richclient.impl.DeleteCarAction;
import richclient.impl.FilesMenu;
import richclient.impl.RefreshCarsAction;
import utils.Messages;
import org.osgi.framework.Bundle;
import org.osgi.framework.BundleContext;
import org.osgi.framework.BundleException;
import org.osgi.framework.launch.Framework;

public class MainWindow extends Stage {

    public static MainWindow instance = new MainWindow();

    private BundleContext context;
    private final MenuBar regMenuBar;
    private final ToolBar regToolBar;
    private final CarPanel carPanel;

    public void stop() {
        Bundle sb = context.getBundle(0);
        Framework f = (Framework) sb;
        try {
            f.stop(1000);
        } catch (BundleException ex) {
            Logger.getLogger(MainWindow.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    private MainWindow() {
        setTitle(Messages.Register.createMess());
        setOnCloseRequest(new EventHandler<WindowEvent>() {

            @Override
            public void handle(WindowEvent event) {
                stop();
            }
        });
        regMenuBar = new MenuBar(new FilesMenu(), new CarMenu());
        regToolBar = new ToolBar(
                CreateCarAction.instance.genButton(),
                DeleteCarAction.instance.genButton(),
                RefreshCarsAction.instance.genButton()
        );
        carPanel = new CarPanel();
        VBox root = new VBox(regMenuBar, getRegToolBar(),
                new SplitPane(carPanel));
        Scene s = new Scene(root, 800, 600);
        setScene(s);
        show();
    }

    public void setContext(BundleContext context) {
        this.context = context;
    }

    public MenuBar getRegMenuBar() {
        return regMenuBar;
    }

    public ToolBar getRegToolBar() {
        return regToolBar;
    }

    public CarPanel getCarPanel() {
        return carPanel;
    }

    public void refresh() {

    }

}
