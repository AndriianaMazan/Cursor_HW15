package com.cursor;

import com.cursor.Dao.AuthorInDBDao;
import com.cursor.Dao.BookInDBDao;
import com.cursor.Dao.UserInDBDao;
import com.cursor.Models.Author;
import com.cursor.Models.Book;
import com.cursor.Models.User;
import org.hibernate.SessionFactory;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

import java.util.ArrayList;
import java.util.List;

public class LibraryProject {

    private static final ApplicationContext applicationContext =
            new AnnotationConfigApplicationContext(SpringConfig.class);

    public static void main(String[] args) {
        SessionFactory sessionFactory = applicationContext.getBean(SessionFactory.class);
        AuthorInDBDao authorDao = applicationContext.getBean(AuthorInDBDao.class);
        BookInDBDao bookDao = applicationContext.getBean(BookInDBDao.class);
        UserInDBDao userDao = applicationContext.getBean(UserInDBDao.class);

        Author author = authorDao.read(1);
        Book book = applicationContext.getBean(Book.class);
        User user = applicationContext.getBean(User.class);

        book.setId(3);
        book.setTitle("And then there were none");
        List<Author> authorList = new ArrayList<>();
        authorList.add(author);
        book.setAuthorList(authorList);
        bookDao.create(book);

        user.setId(2);
        user.setName("Lilia K");
        System.out.println("Creates new user: " + userDao.create(user));

        List<Book> bookList = author.getBookList();
        bookList.add(book);
        author.setBookList(bookList);
        authorDao.update(author);

        Book readBook = bookDao.read(3);
        Author readAuthor = authorDao.read(1);
        User readUser = userDao.read(2);
        System.out.println(readBook.toString());
        System.out.println("\n" + readAuthor.toString());
        System.out.println("\n" + readUser.toString());

        System.out.println("Deleted user #2: " + userDao.delete(user));

        List<User> allUsers = userDao.getAll(User.class);
        List<Book> allBooks = bookDao.getAll(Book.class);
        List<Author> allAuthors = authorDao.getAll(Author.class);

        System.out.println("\n\nBooks:\n");
        System.out.println(allBooks);
        System.out.println("\nAuthors:\n");
        System.out.println(allAuthors);
        System.out.println("\nUsers:\n");
        System.out.println(allUsers);

        sessionFactory.close();
    }
}
