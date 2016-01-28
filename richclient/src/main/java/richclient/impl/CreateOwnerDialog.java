package richclient.impl;

import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.geometry.Insets;
import javafx.scene.Node;
import javafx.scene.control.ButtonType;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import business.RegisterFacade;
import richclient.AbstractCarDialog;
import richclient.MyAlert;
import richclient.PersistentDateState;
import richclient.ValidatedTextField;
import utils.RegException;
import static utils.Messages.*;

public class CreateOwnerDialog extends AbstractCarDialog {
    
    private ValidatedTextField name;
    private ValidatedTextField surname;
    
    public CreateOwnerDialog() {
        super(Create_car.createMess());
    }
    
    @Override
    protected Node createContent() {
        GridPane gp = new GridPane();
        gp.setVgap(5);
        gp.setHgap(5);
        gp.setPadding(new Insets(5));
        gp.add(new Label(Name.createMess() + ":"), 0, 0);
        gp.add(name = new ValidatedTextField(this), 1, 0);
        gp.add(new Label(Surname.createMess() + ":"), 0, 1);
        gp.add(surname = new ValidatedTextField(this), 1, 1);
        return gp;
    }
    
    protected void ok() {
        try {
            RegisterFacade.getService().createOwner(name.getText(), surname.getText());
            PersistentDateState.instance.dateChanged();
        } catch (RegException ex) {
            Logger.getLogger(CreateOwnerDialog.class.getName()).log(Level.SEVERE, null, ex);
            MyAlert.error(ex.toString());
        }
    }
    
    public void validate() {
        boolean error = false;
        getDialogPane().lookupButton(ButtonType.OK).setDisable(false);
        
        if (name.getText().isEmpty()) {
            errorPanel.setError(Empty_name.createMess());
            error = true;
        }
        
        if (surname.getText().isEmpty()) {
             errorPanel.setError(Empty_surname.createMess());
            error = true;
        }
        
        if (!error) {
            errorPanel.clearError();
        }
        getDialogPane().lookupButton(ButtonType.OK).setDisable(error);
        
    }
    
}
