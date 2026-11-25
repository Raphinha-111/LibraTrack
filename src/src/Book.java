public class Book implements Borrowable {

    private String title;
    private String author;
    private String isbn;
    private boolean available;

    public Book(String title, String author, String isbn, boolean available) {
        this.title = title;
        this.author = author;
        this.isbn = isbn;
        this.available = available;
    }

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
    } // <- toString ENDS here

    // These must be OUTSIDE toString, directly inside the class
    @Override
    public void borrow() {
        available = false;
    }

    @Override
    public void returnBook() {
        available = true;
    }
}