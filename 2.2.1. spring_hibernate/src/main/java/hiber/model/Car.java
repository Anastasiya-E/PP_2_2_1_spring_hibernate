package hiber.model;

import javax.persistence.Entity;
import javax.persistence.Id;

@Entity
public class Car {
    @Id
    private Long id;
    private String model;
    private int series;
    public Car() {}

    public Car(Long id, String model1, int i) {
        this.id = id;
        this.model = model1;
        this.series = i;
    }

    public Long getId() {
        return id;
    }

    public String getModel() {
        return model;
    }
    public int getSeries() {
        return series;
    }
    public void setId(Long id) {
        this.id = id;
    }
    public void setModel(String m) {
        this.model = m;
    }
    public void setSeries(int s) {
        this.series = s;
    }
}
