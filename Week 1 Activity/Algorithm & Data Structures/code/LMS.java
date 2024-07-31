import java.util.List;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

class Book {
    private int bookId;
    private String title;
    private String author;

    public Book(int bookId, String title, String author) {
        this.bookId = bookId;
        this.title = title;
        this.author = author;
    }

    public int getBookId() {
        return bookId;
    }

    public String getTitle() {
        return title;
    }

    public String getAuthor() {
        return author;
    }

    @Override
    public String toString() {
        return "Book{" +
                "bookId=" + bookId +
                ", title='" + title + '\'' +
                ", author='" + author + '\'' +
                '}';
    }
}

 class Library {
    private List<Book> books;

    public Library(List<Book> books) {
        this.books = books;
    }

    // Binary search to find books by title (assumes list is sorted by title)
    public Book binarySearchByTitle(String title) {
        int low = 0;
        int high = books.size() - 1;

        while (low <= high) {
            int mid = low + (high - low) / 2;
            Book midBook = books.get(mid);
            int comparison = midBook.getTitle().compareToIgnoreCase(title);

            if (comparison == 0) {
                return midBook;
            } else if (comparison < 0) {
                low = mid + 1;
            } else {
                high = mid - 1;
            }
        }
        return null;
    }
    
    // Linear search to find books by title
    public Book linearSearchByTitle(String title) {
        for (Book book : books) {
            if (book.getTitle().equalsIgnoreCase(title)) {
                return book;
            }
        }
        return null;
    }

    // Traverse and display all books
    public void displayBooks() {
        for (Book book : books) {
            System.out.println(book);
        }
    }
}

public class LMS {
    public static void main(String[] args) {
        // Create a list of books
        List<Book> books = new ArrayList<>();
        books.add(new Book(1, "To Kill a Mockingbird", "Harper Lee"));
        books.add(new Book(2, "1984", "George Orwell"));
        books.add(new Book(3, "The Great Gatsby", "F. Scott Fitzgerald"));
        books.add(new Book(4, "Pride and Prejudice", "Jane Austen"));
        books.add(new Book(5, "Moby Dick", "Herman Melville"));

        // Create a library
        Library library = new Library(books);

        // Linear search for a book by title
        String searchTitleLinear = "1984";
        Book foundBookLinear = library.linearSearchByTitle(searchTitleLinear);
        if (foundBookLinear != null) {
            System.out.println("Linear Search - Book found: " + foundBookLinear);
        } else {
            System.out.println("Linear Search - Book with title '" + searchTitleLinear + "' not found.");
        }

        // Sort books by title for binary search
        Collections.sort(books, Comparator.comparing(Book::getTitle, String.CASE_INSENSITIVE_ORDER));

        // Binary search for a book by title
        String searchTitleBinary = "Pride and Prejudice";
        Book foundBookBinary = library.binarySearchByTitle(searchTitleBinary);
        if (foundBookBinary != null) {
            System.out.println("Binary Search - Book found: " + foundBookBinary);
        } else {
            System.out.println("Binary Search - Book with title '" + searchTitleBinary + "' not found.");
        }

        // Display all books
        System.out.println("All Books:");
        library.displayBooks();
    }
}