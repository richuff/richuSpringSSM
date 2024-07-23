package com.richu.iorichu1.Mapper;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.core.mapper.BaseMapper;
import com.richu.iorichu1.domain.po.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;


@Mapper
public interface UserMapper extends BaseMapper<User> {

    void updateblance(@Param("ew") QueryWrapper<User> queryWrapper,@Param("amount") int amount);

    @Update("update user set balance = balance - #{money} where id = #{id}")
    void changemoney(@Param("id") Long id,@Param("money") Integer money);
}
