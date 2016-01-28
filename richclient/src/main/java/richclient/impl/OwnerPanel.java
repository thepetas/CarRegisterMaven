package richclient.impl;

import java.util.Collection;
import java.util.Observable;
import java.util.Observer;
import java.util.logging.Logger;
import javafx.collections.FXCollections;
import javafx.collections.ListChangeListener;
import javafx.collections.ObservableList;
import javafx.scene.control.SelectionMode;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TitledPane;
import javafx.scene.control.cell.PropertyValueFactory;
import business.RegisterFacade;
import model.owner.Owner;
import model.owner.OwnerId;
import richclient.ActionsState;
import richclient.MyAlert;
import richclient.PersistentDateState;
import static utils.Messages.*;
import utils.RegException;

public class OwnerPanel extends TitledPane implements Observer {

    private static final Logger LOG = Logger.getLogger(OwnerPanel.class.getName());

    ObservableList<Owner> owners = FXCollections.observableArrayList();
    private TableView<Owner> table;

    private TableView<Owner> createTable() {

        TableView<Owner> table = new TableView<Owner>();

        TableColumn<Owner, OwnerId> idCol
                = new TableColumn<>(Id.createMess());
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));

        TableColumn<Owner, String> nameCol
                = new TableColumn<>(Name.createMess());
        nameCol.setCellValueFactory(new PropertyValueFactory<>("name"));

        TableColumn<Owner, String> surnameCol
                = new TableColumn<>(Surname.createMess());
        surnameCol.setCellValueFactory(new PropertyValueFactory<>("surname"));

        table.getColumns().addAll(idCol, nameCol, surnameCol);
        table.setItems(owners);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        table.getSelectionModel().getSelectedItems().
                addListener(new ListChangeListener<Owner>() {

                    @Override
                    public void onChanged(ListChangeListener.Change<? extends Owner> c) {
                        ActionsState.instance.dateChanged();
                    }
                });

        return table;
    }

    public OwnerPanel() {
        super(Owners.createMess(), null);
        table = createTable();
        setContent(table);
        PersistentDateState.instance.addObserver(this);
        refresh();
    }

    public void refresh() {
        try {
            if (RegisterFacade.getService().isAvailable()) {
                Collection<Owner> callowners = RegisterFacade.getService().getAllOwners();
                owners.clear();
                owners.addAll(callowners);
            }
        } catch (RegException ex) {
            MyAlert.error(ex.toString());
        }
    }

    public void resetPanel() {
        owners.clear();
    }

    @Override
    public void update(Observable o, Object arg) {
        refresh();
    }

    public TableView<Owner> getTable() {
        return table;
    }

    ObservableList<Owner> selectedOwners() {
        return table.getSelectionModel().getSelectedItems();
    }

}
