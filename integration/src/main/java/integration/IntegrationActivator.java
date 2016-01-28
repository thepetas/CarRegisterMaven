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
        ServiceTracker<RegDAOFactory, RegDAOFactory> st
                = new ServiceTracker<>(context, RegDAOFactory.class.getName(), null);
        
       
        st.open();  // !!!!!!!!
        RegDAOFactory.setSt(st);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        log.info("");
    }

}
