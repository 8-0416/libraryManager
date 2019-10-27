package service.impl;

import dao.BorrowRecordDao;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import po.BorrowRecord;
import service.BorrowAndReturnService;

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
}
