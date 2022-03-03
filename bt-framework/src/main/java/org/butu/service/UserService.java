package org.butu.service;

import org.butu.common.api.ApiResult;
import org.butu.model.dto.LoginDTO;
import org.butu.model.dto.RegisterDTO;
import org.butu.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-02
 */
public interface UserService extends IService<User> {

//    User executeRegister(RegisterDTO dto);
//
//    String executeLogin(LoginDTO dto);
//
//    User getUserByUsername(String username);

    ApiResult login(User user);
}
