package derbydb;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import org.apache.derby.jdbc.EmbeddedDriver;
import integration.DAOFactory;
import integration.CarDAO;

public class DerbyDBDAOFactory extends DAOFactory {
    
    Connection conn;
    private static final Logger LOG = Logger.getLogger(DerbyDBDAOFactory.class.getName());
    
    public DerbyDBDAOFactory() {
        try {
            new EmbeddedDriver();
            String url = "jdbc:derby:" + System.getProperty("user.home") + "/APJ/registerDB; create=true";
            conn = DriverManager.getConnection(url);
            DatabaseMetaData dbmd = conn.getMetaData();
            ResultSet rs = dbmd.getTables(null, null, "CARS", null);
            if (!rs.next()) {
                LOG.info("CREATE TABLE CARS");
                Statement stm = conn.createStatement();
                stm.executeUpdate("CREATE TABLE CARS"
                        + "(ID INT NOT NULL GENERATED ALWAYS AS IDENTITY,"
                        + "BRAND   VARCHAR(50),"
                        + "MODEL   VARCHAR(50),"
                        + "OWNER  VARCHAR(50),"
                        + "PRIMARY KEY (ID))");
                
            }
        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }
    
    @Override
    public CarDAO getCarDAO() {
        return new DerbyDBCarDAO(conn);
    }
    
}
