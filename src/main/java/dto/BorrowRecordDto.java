package dto;

/**
 * @author FHJ
 * @date 2019/10/27 23:56
 */
public class BorrowRecordDto {
    private String bookName;
    private String borrowDate;
    private String returnDate;
    private Integer borrowTime;
    private Integer isReturn;

    public String getBookName() {
        return bookName;
    }

    public void setBookName(String bookName) {
        this.bookName = bookName;
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
