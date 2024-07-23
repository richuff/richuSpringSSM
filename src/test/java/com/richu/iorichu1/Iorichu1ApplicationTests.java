package com.richu.iorichu1;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.richu.iorichu1.Mapper.UserMapper;
import org.junit.jupiter.api.Test;
import com.richu.iorichu1.domain.po.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class Iorichu1ApplicationTests {

    @Autowired
    private UserMapper userMapper;

    @Test
    public void testInsert() {
        User user = new User();
        user.setId(5L);
        user.setUsername("Lucy");
        user.setPassword("123");
        user.setPhone("18688990011");
        user.setBalance(200);
        user.setInfo("{\"age\": 24, \"intro\": \"英文老师\", \"gender\": \"female\"}");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userMapper.insert(user);
    }

    @Test
    public void testSelectById() {
        User user = userMapper.selectById(5L);
        System.out.println("user = " + user);
    }


    @Test
    public void testQueryByIds() {
        List<User> users = userMapper.selectBatchIds(List.of(1L, 2L, 3L, 4L));
        users.forEach(System.out::println);
    }

    @Test
    public void testUpdateById() {
        User user = new User();
        user.setId(5L);
        user.setBalance(20000);
        userMapper.updateById(user);
    }

    @Test
    public void testDeleteUser() {
        userMapper.deleteById(5L);
    }

    @Test
    public void CustomTest() {
        List<Long> ids = List.of(1L, 2L, 3L, 4L);
        int amount=200;
        QueryWrapper<User> queryWrapper = new QueryWrapper<User>().in("id",ids);
        userMapper.updateblance(queryWrapper,amount);
    }
}
