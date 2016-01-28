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
import integration.RegDAOFactory;
import integration.RegDAO;

public class DerbyDBDAOFactory extends RegDAOFactory {

    Connection conn;
    private static final Logger LOG = Logger.getLogger(DerbyDBDAOFactory.class.getName());

    public DerbyDBDAOFactory() {
        try {
            new EmbeddedDriver();
            String url = "jdbc:derby:" + System.getProperty("user.home") + "/.netbeans-derby/regDB; create=true";
            conn = DriverManager.getConnection(url);
            DatabaseMetaData dbmd = conn.getMetaData();

            ResultSet rs2 = dbmd.getTables(null, null, "OWNERS", null);
            if (!rs2.next()) {
                LOG.info("CREATE TABLE OWNERS");
                Statement stm = conn.createStatement();
                stm.executeUpdate("CREATE TABLE OWNERS"
                        + "(ID_OWNER INT NOT NULL GENERATED ALWAYS AS IDENTITY,"
                        + "NAME   VARCHAR(50),"
                        + "SURNAME   VARCHAR(50),"
                        + "PRIMARY KEY (ID_OWNER))");
            }
            else
                System.out.println("---------------------------------------------------------------------------------");

            ResultSet rs = dbmd.getTables(null, null, "CARS", null);
            if (!rs.next()) {
                LOG.info("CREATE TABLE CARS");
                Statement stm = conn.createStatement();
                stm.executeUpdate("CREATE TABLE CARS"
                        + "(ID_CAR INT NOT NULL GENERATED ALWAYS AS IDENTITY,"
                        + "ID_OWNER INT,"
                        + "BRAND   VARCHAR(50),"
                        + "MODEL   VARCHAR(50),"
                        + "FOREIGN KEY (ID_OWNER) REFERENCES OWNERS (ID_OWNER),"
                        + "PRIMARY KEY (ID_CAR))");

            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public RegDAO getRegDAO() {
        return new DerbyDBRegDAO(conn);
    }

}
