package fr.ensai.library;

import java.util.Date;

public class Loan {
    // Attributes
    private Item item;
    private Student borrower;
    private Date startDate;
    private Date returnDate;

    // Constructor
    public Loan(Item item, Student borrower, Date startDate) {
        this.item = item;
        this.borrower = borrower;
        this.startDate = startDate;
        this.returnDate = null;
    }

    public Item getItem() {
        return this.item;
    }

    public Student getBorrower() {
        return this.borrower;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        if (returnDate != null) {
            return "Item '" + this.item.getTitle() + "' borrowed by " + this.borrower + " from " + this.startDate
                    + " to " + this.returnDate
                    + ".";
        } else {
            return "Item '" + this.item.getTitle() + "' borrowed by " + this.borrower + " since " + this.startDate
                    + ".";
        }
    }

}
