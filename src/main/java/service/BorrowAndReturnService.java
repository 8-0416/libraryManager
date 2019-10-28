package service;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import po.BorrowRecord;

import java.util.List;

/**
 * @author 0416
 * @date 2019/10/27
 **/
public interface BorrowAndReturnService {

    void addBorrowRecord(BorrowRecord borrowRecord);

    List<BorrowRecord> findBorrowRecord();

    // 根据学号（工号）查询学生（老师）借书记录
    List<BorrowRecord> findBorrowRecordById(String userId, Integer startPage, Integer pageSize);

    // 根据学号（工号）和图书id修改续借次数
    Integer updateBorrowTimeByUserIdAndBookId(String userId, String bookId);

    // 根据学号（工号）和图书id查询续借次数
    Integer findBorrowTimeByUserIdAndBookId(String userId, String bookId);

    // 根据学号（工号）查询借书总记录数
    Integer findTotalNumByUserId(String userId);
}
