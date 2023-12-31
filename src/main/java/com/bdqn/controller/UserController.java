package com.bdqn.controller;

import com.alibaba.fastjson.JSON;
import com.bdqn.entity.User;
import com.bdqn.service.UserService;
import com.bdqn.utils.DataGridViewResult;
import com.bdqn.utils.SystemConstant;
import com.bdqn.vo.UserVo;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

import javax.annotation.Resource;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@Controller
@RequestMapping("/user")
public class UserController {

    @Resource
    private UserService userService;


    /**
     * 注册
     * @param user
     * @return
     */
    @ResponseBody
    @RequestMapping("/register")
    public String register(User user){
        //创建Map集合，保存结果信息
        Map<String,Object> map = new HashMap<String,Object>();
        //调用注册的方法
        if(userService.addUser(user)>0){
            map.put(SystemConstant.SUCCESS,true);
            map.put(SystemConstant.MESSAGE,"恭喜你,注册成功!");
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"很遗憾,注册失败,请重新尝试!");
        }
        return JSON.toJSONString(map);
    }


    /**
     * 检查用户名是否存在
     * @param loginName
     * @return
     */
    @ResponseBody
    @RequestMapping("/checkName")
    public String checkName(String loginName){
        //创建Map集合，保存结果信息
        Map<String,Object> map = new HashMap<String,Object>();
        //调用根据用户名查询用户信息的方法
        if(userService.findUserByName(loginName)!=null){
            map.put(SystemConstant.EXIST,true);
            map.put(SystemConstant.MESSAGE,"用户名已被使用,请重新输入！");
        }else{
            map.put(SystemConstant.EXIST,false);
        }
        return JSON.toJSONString(map);
    }

    /**
     * 登录
     * @return
     */
    @ResponseBody
    @RequestMapping("/login")
    public String login(String loginName, String password, HttpSession session){
        //创建Map集合，保存结果信息
        Map<String,Object> map = new HashMap<String,Object>();
        //调用登录的方法
        User loginUser = userService.login(loginName, password);
        //判断对象是否为空
        if(loginUser!=null){
            map.put(SystemConstant.SUCCESS,true);
            loginUser.setPassword(null);//清空
            //保存用户信息到会话中
            session.setAttribute(SystemConstant.FRONT_LOGIN_USER,loginUser);
        }else{
            map.put(SystemConstant.SUCCESS,false);
            map.put(SystemConstant.MESSAGE,"用户名或密码错误，请重新输入！");
        }
        return JSON.toJSONString(map);
    }
    /**
     * 查询用户列表
     * @param userVo
     * @return
     */
    @RequestMapping("/list")
    public DataGridViewResult list(UserVo userVo){
        //设置分页信息
        PageHelper.startPage(userVo.getPage(),userVo.getLimit());
        //调用查询的方法
        List<User> userList = userService.findUserList(userVo);
        //创建分页对象
        PageInfo<User> pageInfo = new PageInfo<User>(userList);
        //返回数据
        return new DataGridViewResult(pageInfo.getTotal(),pageInfo.getList());
    }
    /**
     * 添加用户
     * @param user
     * @param session
     * @return
     */
    @RequestMapping("/addUser")
    public String addUser(User user, HttpSession session){
        Map<String,Object> map = new HashMap<String,Object>();
        //获取当前登录用户
        User loginUser = (User) session.getAttribute(SystemConstant.LOGINUSER);

        //调用新增员工的方法
        if(userService.addUser(user)>0) {
            map.put(SystemConstant.SUCCESS, true);
            map.put(SystemConstant.MESSAGE, "添加成功");
        }else{
            map.put(SystemConstant.SUCCESS, false);
            map.put(SystemConstant.MESSAGE, "添加失败");
        }
        return JSON.toJSONString(map);
    }
    /**
     * 修改用户
     * @param user
     * @param session
     * @return
     */
    @RequestMapping("/updateUser")
    public String updateUser(User user, HttpSession session){
        Map<String,Object> map = new HashMap<String,Object>();
        //获取当前登录用户
        User loginUser = (User) session.getAttribute(SystemConstant.LOGINUSER);

        //调用新增员工的方法
        if(userService.updateUser(user)>0) {
            map.put(SystemConstant.SUCCESS, true);
            map.put(SystemConstant.MESSAGE, "修改成功");
        }else{
            map.put(SystemConstant.SUCCESS, false);
            map.put(SystemConstant.MESSAGE, "修改失败");
        }
        return JSON.toJSONString(map);
    }
    /**
     * 删除员工
     * @param id
     * @return
     */
    @RequestMapping("/deleteById")
    public String deleteById(int id){
        Map<String,Object> map = new HashMap<String,Object>();
        //调用删除员工的方法
        if(userService.deleteById(id)>0){
            map.put(SystemConstant.SUCCESS, true);
            map.put(SystemConstant.MESSAGE, "删除成功");
        }else{
            map.put(SystemConstant.SUCCESS, false);
            map.put(SystemConstant.MESSAGE, "删除失败");
        }
        return JSON.toJSONString(map);
    }
    /**
     * 重置密码
     * @param id
     * @return
     */
    @RequestMapping("/resetPwd")
    public String resetPwd(int id){
        Map<String,Object> map = new HashMap<String,Object>();
        //调用重置密码的方法
        if(userService.resetPwd(id)>0){
            map.put(SystemConstant.SUCCESS, true);
            map.put(SystemConstant.MESSAGE, "密码重置成功");
        }else{
            map.put(SystemConstant.SUCCESS, false);
            map.put(SystemConstant.MESSAGE, "密码重置失败");
        }
        return JSON.toJSONString(map);
    }
}
