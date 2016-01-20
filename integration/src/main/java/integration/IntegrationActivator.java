package integration;

import java.util.logging.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class IntegrationActivator implements BundleActivator {

    static final Logger log = Logger.getLogger(IntegrationActivator.class.getName());

    @Override
    public void start(BundleContext context) throws Exception {
        log.info("");
        ServiceTracker<DAOFactory, DAOFactory> st
                = new ServiceTracker<>(context, DAOFactory.class.getName(), null);
        st.open();  // !!!!!!!!
        DAOFactory.setSt(st);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        log.info("");
    }

}
