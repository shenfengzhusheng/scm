package org.xfs.scm.data.business.user.account.mapper;

import org.xfs.scm.data.business.user.account.vo.AccountVo;
import org.xfs.scm.data.business.user.account.vo.UserBasicVo;

import java.util.List;

public interface UserBasicMapper {
    int removeUserBasic(UserBasicVo record);

    int addUserBasic(UserBasicVo record);

    List<UserBasicVo> getUserBasics(UserBasicVo record);

    int modifyUserBasic(UserBasicVo record);

    List<AccountVo> getAccounts(AccountVo record);

}