package com.itrjp.demo.pojo;

import com.itrjp.demo.valid.Filled;
import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 用户更新 请求实体
 *
 * @author renjp
 * @date 2022/4/27 23:05
 */
@Data
@EqualsAndHashCode(callSuper = true)
public class UserUpdateParam extends CommonParam {

    private String userId;
    @Filled("userName")
    private String userName;
}
