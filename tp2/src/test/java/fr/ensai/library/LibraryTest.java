package fr.ensai.library;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;

import java.util.Date;

import org.junit.jupiter.api.Test;

class LibraryTest {

    @Test
    void findActiveLoanForItem_ItemIsLoaned() {
        // GIVEN
        Library library = new Library("Test Library");
        Book book1 = new Book("978-0321765723", "Book 1", new Author("Author 1"), 2008, 320);
        Student student = new Student("John Doe", 20, 2, true);
        Loan loan1 = new Loan(book1, student, new Date());
        library.getActiveLoans().add(loan1);

        // WHEN
        Loan foundLoan = library.findActiveLoanForItem(book1);

        // THEN
        assertEquals(loan1, foundLoan);
    }

    @Test
    void findActiveLoanForItem_ItemIsNotLoaned() {
        // GIVEN
        Library library = new Library("Test Library");
        Book book1 = new Book("978-0321765723", "Book 1", new Author("Author 1"), 2008, 320);
        Book book2 = new Book("978-0596009205", "Book 2", new Author("Author 2"), 2005, 450);
        Student student = new Student("John Doe", 20, 2, true);
        Loan loan1 = new Loan(book1, student, new Date());
        library.getActiveLoans().add(loan1);

        // WHEN
        Loan foundLoan = library.findActiveLoanForItem(book2);

        // THEN
        assertNull(foundLoan);
    }

}