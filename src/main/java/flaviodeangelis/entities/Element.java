package flaviodeangelis.entities;

import javax.persistence.*;
import java.util.List;
import java.util.Random;

@Entity
@Table(name = "elements")
@Inheritance(strategy = InheritanceType.JOINED)
public abstract class Element {
    @Id
    private long ISBN;
    private String title;
    private int yearOfPublication;
    private int numberOfPage;
    @OneToMany(mappedBy = "elemento")
    private List<Prestito> prestiti;

    public Element(String title, int yearOfPublication, int numberOfPage) {
        Random rndm = new Random();
        this.ISBN = rndm.nextInt(0, 99999999);
        this.title = title;
        this.yearOfPublication = yearOfPublication;
        this.numberOfPage = numberOfPage;

    }

    public Element() {

    }


    public long getISBN() {
        return ISBN;
    }

    public int getYearOfPublication() {
        return yearOfPublication;
    }

    public String getTitle() {
        return title;
    }

    public int getNumberOfPage() {
        return numberOfPage;
    }

    public List<Prestito> getPrestiti() {
        return prestiti;
    }

    @Override
    public String toString() {
        return "Element{" +
                "ISBN=" + ISBN +
                ", title='" + title + '\'' +
                ", yearOfPublication=" + yearOfPublication +
                ", numberOfPage=" + numberOfPage +
                '}';
    }
}
