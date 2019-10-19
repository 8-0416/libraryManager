package dao;

import po.Book;
import po.Page;

import java.util.List;

/**
 * @AUTHOR:0416
 * @DESCRIPTION:
 * @DATE:2019/10/1
 **/
public interface BookDao {

    public Book findBookById(String bookId);

    public List<Book> findBookByPageNum(Page page);

    public List<Book> findBookByfield(String field, String info);

    public void entryBook(Book book);

    public void updateBookInfo(Book book);

    public void deleteBook(String bookId);
}
