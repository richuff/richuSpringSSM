package com.richu.iorichu1.Service.impl;

import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.richu.iorichu1.Service.Userservice;
import com.richu.iorichu1.domain.po.User;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;

import java.time.LocalDateTime;
import java.util.List;

@SpringBootTest
class UserserviceimplTest {

    @Autowired
    private Userservice userservice;

    @Test
    public void testsaveUser(){
        User user = new User();
        user.setId(7L);
        user.setUsername("李磊");
        user.setPassword("123");
        user.setPhone("18688990011");
        user.setBalance(200);
        user.setInfo("{\"age\": 24, \"intro\": \"数学老师\", \"gender\": \"female\"}");
        user.setCreateTime(LocalDateTime.now());
        user.setUpdateTime(LocalDateTime.now());
        userservice.save(user);
    }

    @Test
    public void testpageUser(){
        //分页条件
        //页数
        int pagenumber = 2;
        //每页大小
        int pagesize = 3;

        Page<User> page = new Page<>(pagenumber, pagesize);

        page.addOrder(new OrderItem("balance",true));

        Page<User> users = userservice.page(page);

        //解析结果
        long total = users.getTotal();
        System.out.println(total);
        List<User> records = users.getRecords();
        records.forEach(System.out::println);
    }
}