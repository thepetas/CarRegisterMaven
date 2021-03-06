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
import richclient.ValidatedOwnerPanel;
import richclient.ValidatedTextField;
import utils.RegException;
import static utils.Messages.*;

public class CreateCarDialog extends AbstractCarDialog {

    private ValidatedTextField brand;
    private ValidatedTextField model;
    private ValidatedOwnerPanel ownerPanel;

    public CreateCarDialog() {
        super(Create_car.createMess());
    }

    @Override
    protected Node createContent() {
        GridPane gp = new GridPane();
        gp.setVgap(5);
        gp.setHgap(5);
        gp.setPadding(new Insets(5));
        gp.add(new Label(Brand.createMess() + ":"), 0, 0);
        gp.add(brand = new ValidatedTextField(this), 1, 0);
        gp.add(new Label(Model.createMess() + ":"), 0, 1);
        gp.add(model = new ValidatedTextField(this), 1, 1);
        gp.add(ownerPanel = new ValidatedOwnerPanel(this), 1, 2);
        return gp;
    }

    protected void ok() {
        try {
            RegisterFacade.getService().createCar(brand.getText(), model.getText(), ownerPanel.getSelectedOwner().getId().getId());
            PersistentDateState.instance.dateChanged();
        } catch (RegException ex) {
            Logger.getLogger(CreateCarDialog.class.getName()).log(Level.SEVERE, null, ex);
            MyAlert.error(ex.toString());
        }
    }

    public void validate() {
        boolean error = false;
        getDialogPane().lookupButton(ButtonType.OK).setDisable(false);

        if (brand.getText().isEmpty()) {
            errorPanel.setError(Empty_brand.createMess());
            error = true;
        }

        if (model.getText().isEmpty()) {
            errorPanel.setError(Empty_model.createMess());
            error = true;
        }
        
        if (ownerPanel.isEmpty()){
            errorPanel.setError(No_selected_owner.createMess());
            error = true;
        }

        if (!error) {
            errorPanel.clearError();
        }
        getDialogPane().lookupButton(ButtonType.OK).setDisable(error);

    }

}
