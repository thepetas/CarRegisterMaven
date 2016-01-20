package business;

import java.util.logging.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;
import org.osgi.util.tracker.ServiceTracker;

public class BusinessActivator implements BundleActivator {

    private static final Logger LOG = Logger.getLogger(BusinessActivator.class.getName());

    @Override
    public void start(BundleContext context) throws Exception {
        LOG.info("");
        ServiceTracker<RegisterFacade, RegisterFacade> st
                = new ServiceTracker<>(context, RegisterFacade.class, null);
        st.open();
        RegisterFacade.setSt(st);
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        LOG.info("");
    }

}
