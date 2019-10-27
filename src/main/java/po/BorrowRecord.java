package po;

/**
 * @author FHJ
 * @date 2019/10/26 15:51
 * 借书记录表
 */
public class BorrowRecord {
    // 图书id
    private String bookId;
    // 用户id
    private String userId;
    // 借书日期
    private String borrowDate;
    // 归还日期
    private String returnDate;
    // 续借次数
    private Integer borrowTime;
    // 是否归还
    private Integer isReturn;

    public String getBookId() {
        return bookId;
    }

    public void setBookId(String bookId) {
        this.bookId = bookId;
    }

    public String getUserId() {
        return userId;
    }

    public void setUserId(String userId) {
        this.userId = userId;
    }

    public String getBorrowDate() {
        return borrowDate;
    }

    public void setBorrowDate(String borrowDate) {
        this.borrowDate = borrowDate;
    }

    public String getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(String returnDate) {
        this.returnDate = returnDate;
    }

    public Integer getBorrowTime() {
        return borrowTime;
    }

    public void setBorrowTime(Integer borrowTime) {
        this.borrowTime = borrowTime;
    }

    public Integer getIsReturn() {
        return isReturn;
    }

    public void setIsReturn(Integer isReturn) {
        this.isReturn = isReturn;
    }
}