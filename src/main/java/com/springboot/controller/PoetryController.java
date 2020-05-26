package com.springboot.controller;
import com.springboot.bean.Result;
import com.springboot.entity.PRecommend;
import com.springboot.entity.TPoet;
import com.springboot.entity.TPoetry;
import com.springboot.entity.VPoetry;
import com.springboot.service.PoetryService;
import com.springboot.service.VPoetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@CrossOrigin(origins = {"http://localhost:3000"},allowCredentials = "true",allowedHeaders = {"X-Custom-Header"},
        maxAge = 3600L, methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.HEAD})
public class PoetryController {
    @Autowired
    PoetryService poetryService;
    @Autowired
    VPoetryService vPoetryService;
    //展示所有诗词
    @GetMapping("/listallpoetrys")
    @ResponseBody
    public Result listallpoetrys(){
        Result result = new Result();
        List<VPoetry> list= vPoetryService.showAll();
        //放到data中
        result.setData(list);
        return result;
    }
    //根据关键字展示诗词内容
    @PostMapping("/listpoetrybykw")
    @ResponseBody
    public Result listallcollbykw(@RequestBody String kw){
        if (kw!=null) kw="%"+kw+"%";
        if (kw==null) kw="%%";
        Result result = new Result();
        List<VPoetry> list= vPoetryService.showAllbyKw(kw);
        //放到data中
        result.setData(list);
        return result;
    }
    @PostMapping("/displaypoetrybyid")
    @ResponseBody
    public Result displaypoetrybyid(@RequestBody String id, HttpSession session){
        Result result = new Result();
        Integer id1=Integer.valueOf(id);
        List<VPoetry> listpoes= vPoetryService.displayPoetry(id1);
        //放到data中
        result.setData(listpoes);
        return result;
    }

}
