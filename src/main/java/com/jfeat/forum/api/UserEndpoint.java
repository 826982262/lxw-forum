
package com.jfeat.forum.api;


import io.swagger.annotations.Api;
import io.swagger.annotations.ApiImplicitParam;
import io.swagger.annotations.ApiImplicitParams;
import io.swagger.annotations.ApiOperation;
import org.springframework.web.bind.annotation.GetMapping;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.dao.DuplicateKeyException;
import com.jfeat.forum.services.domain.dao.QueryUserDao;
import com.jfeat.crud.base.tips.SuccessTip;
import com.jfeat.crud.base.tips.Tip;
import com.jfeat.crud.base.annotation.BusinessLog;
import com.jfeat.crud.base.exception.BusinessCode;
import com.jfeat.crud.base.exception.BusinessException;
import com.jfeat.forum.api.permission.*;
import com.jfeat.am.common.annotation.Permission;

import com.jfeat.forum.services.domain.service.*;
import com.jfeat.forum.services.domain.model.UserRecord;
import com.jfeat.forum.services.gen.persistence.model.User;

    import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;
import java.util.Date;
import java.util.List;

/**
 * <p>
 *  api
 * </p>
 *
 * @author Code generator
 * @since 2022-05-10
 */
    @RestController
    @Api("User")
            @RequestMapping("/api/crud/user/user/users")
    public class UserEndpoint {

    @Resource
    UserService userService;

    @Resource
    QueryUserDao queryUserDao;



    @BusinessLog(name = "User", value = "create User")
    @Permission(UserPermission.USER_NEW)
    @PostMapping
    @ApiOperation(value = "新建 User",response = User.class)
    public Tip createUser(@RequestBody User entity){
        Integer affected=0;
        try{
                affected= userService.createMaster(entity);
            }catch(DuplicateKeyException e){
             throw new BusinessException(BusinessCode.DuplicateKey);
        }

        return SuccessTip.create(affected);
}

    @Permission(UserPermission.USER_VIEW)
    @GetMapping("/{id}")
    @ApiOperation(value = "查看 User",response = User.class)
    public Tip getUser(@PathVariable Long id){
                        return SuccessTip.create(userService.queryMasterModel(queryUserDao, id));
            }

    @BusinessLog(name = "User", value = "update User")
    @Permission(UserPermission.USER_EDIT)
    @PutMapping("/{id}")
    @ApiOperation(value = "修改 User",response = User.class)
    public Tip updateUser(@PathVariable Long id,@RequestBody User entity){
             entity.setId(id);
                return SuccessTip.create(userService.updateMaster(entity));
            }

    @BusinessLog(name = "User", value = "delete User")
    @Permission(UserPermission.USER_DELETE)
    @DeleteMapping("/{id}")
    @ApiOperation("删除 User")
    public Tip deleteUser(@PathVariable Long id){
            return SuccessTip.create(userService.deleteMaster(id));
        }

    @Permission(UserPermission.USER_VIEW)
    @ApiOperation(value = "User 列表信息",response = UserRecord.class)
    @GetMapping
    @ApiImplicitParams({
        @ApiImplicitParam(name= "pageNum", dataType = "Integer"),
        @ApiImplicitParam(name= "pageSize", dataType = "Integer"),
        @ApiImplicitParam(name= "search", dataType = "String"),
                                                                                        @ApiImplicitParam(name = "id", dataType = "Long"),
                                                                                    @ApiImplicitParam(name = "account", dataType = "String"),
                                                                                    @ApiImplicitParam(name = "uname", dataType = "String"),
                                                                                    @ApiImplicitParam(name = "password", dataType = "String"),
                                                                                    @ApiImplicitParam(name = "email", dataType = "String"),
                                                                                            @ApiImplicitParam(name = "flag", dataType = "Integer"),
                                                                                            @ApiImplicitParam(name = "headUrl", dataType = "String"),
                                                                                            @ApiImplicitParam(name = "createTime", dataType = "Date") ,
                @ApiImplicitParam(name = "orderBy", dataType = "String"),
                @ApiImplicitParam(name = "sort", dataType = "String")
            })
    public Tip queryUserPage(Page<UserRecord> page,
    @RequestParam(name = "pageNum", required = false, defaultValue = "1") Integer pageNum,
    @RequestParam(name = "pageSize", required = false, defaultValue = "10") Integer pageSize,
    // for tag feature query
    @RequestParam(name = "tag" , required = false)String tag,
    // end tag
    @RequestParam(name = "search", required = false) String search,

    @RequestParam(name = "account", required = false) String account,

    @RequestParam(name = "uname", required = false) String uname,

    @RequestParam(name = "password", required = false) String password,

    @RequestParam(name = "email", required = false) String email,

    @RequestParam(name = "flag", required = false) Integer flag,

    @RequestParam(name = "headUrl", required = false) String headUrl,

    @DateTimeFormat(pattern="yyyy-MM-dd HH:mm:ss")
    @RequestParam(name = "createTime", required = false) Date createTime,
        @RequestParam(name = "orderBy", required = false) String orderBy,
        @RequestParam(name = "sort", required = false)  String sort) {
                    
            if(orderBy!=null&&orderBy.length()>0){
        if(sort!=null&&sort.length()>0){
        String sortPattern = "(ASC|DESC|asc|desc)";
        if(!sort.matches(sortPattern)){
        throw new BusinessException(BusinessCode.BadRequest.getCode(), "sort must be ASC or DESC");//此处异常类型根据实际情况而定
        }
        }else{
        sort = "ASC";
        }
        orderBy = "`"+orderBy+"`" +" "+sort;
        }
        page.setCurrent(pageNum);
        page.setSize(pageSize);

    UserRecord record = new UserRecord();
                                                                                                                                                                                        record.setAccount(account);
                                                                                                                record.setUname(uname);
                                                                                                                record.setPassword(password);
                                                                                                                record.setEmail(email);
                                                                                                                record.setFlag(flag);
                                                                                                                        record.setHeadUrl(headUrl);
                                                                                                                record.setCreateTime(createTime);
                        
                                

    List<UserRecord> userPage = queryUserDao.findUserPage(page, record, tag, search, orderBy, null, null);

        
        page.setRecords(userPage);

        return SuccessTip.create(page);
    }
}

