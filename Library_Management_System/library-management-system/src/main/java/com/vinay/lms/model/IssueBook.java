package com.vinay.lms.model;

import java.time.LocalDate;

public class IssueBook {
    private int issueId;
    private int memberId;
    private int bookId;
    private LocalDate issueDate;
    private LocalDate dueDate;
    private boolean returned;
    private double fine;
    private LocalDate returnDate;

    public IssueBook(int issueId, int memberId, int bookId, LocalDate issueDate, LocalDate dueDate, boolean returned) {
        this.issueId = issueId;
        this.memberId = memberId;
        this.bookId = bookId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.returned = returned;
        this.returnDate = null;
        this.fine = 0;
    }

    public IssueBook(int issueId,
                     int memberId,
                     int bookId,
                     LocalDate issueDate,
                     LocalDate dueDate,
                     LocalDate returnDate,
                     boolean returned,
                     double fine) {

        this.issueId = issueId;
        this.memberId = memberId;
        this.bookId = bookId;
        this.issueDate = issueDate;
        this.dueDate = dueDate;
        this.returnDate = returnDate;
        this.returned = returned;
        this.fine = fine;
    }

    public int getIssueId() {
        return issueId;
    }

    public void setIssueId(int issueId) {
        this.issueId = issueId;
    }

    public int getMemberId() {
        return memberId;
    }

    public void setMemberId(int memberId) {
        this.memberId = memberId;
    }

    public LocalDate getIssueDate() {
        return issueDate;
    }

    public void setIssueDate(LocalDate issueDate) {
        this.issueDate = issueDate;
    }

    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    public LocalDate getDueDate() {
        return dueDate;
    }

    public void setDueDate(LocalDate dueDate) {
        this.dueDate = dueDate;
    }

    public boolean isReturned() {
        return returned;
    }

    public void setReturned(boolean returned) {
        this.returned = returned;
    }


    public double getFine() {
        return fine;
    }

    public void setFine(double fine) {
        this.fine = fine;
    }

    public LocalDate getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(LocalDate returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public String toString() {
        return "IssueBook{" +
                "issueId=" + issueId +
                ", memberId=" + memberId +
                ", bookId=" + bookId +
                ", issueDate=" + issueDate +
                ", dueDate=" + dueDate +
                ", returned=" + returned +
                ", fine=" + fine +
                ", returnDate=" + returnDate +
                '}';
    }
}

