package dao;

import dto.BorrowRecordDto;
import org.apache.ibatis.annotations.Param;
import po.BorrowRecord;
import po.Page;

import java.util.List;

/**
 * @author FHJ
 * @date 2019/10/27 14:22
 */
public interface BorrowRecordDao {
    // 根据学号（工号）查询学生（老师）借书记录
    List<BorrowRecordDto> findBorrowRecordByUserId(@Param("userId") String userId, @Param("startIndex") Integer startPage, @Param("pageSize") Integer pageSize);

    // 根据学号（工号）和图书id修改续借次数
    Integer updateBorrowTimeByUserIdAndBookId(@Param("userId") String userId, @Param("bookId") String bookId);

    // 根据学号（工号）和图书id查询续借次数
    Integer findBorrowTimeByUserIdAndBookId(@Param("userId") String userId, @Param("bookId") String bookId);

    // 根据学号（工号）查询借书总记录数
    Integer findTotalNumByUserId(String userId);

    // 根据学号（工号）和图书id查询图书归还日期
    String findReturnDateByUserIdAndBookId(@Param("userId") String userId, @Param("bookId") String bookId);

    // 根据学号（工号）和图书id修改图书归还日期
    Integer updateReturnDateByUserIdAndBookId(@Param("userId") String userId, @Param("bookId") String bookId, @Param("newDate") String newDate);

    void addBorrowRecord(BorrowRecord borrowRecord);

    List<BorrowRecord> findBorrowRecord(Page page);

    void returnBook(String userId, String bookId);

    Integer findBorrowRecordByUserIdAndBookId(String userId, String bookId);
}
