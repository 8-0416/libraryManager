package po;

/**
 * @author 0416
 * @date 2019/10/5
 **/
public class Page {
    private Integer pageNum;
    private Integer records;
    private Integer startIndex;

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

    public Integer getStartIndex() {
        return (pageNum - 1) * records;
    }

    public void setStartIndex(Integer startIndex) {
        this.startIndex = startIndex;
    }
}
