package app;

import model.*;
import service.LibraryService;
import service.LoanService;

public class Main {
    public static void main(String[] args) {

        LibraryService libraryService = new LibraryService();
        LoanService loanService = new LoanService(libraryService);

        libraryService.addBook(new Book("Clean Code", "Robert C. Martin", 2008));
        libraryService.addBook(new Book("Effective Java", "Joshua Bloch", 2017));

        User user = new User("Lucas", UserType.STUDENT);
        libraryService.addUser(user);

        loanService.loanBook("Clean Code", user);
        loanService.returnBook("Clean Code", user);

        libraryService.listBooks();
    }
}
