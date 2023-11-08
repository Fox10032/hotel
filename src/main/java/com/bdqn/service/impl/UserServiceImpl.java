package com.bdqn.service.impl;

import com.bdqn.dao.UserMapper;
import com.bdqn.entity.User;
import com.bdqn.service.UserService;
import com.bdqn.utils.PasswordUtil;
import com.bdqn.utils.SystemConstant;
import com.bdqn.utils.UUIDUtils;
import com.bdqn.vo.UserVo;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Resource;
import java.util.List;

@Service
@Transactional
public class UserServiceImpl implements UserService {

    @Resource
    private UserMapper userMapper;

    /**
     * 添加用户
     *
     * @param user
     * @return
     */
    public int addUser(User user) {
        //自动生成盐值
        user.setSalt(UUIDUtils.randomUUID());//shiro安全验证框架
        //密码加密
        user.setPassword(PasswordUtil.md5(user.getPassword(),user.getSalt(), SystemConstant.PASSWORD_COUNT));
        return userMapper.addUser(user);
    }
    //查询员工信息
    public User findUserByName(String loginName) {
        return userMapper.findUserByName(loginName);
    }
    public User findUserByRealName(String realName) {
        return userMapper.findUserByName(realName);
    }

    public List<User> findUserList(UserVo userVo) {
        return userMapper.findUserList(userVo);
    }
    /**
     *删除用户
     * @param id
     * @return
     */
    public int deleteById(Integer id) {

        //调用删除员工的方法
        return userMapper.deleteById(id);
    }

    /**修改用户
     *
     * @param user
     * @return
     */
    public int updateUser(User user) {
        return userMapper.updateUser(user);
    }


    public int resetPwd(long id) {
        User user = new User();
        user.setSalt(UUIDUtils.randomUUID());//必须先设置盐值，再给密码重新加密赋值
        user.setLoginPwd(PasswordUtil.md5(SystemConstant.DEFAULT_LOGIN_PWD,user.getSalt(),SystemConstant.PASSWORD_COUNT));
        user.setId(id);//主键，员工编号
        return userMapper.updateUser(user);
    }


    public User login(String loginName, String password) {
        //调用查询用户信息的方法
        User loginUser = userMapper.findUserByName(loginName);
        //判断对象是否为空
        if(loginUser!=null){
            //密码加密
            String newPassword = PasswordUtil.md5(password, loginUser.getSalt(),SystemConstant.PASSWORD_COUNT);
            //比较密码是否相等
            if(loginUser.getPassword().equals(newPassword)){
                return loginUser;
            }
        }
        return null;
    }
}
