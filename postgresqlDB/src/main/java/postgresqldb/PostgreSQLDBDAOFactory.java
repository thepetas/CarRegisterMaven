package postgresqldb;

import java.sql.Connection;
import java.sql.DatabaseMetaData;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.logging.Level;
import java.util.logging.Logger;
import integration.CarDAO;
import integration.DAOFactory;
import org.postgresql.*;

public class PostgreSQLDBDAOFactory extends DAOFactory {

    Connection conn = null;
    private static final Logger LOG = Logger.getLogger(PostgreSQLDBDAOFactory.class.getName());

    public PostgreSQLDBDAOFactory() throws ClassNotFoundException {
        try {
            Class.forName("org.postgresql.Driver");
            String url = "jdbc:postgresql://localhost:5432/CarsAPJ";
            String user  = "petr";
            String password = "pass";
            conn = DriverManager.getConnection(url, user, password);
            DatabaseMetaData dmbd = conn.getMetaData();
            ResultSet rs = dmbd.getTables(null, null, "CARS", null);
            if (!rs.next()) {
                LOG.info("CREATE TABLE CARS");
                Statement stm = conn.createStatement();
                String sql = "CREATE TABLE CARS"
                            + "(ID INT NOT NULL GENERATED ALWAYS AS IDENTITY,"
                            + "BRAND   VARCHAR(50),"
                            + "MODEL  VARCHAR(50),"
                            + "OWNER   VARCHAR(50),"
                            + "PRIMARY KEY (ID))";
                stm.executeUpdate(sql);

            }

        } catch (SQLException ex) {
            LOG.log(Level.SEVERE, null, ex);
        } catch (Exception ex)
        {
            LOG.log(Level.SEVERE, ex.getClass().getName() +  ": " + ex.getMessage());
        }

    }

    @Override
    public CarDAO getCarDAO() {
        return new PostgreSQLCarDAO(conn);
    }

}
