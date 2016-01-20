package richclient.impl;

import javafx.scene.control.Menu;
import utils.Messages;

public class FilesMenu extends Menu {

    public FilesMenu() {
        super(Messages.File.createMess());
        getItems().addAll(ExitAction.instance.genMenuItem());
    }

}
