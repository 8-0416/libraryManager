package service.impl;

import dao.CollectionDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import po.LibraryCollection;
import service.CollectionService;

/**
 * @author 0416
 * @date 2019/10/29
 **/
@Service("CollectionService")
@Transactional(rollbackFor = Exception.class)
public class CollectionServiceImpl implements CollectionService {
    @Autowired
    CollectionDao collectionDao;

    @Override
    public LibraryCollection findCollectionByBookId(Integer integer) {
        return collectionDao.findCollectionByBookId(integer);
    }

    @Override
    public void addCollection(LibraryCollection collection) {
        collectionDao.addCollection(collection);
    }
}
