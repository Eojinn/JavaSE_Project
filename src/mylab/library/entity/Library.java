package mylab.library.entity;

import java.util.ArrayList;
import java.util.List;

public class Library {
    private String name;
    private List<Book> books;

    public Library(String name) {
        this.name = name;
        this.books = new ArrayList<>(); // 빈 목록 생성
    }

    public void addBook(Book book) {
        books.add(book);
        System.out.println("도서가 추가되었습니다: " + book.getTitle());
    }

    public Book findBookByTitle(String title) {
        for (Book b : books) {
            if (b.getTitle().equals(title)) return b;
        }
        return null;
    }

    public List<Book> findBooksByAuthor(String author) {
        List<Book> result = new ArrayList<>();
        for (Book b : books) {
            if (b.getAuthor().equals(author)) result.add(b);
        }
        return result;
    }

    public Book findBookByISBN(String isbn) {
        for (Book b : books) {
            if (b.getIsbn().equals(isbn)) return b;
        }
        return null;
    }

    public boolean checkOutBook(String isbn) {
        Book b = findBookByISBN(isbn);
        if (b != null) return b.checkOut();
        return false;
    }

    public void returnBook(String isbn) {
        Book b = findBookByISBN(isbn);
        if (b != null) b.returnBook();
    }

    public List<Book> getAvailableBooks() {
        List<Book> availableList = new ArrayList<>();
        for (Book b : books) {
            if (b.isAvailable()) availableList.add(b);
        }
        return availableList;
    }

    public List<Book> getAllBooks() { return books; }
    public int getTotalBooks() { return books.size(); }
    public String getName() { return name; }

    public int getAvailableBooksCount() {
        return getAvailableBooks().size();
    }

    public int getBorrowedBooksCount() {
        return getTotalBooks() - getAvailableBooksCount();
    }
}