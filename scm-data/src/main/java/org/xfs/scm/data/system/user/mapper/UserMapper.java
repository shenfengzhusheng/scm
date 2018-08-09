package org.xfs.scm.data.system.user.mapper;

import java.util.List;

import org.xfs.scm.data.system.user.entity.User;
import org.xfs.scm.data.system.user.po.UserPo;
import org.xfs.scm.data.system.user.vo.UserVo;

import com.github.abel533.mapper.Mapper;

public interface UserMapper extends Mapper<User> {

   int addUser(UserVo record);

   int modifyUser(UserVo record);
   List<UserPo>getUser(UserVo vo);

   List<UserPo>getUsers(UserVo vo);

   Long countUser(UserVo vo);

   List<UserPo>getLoginUserInfo(UserVo vo);
}