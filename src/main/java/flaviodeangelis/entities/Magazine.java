package flaviodeangelis.entities;


import javax.persistence.Entity;
import javax.persistence.EnumType;
import javax.persistence.Enumerated;
import javax.persistence.Table;

@Entity
@Table(name = "magazines")
public class Magazine extends Element {
    @Enumerated(EnumType.STRING)
    private Periodicità periodicità;

    public Magazine(String title, int yearOfPublication, int numberOfPage, Periodicità periodicità) {
        super(title, yearOfPublication, numberOfPage);
        this.periodicità = periodicità;
    }

    public Magazine() {
    }

    @Override
    public String toString() {
        return "Magazine{" +
                "periodicità=" + periodicità +
                '}';
    }
}
