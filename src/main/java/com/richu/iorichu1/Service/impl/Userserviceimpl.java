package com.richu.iorichu1.Service.impl;

import cn.hutool.core.bean.BeanUtil;
import cn.hutool.core.collection.CollUtil;
import cn.hutool.core.util.StrUtil;
import com.baomidou.mybatisplus.core.metadata.OrderItem;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.baomidou.mybatisplus.extension.toolkit.Db;
import com.richu.iorichu1.Mapper.UserMapper;
import com.richu.iorichu1.Service.Userservice;
import com.richu.iorichu1.domain.dto.PageDto;
import com.richu.iorichu1.domain.po.Address;
import com.richu.iorichu1.domain.po.User;
import com.richu.iorichu1.domain.query.UserQuery;
import com.richu.iorichu1.domain.vo.AddressVo;
import com.richu.iorichu1.domain.vo.UserVo;
import org.springframework.stereotype.Service;

import java.util.*;
import java.util.stream.Collectors;

@Service
public class Userserviceimpl extends ServiceImpl<UserMapper, User> implements Userservice{

    @Override
    public void updatebalance(Long id, Integer money) {
        //1.查询
        User user = getById(id);

        if (user == null || user.getStatus() == 2){
            throw new RuntimeException("用户状态异常");
        }

        if (user.getBalance()-money < 0){
            throw new RuntimeException("用户余额不足");
        }

        baseMapper.changemoney(id,money);
    }

    @Override
    public List<User> queryUsers(String name, Integer status, Integer minBlance, Integer maxBlance) {
        return lambdaQuery()
                .like(name != null ,User::getUsername, name)
                .like(status != null ,User::getStatus, status)
                .like(minBlance != null ,User::getBalance, minBlance)
                .like(maxBlance != null ,User::getBalance, maxBlance).list();
    }

    @Override
    public UserVo queryUsersAndAddress(Long id) {
        User user = getById(id);
        if (user == null || user.getStatus() == 2){
            throw new RuntimeException("用户状态异常");
        }

        //静态DB
        List<Address> addresses=Db.lambdaQuery(Address.class)
                .eq(Address::getUserId,user.getId()).list();

        UserVo uservo = BeanUtil.copyProperties(user,UserVo.class);

        if (CollUtil.isNotEmpty(addresses)){
            //设置uservo的地址集合
            uservo.setAddresses(BeanUtil.copyToList(addresses, AddressVo.class));
        }
        return uservo;
    }

    @Override
    public List<UserVo> queryUsersByIds(List<Long> ids) {
        List<User> users = listByIds(ids);

        if (!CollUtil.isNotEmpty(users)){
            return List.of();
        }

        List<Long> Userids = users.stream().map(User::getId).collect(Collectors.toList());
        List<Address> addresses = Db.lambdaQuery(Address.class).in(Address::getUserId,Userids).list();
        List<AddressVo> addressVoList = BeanUtil.copyToList(addresses, AddressVo.class);

        Map<Long,List<AddressVo>> adressmap = new HashMap<>(0);
        if (!CollUtil.isNotEmpty(addressVoList)){
            adressmap = addressVoList.stream().collect(Collectors.groupingBy(AddressVo::getId));
        }

        List<UserVo> list = new ArrayList<>(users.size());

        for (User user : users ){
            UserVo uservo = BeanUtil.copyProperties(user,UserVo.class);
            uservo.setAddresses(adressmap.get(uservo.getId()));
            list.add(uservo);
        }
        return list;
    }

    @Override
    public PageDto<UserVo> pagelist(UserQuery query) {
        String name = query.getName();
        Integer status = query.getStatus();
        //配置分页条件
        Page<User> page = Page.of(query.getPageNo(),query.getPageSize());
        //判断是否传入排序字段
        if (StrUtil.isNotBlank(query.getSortBy())){
            page.addOrder(new OrderItem(query.getSortBy(),query.getIsAsc()));
        }else{
            page.addOrder(new OrderItem("update_time",false));
        }
        //查询
        Page<User> pagelist = lambdaQuery().like(name != null,User::getUsername,name)
                .eq(status != null,User::getStatus,status)
                .page(page);
        //封装结果
        PageDto<UserVo> dto = new PageDto<>();
        dto.setPage((int) pagelist.getPages());
        dto.setTotal((int) pagelist.getTotal());
        if (CollUtil.isEmpty(pagelist.getRecords())){
            dto.setList(Collections.emptyList());
            return dto;
        }
        List<User> p = pagelist.getRecords();
        dto.setList(BeanUtil.copyToList(p,UserVo.class));
        return dto;
    }
}
