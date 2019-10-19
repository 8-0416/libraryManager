package po;

/**
 * @AUTHOR:0416
 * @DESCRIPTION: 推荐书籍
 * @DATE:2019/10/1
 **/
public class Reference {
    private String userId;
    private String bookName;
    private String bookAuthor;

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
    }

    public String getBookAuthor() {
        return bookAuthor;
    }

    public void setBookAuthor(String bookAuthor) {
        this.bookAuthor = bookAuthor;
    }
}
