package edu.gzhh.forum.controller.admin;

import cn.hutool.core.util.ObjectUtil;
import edu.gzhh.forum.common.exception.ExceptionCast;
import edu.gzhh.forum.entity.Label;
import edu.gzhh.forum.model.CommonCode;
import edu.gzhh.forum.model.QueryResult;
import edu.gzhh.forum.model.ResponseResult;
import edu.gzhh.forum.service.LabelService;
import edu.gzhh.forum.util.PageQueryUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.ModelMap;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

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

    @Autowired
    LabelService labelService;

    @RequestMapping("/labelmanage")
    public String labelManage(ModelMap modelMap){
        modelMap.addAttribute("path","labelmanage");

        return "admin/labelManage";
    }

    @RequestMapping(value = "/label/list",method = RequestMethod.GET)
    @ResponseBody
    public QueryResult list(@RequestParam Map<String,Object> params){
        if (StringUtils.isEmpty(params.get("page")) || StringUtils.isEmpty(params.get("limit"))) {
             ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }
        PageQueryUtil pageUtil = new PageQueryUtil(params);

        return labelService.getLabelOrder(pageUtil);
    }
    @RequestMapping(value = "/label/save",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult saveLabel(@RequestBody Label label){


        if (ObjectUtil.hasEmpty(label.getLName(),label.getRanking())){ExceptionCast.cast(CommonCode.ISNOTNULL);}
        return labelService.saveLabel(label);
    }
    @RequestMapping(value = "/label/update",method = RequestMethod.POST)
    @ResponseBody
    public ResponseResult updateLabel(@RequestBody Label label){
        if (ObjectUtil.hasEmpty(label.getLName(),label.getRanking(),label.getLId())){ExceptionCast.cast(CommonCode.ISNOTNULL);}

        return labelService.updateLabel(label);
    }

    @ResponseBody
    @RequestMapping(value = "/label/delete" ,method = RequestMethod.POST)
    public ResponseResult deleteLabel(@RequestBody List<Long> ids){
        if (ids.size()<1){
            ExceptionCast.cast(CommonCode.INVALID_PARAM);
        }


        return labelService.deleteLabel(ids);

    }

}
