package org.xfs.scm.data.business.study.hello.service;

import com.mongodb.WriteResult;
import org.springframework.stereotype.Service;
import org.xfs.scm.data.business.study.hello.model.Interview;
import org.xfs.scm.platform.config.data.mongo.dao.impl.MongoDaoImpl;

/**
 * Created by 神风逐胜 on 2017/10/14 0014.23:08
 * version:1.0
 */
@Service
public class InterviewService extends MongoDaoImpl<Interview> {


    @Override
    public WriteResult update(Interview entity) {
        return null;
    }
}
