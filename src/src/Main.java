import java.util.Arrays;
import java.util.Scanner;
import java.util.List;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.stream.Stream;

public class Main {

}
    // =====================================
    // BOOK INFORMATION IN PARALLEL ARRAYS
    // =====================================

    // Store book titles
    static String[] titles = {
            "Things Fall Apart",
            "The River Between",
            "Java Programming"
    };

    // Store book authors
    static String[] authors = {
            "Amadou Gillen",
            "Ebrima Saine",
            "Amadou Beyai"
    };

    // Store book ISBN numbers
    static String[] isbns = {
            "9780385474542",
            "9780143106692",
            "9780071808552"
    };

    // Store book availability
    static boolean[] available = {
            true,
            true,
            false
    };

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.println("==================================");
        System.out.println("       LIBRATRACK SYSTEM");
        System.out.println("==================================");
        System.out.println("Total Books: " + titles.length);

        // Display all books
        printCatalog();

        // Search for a book
        System.out.print("\nEnter book title to search: ");
        String searchTitle = scanner.nextLine();

        int result = searchBook(searchTitle);

        if (result != -1) {
            System.out.println("Book found: " + titles[result]);
        } else {
            System.out.println("Book not found.");
        }

        List<Book> books = new ArrayList<>();

        books.add(new Book("Things Fall Apart", "Amadou Gillen",
                "9780385474542", true));

        books.add(new Book("The River Between", "Ebrima Saine",
                "9780143106692", true));

        books.add(new Book("Java Programming", "Amadou Beyai",
                "9780071808552", false));
        displayAvailableBooks(books);

        // Add a new book
        addBook(scanner);

        // Checkout or return a book
        toggleBookStatus(scanner);

        // Display updated catalog
        printCatalog();

        scanner.close();
    }
    // SEARCH BOOK
    // =====================================
    public static int searchBook(String searchTitle) {
        for (int i = 0; i < titles.length; i++) {
            if (titles[i].equalsIgnoreCase(searchTitle)) {
                return i;
            }
        }
        return -1;
    }

    // =====================================
    // DISPLAY CATALOG
    // =====================================
    public static void printCatalog() {

        System.out.println("\n========== LIBRARY CATALOG ==========");

        if (titles.length == 0) {
            System.out.println("No books available in the catalog.");
            return;
        }

        for (int i = 0; i < titles.length; i++) {

            System.out.println("\nBook " + (i + 1));

            System.out.println("Title: " + titles[i]);
            System.out.println("Author: " + authors[i]);
            System.out.println("ISBN: " + isbns[i]);

            if (available[i]) {
                System.out.println("Availability: Available");
            } else {
                System.out.println("Availability: Borrowed");
            }

            System.out.println("-----------------------------------");
        }

        System.out.println("Total Books: " + titles.length);
    }

    // =====================================
    // ADD NEW BOOK
    // =====================================
    public static void addBook(Scanner scanner) {

        System.out.println("\n========== ADD NEW BOOK ==========");

        // Get book information from the user
        System.out.print("Enter book title: ");
        String newTitle = scanner.nextLine();

        System.out.print("Enter author's name: ");
        String newAuthor = scanner.nextLine();

        System.out.print("Enter ISBN: ");
        String newIsbn = scanner.nextLine();

        // Resize the arrays
        titles = Arrays.copyOf(titles, titles.length + 1);
        authors = Arrays.copyOf(authors, authors.length + 1);
        isbns = Arrays.copyOf(isbns, isbns.length + 1);
        available = Arrays.copyOf(available, available.length + 1);

        // Add the new book at the last index
        int newIndex = titles.length - 1;

        titles[newIndex] = newTitle;
        authors[newIndex] = newAuthor;
        isbns[newIndex] = newIsbn;
        available[newIndex] = true;

        System.out.println("\nBook added successfully!");
        System.out.println("New Book: " + titles[newIndex]);
        System.out.println("New Author: " + authors[newIndex]);
        System.out.println("New ISBN: " + isbns[newIndex]);
        System.out.println("Available: " + available[newIndex]);
    }

    // =====================================
    // CHECKOUT / RETURN
    // =====================================
    public static void toggleBookStatus(Scanner scanner) {

        System.out.println("\n========== CHECKOUT / RETURN ==========");

        System.out.print("Enter the book title: ");
        String searchTitle = scanner.nextLine();

        // Search for the book
        int index = searchBook(searchTitle);

        // Check whether the book exists
        if (index == -1) {
            System.out.println("Book not found!");
        } else {
            // Check availability
            if (available[index]) {
                available[index] = false;
                System.out.println("Book checked out successfully!");
                System.out.println("Title: " + titles[index]);
            } else {
                available[index] = true;
                System.out.println("Book returned successfully!");
                System.out.println("Title: " + titles[index]);
            }
        }
    }
    public static void displayAvailableBooks(List<Book> books) {

        System.out.println("\n===== AVAILABLE BOOKS =====");

        books.stream()
                .filter(Book::isAvailable)
                .sorted(Comparator.comparing(Book::getTitle))
                .forEach(System.out::println);

    }
