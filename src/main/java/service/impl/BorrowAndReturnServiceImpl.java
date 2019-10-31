package service.impl;

import dao.BorrowRecordDao;
import dto.BorrowRecordDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import po.BorrowRecord;
import service.BorrowAndReturnService;

import java.text.ParsePosition;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.List;

/**
 * @author 0416
 * @date 2019/10/27
 **/
@Service("borrowAndReturnService")
@Transactional(rollbackFor = Exception.class)
public class BorrowAndReturnServiceImpl implements BorrowAndReturnService {

    @Autowired
    BorrowRecordDao borrowRecordDao;

    @Override
    public void addBorrowRecord(BorrowRecord borrowRecord) {
        borrowRecordDao.addBorrowRecord(borrowRecord);
    }

    @Override
    public List<BorrowRecord> findBorrowRecord() {
        return borrowRecordDao.findBorrowRecord();
    }

    @Override
    public List<BorrowRecordDto> findBorrowRecordByUserId(String userId, Integer startIndex, Integer pageSize) {
        return borrowRecordDao.findBorrowRecordByUserId(userId, startIndex, pageSize);
    }

    @Override
    public Integer updateBorrowTimeByUserIdAndBookId(String userId, String bookId) {
        Integer flag1 = borrowRecordDao.updateBorrowTimeByUserIdAndBookId(userId, bookId);
        Integer flag2 = updateReturnDateByUserIdAndBookId(userId, bookId);
        if (flag1 != null && flag2 != null && flag1 == 1 && flag2 == 1) {
            return flag1;
        }
        return null;
    }

    @Override
    public Integer findBorrowTimeByUserIdAndBookId(String userId, String bookId) {
        return borrowRecordDao.findBorrowTimeByUserIdAndBookId(userId, bookId);
    }

    @Override
    public Integer findTotalNumByUserId(String userId) {
        return borrowRecordDao.findTotalNumByUserId(userId);
    }

    @Override
    public Integer updateReturnDateByUserIdAndBookId(String userId, String bookId) {
        // 查询日期
        String oldDate = borrowRecordDao.findReturnDateByUserIdAndBookId(userId, bookId);
        System.out.println("oldDate:  " + oldDate);
        // 将日期增加一个月
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        ParsePosition pos = new ParsePosition(0);
        Date date = sdf.parse(oldDate, pos);
        Calendar calendar = new GregorianCalendar();
        calendar.setTime(date);
        calendar.add(calendar.MONTH, 1);
        date = calendar.getTime();
        String newDate = sdf.format(date);
        System.out.println("newDate:  " + newDate);
        // 修改日期
        Integer flag = borrowRecordDao.updateReturnDateByUserIdAndBookId(userId, bookId, newDate);
        if (flag != null && flag == 1)
            return flag;
        return null;
    }

    @Override
    public void returnBook(String userId, String bookId) {
        borrowRecordDao.returnBook(userId, bookId);
    }

    @Override
    public Integer findBorrowRecordByUserIdAndBookId(String userId, String bookId) {
        return borrowRecordDao.findBorrowRecordByUserIdAndBookId(userId, bookId);
    }
}
