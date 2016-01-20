package utils;

import java.util.logging.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class UtilsActivator implements BundleActivator {

    Logger log = Logger.getLogger(UtilsActivator.class.getName());

    public void start(BundleContext context) throws Exception {
        log.info("");
    }

    public void stop(BundleContext context) throws Exception {
        log.info("");
    }

}
