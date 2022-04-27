package com.itrjp.demo.pojo;

import lombok.Data;
import lombok.EqualsAndHashCode;

/**
 * 删除用户 请求参数
 *
 * @author renjp
 * @date 2022/4/27 23:10
 */

@EqualsAndHashCode(callSuper = true)
@Data
public class UserDeleteParam extends CommonParam {
    private String userId;
}
