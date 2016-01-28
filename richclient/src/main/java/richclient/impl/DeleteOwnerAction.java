package richclient.impl;

import business.RegisterFacade;
import richclient.AbstractCarAction;
import richclient.MainWindow;
import utils.Messages;

public class DeleteOwnerAction extends AbstractCarAction {

    public static DeleteOwnerAction instance = new DeleteOwnerAction();

    private DeleteOwnerAction() {
        super(Messages.Delete_owner.createMess());
    }

    @Override
    public boolean testDisable() {
        return MainWindow.instance.getOwnerPanel().selectedOwners().isEmpty()
                || !RegisterFacade.getService().isAvailable();
    }

    @Override
    public void execute() {
        new DeleteOwnerDialog().execute();
    }

}
