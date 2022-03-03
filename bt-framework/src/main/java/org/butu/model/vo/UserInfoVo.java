package org.butu.model.vo;

import lombok.Data;
import lombok.experimental.Accessors;

/**
 * @author BUTU
 */
@Data
@Accessors(chain = true)
public class UserInfoVo {
    private String id;
    private String username;
    private String avatar;
    private String email;
}
