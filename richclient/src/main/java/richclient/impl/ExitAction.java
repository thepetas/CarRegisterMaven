package richclient.impl;

import richclient.AbstractCarAction;
import richclient.MainWindow;
import utils.Messages;

public class ExitAction extends AbstractCarAction {

    public static ExitAction instance = new ExitAction();

    private ExitAction() {
        super(Messages.Exit.createMess());
    }

    @Override
    public void execute() {
        MainWindow.instance.stop();
    }

}
