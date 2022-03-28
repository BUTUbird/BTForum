package org.butu.model.dto;

import lombok.Data;

/**
 * @program: BTForum
 * @description:
 * @packagename: org.butu.model.dto
 * @author: BUTUbird
 * @date: 2022-03-27 13:37
 **/
@Data
public class PwdDTO {
    private String username;
    private String password;
    private String rePassword;
}
