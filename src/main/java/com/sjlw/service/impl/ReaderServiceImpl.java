package com.sjlw.service.impl;

import com.sjlw.bean.Book;
import com.sjlw.bean.Lend;
import com.sjlw.bean.Reader;
import com.sjlw.bean.ReaderInfo;
import com.sjlw.dao.IReaderDao;
import com.sjlw.service.IReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ReaderServiceImpl implements IReaderService {
    @Autowired
    private IReaderDao readerDao;

    @Override
    public boolean login(long readerId, String password) {
        Reader reader=readerDao.findReaderByUsername(readerId);
        if(reader!=null&&reader.getPassword().equals(password)){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public List<Book> findAllBook() {
        return readerDao.findAllBook();
    }



    @Override
    public boolean lendBook(long bookId, long readerId) {
        return readerDao.lendbookById(bookId)&&readerDao.lendbookUpdata(bookId,readerId);
    }

    @Override
    public boolean returnBook(long bookId, long readerId) {
        return readerDao.returnBook(bookId)&&readerDao.returnBookUpdata(bookId,readerId);
    }

    @Override
    public ArrayList<Lend> myLendList(long readerId) {
        return readerDao.myLendList(readerId);
    }

    @Override
    public Book getBook(long bookId) {
        return readerDao.getBook(bookId);
    }

    @Override
    public ReaderInfo getReaderInfo(long readerId) {
        return readerDao.findReaderInfoByReaderId(readerId);
    }

    @Override
    public boolean editReaderInfo(ReaderInfo readerInfo) {
        return readerDao.editReaderInfo(readerInfo);
    }

    @Override
    public boolean editReader(ReaderInfo readerInfo) {
        return readerDao.editReader(readerInfo);
    }

    @Override
    public Reader findReaderByReaderId(long readerId) {
        return readerDao.findReaderByReaderId(readerId);
    }

    @Override
    public boolean hasMatchReader(long readerId,String password) {
        return readerDao.getIdMatchCount(readerId, password);
    }

    @Override
    public boolean hasMatchAdmin(long adminId,String password) {
        return readerDao.getMatchCount(adminId, password);
    }

    @Override
    public String getAdminUsername(long adminId) {
        return readerDao.getUsername(adminId);
    }

    @Override
    public boolean readerRePassword(long readerId, String newPassword) {
        return readerDao.resetPassword(readerId, newPassword);
    }

    @Override
    public String getReaderPassword(long readerId) {
        return readerDao.getPassword(readerId);
    }


}
