package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
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

    @RequestMapping("/addCollection.do")
    @ResponseBody
    public Message addCollection(@RequestBody LibraryCollection collection){
        Message message = new Message();
        try{
            collectionService.addCollection(collection);
        }catch (Exception e){
            e.printStackTrace();
            return message.fail();
        }
        return message.success();
    }
}
