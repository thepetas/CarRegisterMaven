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
import model.Car;
import model.CarId;
import richclient.ActionsState;
import richclient.MyAlert;
import richclient.PersistentDateState;
import static utils.Messages.*;
import utils.RegException;

public class CarPanel extends TitledPane implements Observer {

    private static final Logger LOG = Logger.getLogger(CarPanel.class.getName());

    ObservableList<Car> cars = FXCollections.observableArrayList();
    private TableView<Car> table;

    private TableView<Car> createTable() {
        
        TableView<Car> table = new TableView<Car>();
        
        TableColumn<Car, CarId> idCol
                = new TableColumn<>(Id.createMess());
        idCol.setCellValueFactory(new PropertyValueFactory<>("id"));
        
        TableColumn<Car, CarId> brandCol
                = new TableColumn<>(Model.createMess());
        brandCol.setCellValueFactory(new PropertyValueFactory<>("brand"));
        
        TableColumn<Car, CarId> modelCol
                = new TableColumn<>(Brand.createMess());
        modelCol.setCellValueFactory(new PropertyValueFactory<>("model"));
        
        TableColumn<Car, CarId> ownerCol
                = new TableColumn<>(Owner.createMess());
        ownerCol.setCellValueFactory(new PropertyValueFactory<>("owner"));
        
        table.getColumns().addAll(idCol, brandCol, modelCol, ownerCol);
        table.setItems(cars);
        table.getSelectionModel().setSelectionMode(SelectionMode.MULTIPLE);
        table.getSelectionModel().getSelectedItems().
                addListener(new ListChangeListener<Car>() {
                    @Override
                    public void onChanged(ListChangeListener.Change<? extends Car> changed) {
                        ActionsState.instance.dateChanged();
                    }
                });
        
        return table;
    }

    public CarPanel() {
        super(Cars.createMess(), null);
        table = createTable();
        setContent(table);
        PersistentDateState.instance.addObserver(this);
        refresh();
    }

    public void refresh() {
        try {
            if (RegisterFacade.getService().isAvailable()) {
                Collection<Car> callcars = RegisterFacade.getService().getAllCars();
                cars.clear();
                cars.addAll(callcars);
            }
        } catch (RegException ex) {
            MyAlert.error(ex.toString());
        }
    }
    
    public void resetPanel(){
        cars.clear();
    }

    @Override
    public void update(Observable o, Object arg) {
        refresh();
    }

    public TableView<Car> getTable() {
        return table;
    }

    ObservableList<Car> selectedCars() {
        return table.getSelectionModel().getSelectedItems();
    }

}
