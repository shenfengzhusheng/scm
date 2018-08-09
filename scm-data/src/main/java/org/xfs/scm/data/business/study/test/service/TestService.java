package org.xfs.scm.data.business.study.test.service;

import org.springframework.stereotype.Service;
import org.xfs.scm.data.business.study.test.model.User;
import org.xfs.scm.platform.config.data.mongo.dao.impl.MongoDaoImpl;

/**
 * Created by 神风逐胜 on 2017/10/15 0015.16:13
 * version:1.0
 */

@Service
public class TestService extends MongoDaoImpl<User> {
}
