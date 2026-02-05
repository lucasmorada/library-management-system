package model;

public class Book {

    private String title;
    private String author;
    private int year;
    private boolean available;

    public Book(String title, String author, int year) {
        this.title = title;
        this.author = author;
        this.year = year;
        this.available = true;
    }

    public boolean isAvailable() {
        return available;
    }

    public void loan() {
        this.available = false;
    }

    public void giveBack() {
        this.available = true;
    }

    public String getTitle() {
        return title;
    }

    @Override
    public String toString() {
        return title + " (" + author + ", " + year + ") - " +
                (available ? "Available" : "Unavailable");
    }
}
