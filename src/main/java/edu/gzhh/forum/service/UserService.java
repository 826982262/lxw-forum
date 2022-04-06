package edu.gzhh.forum.service;

import com.baomidou.mybatisplus.extension.service.IService;
import edu.gzhh.forum.entity.User;
import edu.gzhh.forum.model.response.QueryResult;
import edu.gzhh.forum.model.response.ResponseResult;
import edu.gzhh.forum.util.PageQueryUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

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
     boolean exitByAccount(String Account);
     /*用户登录*/
     ResponseResult userLogin(String type,String name, String password, HttpServletRequest request);
     /*查询用户*/
     QueryResult selectUserList(PageQueryUtil pageQueryUtil);
     ResponseResult updateUsersFlag(List<Long> Ids,Integer flag);
}
