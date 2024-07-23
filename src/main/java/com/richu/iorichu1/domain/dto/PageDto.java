package com.richu.iorichu1.domain.dto;

import lombok.Data;
import java.util.List;

@Data
public class PageDto<T> {
    //总条数
    private Integer total;
    //总页数
    private Integer page;
    //总集合
    private List<T> list;
}
