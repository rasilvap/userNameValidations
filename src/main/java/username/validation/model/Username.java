package username.validation.model;


import java.io.Serializable;
import java.util.Objects;

public class Username implements Serializable{

    private static final long serialVersionUID = 8799656478674716638L;
    private String name;

    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Username username = (Username) o;
        return Objects.equals(name, username.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

    @Override
    public String toString() {
        return "Username{" +
                "name='" + name + '\'' +
                '}';
    }
}
