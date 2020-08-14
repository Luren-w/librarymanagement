package com.sjlw.bean;

import java.util.Date;

public class Lend {
    private long serNum;
    private long bookId;
    private long readerId;
    private Date lendDate;
    private Date backDate;

    public long getserNum() {
        return serNum;
    }

    public void setserNum(long serNum) {
        this.serNum = serNum;
    }

    public long getbookId() {
        return bookId;
    }

    public void setbookId(long bookId) {
        this.bookId = bookId;
    }

    public long getreaderId() {
        return readerId;
    }

    public void setreaderId(long readerId) {
        this.readerId = readerId;
    }

    public Date getlendDate() {
        return lendDate;
    }

    public void setlendDate(Date lendDate) {
        this.lendDate = lendDate;
    }

    public Date getbackDate() {
        return backDate;
    }

    public void setbackDate(Date backDate) {
        this.backDate = backDate;
    }

    @Override
    public String toString() {
        return "Lend{" +
                "serNum=" + serNum +
                ", bookId=" + bookId +
                ", readerId=" + readerId +
                ", lendDate=" + lendDate +
                ", backDate=" + backDate +
                '}';
    }
}
