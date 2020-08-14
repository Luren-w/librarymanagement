package com.sjlw.dao;

import com.sjlw.bean.Book;
import com.sjlw.bean.Lend;
import com.sjlw.bean.Reader;
import com.sjlw.bean.ReaderInfo;
import org.apache.ibatis.annotations.Param;

import java.util.ArrayList;
import java.util.List;

public interface IReaderDao {
    Reader findReaderByUsername(long readerId);
    List<Book> findAllBook();
    boolean lendbookById(@Param("bookId") long bookId);
    boolean lendbookUpdata(@Param("bookId") long bookId, @Param("readerId") long readerId);
    boolean returnBook(long bookId);
    boolean returnBookUpdata(@Param("bookId") long bookId, @Param("readerId") long readerId);
    ArrayList<Lend> myLendList(long readerId);
    Book getBook(long bookId);
    ReaderInfo findReaderInfoByReaderId(long readerId);

    boolean editReaderInfo(ReaderInfo readerInfo);

    boolean editReader(ReaderInfo readerInfo);

    Reader findReaderByReaderId(long readerId);

    boolean getIdMatchCount(long readerId, String password);

    boolean getMatchCount(long adminId, String password);

    String getUsername(long adminId);

    boolean resetPassword(@Param("readerId") long readerId, @Param("newPassword") String newPassword);

    String getPassword(long readerId);
}
