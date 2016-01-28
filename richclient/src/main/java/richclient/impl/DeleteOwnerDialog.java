package richclient.impl;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.Node;
import javafx.scene.control.ListView;
import javafx.scene.control.MultipleSelectionModel;
import business.RegisterFacade;
import model.owner.Owner;
import richclient.AbstractCarDialog;
import richclient.ActionsState;
import richclient.MainWindow;
import richclient.MyAlert;
import richclient.PersistentDateState;
import utils.RegException;
import utils.Messages;

public class DeleteOwnerDialog extends AbstractCarDialog {

    private static final Logger LOG = Logger.getLogger(DeleteOwnerDialog.class.getName());

    public DeleteOwnerDialog() {
        super(Messages.Delete_owner.createMess());
    }

    @Override
    protected Node createContent() {
        ListView<Owner> lv = new ListView<>(MainWindow.instance.getOwnerPanel().selectedOwners());
        lv.setSelectionModel(new MultipleSelectionModel<Owner>() {
            
             {
                super.setSelectedIndex(-1);
                super.setSelectedItem(null);
            }

            @Override
            public ObservableList<Integer> getSelectedIndices() {
             return FXCollections.emptyObservableList();
            }

            @Override
            public ObservableList<Owner> getSelectedItems() {
                return FXCollections.emptyObservableList();
            }

            @Override
            public void selectIndices(int index, int... indices) {
            }

            @Override
            public void selectAll() {
            }

            @Override
            public void selectFirst() {
            }

            @Override
            public void selectLast() {
            }

            @Override
            public void clearAndSelect(int index) {
            }

            @Override
            public void select(int index) {
            }

            @Override
            public void select(Owner obj) {
            }

            @Override
            public void clearSelection(int index) {
            }

            @Override
            public void clearSelection() {
            }

            @Override
            public boolean isSelected(int index) {
                return false;
            }

            @Override
            public boolean isEmpty() {
                return true;
            }

            @Override
            public void selectPrevious() {
            }

            @Override
            public void selectNext() {
            }
        });

        return lv;
    }

    @Override
    protected void ok() {
        try {
            ArrayList<Owner> ow = new ArrayList<>(MainWindow.instance.getOwnerPanel().selectedOwners());
            for (Owner ow1 : ow) {
                RegisterFacade.getService().deleteCars(RegisterFacade.getService().getOwnersCars(ow1.getId().getId()));
            }
            RegisterFacade.getService().deleteOwners(ow);
            PersistentDateState.instance.dateChanged();
            ActionsState.instance.dateChanged();
        } catch (RegException ex) {
            LOG.log(Level.SEVERE, null, ex);
            MyAlert.error(ex);
        }
    }

    @Override
    public void validate() {
    }

}
