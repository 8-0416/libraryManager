package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;
import po.Book;
import po.Message;
import po.Page;
import service.BookService;
import utils.UploadImageUtil;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 0416
 * @date 2019/10/1
 **/
@CrossOrigin
@Controller
public class BookController {
    @Autowired
    private BookService bookService;
    private final int RECORD_OF_ONE_PAGE = 10;
    private final String DEFAULT_PICTURE = "image\\default.jpg";

    @RequestMapping("/findBookByPageNum.do")
    @ResponseBody
    public Message findBookByPageNum(Integer pageNum){
        Page page = new Page(pageNum, RECORD_OF_ONE_PAGE);
        Message message = new Message();
        List<Book> book;
        try {
            book = bookService.findBookByPageNum(page);
        }catch (Exception e){
            e.printStackTrace();
            return message.fail();
        }
        if(book.isEmpty()){
            return message.success("没有更多数据了……");
        }
        Map<String, List> map = new HashMap<>();
        map.put("listBook", book);
        message.setReturnData(map);
        return message.success();
    }

    @RequestMapping(value="/findBookByField.do")
    @ResponseBody
    public Message findBookByField(String field, String info){
        List<Book> book ;
        Message message = new Message();
        try{
            book = bookService.findBookByField(field, info);
        }catch (Exception e){
            e.printStackTrace();
            return message.fail();
        }
        if(book.isEmpty()){
            return message.setCodeAndPrompt("0", "查询不到符合条件的数据!");
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

        String isbn = book.getIsbn();
        String name = book.getBookName();
        String author = book.getAuthor();
        String bookPage = book.getBookPage();
        String publisher = book.getPublisher();
        if(isbn == null || "".equals(isbn) || name == null || "".equals(name) || author == null
                || "".equals(author) || bookPage == null || "".equals(bookPage) || publisher == null
                || "".equals(publisher)){
            return message.setCodeAndPrompt("-1", "isbn、书籍名称、作者、出版社、页数" +
                    "均不能为空");
        }

        Integer bookId = bookService.findBookByIsbn(isbn);
        if(bookId != null && bookId != 0 ){
            return message.fail("该isbn已存在，请重新输入！");
        }

        try{
            bookService.entryBook(book);
        }catch (Exception e){
            e.printStackTrace();
            return message.fail("添加失败！");
        }

        bookId = bookService.findBookByIsbn(isbn);
        Map<String, Integer> map = new HashMap<>();
        map.put("bookId", bookId);
        message.setReturnData(map);
        return message.success("添加成功！");
    }

    @RequestMapping("/entryImage.do")
    @ResponseBody
    public Message entryImage(MultipartFile bookPicture, String bookIsbn){
        Message message = new Message();

        try{
            String fileName = new UploadImageUtil().upload(bookPicture);
            bookService.entryImage(fileName, bookIsbn);
        }catch (Exception e){
            e.printStackTrace();
            return message.fail("图片上传失败！");
        }

        return message.success("上传成功！");
    }

    @RequestMapping("/updateBookInfo.do")
    @ResponseBody
    public Message updateBookInfo(@RequestBody Book book){
        Message message = new Message();
        Book newBook;

        try{
            newBook = bookService.findBookById(book.getBookId());
        }catch (Exception e){
            e.printStackTrace();
            return message.fail();
        }

        if(newBook == null){
            return message.fail("此书不存在");
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
        return message.success("删除成功");
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
        if(books.isEmpty()){
            return message.fail("此书不存在");
        }
        Map<String, List> map = new HashMap<>();
        map.put("listBook", books);
        message.setReturnData(map);
        return message.success();
    }

    @RequestMapping("/findBookByRandom.do")
    @ResponseBody
    public Message findBookByRandom(){
        Message message = new Message();
        List<Book> books;
        try{
            books = bookService.findBookByRandom();
        }catch (Exception e){
            e.printStackTrace();
            return message.fail();
        }
        if(books.isEmpty()){
            return message.fail("暂无数据");
        }
        for(Book book : books){
            if(book.getBookPicture() == null){
                book.setBookPicture(DEFAULT_PICTURE);
            }
        }
        Map<String, List> map = new HashMap<>();
        map.put("listBook", books);
        message.setReturnData(map);
        return message.success();
    }
}
