USE library;

CREATE TABLE users
(
    user_id INTEGER UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL,
    CONSTRAINT users_pk PRIMARY KEY (user_id)
);

CREATE TABLE books
(
    book_id INTEGER UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
    title VARCHAR(40) NOT NULL ,
    CONSTRAINT books_pk PRIMARY KEY (book_id)
);

CREATE TABLE authors
(
    author_id INTEGER UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
    name VARCHAR(50) NOT NULL ,
    CONSTRAINT authors_pk PRIMARY KEY (author_id)
);

CREATE TABLE book_author
(
    book_author_id INTEGER UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
    book_id INTEGER UNSIGNED NOT NULL,
    author_id INTEGER UNSIGNED NOT NULL,
    CONSTRAINT book_author_pk PRIMARY KEY (book_author_id),
    CONSTRAINT book_author_book_fk
        FOREIGN KEY (book_id)
            REFERENCES books(book_id),
    CONSTRAINT book_author_author_fk
        FOREIGN KEY (author_id)
            REFERENCES authors(author_id)
);

CREATE TABLE book_user
(
    book_user_id INTEGER UNSIGNED NOT NULL UNIQUE AUTO_INCREMENT,
    book_id INTEGER UNSIGNED NOT NULL,
    user_id INTEGER UNSIGNED NOT NULL,
    CONSTRAINT book_user_pk PRIMARY KEY (book_user_id),
    CONSTRAINT book_user_book_fk
        FOREIGN KEY (book_id)
            REFERENCES books(book_id),
    CONSTRAINT book_user_user_fk
        FOREIGN KEY (user_id)
            REFERENCES users(user_id)
);

INSERT INTO books(book_id, title) VALUES
(1, 'Murder on the Orient Express'),
(2, 'Da Vinci Code');

INSERT INTO users(user_id, name) VALUES (1, 'Andriiana M');

INSERT INTO authors(author_id, name) VALUES
(1, 'Agata Kristi'),
(2, 'Dan Brown');

INSERT INTO book_author(book_author_id, book_id, author_id) VALUES
(1, 1, 1),
(2, 2, 2);

INSERT INTO book_user(book_user_id, book_id, user_id) VALUES (1, 1, 1);
