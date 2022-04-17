package org.butu.service;

import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import org.butu.model.dto.LoginDTO;
import org.butu.model.dto.PwdDTO;
import org.butu.model.dto.RegisterDTO;
import org.butu.model.entity.User;
import com.baomidou.mybatisplus.extension.service.IService;
import org.butu.model.vo.ProfileVO;

import java.util.List;

/**
 * <p>
 * 用户表 服务类
 * </p>
 *
 * @author BUTUbird
 * @since 2022-03-02
 */
public interface UserService extends IService<User> {

    User executeRegister(RegisterDTO dto);

    String executeLogin(LoginDTO dto);

    User getUserByUsername(String username);

    ProfileVO getUserProfile(String userId);

    User getUserById(String userId);

    Page<User> searchKey(String key, Page<User> page);

    void updateImg(String url,String username);

    void logout(String name);

    User findUserByMail(String mail);

    User findUserByToken(String token);

    void resetPwd(PwdDTO dto);

    List<User> getFans(String id);
}
