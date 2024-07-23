package com.richu.iorichu1.Service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.richu.iorichu1.domain.dto.PageDto;
import com.richu.iorichu1.domain.po.User;
import com.richu.iorichu1.domain.query.UserQuery;
import com.richu.iorichu1.domain.vo.UserVo;

import java.util.List;

public interface Userservice extends IService<User>{
    void updatebalance(Long id, Integer money);

    List<User> queryUsers(String name, Integer status, Integer minBlance, Integer maxBlance);

    UserVo queryUsersAndAddress(Long id);

    List<UserVo> queryUsersByIds(List<Long> ids);

    PageDto<UserVo> pagelist(UserQuery query);
}
