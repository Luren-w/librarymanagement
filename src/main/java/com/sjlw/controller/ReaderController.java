package com.sjlw.controller;

import com.sjlw.bean.*;
import com.sjlw.service.IAdminService;
import com.sjlw.service.IReaderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

@Controller
@RequestMapping("reader")
public class ReaderController {
    @Autowired
    private IReaderService readerService;

    @Autowired
    private IAdminService adminService;

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



    @RequestMapping("login.do")
    public ModelAndView login(Reader reader,HttpServletRequest request, HttpSession session){
        String role=request.getParameter("role");
        ModelAndView modelAndView=new ModelAndView();
        if("admin".equals(role)){
            boolean flag=adminService.login(reader.getreaderId(),reader.getPassword());
            if(flag){
                modelAndView.setViewName("admin-main");
                session.setAttribute("admin",new Admin(reader.getreaderId(),reader.getPassword(),"admin"));
            }else {
                modelAndView.setViewName("../failure");
            }
        }else {
            boolean flag = readerService.login(reader.getreaderId(), reader.getPassword());
            if (flag) {
                modelAndView.setViewName("main");
                session.setAttribute("reader", reader);
            } else {
                modelAndView.setViewName("../failure");
            }
        }
        return modelAndView;
    }

    @RequestMapping("logout.do")
    public String logout(HttpSession session){
        session.removeAttribute("reader");
        return "../login";
    }

    @RequestMapping("findAll.do")
    public ModelAndView findAll(HttpSession session){
        List<Book> bookList=readerService.findAllBook();
        ModelAndView modelAndView=new ModelAndView();
        Reader reader = (Reader) session.getAttribute("reader");
        ArrayList<Lend> myAllLendList = readerService.myLendList(reader.getreaderId());
        ArrayList<Long> myLendList = new ArrayList<>();
        for (Lend lend : myAllLendList) {
            // 是否已归还
            if (lend.getbackDate() == null) {
                myLendList.add(lend.getbookId());
            }
        }
        modelAndView.setViewName("book-list");
        modelAndView.addObject("bookList",bookList);
        modelAndView.addObject("myLendList", myLendList);
        return modelAndView;
    }

    @RequestMapping("returnbook.do")
    public String returnBookById(HttpServletRequest request, RedirectAttributes redirectAttributes){
        long bookId = Long.parseLong(request.getParameter("bookId"));
        long readerId = ((Reader) request.getSession().getAttribute("reader")).getreaderId();
        if (readerService.returnBook(bookId, readerId)) {
            redirectAttributes.addFlashAttribute("succ", "图书归还成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "图书归还失败！");
        }
        return "redirect:findAll.do";
    }

    @RequestMapping("lendbook.do")
    public String lendbookById(HttpServletRequest request,RedirectAttributes redirectAttributes){
        long bookId =Long.parseLong(request.getParameter("bookId"));
        long readerId =((Reader) request.getSession().getAttribute("reader")).getreaderId();
        if (readerService.lendBook(bookId, readerId)) {
            redirectAttributes.addFlashAttribute("succ", "图书借阅成功！");
        } else {
            redirectAttributes.addFlashAttribute("succ", "图书借阅成功！");
        }
        return "redirect:findAll.do";
    }

    @RequestMapping("bookdetail.do")
    public ModelAndView bookDetail(HttpServletRequest request){
        long bookId = Long.parseLong(request.getParameter("bookId"));
        Book book = readerService.getBook(bookId);
        ModelAndView modelAndView = new ModelAndView("book-detail");
        modelAndView.addObject("detail", book);
        return modelAndView;
    }

    @RequestMapping("mylend.do")
    public ModelAndView myLend(HttpServletRequest request) {
        Reader reader = (Reader) request.getSession().getAttribute("reader");
        ModelAndView modelAndView = new ModelAndView("reader-lend");
        modelAndView.addObject("list", readerService.myLendList(reader.getreaderId()));
        return modelAndView;
    }

    @RequestMapping("readerinfo.do")
    public ModelAndView readerInfo(HttpServletRequest request) {
        Reader reader = (Reader) request.getSession().getAttribute("reader");
        ReaderInfo readerInfo = readerService.getReaderInfo(reader.getreaderId());
        ModelAndView modelAndView = new ModelAndView("reader-info");
        modelAndView.addObject("readerinfo", readerInfo);
        return modelAndView;
    }

    @RequestMapping("readerinfoedit.do")
    public ModelAndView readerInfoEditReader(HttpServletRequest request) {
        Reader reader = (Reader) request.getSession().getAttribute("reader");
        ReaderInfo readerInfo = readerService.getReaderInfo(reader.getreaderId());
        ModelAndView modelAndView = new ModelAndView("reader-info-edit");
        modelAndView.addObject("readerinfo", readerInfo);
        return modelAndView;
    }

    @RequestMapping("readeredit.do")
    public String readerInfoEditDoReader(HttpServletRequest request, String name, String sex, String birth, String address, String phone, RedirectAttributes redirectAttributes) {
        Reader reader = (Reader) request.getSession().getAttribute("reader");
        ReaderInfo readerInfo = getReaderInfo(reader.getreaderId(), name, sex, birth, address, phone);
        if (readerService.editReaderInfo(readerInfo) && readerService.editReader(readerInfo)) {
            Reader readerNew = readerService.findReaderByReaderId(reader.getreaderId());
            request.getSession().setAttribute("reader", readerNew);
            redirectAttributes.addFlashAttribute("succ", "信息修改成功！");
        } else {
            redirectAttributes.addFlashAttribute("error", "信息修改失败！");
        }
        return "redirect:readerinfo.do";
    }
    
    @RequestMapping("toreaderrepassword.do")
    public ModelAndView reReaderPassword() {
        return new ModelAndView("reader-repassword");
    }
    
    @RequestMapping("readerrepassword.do")
    public String reReaderPasswordDo(HttpServletRequest request, String oldPassword, String newPassword, String reNewPassword, RedirectAttributes redirectAttributes) {
        Reader reader = (Reader) request.getSession().getAttribute("reader");
        long id = reader.getreaderId();
        String password = readerService.getReaderPassword(id);
        if (password.equals(oldPassword)) {
            if (readerService.readerRePassword(id, newPassword)) {
                redirectAttributes.addFlashAttribute("succ", "密码修改成功！");
                return "redirect:/reader/toreaderrepassword.do";
            } else {
                redirectAttributes.addFlashAttribute("error", "密码修改失败！");
                return "redirect:/reader/toreaderrepassword.do";
            }
        } else {
            redirectAttributes.addFlashAttribute("error", "旧密码错误！");
            return "redirect:/reader/toreaderrepassword.do";
        }
    }


}
