package integration.impl;

import integration.DAOFactory;
import integration.CarDAO;

public class DefaultDAOFactory extends DAOFactory {

    private DefaultCarDAO defaultCarDAO;

    @Override
    public CarDAO getCarDAO() {
        if (defaultCarDAO == null) {
            defaultCarDAO = new DefaultCarDAO();
        }
        return defaultCarDAO;

    }

}
