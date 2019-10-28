package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import po.Message;
import po.RecommendBuy;
import service.RecommendBuyService;

import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author FHJ
 * @date 2019/10/26 18:44
 */
@CrossOrigin
@Controller
public class RecommendController {

    @Autowired
    RecommendBuyService recommendBuyService;

    @RequestMapping("/addRecommend.do")
    @ResponseBody
    public Message addRecommend(RecommendBuy recommendBuy) {
        // 荐购的书名
        String bookName = recommendBuy.getBookName();
        // 作者名
        String authorName = recommendBuy.getAuthorName();

        Message message = new Message();

        if (bookName == null || authorName == null || bookName.equals("") || authorName.equals("")) {
            return message.fail("必要数据不能为空！");
        }

        // 添加荐购时间
        Date date = new Date();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        recommendBuy.setRecommendTime(sdf.format(date));
        // 设置已读标志为0
        recommendBuy.setIsRead(0);

        int state = recommendBuyService.addRecommend(recommendBuy);

        if (state == 1) {
            return message.success("成功添加一条数据！");
        } else {
            return message.fail("数据添加失败！");
        }
    }

    @RequestMapping("/findRecommendBookNotRead.do")
    @ResponseBody
    public Message findRecommendBookNotRead(){
        Message message = new Message();
        List<RecommendBuy> book = new ArrayList();
        try{
            book = recommendBuyService.findRecommendBookNotRead();
        }catch (Exception e){
            e.printStackTrace();
            return message.fail();
        }
        Map<String, List> map = new HashMap<>();
        map.put("listBook", book);
        message.setReturnData(map);
        return message.success();
    }

    @RequestMapping("/readRecommendBook.do")
    @ResponseBody
    public Message readRecommendBook(){
        Message message = new Message();
        try{
            recommendBuyService.readRecommendBook();
        }catch (Exception e){
            e.printStackTrace();
            return message.fail();
        }
        return message.success();
    }
}
