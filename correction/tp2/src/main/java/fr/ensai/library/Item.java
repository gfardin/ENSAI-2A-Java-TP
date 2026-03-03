package fr.ensai.library;

public abstract class Item {
    protected String title;
    protected int year;
    protected int pageCount;

    public Item(String title, int year, int pageCount) {
        this.title = title;
        this.year = year;
        this.pageCount = pageCount;
    }

    // Abstract method to enforce implementation in child classes
    public abstract String toString();

    public String getTitle() {
        return title;
    }

}
