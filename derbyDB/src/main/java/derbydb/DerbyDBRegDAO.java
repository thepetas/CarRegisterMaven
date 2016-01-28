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
import integration.RegDAO;
import model.car.Car;
import model.car.CarId;
import model.owner.Owner;
import model.owner.OwnerId;
import utils.RegException;

public class DerbyDBRegDAO implements RegDAO {

    private PreparedStatement getAllCarsPS;
    private PreparedStatement createCarPS;
    private PreparedStatement deleteCarsPS;
    private PreparedStatement getOwnerPS;

    private PreparedStatement getAllOwnersPS;
    private PreparedStatement createOwnerPS;
    private PreparedStatement deleteOwnersPS;
    private PreparedStatement getCarsOfOwnerPS;

    public DerbyDBRegDAO(Connection conn) {
        try {
            getAllCarsPS = conn.prepareStatement("SELECT * FROM CARS");
            createCarPS = conn.prepareStatement("INSERT INTO CARS VALUES(DEFAULT, ?, ?, ?)");
            deleteCarsPS = conn.prepareStatement("DELETE FROM CARS WHERE ID_CAR = ?");
            getOwnerPS = conn.prepareStatement("SELECT * FROM OWNERS WHERE ID_OWNER = ?");

            getAllOwnersPS = conn.prepareCall("SELECT * FROM OWNERS");
            createOwnerPS = conn.prepareStatement("INSERT INTO OWNERS VALUES(DEFAULT, ?, ?)");
            deleteOwnersPS = conn.prepareStatement("DELETE FROM OWNERS WHERE ID_OWNER = ?");
            getCarsOfOwnerPS = conn.prepareStatement("SELECT * FROM CARS WHERE ID_OWNER = ?");

        } catch (SQLException ex) {
            Logger.getLogger(DerbyDBRegDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void createCar(String brand, String model, int idOwner) throws RegException {
        try {
            createCarPS.setInt(1, idOwner);
            createCarPS.setString(2, brand);
            createCarPS.setString(3, model);
            createCarPS.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DerbyDBRegDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RegException(ex);
        }
    }

    @Override
    public Collection<Car> getAllCars() throws RegException {
        List<Car> cars = new ArrayList<>();
        try {

            ResultSet rs = getAllCarsPS.executeQuery();
            while (rs.next()) {
                cars.add(new Car(new CarId(rs.getInt(1)), rs.getString(3), rs.getString(4), rs.getInt(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DerbyDBRegDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RegException(ex);
        }
        return cars;
    }

    @Override
    public void deleteCar(CarId id) throws RegException {
        try {
            deleteCarsPS.setInt(1, id.getId());
            deleteCarsPS.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DerbyDBRegDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RegException(ex);
        }
    }

    @Override
    public void createOwner(String name, String surname) throws RegException {
        try {
            createOwnerPS.setString(1, name);
            createOwnerPS.setString(2, surname);
            createOwnerPS.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DerbyDBRegDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RegException(ex);
        }
    }

    @Override
    public Collection<Owner> getAllOwners() throws RegException {
        List<Owner> owners = new ArrayList<>();
        try {

            ResultSet rs = getAllOwnersPS.executeQuery();
            while (rs.next()) {
                owners.add(new Owner(new OwnerId(rs.getInt(1)), rs.getString(2),
                        rs.getString(3)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DerbyDBRegDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RegException(ex);
        }
        return owners;

    }

    @Override
    public void deleteOwner(OwnerId id) throws RegException {
        try {
            deleteOwnersPS.setInt(1, id.getId());
            deleteOwnersPS.executeUpdate();
        } catch (SQLException ex) {
            Logger.getLogger(DerbyDBRegDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RegException(ex);
        }
    }

    @Override
    public Collection<Car> getOwnersCars(int idOwner) throws RegException {

        List<Car> cars = new ArrayList<>();
        try {
            getCarsOfOwnerPS.setInt(1, idOwner);
            ResultSet rs = getCarsOfOwnerPS.executeQuery();
            while (rs.next()) {
                cars.add(new Car(new CarId(rs.getInt(1)),
                        rs.getString(3), rs.getString(4), rs.getInt(2)));
            }
        } catch (SQLException ex) {
            Logger.getLogger(DerbyDBRegDAO.class.getName()).log(Level.SEVERE, null, ex);
            throw new RegException(ex);
        }
        return cars;
    }

    @Override
    public Owner getOwner(int ownerId) throws RegException {
        Owner ow = null;
        try {
            getOwnerPS.setInt(1, ownerId);
            ResultSet rs = getOwnerPS.executeQuery();
            rs.next();
            ow = new Owner(new OwnerId(rs.getInt(1)),
                    rs.getString(2), rs.getString(3));
        } catch (SQLException ex) {
            Logger.getLogger(DerbyDBRegDAO.class.getName()).log(Level.SEVERE, null, ex);
        }
        return ow;
    }

}
