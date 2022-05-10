package edu.gzhh.forum.util;

import cn.hutool.core.io.FileUtil;
import cn.hutool.core.io.file.FileWriter;
import cn.hutool.http.HtmlUtil;
import cn.hutool.system.RuntimeInfo;
import cn.hutool.system.SystemUtil;
import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;

import com.jfeat.forum.common.SensitiveWordFilterInit;
import com.jfeat.forum.services.domain.service.LabelService;
import com.jfeat.forum.services.domain.service.UserService;
import com.jfeat.forum.services.gen.persistence.dao.UserMapper;
import com.jfeat.forum.services.gen.persistence.model.Label;
import com.jfeat.forum.services.gen.persistence.model.Topic;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.annotation.Resource;
import java.util.List;

/**
 * @Author 林学文
 * @Date 2021/12/17 10:14
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestMapper {

    @Resource
    LabelService labelService;
    @Test
    public void testService(){
        List<Label> list = labelService.getAllLabel();
        list.isEmpty();
    }


}
