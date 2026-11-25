import java.util.Comparator;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class Main {

    public static void main(String[] args) {

        Library library = new Library();

        library.addBook(new Book("Things Fall Apart", "Amadou Gillen",
                "9780385474542", true));

        library.addBook(new Book("The River Between", "Ebrima Saine",
                "9780143106692", true));

        library.addBook(new Book("Java Programming", "Amadou Beyai",
                "9780071808552", false));

        System.out.println("==================================");
        System.out.println("       LIBRATRACK SYSTEM");
        System.out.println("==================================");

        runMenu(library);

        // This part runs AFTER you choose 6 - Exit
        Student student = new Student("STU001", "Amadou Gillen");
        Faculty faculty = new Faculty("FAC001", "Ebrima Saine");

        System.out.println("\n===== MEMBER BORROWING LIMITS =====");

        System.out.println(student.getName() +
                " (Student): " + student.getBorrowLimit() + " books");

        System.out.println(faculty.getName() +
                " (Faculty): " + faculty.getBorrowLimit() + " books");
    }

    // =====================================
    // MENU
    // =====================================
    public static void runMenu(Library library) {

        Scanner scanner = new Scanner(System.in);

        Map<String, Runnable> menuActions = new HashMap<>();

        menuActions.put("1", () -> addBook(library, scanner));
        menuActions.put("2", () -> searchBook(library, scanner));
        menuActions.put("3", () -> library.displayBooks());
        menuActions.put("4", () -> displayAvailableBooks(library));
        menuActions.put("5", () -> toggleBookStatus(library, scanner));

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
    public static void addBook(Library library, Scanner scanner) {

        System.out.println("\n========== ADD NEW BOOK ==========");

        System.out.print("Enter book title: ");
        String newTitle = scanner.nextLine();

        System.out.print("Enter author's name: ");
        String newAuthor = scanner.nextLine();

        System.out.print("Enter ISBN: ");
        String newIsbn = scanner.nextLine();

        library.addBook(new Book(newTitle, newAuthor, newIsbn, true));

        System.out.println("\nBook added successfully!");
        System.out.println("New Book: " + newTitle);
        System.out.println("New Author: " + newAuthor);
        System.out.println("New ISBN: " + newIsbn);
        System.out.println("Available: true");
    }

    // =====================================
    // SEARCH BOOK
    // =====================================
    public static void searchBook(Library library, Scanner scanner) {

        System.out.print("\nEnter book title to search: ");
        String searchTitle = scanner.nextLine();

        Book result = library.searchBook(searchTitle);

        if (result != null) {
            System.out.println("Book found:");
            System.out.println(result);
        } else {
            System.out.println("Book not found.");
        }
    }

    // =====================================
    // DISPLAY AVAILABLE BOOKS
    // =====================================
    public static void displayAvailableBooks(Library library) {

        System.out.println("\n===== AVAILABLE BOOKS =====");

        library.getBooks().stream()
                .filter(Book::isAvailable)
                .sorted(Comparator.comparing(Book::getTitle))
                .forEach(System.out::println);
    }

    // =====================================
    // CHECKOUT / RETURN
    // =====================================
    public static void toggleBookStatus(Library library, Scanner scanner) {

        System.out.println("\n========== CHECKOUT / RETURN ==========");

        System.out.print("Enter the book title: ");
        String searchTitle = scanner.nextLine();

        Book book = library.searchBook(searchTitle);

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