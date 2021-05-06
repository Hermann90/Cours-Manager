/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.hermann90.CoursManager.utils;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import lombok.ToString;

/**
 *
 * @author hermann90
 */
@Data @AllArgsConstructor @NoArgsConstructor @ToString
public class RegisterForm {
    private String userName;
    private String password;
    private String rePassword;
}
