package com.itrjp.demo.controller;

import com.itrjp.demo.pojo.UserDeleteParam;
import com.itrjp.demo.pojo.UserUpdateParam;
import com.itrjp.demo.result.Result;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import javax.validation.Valid;


/**
 * 用户Controller
 *
 * @author renjp
 * @date 2022/4/27 23:03
 */
@Slf4j
@RestController
@RequestMapping("api/user")
public class UserController {
    /**
     * 此方法请求参数使用了{@link com.itrjp.demo.valid.Filled}注解.
     * @param param
     * @return
     */
    @PostMapping("update")
    public Result<String> update(@Valid UserUpdateParam param) {
        log.info("UserController#update, param: {}", param);
        return Result.ok("ok");
    }


    @PostMapping("delete")
    public Result<String> delete(UserDeleteParam param) {
        log.info("UserController#delete, param: {}", param);
        return Result.ok("ok");
    }
}
