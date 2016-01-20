package postgresqldb;

import java.util.logging.Logger;
import integration.DAOFactory;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class PostgreSQLDBActivator implements BundleActivator {
    
    private static final Logger LOG = Logger.getLogger(PostgreSQLDBActivator.class.getName());


    public void start(BundleContext context) throws Exception {
        LOG.info("");
        context.registerService(DAOFactory.class.getName(), new PostgreSQLDBDAOFactory(), null);
    }

    public void stop(BundleContext context) throws Exception {
        LOG.info("");
    }

}
