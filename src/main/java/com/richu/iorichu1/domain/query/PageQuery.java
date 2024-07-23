package com.richu.iorichu1.domain.query;

import lombok.Data;

@Data
public class PageQuery{
    private Integer pageNo;

    private Integer pageSize;

    private String sortBy;

    private Boolean isAsc;

}
