package service.impl;

import dao.BorrowRecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import po.BorrowRecord;
import service.BorrowAndReturnService;

import java.util.ArrayList;
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
    public List<BorrowRecord> findBorrowRecordById(String userId, Integer startPage, Integer pageSize) {
        return borrowRecordDao.findBorrowRecordById(userId, startPage, pageSize);
    }

    @Override
    public Integer updateBorrowTimeByUserIdAndBookId(String userId, String bookId) {
        return borrowRecordDao.updateBorrowTimeByUserIdAndBookId(userId, bookId);
    }

    @Override
    public Integer findBorrowTimeByUserIdAndBookId(String userId, String bookId) {
        return borrowRecordDao.findBorrowTimeByUserIdAndBookId(userId, bookId);
    }

    @Override
    public Integer findTotalNumByUserId(String userId) {
        return borrowRecordDao.findTotalNumByUserId(userId);
    }
}
