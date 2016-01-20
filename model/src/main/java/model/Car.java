package model;

import java.io.Serializable;
import java.util.Objects;

public class Car implements Serializable {

    private CarId id;
    private String owner;
    private String brand;
    private String model;

    public Car(CarId id, String title, String author) {
        this.id = id;
        this.owner = title;
        this.brand = author;
        this.model = " ";
    }
    
    public Car(CarId id, String brand, String model, String owner) {
        this.id = id;
        this.owner = owner;
        this.brand = brand;
        this.model = model;
    }

    public Car() {
    }

    public CarId getId() {
        return id;
    }

    public String getOwner() {
        return owner;
    }

    public String getBrand() {
        return brand;
    }

    public String getModel() {
        return model;
    }
    

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + brand + " " + model + ": " + owner;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Car other = (Car) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
