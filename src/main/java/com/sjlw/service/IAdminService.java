package com.sjlw.service;

import com.sjlw.bean.Book;
import com.sjlw.bean.Lend;
import com.sjlw.bean.ReaderInfo;

import java.util.ArrayList;

public interface IAdminService {
    boolean login(long adminId, String password);

    ArrayList<Book> getAllBooks();

    Book getBook(long bookId);

    boolean editBook(Book book);

    boolean deleteBook(long bookId);

    boolean addBook(Book book);

    ArrayList<ReaderInfo> readerInfos();

    ReaderInfo getReaderInfo(long readerId);

    boolean deleteReaderInfo(long readerId);

    boolean deleteReaderCard(long readerId);

    boolean editReaderInfo(ReaderInfo readerInfo);

    boolean editReaderCard(ReaderInfo readerInfo);

    long addReaderInfo(ReaderInfo readerInfo);

    boolean addReaderCard(ReaderInfo readerInfo, String password);

    ArrayList<Lend> lendList();

    int deleteLend(long serNum);

    String getAdminPassword(long adminId);

    boolean adminRePassword(long adminId, String newPassword);
}
