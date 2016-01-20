package model;

import java.util.logging.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class ModelActivator implements BundleActivator {

    private static final Logger LOG = Logger.getLogger(ModelActivator.class.getName());

    @Override
    public void start(BundleContext context) throws Exception {
        LOG.info("");
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        LOG.info("");
    }

}
