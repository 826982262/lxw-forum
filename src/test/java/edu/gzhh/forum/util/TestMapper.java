package edu.gzhh.forum.util;

import edu.gzhh.forum.entity.User;
import edu.gzhh.forum.mapper.UserMapper;
import edu.gzhh.forum.service.UserService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

/**
 * @Author 林学文
 * @Date 2021/12/17 10:14
 * @Version 1.0
 */
@SpringBootTest
@RunWith(SpringRunner.class)
public class TestMapper {
    @Autowired
    UserService userService;
    @Autowired
    UserMapper userMapper;

    @Test
    public void testService(){
        System.out.println(userService.exitByName("林学文"));
    }
}
