package org.xfs.scm.system.user.dao;

import java.util.List;

import org.xfs.scm.system.user.entity.User;
import org.xfs.scm.system.user.po.UserPo;
import org.xfs.scm.system.user.vo.UserVo;

import com.github.abel533.mapper.Mapper;

public interface UserMapper extends Mapper<User> {
   List<UserPo>getUser(UserVo vo);
   
   Long countUser(UserVo vo);

   List<UserPo>getLoginUserInfo(UserVo vo);
}