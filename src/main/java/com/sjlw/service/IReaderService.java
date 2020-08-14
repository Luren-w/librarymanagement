package com.sjlw.service;

import com.sjlw.bean.Book;
import com.sjlw.bean.Lend;
import com.sjlw.bean.Reader;
import com.sjlw.bean.ReaderInfo;

import java.util.ArrayList;
import java.util.List;

public interface IReaderService {
    boolean login(long readerId, String password);
    List<Book> findAllBook();
    boolean lendBook(long bookId,long readerId);
    boolean returnBook(long bookId, long readerId);
    ArrayList<Lend> myLendList(long readerId);
    Book getBook(long bookId);
    ReaderInfo getReaderInfo(long readerId);

    boolean editReaderInfo(ReaderInfo readerInfo);
    boolean editReader(ReaderInfo readerInfo);
    Reader findReaderByReaderId(long readerId);

    boolean hasMatchReader(long readerId,String password);

    boolean hasMatchAdmin(long adminId,String password);

    String getAdminUsername(long id);

    boolean readerRePassword(long readerId, String newPassword);

    String getReaderPassword(long readerId);
}
