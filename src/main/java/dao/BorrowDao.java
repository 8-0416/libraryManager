package dao;

import po.BorrowRecord;

import java.util.List;

/**
 * @author FHJ
 * @date 2019/10/27 14:22
 */
public interface BorrowDao {
    // 根据学号（工号）查询学生（老师）借书记录
    List<BorrowRecord> findBorrwoRecordById(String userId);

    // 根据学号（工号）和图书id修改续借次数
    Integer updateBorrowTimeByUserIdAndBookId(String userId, String BookId);
}
