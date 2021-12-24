package edu.gzhh.forum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.gzhh.forum.entity.User;
import edu.gzhh.forum.model.response.ResponseResult;

/**
 * <p>
 *  服务类
 * </p>
 *
 * @author lxw
 * @since 2021-12-16
 */
public interface UserService extends IService<User> {
     /*注册用户*/
     ResponseResult saveUser(User user);
     /*查询用户名*/
     boolean exitByName(String name);
     /*用户登录*/
     ResponseResult userLogin(String name,String password);
}
