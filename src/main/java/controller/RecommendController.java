package controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.support.MessageSourceAccessor;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import po.Message;
import po.RecommendBuy;
import service.RecommendBuyService;

import java.text.SimpleDateFormat;
import java.util.Date;

/**
 * @author FHJ
 * @date 2019/10/26 18:44
 */
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

        System.out.println(recommendBuy.getAuthorName());
        System.out.println(recommendBuy.getBookName());
        System.out.println(recommendBuy.getIsRead());
        System.out.println(recommendBuy.getPress());
        System.out.println(recommendBuy.getPublicationDate());
        System.out.println(recommendBuy.getRecommendTime());
        System.out.println(recommendBuy.getRecommendId());

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
}
