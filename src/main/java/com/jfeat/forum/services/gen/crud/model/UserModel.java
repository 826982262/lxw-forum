package com.jfeat.forum.services.gen.crud.model;
// this is serviceModel.java.vm




import com.jfeat.forum.services.gen.persistence.model.User;
import lombok.Data;

/**
 * Created by Code generator on 2022-05-10
 *  * slaves.size() : 0
 *  * modelpack : $modelpack
 */
@Data
public class UserModel extends User{

    /*验证码*/
    private String captchaValue;
    private String oldPassword;
}
