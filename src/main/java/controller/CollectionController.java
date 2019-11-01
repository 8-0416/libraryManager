package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import po.Book;
import po.LibraryCollection;
import po.Message;
import service.BookService;
import service.CollectionService;

/**
 * @author 0416
 * @date 2019/10/29
 **/
@CrossOrigin
@Controller
public class CollectionController {
    @Autowired
    CollectionService collectionService;
    @Autowired
    BookService bookService;

    @RequestMapping("/addCollection.do")
    @ResponseBody
    public Message addCollection(@RequestBody LibraryCollection collection){
        Message message = new Message();

        String bookId = collection.getBookId();
        if(bookId == null || "".equals(bookId)){
            return message.setCodeAndPrompt("-1", "bookId不能为空");
        }

        Book book;
        try{
            book = bookService.findBookById(bookId);
        }catch (Exception e){
            e.printStackTrace();
            return message.fail();
        }
        if(book == null){
            return message.setCodeAndPrompt("-1", "此书不存在，请重新输入bookId");
        }

        LibraryCollection libraryCollection;
        try{
            libraryCollection = collectionService.findCollectionByBookId(bookId);
        }catch (Exception e){
            e.printStackTrace();
            return message.fail();
        }
        if(libraryCollection != null){
            return message.setCodeAndPrompt("1", "该书籍的馆藏信息已存在");
        }

        String type = collection.getType();
        String searchId = collection.getSearchId();
        String place = collection.getPlaceName();
        String placeId = collection.getPlaceId();
        String state = collection.getState();
        if(type == null || "".equals(type) || searchId == null || "".equals(searchId) || place == null ||
        "".equals(place) || placeId == null || "".equals(placeId) || state == null || "".equals(state)){
            return message.setCodeAndPrompt("-1", "图书类别、索引书号、书库名称、" +
                    "书库位置编号、馆藏信息均不能为空");
        }

        try{
            collectionService.addCollection(collection);
        }catch (Exception e){
            e.printStackTrace();
            return message.fail("添加失败");
        }
        return message.success();
    }
}
