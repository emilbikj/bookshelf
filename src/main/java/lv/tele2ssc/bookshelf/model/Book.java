package lv.tele2ssc.bookshelf.model;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import org.hibernate.validator.constraints.NotEmpty;
import org.hibernate.validator.constraints.Range;

@Entity
public class Book implements Serializable {
    
    @Id
    @GeneratedValue
    private Long id;
    
    @Column
    @NotEmpty
    private String title;
    
    @Column
    @NotEmpty
    private String author;
    
    @Column(length = 2000)
    private String description;
    
    @Column
    @NotEmpty
    private String isbn;
    
    @Column
    @Range(min=1500, max=2050)
    private int year;

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getAuthor() {
        return author;
    }

    public void setAuthor(String author) {
        this.author = author;
    }

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }
    
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    public int getYear() {
        return year;
    }

    public void setYear(int year) {
        this.year = year;
    }

    @Override
    public String toString() {
        return "Book{" + "id=" + id + ", title=" + title + ", author=" + author + ", description=" + description + ", isbn=" + isbn + ", year=" + year + '}';
    }
    
    
}
