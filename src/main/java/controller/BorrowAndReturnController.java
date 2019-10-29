package controller;

import dto.BorrowRecordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import po.BorrowRecord;
import po.Message;
import service.BorrowAndReturnService;

import javax.swing.text.MaskFormatter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * @author 0416
 * @date 2019/10/27
 **/
@CrossOrigin
@Controller
public class BorrowAndReturnController {

    @Autowired
    BorrowAndReturnService borrowAndReturnService;

    @RequestMapping("/addBorrowRecord.do")
    @ResponseBody
    public Message addBorrowRecord(BorrowRecord borrowRecord) {
        Message message = new Message();
        try {
            borrowAndReturnService.addBorrowRecord(borrowRecord);
        } catch (Exception e) {
            e.printStackTrace();
            return message.fail();
        }
        return message.success();
    }

    @RequestMapping("/findBorrowRecord.do")
    @ResponseBody
    public Message findBorrowRecord() {
        Message message = new Message();
        List<BorrowRecord> borrowRecords;
        try {
            borrowRecords = borrowAndReturnService.findBorrowRecord();
        } catch (Exception e) {
            e.printStackTrace();
            return message.fail();
        }
        Map<String, List> map = new HashMap<>();
        map.put("listBook", borrowRecords);
        message.setReturnData(map);
        return message.success();
    }

    @RequestMapping("/findAllRecordByUserId.do")
    @ResponseBody
    public Message findAllRecordByUserId(String userId, Integer startPage, Integer pageSize) {
        Message message = new Message();
        if (userId == null || userId.equals("") || startPage == null || startPage <= 0 || pageSize == null || pageSize <= 0) {
            return message.setCodeAndPrompt("-1", "必要数据为空或者数值不符合要求！");
        }

        Integer recordNum = borrowAndReturnService.findTotalNumByUserId(userId);

        if (recordNum == 0) {
            return message.setCodeAndPrompt("0", "查询到0条数据！");
        }

        if ((startPage - 1) * pageSize > recordNum) {
            return message.setCodeAndPrompt("2", "页码超出最大范围！");
        }

        List<BorrowRecordDto> borrowRecordDtoList = borrowAndReturnService.findBorrowRecordByUserId(userId, (startPage - 1) * pageSize, pageSize);

        Map<String, Object> map = new HashMap<>();
        map.put("totalNum", recordNum);
        map.put("recordList", borrowRecordDtoList);
        message.setCodeAndPrompt("1", "查询数据成功！");
        message.setReturnData(map);

        return message;
    }

    @RequestMapping("/renewByUserIdAndBookId.do")
    @ResponseBody
    public Message renewByUserIdAndBookId(String userId, String bookId) {
        Message message = new Message();

        if (userId == null || userId.equals("") || bookId == null || bookId.equals("")) {
            return message.setCodeAndPrompt("0", "必要数据为空！");
        }

        // 查询这本数续借的次数
        Integer renewNum = borrowAndReturnService.findBorrowTimeByUserIdAndBookId(userId, bookId);

        if (renewNum == 0) {  // 未续借

            Integer flag = borrowAndReturnService.updateBorrowTimeByUserIdAndBookId(userId, bookId);

            if (flag != null && flag == 1) {
                return message.setCodeAndPrompt("1", "续借成功！");
            } else {
                return message.setCodeAndPrompt("-1", "续借失败！");
            }
        } else {  // 续借过
            return message.setCodeAndPrompt("2", "已经续借过一次了，不能续借！");
        }
    }

}
