package richclient;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Observable;
import java.util.Observer;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.MenuItem;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import richclient.AbstractCarAction.Disable;

public abstract class AbstractCarAction implements Observer {

    interface Disable {

        public void setDisable(boolean b);
    }

    static class CarMenuItem extends MenuItem implements Disable {

        private CarMenuItem(String name) {
            super(name);
        }

    }

    static class CarButton extends Button implements Disable {

        private CarButton(String name) {
            super(name);
        }

        private CarButton(String name, ImageView iv) {
            super(name, iv);
            

        }

    }
    private final String name;
    private ImageView imageView;
    private final Collection<Disable> items = new ArrayList<>();

    public AbstractCarAction(String name) {
        this.name = name;
        this.imageView = null;
        ActionsState.instance.addObserver(this);
    }

    public void setImageView(Image im) {
        this.imageView = new ImageView(im);
    }

    public MenuItem genMenuItem() {
        CarMenuItem mi = new CarMenuItem(name);
        items.add(mi);
        mi.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                execute();
            }
        });
        return mi;
    }

    public Button genButton() {
        CarButton mi;
        if (imageView != null) {
            mi = new CarButton(name, imageView);
        } else {
            mi = new CarButton(name);
        }
        items.add(mi);
        mi.setOnAction(new EventHandler<ActionEvent>() {

            @Override
            public void handle(ActionEvent event) {
                execute();
            }
        });
        return mi;
    }

    public boolean testDisable() {
        return false;
    }

    @Override
    public void update(Observable o, Object arg) {
        for (Disable item : items) {
            item.setDisable(testDisable());
        }
    }

    public abstract void execute();

}
