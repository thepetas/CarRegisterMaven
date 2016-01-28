package integration;

import org.osgi.util.tracker.ServiceTracker;

public abstract class RegDAOFactory {

    private static RegDAOFactory instance;
    private static ServiceTracker<RegDAOFactory, RegDAOFactory> st;

    public static RegDAOFactory service() {
        if (instance == null) {
            instance = st.getService();
            if (instance == null) {
                instance = new DefaultDAOFactory();
            }
        }
        return instance;
    }

    static void setSt(ServiceTracker<RegDAOFactory, RegDAOFactory> stp) {
        st = stp;
    }

    public abstract RegDAO getRegDAO();

}
