import java.util.ArrayList;
import java.util.List;

public class Library implements Searchable {

    private final List<Book> books;

    public Library() {
        this.books = new ArrayList<>();
    }

    public void addBook(Book book) {
        books.add(book);
    }

    public List<Book> getBooks() {
        return books;
    }

    @Override
    public Book searchBook(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    public void displayBooks() {

        System.out.println("\n========== LIBRARY CATALOG ==========");

        if (books.isEmpty()) {
            System.out.println("No books available.");
            return;
        }

        for (Book book : books) {
            System.out.println(book);
            System.out.println("-----------------------------------");
        }

        System.out.println("Total Books: " + books.size());
    }
}