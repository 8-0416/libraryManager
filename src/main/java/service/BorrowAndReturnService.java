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
}
