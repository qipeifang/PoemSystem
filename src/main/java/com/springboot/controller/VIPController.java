package com.springboot.controller;
import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.springboot.entity.TCollection;
import com.springboot.entity.TUser;
import com.springboot.entity.UserLogin;
import com.springboot.entity.VCollAndPoem;
import com.springboot.security.SHA1Test;
import com.springboot.service.CollectionService;
import com.springboot.service.UserService;
import com.springboot.service.VCollAndPoemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

@Controller
@CrossOrigin(origins = {"http://localhost:3000"},allowCredentials = "true",allowedHeaders = {"X-Custom-Header"},
        maxAge = 3600L, methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.HEAD})
public class VIPController {
    @Autowired
    private UserService userService;

    @PostMapping("/logintest")
    @ResponseBody
    public String logintest(HttpSession session, Model model,TUser user) throws NoSuchAlgorithmException {

        System.out.println("@@@@@@@@@@@@@@@@@@"+user.getPassword());
       return "";
    }
}
