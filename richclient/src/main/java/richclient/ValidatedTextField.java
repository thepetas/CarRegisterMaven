package richclient;

import javafx.event.EventHandler;
import javafx.scene.control.TextField;
import javafx.scene.input.KeyEvent;

public class ValidatedTextField extends TextField {

    public ValidatedTextField(AbstractCarDialog createCarDialog) {
        this(createCarDialog, "");
    }

    public ValidatedTextField(AbstractCarDialog createCarDialog, String text) {
        super(text);
        setOnKeyReleased(new EventHandler<KeyEvent>() {
            @Override
            public void handle(KeyEvent event) {
                createCarDialog.validate();
            }
        });
    }

}
