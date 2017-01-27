package model.entities;

/**
 * Procedure
 * Created by alexey.morenets@gmail.com on 24.01.2017.
 */
public class Procedure {

    private int id;
    private String name;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public String toString() {
        return "Procedure{" +
                "id=" + id +
                ", name='" + name + '\'' +
                '}';
    }

}
