package com.cursor.Models;

import org.hibernate.annotations.Proxy;
import org.springframework.stereotype.Component;

import javax.persistence.*;
import java.io.Serializable;
import java.util.List;
import java.util.Objects;

@Component
@Entity
@Table(name = "users")
@Proxy(lazy = false)
public class User implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "user_id")
    private Integer id;

    @Column
    private String name;

    @OneToMany(fetch = FetchType.EAGER)
    @JoinTable(
            name = "book_user",
            joinColumns = {@JoinColumn(name = "user_id")},
            inverseJoinColumns = {@JoinColumn(name = "book_id")}
    )
    private List<Book> bookList;

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getId() {
        return id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Book> getBookList() {
        return bookList;
    }

    public void setBookList(List<Book> bookList) {
        this.bookList = bookList;
    }

    public String getBooks(){
        String books = "";
        for(Book book : bookList){
            books += book.getTitle() + ";";
        }
        return books;
    }


    @Override
    public String toString() {
        return "User{" +
                "name='" + name + '\'' +
                ", bookList=" + getBooks() +
                '}';
    }
}
