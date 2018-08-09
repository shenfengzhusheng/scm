package org.xfs.scm.data.business.user.account.mapper;

import org.xfs.scm.data.business.user.account.vo.UserInfoVo;

import java.util.List;

public interface UserInfoMapper {
    int remvoeUserInfo(UserInfoVo record);

    int addUserInfo(UserInfoVo record);

    List<UserInfoVo> getUserInfos(UserInfoVo record);

    int modfiyUserInfo(UserInfoVo record);

}