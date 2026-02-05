package service;

import model.Book;
import model.User;
import exception.LibraryException;

public class LoanService {

    private LibraryService libraryService;

    public LoanService(LibraryService libraryService) {
        this.libraryService = libraryService;
    }

    public void loanBook(String title, User user) {
        Book book = libraryService.findBook(title);

        if (!book.isAvailable())
            throw new LibraryException("Book already loaned");

        book.loan();
        System.out.println(user.getName() + " loaned the book: " + title);
    }

    public void returnBook(String title, User user) {
        Book book = libraryService.findBook(title);
        book.giveBack();
        System.out.println(user.getName() + " returned the book: " + title);
    }
}
