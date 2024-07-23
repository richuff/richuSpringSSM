package com.richu.iorichu1.domain.vo;

import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableName;
import com.baomidou.mybatisplus.extension.handlers.JacksonTypeHandler;
import lombok.Data;
import org.apache.ibatis.type.TypeHandler;

import java.util.List;

@Data
@TableName(value = "user")
public class UserVo {

    private Long id;

    private String username;

    private String info;

    private Integer status;

    private String balance;

    public List<AddressVo> addresses;
}
