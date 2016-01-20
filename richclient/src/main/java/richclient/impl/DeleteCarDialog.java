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
import model.Car;
import richclient.AbstractCarDialog;
import richclient.ActionsState;
import richclient.MainWindow;
import richclient.MyAlert;
import richclient.PersistentDateState;
import utils.RegException;
import utils.Messages;

public class DeleteCarDialog extends AbstractCarDialog {

    private static final Logger LOG = Logger.getLogger(DeleteCarDialog.class.getName());

    public DeleteCarDialog() {
        super(Messages.Delete_Car.createMess());
    }

    @Override
    protected Node createContent() {
        ListView<Car> lv = new ListView<>(MainWindow.instance.getCarPanel().selectedCars());
        lv.setSelectionModel(new MultipleSelectionModel<Car>() {

            {
                super.setSelectedIndex(-1);
                super.setSelectedItem(null);
            }

            @Override
            public ObservableList<Integer> getSelectedIndices() {
                return FXCollections.emptyObservableList();
            }

            @Override
            public ObservableList<Car> getSelectedItems() {
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
            public void clearAndSelect(int i) {
            }

            @Override
            public void select(int i) {
            }

            @Override
            public void select(Car t) {
            }

            @Override
            public void clearSelection(int i) {
            }

            @Override
            public void clearSelection() {
            }

            @Override
            public boolean isSelected(int i) {
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
            RegisterFacade.getService().deleteCars(
                    new ArrayList(MainWindow.instance.getCarPanel().selectedCars()));
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
