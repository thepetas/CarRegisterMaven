package richclient.impl;

import business.RegisterFacade;
import richclient.AbstractCarAction;
import richclient.MainWindow;
import utils.Messages;

public class DeleteCarAction extends AbstractCarAction {

    public static DeleteCarAction instance = new DeleteCarAction();

    private DeleteCarAction() {
        super(Messages.Delete_car.createMess());
    }

    @Override
    public boolean testDisable() {
        return MainWindow.instance.getCarPanel().selectedCars().isEmpty()
                || !RegisterFacade.getService().isAvailable();
    }

    @Override
    public void execute() {
        new DeleteCarDialog().execute();
    }

}
