package integration;

public class DefaultDAOFactory extends RegDAOFactory {

    private DefaultDAO defaultCarDAO;

    @Override
    public RegDAO getRegDAO() {
        if (defaultCarDAO == null) {
            defaultCarDAO = new DefaultDAO();
        }
        return defaultCarDAO;

    }

}
