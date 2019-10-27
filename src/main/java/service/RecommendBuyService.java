package service;

import po.RecommendBuy;

import java.util.List;

/**
 * @author FHJ
 * @date 2019/10/26 17:21
 */
public interface RecommendBuyService {
    // 添加荐购
    Integer addRecommend(RecommendBuy recommendBuy);

    List<RecommendBuy> findRecommendBookNotRead();

    void readRecommendBook();
}
