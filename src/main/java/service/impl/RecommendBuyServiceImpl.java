package service.impl;

import dao.RecommendBuyDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import po.RecommendBuy;
import service.RecommendBuyService;

/**
 * @author FHJ
 * @date 2019/10/26 17:21
 */
@Service("recommendBuyService")
public class RecommendBuyServiceImpl implements RecommendBuyService {

    @Autowired
    RecommendBuyDao recommendBuyDao;

    @Override
    public Integer addRecommend(RecommendBuy recommendBuy) {
        return recommendBuyDao.addRecommend(recommendBuy);
    }
}
