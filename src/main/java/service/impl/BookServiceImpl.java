package service.impl;

import dao.BookDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import po.Book;
import po.Page;
import service.BookService;

import java.util.List;

/**
 * @AUTHOR:0416
 * @DESCRIPTION:
 * @DATE:2019/10/1
 **/
@Service("bookService")
@Transactional(rollbackFor = Exception.class)
public class BookServiceImpl implements BookService {
    @Autowired
    private BookDao bookDao;

    @Override
    public Book findBookById(String bookId){
        return this.bookDao.findBookById(bookId);
    }

    @Override
    public List<Book> findBookByPageNum(Page page) {
        return this.bookDao.findBookByPageNum(page);
    }

    @Override
    public List<Book> findBookByfield(String field, String info){
        return this.bookDao.findBookByfield(field, info);
    }

    @Override
    public void entryBook(Book book){
        bookDao.entryBook(book);
    }

    @Override
    public void updateBookInfo(Book book){
        this.bookDao.updateBookInfo(book);
    }

    @Override
    public void deleteBook(String bookId){
        this.bookDao.deleteBook(bookId);
    }
}
