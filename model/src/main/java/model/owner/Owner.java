package model.owner;

import java.io.Serializable;
import java.util.Objects;

public class Owner implements Serializable {

    private OwnerId id;
    private String name;
    private String surname;

    public Owner(OwnerId id, String name, String surname) {
        this.id = id;
        this.name = name;
        this.surname = surname;
    }

    public OwnerId getId() {
        return id;
    }

    public String getName() {
        return name;
    }
    
    public String getNameSurname(){
        return name + " " + surname;
    }

    public String getSurname() {
        return surname;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 59 * hash + Objects.hashCode(this.id);
        return hash;
    }

    @Override
    public String toString() {
        return "[" + id + "] " + name + " " + surname;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Owner other = (Owner) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        return true;
    }

}
