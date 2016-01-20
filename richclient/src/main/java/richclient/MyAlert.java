package richclient;

import javafx.scene.control.Alert;

public class MyAlert {

    public static void error(Exception ex) {
        error(ex.toString());
    }

    public static void error(String mess) {
        Alert a = new Alert(Alert.AlertType.ERROR);
        a.setTitle(utils.Messages.Error.createMess());
        a.setContentText(mess);
        a.setHeaderText(utils.Messages.Error.createMess());
        a.showAndWait();
    }
}
