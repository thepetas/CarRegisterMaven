package richclient;

import java.util.Optional;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Dialog;
import javafx.scene.control.Label;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import utils.Messages;

public abstract class AbstractCarDialog extends Dialog<ButtonType> {

    public static class ErrorPanel extends HBox {

        private final Label errorLab;
        private final Text errorText;

        public void setError(String err) {
            errorText.setText(err);
            errorLab.setVisible(true);
        }

        public void clearError() {
            errorText.setText("");
            errorLab.setVisible(false);
        }

        public ErrorPanel() {
            errorLab = new Label(Messages.Error.createMess() + ':');
            errorText = new Text();
            errorLab.setVisible(false);
            errorLab.setLabelFor(errorText);
            errorLab.setTextFill(Color.RED);

            getChildren().addAll(errorLab, errorText);
            setSpacing(5);
            errorText.setFill(Color.RED);

        }

    }

    protected ErrorPanel errorPanel = new ErrorPanel();

    public AbstractCarDialog(String title) {
        setTitle(title);
        getDialogPane().setContent(new VBox(createContent(),
                errorPanel));
        getDialogPane().getButtonTypes().addAll(ButtonType.OK, ButtonType.CANCEL);
        validate();
    }

    public AbstractCarDialog() {
    }

    protected abstract Node createContent();

    public void execute() {
        Optional<ButtonType> result = showAndWait();
        ButtonType bt = result.get();
        if (bt.getButtonData().isCancelButton()) {
            return;
        }
        ok();
    }

    protected abstract void ok();

    public abstract void validate();

}
