package po;

/**
 * @AUTHOR:0416
 * @DESCRIPTION:
 * @DATE:2019/10/5
 **/
public class Page {
    private Integer pageNum;
    private Integer records;
    private Integer startRecord;
    private Integer endRecord;

    public Page() {
    }

    public Page(Integer pageNum, Integer records) {
        this.pageNum = pageNum;
        this.records = records;
    }

    public Integer getPageNum() {
        return pageNum;
    }

    public void setPageNum(Integer pageNum) {
        this.pageNum = pageNum;
    }

    public Integer getRecords() {
        return records;
    }

    public void setRecords(Integer records) {
        this.records = records;
    }

    public Integer getStartRecord() {
        return (pageNum - 1) * records;
    }

    public Integer getEndRecord() {
        return pageNum*records - 1;
    }

}
