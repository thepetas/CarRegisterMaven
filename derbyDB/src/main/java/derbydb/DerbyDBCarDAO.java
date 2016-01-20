package derbydb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import integration.CarDAO;
import model.Car;
import model.CarId;
import utils.RegException;

public class DerbyDBCarDAO implements CarDAO {

    private PreparedStatement getAllPS;
    private PreparedStatement createCarPS;
    private PreparedStatement deleteCarsPS;

    public DerbyDBCarDAO(Connection conn) {
        try {
            getAllPS = conn.prepareStatement("SELECT * FROM CARS");
            createCarPS = conn.prepareStatement("INSERT INTO CARS VALUES(DEFAULT, ?, ?, ?)");
            deleteCarsPS = conn.prepareStatement("DELETE FROM CARS WHERE ID = ?");
        } catch (SQLException ex) {
            Logger.getLogger(DerbyDBCarDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void create(String brand, String model, String owner) throws RegException {
        try {
            createCarPS.setString(1, brand);
            createCarPS.setString(2, model);
            createCarPS.setString(3, owner);
            createCarPS.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DerbyDBCarDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RegException(ex);
        }

    }

    @Override
    public Collection<Car> getAll() throws RegException {
        List<Car> cars = new ArrayList<>();
        try {

            ResultSet rs = getAllPS.executeQuery();
            while (rs.next()) {
                cars.add(new Car(new CarId(rs.getInt(1)), rs.getString(2),
                        rs.getString(3), rs.getString(4)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DerbyDBCarDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RegException(ex);
        }
        return cars;

    }

    @Override
    public void delete(CarId id) throws RegException {
        try {
            deleteCarsPS.setInt(1, id.getId());
            deleteCarsPS.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DerbyDBCarDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RegException(ex);
        }
    }

}
