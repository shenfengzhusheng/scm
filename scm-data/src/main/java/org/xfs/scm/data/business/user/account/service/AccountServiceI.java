package org.xfs.scm.data.business.user.account.service;

import org.xfs.scm.common.base.model.Grid;
import org.xfs.scm.data.business.user.account.vo.AccountVo;

public interface AccountServiceI {

    Grid<AccountVo> gridAccount(AccountVo vo, Integer page, Integer rows);



}
