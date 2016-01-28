package postgresqldb;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import integration.RegDAO;
import model.car.Car;
import model.car.CarId;
import utils.RegException;

public class PostgreSQLCarDAO implements RegDAO {
    
    private PreparedStatement getAllPS;
    private PreparedStatement createBookPS;
    private PreparedStatement deleteBookPS;

    public PostgreSQLCarDAO(Connection conn) {
        try {
            getAllPS = conn.prepareStatement("SELECT * FROM CARS");
            createBookPS = conn.prepareStatement("INSERT INTO CARS VALUES(DEFAULT, ?, ?)");
//            deleteBookPS = conn.prepareStatement("DELETE BOOKS VALUES(DEFAULT, ?, ?)");
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLCarDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public void create(String brand, String model, String owner) throws RegException {
        try {
            createBookPS.setString(1, brand);
            createBookPS.setString(2, model);
            createBookPS.setString(3, owner);
            createBookPS.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(PostgreSQLCarDAO.class.getName()).log(Level.SEVERE, null, ex);
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
            Logger.getLogger(PostgreSQLCarDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RegException(ex);
        }
        return cars;
    }

    @Override
    public void delete(CarId id) throws RegException {
//        try {
//            deleteBookPS.setInt(1, id.getId());
//        } catch (SQLException ex) {
//            Logger.getLogger(PostgreSQLCarDAO.class.getName()).log(Level.SEVERE, null, ex);
//            throw new RegException(ex);
//        }
    }
    
}
