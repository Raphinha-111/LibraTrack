import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        List<Book> books = new ArrayList<>();

        books.add(new Book("Things Fall Apart", "Amadou Gillen",
                "9780385474542", true));

        books.add(new Book("The River Between", "Ebrima Saine",
                "9780143106692", true));

        books.add(new Book("Java Programming", "Amadou Beyai",
                "9780071808552", false));

        System.out.println("==================================");
        System.out.println("       LIBRATRACK SYSTEM");
        System.out.println("==================================");

        runMenu(books);
    }

    // =====================================
    // MENU
    // =====================================
    public static void runMenu(List<Book> books) {

        Scanner scanner = new Scanner(System.in);

        Map<String, Runnable> menuActions = new HashMap<>();

        menuActions.put("1", () -> addBook(books, scanner));
        menuActions.put("2", () -> searchBook(books, scanner));
        menuActions.put("3", () -> printCatalog(books));
        menuActions.put("4", () -> displayAvailableBooks(books));
        menuActions.put("5", () -> toggleBookStatus(books, scanner));

        boolean running = true;

        while (running) {

            System.out.println("\n========== LIBRATRACK ==========");
            System.out.println("1. Add a Book");
            System.out.println("2. Search for a Book");
            System.out.println("3. Display All Books");
            System.out.println("4. Display Available Books");
            System.out.println("5. Borrow / Return a Book");
            System.out.println("6. Exit");

            System.out.print("Enter your choice: ");
            String choice = scanner.nextLine();

            if (choice.equals("6")) {
                running = false;
                System.out.println("Thank you for using LibraTrack!");
                continue;
            }

            Runnable action = menuActions.get(choice);

            if (action != null) {
                action.run();
            } else {
                System.out.println("Invalid choice. Please try again.");
            }
        }

        scanner.close();
    }

    // =====================================
    // ADD NEW BOOK
    // =====================================
    public static void addBook(List<Book> books, Scanner scanner) {

        System.out.println("\n========== ADD NEW BOOK ==========");

        System.out.print("Enter book title: ");
        String newTitle = scanner.nextLine();

        System.out.print("Enter author's name: ");
        String newAuthor = scanner.nextLine();

        System.out.print("Enter ISBN: ");
        String newIsbn = scanner.nextLine();

        books.add(new Book(newTitle, newAuthor, newIsbn, true));

        System.out.println("\nBook added successfully!");
        System.out.println("New Book: " + newTitle);
        System.out.println("New Author: " + newAuthor);
        System.out.println("New ISBN: " + newIsbn);
        System.out.println("Available: true");
    }

    // =====================================
    // SEARCH BOOK - MENU VERSION
    // =====================================
    public static void searchBook(List<Book> books, Scanner scanner) {

        System.out.print("\nEnter book title to search: ");
        String searchTitle = scanner.nextLine();

        // FIXED: calls the method below, NOT Book.searchBook
        Book result = searchBook(books, searchTitle);

        if (result != null) {
            System.out.println("Book found:");
            System.out.println(result);
        } else {
            System.out.println("Book not found.");
        }
    }

    // =====================================
    // SEARCH BOOK - LOGIC VERSION
    // =====================================
    public static Book searchBook(List<Book> books, String searchTitle) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(searchTitle)) {
                return book;
            }
        }
        return null;
    }

    // =====================================
    // DISPLAY CATALOG
    // =====================================
    public static void printCatalog(List<Book> books) {

        System.out.println("\n========== LIBRARY CATALOG ==========");

        if (books.isEmpty()) {
            System.out.println("No books available in the catalog.");
            return;
        }

        for (int i = 0; i < books.size(); i++) {
            System.out.println("\nBook " + (i + 1));
            System.out.println(books.get(i));
            System.out.println("-----------------------------------");
        }

        System.out.println("Total Books: " + books.size());
    }

    // =====================================
    // DISPLAY AVAILABLE BOOKS
    // =====================================
    public static void displayAvailableBooks(List<Book> books) {

        System.out.println("\n===== AVAILABLE BOOKS =====");

        books.stream()
                .filter(Book::isAvailable)
                .sorted(Comparator.comparing(Book::getTitle))
                .forEach(System.out::println);
    }

    // =====================================
    // CHECKOUT / RETURN
    // =====================================
    public static void toggleBookStatus(List<Book> books, Scanner scanner) {

        System.out.println("\n========== CHECKOUT / RETURN ==========");

        System.out.print("Enter the book title: ");
        String searchTitle = scanner.nextLine();

        // FIXED: calls the method in Main, NOT Book.searchBook
        Book book = searchBook(books, searchTitle);

        if (book == null) {
            System.out.println("Book not found!");
        } else {
            if (book.isAvailable()) {
                book.setAvailable(false);
                System.out.println("Book checked out successfully!");
                System.out.println("Title: " + book.getTitle());
            } else {
                book.setAvailable(true);
                System.out.println("Book returned successfully!");
                System.out.println("Title: " + book.getTitle());
            }
        }
    }
}