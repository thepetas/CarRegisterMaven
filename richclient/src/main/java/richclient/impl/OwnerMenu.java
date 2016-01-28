package richclient.impl;

import javafx.scene.control.Menu;
import utils.Messages;

public class OwnerMenu extends Menu {

    public OwnerMenu() {
        super(Messages.Owners.createMess());
        getItems().addAll(CreateOwnerAction.instance.genMenuItem(),
                DeleteOwnerAction.instance.genMenuItem());
    }

}
