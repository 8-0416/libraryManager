package controller;

import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import po.Book;
import po.Message;
import po.Page;
import service.BookService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 0416
 * @date 2019/10/1
 **/
@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    private final int recordOfOnePage = 10;

    @RequestMapping("/findBookByPageNum.do")
    @ResponseBody
    public Message findBookBypageNum(Integer pageNum){
        Page page = new Page(pageNum, recordOfOnePage + 1);
        Message message = new Message();
        List<Book> book;
        try {
            book = bookService.findBookByPageNum(page);
        }catch (Exception e){
            e.printStackTrace();
            return message.fail();
        }
        Map<String, List> map = new HashMap<>();
        map.put("listBook", book);
        message.setReturnData(map);
        return message.success();
    }

    @RequestMapping(value="/findBookByfield.do")
    @ResponseBody
    public Message findBookByfield(String field, String info){
        List<Book> book ;
        Message message = new Message();
        try{
            book = bookService.findBookByfield(field, info);
        }catch (Exception e){
            e.printStackTrace();
            return message.fail();
        }
        Map<String, List> map = new HashMap<>();
        map.put("listBook", book);
        message.setReturnData(map);
        return message.success();
    }

    @RequestMapping("/entryBook.do")
    @ResponseBody
    public Message entryBook(@RequestBody Book book){
        Message message = new Message();
        try{
            bookService.entryBook(book);
        }catch (Exception e){
            e.printStackTrace();
            return message.fail();
        }
        return message.success();
    }

    @RequestMapping("/updateBookInfo.do")
    @ResponseBody
    public Message updateBookInfo(@RequestBody Book book){
        Message message = new Message();
        Book newBook;
        try{
            newBook = bookService.findBookById(book.getBookId());
            if(newBook == null){
                return message.fail("此书不存在");
            }
        }catch (Exception e){
            e.printStackTrace();
            return message.fail();
        }
        try{
            bookService.updateBookInfo(book);
        }catch (Exception e){
            e.printStackTrace();
            return message.fail();
        }
        return message.success();
    }

    @RequestMapping("/deleteBook.do")
    @ResponseBody
    public Message deleteBook(String bookId){
        Message message = new Message();
        try{
            if(bookService.findBookById(bookId) == null){
                return message.fail("此书不存在");
            }
        }catch (Exception e){
            e.printStackTrace();
            return message.fail();
        }
        try{
            bookService.deleteBook(bookId);
        }catch (Exception e){
            e.printStackTrace();
            return message.fail();
        }
        return message.success();
    }

    @RequestMapping("/findBookDetail.do")
    @ResponseBody
    public Message findBookDetail(String bookId){
        Message message = new Message();
        List<Book> books;
        try{
            books = bookService.findBookDetail(bookId);
        }catch (Exception e){
            e.printStackTrace();
            return message.fail("查询失败!");
        }
        Map<String, List> map = new HashMap<>();
        map.put("listBook", books);
        message.setReturnData(map);
        return message.success();
    }
}
