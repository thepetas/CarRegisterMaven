package integration;

import integration.impl.DefaultDAOFactory;
import org.osgi.util.tracker.ServiceTracker;

public abstract class DAOFactory {

    private static DAOFactory instance;
    private static ServiceTracker<DAOFactory, DAOFactory> st;

    public static DAOFactory service() {
        if (instance == null) {
            instance = st.getService();
            if (instance == null) {
                instance = new DefaultDAOFactory();
            }
        }
        return instance;
    }

    static void setSt(ServiceTracker<DAOFactory, DAOFactory> stp) {
        st = stp;
    }

    public abstract CarDAO getCarDAO();

}
