package com.sjlw.service.impl;
import com.sjlw.bean.Admin;
import com.sjlw.bean.Book;
import com.sjlw.bean.Lend;
import com.sjlw.bean.ReaderInfo;
import com.sjlw.dao.IAdminDao;
import com.sjlw.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;

@Service
public class AdminServiceImpl implements IAdminService {

    @Autowired
    private IAdminDao adminDao;
    @Override
    public boolean login(long adminId, String password) {
       Admin admin=adminDao.findAdminByUsername(adminId);
        if(admin!=null&&admin.getPassword().equals(password)){
            return true;
        }else {
            return false;
        }
    }

    @Override
    public ArrayList<Book> getAllBooks() {
        return adminDao.getAllBooks();
    }

    @Override
    public Book getBook(long bookId) {
        return adminDao.getBook(bookId);
    }

    @Override
    public boolean editBook(Book book) {
        return adminDao.editBook(book);
    }

    @Override
    public boolean deleteBook(long bookId) {
        return adminDao.deleteBook(bookId);
    }

    @Override
    public boolean addBook(Book book) {
        return adminDao.addBook(book);
    }

    @Override
    public ArrayList<ReaderInfo> readerInfos() {
        return adminDao.getAllReaderInfo();
    }

    @Override
    public ReaderInfo getReaderInfo(long readerId) {
        return adminDao.findReaderInfoByReaderId(readerId);
    }

    @Override
    public boolean deleteReaderInfo(long readerId) {
        return adminDao.deleteReaderInfo(readerId);
    }

    @Override
    public boolean deleteReaderCard(long readerId) {
        return adminDao.deleteReaderCard(readerId);
    }

    @Override
    public boolean editReaderInfo(ReaderInfo readerInfo) {
        return adminDao.editReaderInfo(readerInfo);
    }

    @Override
    public boolean editReaderCard(ReaderInfo readerInfo) {
        return adminDao.editReaderCard(readerInfo);
    }

    @Override
    public long addReaderInfo(ReaderInfo readerInfo) {
        return adminDao.addReaderInfo(readerInfo);
    }

    @Override
    public boolean addReaderCard(ReaderInfo readerInfo, String password) {
        return adminDao.addReaderCard(readerInfo,password);
    }

    @Override
    public ArrayList<Lend> lendList() {
        return adminDao.lendList();
    }

    @Override
    public int deleteLend(long serNum) {
        return adminDao.deleteLend(serNum);
    }

    @Override
    public String getAdminPassword(long adminId) {
        return adminDao.getPassword(adminId);
    }

    @Override
    public boolean adminRePassword(long adminId, String newPassword) {
        return adminDao.resetPassword(adminId,newPassword);
    }


}
