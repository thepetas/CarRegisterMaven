package richclient.impl;

import javafx.scene.control.Menu;
import utils.Messages;

public class CarMenu extends Menu {

    public CarMenu() {
        super(Messages.Cars.createMess());
        getItems().addAll(CreateCarAction.instance.genMenuItem(),
                DeleteCarAction.instance.genMenuItem());
    }

}
