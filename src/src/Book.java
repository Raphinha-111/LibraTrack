import java.util.List;

public class Book {

    // Fields
    private String title;
    private String author;
    private String isbn;
    private boolean available;

    // Constructor
    public Book(String title, String author, String isbn, boolean available) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = available;
    }

    // Getters
    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    public String getIsbn() {
        return isbn;
    }

    public boolean isAvailable() {
        return available;
    }

    // Setter - for checkout / return
    public void setAvailable(boolean available) {
        this.available = available;
    }

    @Override
    public String toString() {
        String status = available ? "Available" : "Borrowed";
        return "Title: " + title +
                "\nAuthor: " + author +
                "\nISBN: " + isbn +
                "\nAvailability: " + status;
    }

    // =====================================
    // SEARCH BOOK
    // =====================================
    public static Book searchBook(List<Book> books, String searchTitle) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(searchTitle)) {
                return book;
            }
        }
        return null;
    }
}