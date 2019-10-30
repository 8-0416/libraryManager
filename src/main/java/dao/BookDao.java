package dao;

import po.Book;
import po.Page;

import java.util.List;

/**
 * @author 0416
 * @date 2019/10/1
 **/
public interface BookDao {

    Book findBookById(String bookId);

    List<Book> findBookByPageNum(Page page);

    List<Book> findBookByField(String field, String info);

    void entryBook(Book book);

    void updateBookInfo(Book book);

    void deleteBook(String bookId);

    List<Book> findBookDetail(String bookId);

    void entryImage(String fileName, String bookIsbn);

    Integer findBookByIsbn(String isbn);

    List<Book> findBookByRandom();

}
