package com.sjlw.dao;

import com.sjlw.bean.Admin;
import com.sjlw.bean.Book;
import com.sjlw.bean.Lend;
import com.sjlw.bean.ReaderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;

public interface IAdminDao {
    Admin findAdminByUsername(long adminId);
    ArrayList<Book> getAllBooks();
    Book getBook(long bookId);
    boolean editBook(Book book);
    boolean deleteBook(long bookId);
    boolean addBook(Book book);
    ArrayList<ReaderInfo> getAllReaderInfo();

    ReaderInfo findReaderInfoByReaderId(long readerId);

    boolean deleteReaderInfo(long readerId);

    boolean deleteReaderCard(long readerId);

    boolean editReaderInfo(ReaderInfo readerInfo);

    boolean editReaderCard(ReaderInfo readerInfo);

    long addReaderInfo(ReaderInfo readerInfo);

    boolean addReaderCard(@Param("readerInfo") ReaderInfo readerInfo, @Param("password") String password);

    ArrayList<Lend> lendList();

    int deleteLend(long serNum);

    String getPassword(long adminId);

    boolean resetPassword(@Param("adminId") long adminId, @Param("newPassword") String newPassword);
}
