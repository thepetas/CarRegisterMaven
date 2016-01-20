package richclient;

import java.util.Observable;

public class PersistentDateState extends Observable {

    public static PersistentDateState instance = new PersistentDateState();
    
    public void dateChanged() {
        this.setChanged();
        this.notifyObservers();
    }

}
