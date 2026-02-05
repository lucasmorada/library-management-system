package service;

import model.Book;
import model.User;
import exception.LibraryException;

import java.util.*;

public class LibraryService {

    private Map<String, Book> books = new HashMap<>();
    private List<User> users = new ArrayList<>();

    public void addBook(Book book) {
        books.put(book.getTitle(), book);
    }

    public Book findBook(String title) {
        Book book = books.get(title);
        if (book == null)
            throw new LibraryException("Book not found: " + title);
        return book;
    }

    public void addUser(User user) {
        users.add(user);
    }

    public void listBooks() {
        System.out.println("\n📚 Library Books:");
        books.values().forEach(System.out::println);
    }
}
