package com.richu.iorichu1.controller;

import cn.hutool.core.bean.BeanUtil;
import com.richu.iorichu1.Service.Userservice;
import com.richu.iorichu1.domain.dto.PageDto;
import com.richu.iorichu1.domain.dto.UserFormDto;
import com.richu.iorichu1.domain.po.User;
import com.richu.iorichu1.domain.query.UserQuery;
import com.richu.iorichu1.domain.vo.UserVo;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/users")
public class UserController {

    private final  Userservice userservice;

    @PostMapping
    public void addUser(@RequestBody UserFormDto userdto) {
        //1.把dto转为po
        User user = BeanUtil.copyProperties(userdto,User.class);
        userservice.save(user);
    }

    @DeleteMapping("{id}")
    public void deleteUser(@PathVariable Long id) {
        //1.把dto转为po
        userservice.removeById(id);
    }

/*    @GetMapping("{id}")
    public UserVo SearchUser(@PathVariable Long id) {
        User user = userservice.getById(id);
        //1.把po转为vo
        return BeanUtil.copyProperties(user, UserVo.class);
    }*/

    @GetMapping("{id}")
    public UserVo SearchUser(@PathVariable Long id) {
        return userservice.queryUsersAndAddress(id);
    }

    @GetMapping
    public List<UserVo> SearchUserlist(@RequestParam("ids") List<Long> ids) {
        return userservice.queryUsersByIds(ids);
    }

/*    @GetMapping
    public List<UserVo> SearchUserlist(@PathVariable List<Long> ids) {
        List<User> userlist = userservice.listByIds(ids);
        //1.把po转为vo
        return BeanUtil.copyToList(userlist, UserVo.class);
    }*/

    @GetMapping("/{id}/{money}")
    public void updatebalance(@PathVariable(value = "id") Long id, @PathVariable(value = "money") Integer money) {
        userservice.updatebalance(id,money);
    }

    @GetMapping("/list")
    public List<UserVo> updatelist(@RequestBody UserQuery query) {
        List<User> users = userservice.queryUsers(query.getName(),query.getStatus(),query.getMinBlance(),query.getMaxBlance());
        return BeanUtil.copyToList(users, UserVo.class);
    }


    @GetMapping("/page")
    public PageDto<UserVo> pagelist(@RequestBody UserQuery query) {
        return userservice.pagelist(query);
    }
}
