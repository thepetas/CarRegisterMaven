package richclient.impl;

import business.RegisterFacade;
import richclient.AbstractCarAction;
import utils.Messages;

public class CreateCarAction extends AbstractCarAction {

    public static CreateCarAction instance = new CreateCarAction();

    private CreateCarAction() {
        super(Messages.Create_Car.createMess());
    }

    @Override
    public void execute() {
        new CreateCarDialog().execute();
    }

    @Override
    public boolean testDisable() {
       return !RegisterFacade.getService().isAvailable();
    }

}
