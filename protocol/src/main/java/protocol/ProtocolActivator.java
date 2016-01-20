package protocol;

import java.util.logging.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class ProtocolActivator implements BundleActivator {

    private static final Logger LOG = Logger.getLogger(ProtocolActivator.class.getName());

    @Override
    public void start(BundleContext context) throws Exception {
        LOG.info("");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        LOG.info("");
    }

}
