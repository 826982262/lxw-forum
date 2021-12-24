package edu.gzhh.forum.service.impl;

import cn.hutool.core.util.ObjectUtil;
import cn.hutool.core.util.StrUtil;
import cn.hutool.crypto.digest.DigestUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import edu.gzhh.forum.common.exception.ExceptionCast;
import edu.gzhh.forum.entity.User;
import edu.gzhh.forum.mapper.UserMapper;
import edu.gzhh.forum.model.response.CommonCode;
import edu.gzhh.forum.model.response.ResponseResult;
import edu.gzhh.forum.service.UserService;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * <p>
 *  服务实现类
 * </p>
 *
 * @author lxw
 * @since 2021-12-16
 */
@Service
public class UserServiceImpl extends ServiceImpl<UserMapper, User> implements UserService {


    @Autowired
    private UserMapper userMapper;

    @Override
    public ResponseResult saveUser(User user) {

        if (exitByName(user.getUname())){
             ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }

        /*判断参数书否为空*/
       else if (ObjectUtil.hasEmpty(user.getUname(), user.getPassword(), user.getEmail(),
                user.getPhone(), user.getSex())) {
            ExceptionCast.cast(CommonCode.ISNOTNULL);

            }else {
            //密码加密
            user.setPassword(DigestUtil.md5Hex(user.getPassword()));
            userMapper.insert(user);
            return new ResponseResult(CommonCode.REGISTERSUCCESS);
        }
            return new ResponseResult(CommonCode.REGISTERFAIL);
    }

    /*不存在用户名，*/

    /**.
     * 存在返回true
     * 不存在返回false
     * @param name
     * @return
     */
    @Override
    public boolean exitByName(String name) {
        if (StrUtil.isNotEmpty(name)){
            QueryWrapper<User> queryWrapper = new QueryWrapper<>();
            queryWrapper.eq("uname",name);
            User user = userMapper.selectOne(queryWrapper);
            return ObjectUtil.isNotNull(user) ? true:false;
        }
        return false;
    }

    /**
     * 登录
     *
     * */
    @Override
    public ResponseResult userLogin(String name, String password) {
        QueryWrapper<User> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("uname",name);
        User user = userMapper.selectOne(queryWrapper);
        if (ObjectUtil.isNull(user)){
            ExceptionCast.cast(CommonCode.LOGINERROR);
        }
        password = DigestUtil.md5Hex(password);
        if (!user.getPassword().equals(password))
        {  ExceptionCast.cast(CommonCode.LOGINERROR); }

        return new ResponseResult(CommonCode.LOGSUCCESS);
    }
}
