package com.bdqn.dao;

import com.bdqn.entity.User;
import com.bdqn.vo.UserVo;

import java.util.List;

public interface UserMapper {

    /**
     * 添加用户
     * @param user
     * @return
     */
    int addUser(User user);

    /**
     * 根据用户名查询用户信息
     * @param loginName
     * @return
     */
    User findUserByName(String loginName);
    /**
     * 根据真实姓名查询用户信息
     * @param realName
     * @return
     */
    User findUserByRealName(String realName);
    /**
     * 查询用户列表
     * @param userVo
     * @return
     */
    List<User> findUserList(UserVo userVo);
    /**
     * 删除用户
     * @param id
     * @return
     */
    int deleteById(Integer id);
    /**
     * 修改用户
     * @param user
     * @return
     */
    int updateUser(User user);

}
