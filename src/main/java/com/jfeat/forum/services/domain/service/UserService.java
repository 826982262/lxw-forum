package com.jfeat.forum.services.domain.service;

import com.jfeat.forum.model.QueryResult;
import com.jfeat.forum.model.ResponseResult;
import com.jfeat.forum.services.gen.crud.service.CRUDUserService;
import com.jfeat.forum.services.gen.persistence.model.User;
import com.jfeat.forum.util.PageQueryUtil;

import javax.servlet.http.HttpServletRequest;
import java.util.List;

/**
 * Created by vincent on 2017/10/19.
 */
public interface UserService extends CRUDUserService{
    /*注册用户*/
    ResponseResult saveUser(User user);
    /*查询用户名*/
    boolean exitByAccount(String Account);
    /*用户登录*/
    ResponseResult userLogin(String type,String name, String password, HttpServletRequest request);
    /*查询用户*/
    QueryResult selectUserList(PageQueryUtil pageQueryUtil);
    ResponseResult updateUsersFlag(List<Long> Ids, Integer flag);
    User selectUserById(Long id);


    ResponseResult updateUserHead(Long id, String url);

    ResponseResult updateUserById(Long id, String uname, String oldPassword, String password);

    ResponseResult adminLogin(String userName, String password, HttpServletRequest request);
}