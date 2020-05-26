package com.springboot.controller;

import com.springboot.bean.Result;
import com.springboot.entity.PRecommend;
import com.springboot.service.RecommendService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.List;
@Controller
@CrossOrigin(origins = {"http://localhost:3000"},allowCredentials = "true",allowedHeaders = {"X-Custom-Header"},
        maxAge = 3600L, methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.HEAD})
public class RecommendController {
    @Autowired
    RecommendService recommendService;
    //推荐页面
    @GetMapping("/getrecommend")
    @ResponseBody
    public Result getrecommend(){
        Result result = new Result();
        List<PRecommend> list= recommendService.getAll(0);
        //放到data中
        result.setData(list);
        return result;
    }
}
