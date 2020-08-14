package com.sjlw.controller;

import com.sjlw.bean.Admin;
import com.sjlw.bean.Book;
import com.sjlw.bean.ReaderInfo;
import com.sjlw.service.IAdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

@Controller
@RequestMapping("admin")
public class AdminController {
    @Autowired
    private IAdminService adminService;

    private Date getDate(String pubstr) {
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            return df.parse(pubstr);
        } catch (ParseException e) {
            e.printStackTrace();
            return new Date();
        }
    }

    private ReaderInfo getReaderInfo(long readerId, String name, String sex, String birth, String address, String phone) {
        ReaderInfo readerInfo = new ReaderInfo();
        Date date = new Date();
        try {
            SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd");
            date = df.parse(birth);
        } catch (ParseException e) {
            e.printStackTrace();
        }
        readerInfo.setAddress(address);
        readerInfo.setName(name);
        readerInfo.setreaderId(readerId);
        readerInfo.setPhone(phone);
        readerInfo.setSex(sex);
        readerInfo.setBirth(date);
        return readerInfo;
    }

    @RequestMapping("logout.do")
    public String logout(HttpSession session){
        session.removeAttribute("admin");
        return "../login";
    }

    @RequestMapping("adminbook.do")
    public ModelAndView adminBooks() {
        ArrayList<Book> books = adminService.getAllBooks();
        ModelAndView modelAndView = new ModelAndView("admin-book-list");
        modelAndView.addObject("books", books);
        return modelAndView;
    }

    @RequestMapping("adminbookdetail.do")
    public ModelAndView adminBookDetail(HttpServletRequest request) {
        long bookId = Long.parseLong(request.getParameter("bookId"));
        Book book = adminService.getBook(bookId);
        ModelAndView modelAndView = new ModelAndView("admin-book-detail");
        modelAndView.addObject("detail", book);
        return modelAndView;
    }

    @RequestMapping("updatebook.do")
    public ModelAndView bookEdit(HttpServletRequest request) {
        long bookId = Long.parseLong(request.getParameter("bookId"));
        Book book = adminService.getBook(bookId);
        ModelAndView modelAndView = new ModelAndView("admin-book-edit");
        modelAndView.addObject("detail", book);
        return modelAndView;
    }

    @RequestMapping("bookedit.do")
    public String bookEditDo(@RequestParam(value = "pubstr") String pubstr, Book book, RedirectAttributes redirectAttributes) {
        book.setpubDate(getDate(pubstr));
        if (adminService.editBook(book)) {
            redirectAttributes.addFlashAttribute("succ", "图书修改成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "图书修改失败！");
        }
        return "redirect:/admin/adminbook.do";
    }

    @RequestMapping("deletebook.do")
    public String deleteBook(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        long bookId = Long.parseLong(request.getParameter("bookId"));
        if (adminService.deleteBook(bookId)) {
            redirectAttributes.addFlashAttribute("succ", "图书删除成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "图书删除失败！");
        }
        return "redirect:/admin/adminbook.do";
    }

    @RequestMapping("booktoadd.do")
    public ModelAndView addBook() {
        return new ModelAndView("admin-book-add");
    }

    @RequestMapping("bookadd.do")
    public String addBookDo(@RequestParam(value = "pubstr") String pubstr, Book book, RedirectAttributes redirectAttributes) {
        book.setpubDate(getDate(pubstr));
        if (adminService.addBook(book)) {
            redirectAttributes.addFlashAttribute("succ", "图书添加成功！");
        } else {
            redirectAttributes.addFlashAttribute("succ", "图书添加失败！");
        }
        return "redirect:/admin/adminbook.do";
    }

    @RequestMapping("allreaders.do")
    public ModelAndView allBooks() {
        ArrayList<ReaderInfo> readers = adminService.readerInfos();
        ModelAndView modelAndView = new ModelAndView("admin-readers");
        modelAndView.addObject("readers", readers);
        return modelAndView;
    }

    @RequestMapping("readertoedit.do")
    public ModelAndView readerInfoEdit(HttpServletRequest request) {
        long readerId = Long.parseLong(request.getParameter("readerId"));
        ReaderInfo readerInfo = adminService.getReaderInfo(readerId);
        ModelAndView modelAndView = new ModelAndView("admin-reader-edit");
        modelAndView.addObject("readerInfo", readerInfo);
        return modelAndView;
    }

    @RequestMapping("readeredit.do")
    public String readerInfoEditDo(HttpServletRequest request, String name, String sex, String birth, String address, String phone, RedirectAttributes redirectAttributes) {
        long readerId = Long.parseLong(request.getParameter("readerId"));
        ReaderInfo readerInfo = getReaderInfo(readerId, name, sex, birth, address, phone);
        if (adminService.editReaderInfo(readerInfo) && adminService.editReaderCard(readerInfo)) {
            redirectAttributes.addFlashAttribute("succ", "读者信息修改成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "读者信息修改失败！");
        }
        return "redirect:/admin/allreaders.do";
    }


    @RequestMapping("readerdelete.do")
    public String readerDelete(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        long readerId = Long.parseLong(request.getParameter("readerId"));
        if (adminService.deleteReaderInfo(readerId) && adminService.deleteReaderCard(readerId)) {
            redirectAttributes.addFlashAttribute("succ", "删除成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "删除失败！");
        }
        return "redirect:/admin/allreaders.do";
    }

    @RequestMapping("readertoadd.do")
    public ModelAndView readerInfoAdd() {
        return new ModelAndView("admin-reader-add");
    }

    @RequestMapping("readeradd.do")
    public String readerInfoAddDo(String name, String sex, String birth, String address, String phone, String password, RedirectAttributes redirectAttributes) {
        ReaderInfo readerInfo = getReaderInfo(0, name, sex, birth, address, phone);
        long readerId = adminService.addReaderInfo(readerInfo);
        readerInfo.setreaderId(readerId);
        if (readerId > 0 && adminService.addReaderCard(readerInfo, password)) {
            redirectAttributes.addFlashAttribute("succ", "添加读者信息成功！");
        } else {
            redirectAttributes.addFlashAttribute("succ", "添加读者信息失败！");
        }
        return "redirect:/admin/allreaders.do";
    }

    @RequestMapping("lendlist.do")
    public ModelAndView lendList(HttpServletRequest request) {
        ModelAndView modelAndView = new ModelAndView("admin-lend-list");
        modelAndView.addObject("list", adminService.lendList());
        return modelAndView;
    }

    @RequestMapping("deletelend.do")
    public String deleteLend(HttpServletRequest request, RedirectAttributes redirectAttributes) {
        long serNum = Long.parseLong(request.getParameter("serNum"));
        if (adminService.deleteLend(serNum) > 0) {
            redirectAttributes.addFlashAttribute("succ", "记录删除成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "记录删除失败！");
        }
        return "redirect:/admin/lendlist.do";
    }

    @RequestMapping("adminmain.do")
    public ModelAndView adminmain() {
        return new ModelAndView("admin-main");
    }

    @RequestMapping("toadminrepassword.do")
    public ModelAndView reAdminPassword() {
        return new ModelAndView("admin-repassword");
    }

    @RequestMapping("adminrepassword.do")
    public String reAdminPasswordDo(HttpServletRequest request, String oldPassword, String newPassword, String reNewPassword, RedirectAttributes redirectAttributes) {
        Admin admin = (Admin) request.getSession().getAttribute("admin");
        long id = admin.getAdminId();
        String password = adminService.getAdminPassword(id);
        if (password.equals(oldPassword)) {
            if (adminService.adminRePassword(id, newPassword)) {
                redirectAttributes.addFlashAttribute("succ", "密码修改成功！");
                return "redirect:/admin/toadminrepassword.do";
            } else {
                redirectAttributes.addFlashAttribute("error", "密码修改失败！");
                return "redirect:/admin/toadminrepassword.do";
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "旧密码错误！");
            return "redirect:/admin/toadminrepassword.do";
        }
    }
}
