package po;

import java.util.Date;

/**
 * @author 0416
 * @date 2019/10/1
 **/
public class Buy {
    private Book book;
    private Date buyDate;

    public Book getBook() {
        return book;
    }

    public void setBook(Book book) {
        this.book = book;
    }

    public Date getBuyDate() {
        return buyDate;
    }

    public void setBuyDate(Date buyDate) {
        this.buyDate = buyDate;
    }
}
