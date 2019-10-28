package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import po.Message;
import service.PageService;

import java.util.HashMap;
import java.util.Map;

/**
 * @author 0416
 * @date 2019/10/28
 **/
@CrossOrigin
@Controller
public class PageController {
    @Autowired
    private PageService pageService;
    private final int recordOfOnePage = 10;

    @RequestMapping("/getPageNumber.do")
    @ResponseBody
    public Message getPageNumber(){
        Message message = new Message();
        Integer recordNumber;
        try{
            recordNumber = pageService.getPageNumber();
        }catch (Exception e){
            e.printStackTrace();
            return message.fail();
        }
        Integer pageNumber = recordNumber / recordOfOnePage;
        if(recordNumber % recordOfOnePage != 0){
            pageNumber++;
        }
        Map<String,Integer> map = new HashMap<>();
        map.put("pageNumber",pageNumber);
        message.setReturnData(map);
        return message.success();
    }
}
