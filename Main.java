import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

interface Item{
    String getBookTitle();
    void displayInfo();
}

class Book implements Item{
    private String book_title;
    private String book_author;

    public Book(String book_title, String book_author){
        this.book_title= book_title;
        this.book_author = book_author;
    }


    @Override
    public String getBookTitle() {
        return this.book_title;
    }

    @Override
    public void displayInfo() {
        System.out.println("Book: " + book_title + " author is: " + book_author);
    }
}

interface User{


    String getUserID();
    List<String> getPermissions();
}

class Library implements User{
    private String user_id;
    private List<String> permissions;

    public Library(String user_id) {
        this.user_id = user_id;
        this.permissions = new ArrayList<>();
        permissions.add("add_item");
        permissions.add("remove_item");
    }


    @Override
    public String getUserID() {
        return this.user_id;
    }

    @Override
    public List<String> getPermissions() {
        return this.permissions;
    }
}
class LibraryCatalog {
    private List<Item> items = new ArrayList<>();

    public void addItem(Item item) {
        this.items.add(item);
        System.out.println("\nAdded new book : " + item.getBookTitle() + "\n");
    }

    public void removeItem(String title) {
        this.items.removeIf(item -> item.getBookTitle().equalsIgnoreCase(title));
        System.out.println("Removed: " + title);
    }

    public void displayCatalog() {
        if (items.isEmpty()) {
            System.out.println("The catalog is empty.");
        } else {
            for (Item item : items) {
                item.displayInfo();
            }
        }
    }
}

public class Main {

    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        LibraryCatalog catalog = new LibraryCatalog();

        while (true) {

            System.out.println(
                    "********************    Welcome to the Library!   ********************");
            System.out.println(
                    "                  Select From The Following Options:               ");
            System.out.println(
                    "**********************************************************************");

            System.out.println("\nChoose an action:");
            System.out.println("1. Add a book");
            System.out.println("2. Delete a book");
            System.out.println("3. Display catalog");
            System.out.println("4. Exit");
            System.out.print("Enter option: ");

            String option = scanner.nextLine();
            switch (option) {
                case "1":
                    addBook(catalog);
                    break;
                case "2":
                    deleteBook(catalog);
                    break;
                case "3":
                    catalog.displayCatalog();
                    break;
                case "4":
                    System.out.println("Exiting...");
                    return;
                default:
                    System.out.println("Invalid option. Please enter 1, 2, 3, or 4.");
                    break;
            }
        }
    }

    private static void addBook(LibraryCatalog catalog) {
        System.out.print("Enter book title: ");
        String title = scanner.nextLine();
        System.out.print("Enter author's name: ");
        String author = scanner.nextLine();
        Book book = new Book(title, author);
        catalog.addItem(book);
    }

    private static void deleteBook(LibraryCatalog catalog) {
        System.out.print("Enter the title of the book to delete: ");
        String title = scanner.nextLine();
        catalog.removeItem(title);
    }
}
