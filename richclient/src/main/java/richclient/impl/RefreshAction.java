package richclient.impl;

import javafx.scene.image.Image;
import business.RegisterFacade;
import richclient.AbstractCarAction;
import richclient.PersistentDateState;
import utils.Messages;

public class RefreshAction extends AbstractCarAction {

    public static RefreshAction instance = new RefreshAction();

    private RefreshAction() {
        super(Messages.Refresh.createMess());
        Image im = new Image("https://mobileroadie.zendesk.com/hc/en-us/article_attachments/200564115/Refresh.png");
        setImageView(im);
    }

    @Override
    public void execute() {
        PersistentDateState.instance.dateChanged();
    }
    
    @Override
    public boolean testDisable() {
       return !RegisterFacade.getService().isAvailable();
    }

}
