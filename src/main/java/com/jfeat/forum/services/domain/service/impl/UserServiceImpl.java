package com.jfeat.forum.services.domain.service.impl;
import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.jfeat.forum.common.exception.ExceptionCast;
import com.jfeat.forum.model.CommonCode;
import com.jfeat.forum.model.QueryResult;
import com.jfeat.forum.model.ResponseResult;
import com.jfeat.forum.services.domain.service.UserService;
import com.jfeat.forum.services.gen.crud.service.impl.CRUDUserServiceImpl;
import com.jfeat.forum.services.gen.persistence.dao.UserMapper;
import com.jfeat.forum.services.gen.persistence.model.User;
import com.jfeat.forum.util.PageQueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.List;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author admin
 * @since 2017-10-16
 */

@Service("userService")
public class UserServiceImpl extends CRUDUserServiceImpl implements UserService {
    @Resource
    private UserMapper userMapper;
    @Override
    protected String entityName() {
        return "User";
    }




    @Override
    public ResponseResult saveUser(User user) {

        if (exitByAccount(user.getAccount())){
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }

        /*判断参数书否为空*/
        else if (ObjectUtil.hasEmpty(user.getAccount(),user.getUname(), user.getPassword())) {
            ExceptionCast.cast(CommonCode.ISNOTNULL);

        }else {
            //密码加密
            user.setPassword(DigestUtil.md5Hex(user.getPassword()));
            int flag = userMapper.insert(user);
            if (flag > 0) {
                return new ResponseResult(CommonCode.REGISTERSUCCESS);
            }
        }
        return new ResponseResult(CommonCode.REGISTERFAIL);
    }

    /*不存在用户名，*/

    /**.
     * 存在返回true
     * 不存在返回false
     * @param Account
     * @return
     */
    @Override
    public boolean exitByAccount(String Account) {
        if (StrUtil.isNotEmpty(Account)){
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("account",Account);
            User user =  userMapper.selectOne(queryWrapper);
            return ObjectUtil.isNotNull(user) ? true:false;
        }
        return false;
    }

    /**
     * 登录
     *
     * */
    @Override
    public ResponseResult userLogin(String type,String account, String password, HttpServletRequest request) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        /*登录类型为用户名*/
        if ("10".equals(type)) {
            queryWrapper.eq("account", account);
            /* 登录类型为手机号*/
        }else if("20".equals(type)){
            queryWrapper.eq("phone",account);
        }
        User user =  userMapper.selectOne(queryWrapper);
        /*判断账号存在*/
        if (ObjectUtil.isNull(user)){
            ExceptionCast.cast(CommonCode.LOGINERROR);
        }
        /*密码MD5加密后对比*/
        password = DigestUtil.md5Hex(password);
        /*密码不一致直接报异常中断*/
        if (!user.getPassword().equals(password))
        {  ExceptionCast.cast(CommonCode.LOGINERROR); }
        /*验证成功，保存用户信息*/
        HttpSession session = request.getSession();
        session.setAttribute("user",user);
        /*返回登录成功*/
        return new ResponseResult(CommonCode.LOGSUCCESS);
    }

    @Override
    public QueryResult selectUserList(PageQueryUtil pageQueryUtil) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        int total = userMapper.selectCount(queryWrapper);
        List<User> userList = userMapper.selectUserOrderByTime((pageQueryUtil.getPage()-1)*pageQueryUtil.getLimit(),pageQueryUtil.getLimit());
        QueryResult<User> result = new QueryResult<>(userList,total,pageQueryUtil.getPage(),pageQueryUtil.getLimit());
        return result;
    }

    @Override
    public ResponseResult updateUsersFlag(List<Long> Ids,Integer flag) {
        List<User> userList = userMapper.selectBatchIds(Ids);
        for (User user :userList){
            if (user.getFlag().equals(1)){
                ExceptionCast.cast(CommonCode.INVALID_PARAM);
            }
        }

        Integer sign = userMapper.updateUsersFlagByUids(Ids,flag);
        if (sign== Ids.size()){
            return   new ResponseResult(CommonCode.SUCCESS);
        }else {
            return new ResponseResult(CommonCode.FAIL);
        }

    }

    @Override
    public User selectUserById(Long id) {
        return userMapper.selectUserById(id);
    }

    @Override
    public ResponseResult updateUserHead(Long id,String url) {

              Integer flag =  userMapper.updateUserHeadUrlById(id,url);
              if (flag>0){ return   new ResponseResult(CommonCode.SUCCESS); }else {return new ResponseResult(CommonCode.FAIL);}

    }

    @Override
    public ResponseResult updateUserById(Long id, String uname, String oldPassword, String password) {
        String oldpassword = DigestUtil.md5Hex(oldPassword);
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("id",id);
        User user =  userMapper.selectOne(queryWrapper);
        if (!user.getPassword().equals(oldpassword))
        {  ExceptionCast.cast(CommonCode.LOGINERROR); }
        Integer flag = userMapper.updateUserById(id,uname,DigestUtil.md5Hex(password));
        if (flag>0){ return   new ResponseResult(CommonCode.SUCCESS); }else {return new ResponseResult(CommonCode.FAIL);}
    }
}
