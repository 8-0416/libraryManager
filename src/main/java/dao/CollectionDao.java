package dao;

import po.LibraryCollection;

/**
 * @author 0416
 * @date 2019/10/27
 **/
public interface CollectionDao {

    LibraryCollection findCollectionByBookId(String bookId);

    void addCollection(LibraryCollection collection);
}
