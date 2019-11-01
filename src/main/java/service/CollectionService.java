package service;

import po.LibraryCollection;

/**
 * @author 0416
 * @date 2019/10/29
 **/
public interface CollectionService {

    LibraryCollection findCollectionByBookId(Integer integer);

    void addCollection(LibraryCollection collection);
}
