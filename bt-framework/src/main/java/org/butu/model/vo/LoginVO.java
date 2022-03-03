package org.butu.model.vo;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.butu.model.entity.LoginUser;

/**
 * @author BUTU
 */
@Data
@NoArgsConstructor
@AllArgsConstructor
public class LoginVO {
    private String token;
    private UserInfoVo userInfo;
}
