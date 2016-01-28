package richclient;

import javafx.beans.value.ChangeListener;
import javafx.beans.value.ObservableValue;
import javafx.scene.control.SelectionMode;
import model.owner.Owner;
import richclient.impl.OwnerPanel;

public class ValidatedOwnerPanel extends OwnerPanel {

    public ValidatedOwnerPanel(AbstractCarDialog createCarDialog) {
        this(createCarDialog, "");
        getTable().getSelectionModel().setSelectionMode(SelectionMode.SINGLE);
    }

    public ValidatedOwnerPanel(AbstractCarDialog createCarDialog, String text) {
        super();
        getTable().getSelectionModel().selectedItemProperty().addListener(new ChangeListener<Owner>() {

            @Override
            public void changed(ObservableValue<? extends Owner> observable, Owner oldValue, Owner newValue) {
                createCarDialog.validate();
            }
        });
    }
    
    public boolean isEmpty(){
        return getTable().getSelectionModel().isEmpty();
    }
    
    public Owner getSelectedOwner(){
        return getTable().getSelectionModel().getSelectedItem();
    }

}
