package model.car;

import java.io.Serializable;
import java.util.Objects;

public class Car implements Serializable {

    private CarId id;
    private String brand;
    private String model;
    private int idOwner;

    public Car(CarId id, String brand, String model, int idOwner) {
        this.id = id;
        this.brand = brand;
        this.model = model;
        this.idOwner = idOwner;
    }

    public Car() {
    }

    public CarId getId() {
        return id;
    }
    
    public int getIdOwner(){
        return idOwner;
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
        return "[" + id + "] " + brand + " " + model + "(" + idOwner + ")";
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
