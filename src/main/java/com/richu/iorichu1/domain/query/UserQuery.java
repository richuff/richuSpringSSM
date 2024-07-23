package com.richu.iorichu1.domain.query;

import lombok.Data;

@Data
public class UserQuery extends PageQuery {
    private String name;

    private Integer status;

    private Integer minBlance;

    private Integer maxBlance;
}
