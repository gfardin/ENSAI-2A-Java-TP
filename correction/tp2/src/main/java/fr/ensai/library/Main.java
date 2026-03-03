package fr.ensai.library;

import java.util.ArrayList;

public class Main {
    public static void main(String[] args) {
        Library library = new Library("City Library");

        String csvFilePath = "books.csv";
        library.loadBooksFromCSV(csvFilePath);

        library.addIem(new Magazine("1234-5678", "Tech Monthly", "Vol. 15, Issue 3", 2023, 120));
        library.addIem(new Magazine("9876-5432", "Vogue Style", "September Edition", 2024, 250));
        library.addIem(new Magazine("1122-3344", "Scientific Discoveries", "Issue 247", 2022, 88));

        library.displayItems();

        System.out.println("\n**********************************************");
        System.out.println("* Books by Stephen King                      *");
        System.out.println("**********************************************");

        ArrayList<Book> skingsBooks = library.getBooksByAuthor(new Author("Stephen King"));

        for (Book b : skingsBooks) {
            System.out.println(b);
        }
    }
}