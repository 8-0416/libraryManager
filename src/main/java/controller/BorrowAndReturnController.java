package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import po.BorrowRecord;
import po.Message;
import service.BorrowAndReturnService;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author  0416
 * @date  2019/10/27
 **/
@Controller
public class BorrowAndReturnController {

    @Autowired
    BorrowAndReturnService borrowAndReturnService;

    @RequestMapping("/addBorrowRecord.do")
    @ResponseBody
    public Message addBorrowRecord(BorrowRecord borrowRecord){
        Message message = new Message();
        try{
            borrowAndReturnService.addBorrowRecord(borrowRecord);
        }catch (Exception e){
            e.printStackTrace();
            return message.fail();
        }
        return message.success();
    }

    @RequestMapping("/findBorrowRecord.do")
    @ResponseBody
    public Message findBorrowRecord(){
        Message message = new Message();
        List<BorrowRecord> borrowRecords;
        try{
            borrowRecords = borrowAndReturnService.findBorrowRecord();
        }catch (Exception e){
            e.printStackTrace();
            return message.fail();
        }
        Map<String, List> map = new HashMap<>();
        map.put("listBook", borrowRecords);
        message.setReturnData(map);
        return message.success();
    }
}
