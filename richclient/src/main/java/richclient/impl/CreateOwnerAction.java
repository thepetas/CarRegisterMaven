package richclient.impl;

import business.RegisterFacade;
import richclient.AbstractCarAction;
import utils.Messages;

public class CreateOwnerAction extends AbstractCarAction {

    public static CreateOwnerAction instance = new CreateOwnerAction();

    private CreateOwnerAction() {
        super(Messages.Create_owner.createMess());
    }

    @Override
    public void execute() {
        new CreateOwnerDialog().execute();
    }

    @Override
    public boolean testDisable() {
       return !RegisterFacade.getService().isAvailable();
    }

}
