package richclient;

import java.util.Observable;

public class ActionsState extends Observable {

    public static ActionsState instance = new ActionsState();

    public void dateChanged() {
        this.setChanged();
        this.notifyObservers();
    }

}
