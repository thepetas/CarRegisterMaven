package richclient;

import richclient.impl.FilesMenu;
import richclient.impl.CarMenu;
import javafx.scene.control.MenuBar;

public class RegMenuBar extends MenuBar {

    public RegMenuBar() {
        getMenus().addAll(new FilesMenu(), new CarMenu());
    }
    
}
