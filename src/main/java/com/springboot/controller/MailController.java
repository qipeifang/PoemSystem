package com.springboot.controller;

import com.alibaba.fastjson.JSONObject;
import com.springboot.bean.Newpass;
import com.springboot.bean.Result;
import com.springboot.entity.TUser;
import com.springboot.entity.UserLogin;
import com.springboot.imp.UserImp;
import com.springboot.security.SHA1Test;
import com.springboot.service.MailService;
import com.springboot.utils.VerifyCode;
import org.apache.catalina.servlet4preview.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.io.BufferedReader;
import java.io.InputStreamReader;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import com.springboot.bean.Find;

@CrossOrigin(origins = {"*"},allowCredentials = "true",allowedHeaders = {"*"},
        maxAge = 3600L, methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.HEAD,RequestMethod.OPTIONS})
@RestController
public class MailController {
    @Autowired
    private UserImp userImp;
    @Autowired
    private MailService mailService;
    public VerifyCode verifyCode = new VerifyCode();
    private TUser persistUser;
    Result result = new Result();


    @RequestMapping(value = "/changePassword",produces="application/json;charset=UTF-8")//
    public Result changePassword(@RequestBody Find find) {
        String to_ = new String();
        String username = new String();

        to_ = find.getTo_();
        username = find.getUsername();
        System.out.print(to_);
        if (to_ != null) {
            List<TUser> userList = userImp.findEmail(to_);
            for (TUser tuser : userList) {
                if (tuser.getEmail().equals(to_)) {
                    verifyCode.setCheck(mailService.sendSimpleMail(to_));
                    persistUser = tuser;
                }
            }
            result.setCode(200);
            return result;
        } else {
            result.setCode(400);
            return result;
        }
    }



    @RequestMapping(value = "/setNewPassword",produces="application/json;charset=UTF-8")
    public String setNewPassword(@RequestBody Newpass newpass) throws NoSuchAlgorithmException {
        String check = new String();
        String newPassword = new String();
        check = newpass.getCheck();
        newPassword = newpass.getNewPassword();
        if (verifyCode.getCheck().equals(check)) {
            SHA1Test sha1Test = new SHA1Test();
            String S_password = sha1Test.toHexString(newPassword);
            persistUser.setPassword(S_password);
            userImp.modifyUser(persistUser);
            return "200";
        } else {
            return "setNewPassword";
        }
    }
}


