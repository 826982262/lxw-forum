package com.jfeat.forum.api.admin;

import cn.hutool.core.util.ObjectUtil;
import com.jfeat.forum.common.exception.ExceptionCast;
import com.jfeat.forum.interceptor.adminLogin;
import com.jfeat.forum.model.CommonCode;
import com.jfeat.forum.model.QueryResult;
import com.jfeat.forum.model.ResponseResult;
import com.jfeat.forum.services.domain.service.LabelService;
import com.jfeat.forum.services.gen.persistence.model.Label;
import com.jfeat.forum.util.PageQueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;
import java.util.Map;

/**
 * @Author 林学文
 * @Date 2022/3/5 19:47
 * @Version 1.0
 */
@Controller
@RequestMapping("/admin")
public class adminLabelController {

    @Resource
    LabelService labelService;

    @adminLogin
    @RequestMapping("/labelmanage")
    public String labelManage(ModelMap modelMap){
        modelMap.addAttribute("path","labelmanage");

        return "admin/labelManage";
    }
    @adminLogin
    @RequestMapping(value = "/label/list",method = RequestMethod.GET)
    @ResponseBody
    public QueryResult list(@RequestParam Map<String,Object> params){
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
             ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);

        return labelService.getLabelOrder(pageUtil);
    }
    @adminLogin
    @RequestMapping(value = "/label/save",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult saveLabel(@RequestBody Label label){


        if (ObjectUtil.hasEmpty(label.getlName(),label.getRanking())){ExceptionCast.cast(CommonCode.ISNOTNULL);}
        return labelService.saveLabel(label);
    }
    @adminLogin
    @RequestMapping(value = "/label/update",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult updateLabel(@RequestBody Label label){
        if (ObjectUtil.hasEmpty(label.getlName(),label.getRanking(),label.getId())){ExceptionCast.cast(CommonCode.ISNOTNULL);}

        return labelService.updateLabel(label);
    }
    @adminLogin
    @ResponseBody
    @RequestMapping(value = "/label/delete" ,method = RequestMethod.POST)
    public ResponseResult deleteLabel(@RequestBody List<Long> ids){
        if (ids.size()<1){
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }


        return labelService.deleteLabel(ids);

    }

}
