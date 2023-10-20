package flaviodeangelis.entities;

import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "books")
public class Books extends Element {

    private String author;
    private String type;

    public Books(String title, int yearOfPublication, int numberOfPage, String author, String type) {
        super(title, yearOfPublication, numberOfPage);
        this.author = author;
        this.type = type;
    }

    public Books() {
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Books{" +
                "author='" + author + '\'' +
                ", type='" + type + '\'' +
                '}';
    }
}

