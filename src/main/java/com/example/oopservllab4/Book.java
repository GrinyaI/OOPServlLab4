package com.example.oopservllab4;

public class Book {
    private final String Title;
    private final String Author;
    private final String Genre;
    private final String Publishing;
    private final String ISBN;

    public Book(String title, String author, String genre, String publishing, String ISBN) {
        this.Title = title;
        this.Author = author;
        this.Genre = genre;
        this.Publishing = publishing;
        this.ISBN = ISBN;
    }

    public String getTitle() {
        return Title;
    }

    public String getAuthor() {
        return Author;
    }

    public String getGenre() {
        return Genre;
    }

    public String getPublishing() {
        return Publishing;
    }

    public String getISBN() {
        return ISBN;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Book book = (Book) o;

        if (!Title.equals(book.Title)) return false;
        if (!Author.equals(book.Author)) return false;
        if (!Genre.equals(book.Genre)) return false;
        if (!Publishing.equals(book.Publishing)) return false;
        return ISBN.equals(book.ISBN);
    }

    @Override
    public int hashCode() {
        int result = Title.hashCode();
        result = 31 * result + Author.hashCode();
        result = 31 * result + Genre.hashCode();
        result = 31 * result + Publishing.hashCode();
        result = 31 * result + ISBN.hashCode();
        return result;
    }

    @Override
    public String toString() {
        return "Book{" +
                "Title='" + Title + '\'' +
                ", Author='" + Author + '\'' +
                ", Genre='" + Genre + '\'' +
                ", Publishing=" + Publishing +
                ", ISBN='" + ISBN + '\'' +
                '}';
    }
}
