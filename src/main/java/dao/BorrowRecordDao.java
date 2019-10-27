package dao;

import org.apache.ibatis.annotations.Param;
import po.BorrowRecord;

import java.util.List;

/**
 * @author FHJ
 * @date 2019/10/27 14:22
 */
public interface BorrowRecordDao {
    // 根据学号（工号）查询学生（老师）借书记录
    List<BorrowRecord> findBorrwoRecordById(@Param("userId") String userId, @Param("startPage") String startPage, @Param("pageSize") String pageSize);

    // 根据学号（工号）和图书id修改续借次数
    Integer updateBorrowTimeByUserIdAndBookId(@Param("userId") String userId, @Param("bookId") String bookId);

    // 根据学号（工号）和图书id查询续借次数
    Integer findBorrowTimeByUserIdAndBookId(@Param("userId") String userId, @Param("bookId") String bookId);

    // 根据学号（工号）查询借书总记录数
    Integer findTotalNumByUserId(String userId);
}
