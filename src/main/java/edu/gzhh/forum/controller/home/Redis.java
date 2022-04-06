package edu.gzhh.forum.controller.home;

import cn.hutool.json.JSONObject;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.redis.core.StringRedisTemplate;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @Author 林学文
 * @Date 2022/1/25 12:03
 * @Version 1.0
 */
@RestController
@RequestMapping("/api/redis")
public class Redis {
    @Autowired
    private StringRedisTemplate redisTemplate;

    @PostMapping("/add")
    public String add(@RequestBody JSONObject req){
        String key  = req.getStr("key");
        String value = req.getStr("value");

        try {
            redisTemplate.opsForValue().set(key,value);
            return "add success";
        }catch (Exception e){
            return "add failed";
        }


    }
}
