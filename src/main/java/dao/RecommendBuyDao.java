package dao;

import po.RecommendBuy;

/**
 * @author FHJ
 * @date 2019/10/26 11:15
 * 图书荐购
 */
public interface RecommendBuyDao {
    // 添加荐购
    Integer addRecommend(RecommendBuy recommendBuy);
}
