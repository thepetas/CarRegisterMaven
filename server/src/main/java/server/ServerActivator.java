package server;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.logging.Logger;
import org.osgi.framework.BundleActivator;
import org.osgi.framework.BundleContext;

public class ServerActivator implements BundleActivator {

    private static final Logger LOG = Logger.getLogger(ServerActivator.class.getName());

    @Override
    public void start(BundleContext context) throws Exception {
        LOG.info("");
        ExecutorService es = Executors.newCachedThreadPool();
        es.execute(new ServerTask(es));
    }

    @Override
    public void stop(BundleContext context) throws Exception {
        LOG.info("");
    }

}
