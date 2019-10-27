package dao;

import po.BorrowRecord;

import java.util.List;

/**
 * @author 0416
 * @date 2019/10/27
 **/
public interface BorrowRecordDao {

    void addBorrowRecord(BorrowRecord borrowRecord);

    List<BorrowRecord> findBorrowRecord();

    //TODO
    //void renewBook();
}
