package service.impl;

import dao.PageDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import service.PageService;

/**
 * @author 0416
 * @date 2019/10/28
 **/
@Service("PageService")
@Transactional(rollbackFor = Exception.class)
public class PageServiceImpl implements PageService {

    @Autowired
    private PageDao pageDao;

    @Override
    public Integer getPageNumber() {
        return pageDao.getPageNumber();
    }
}
