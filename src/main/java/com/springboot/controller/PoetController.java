package com.springboot.controller;

import com.springboot.bean.Result;
import com.springboot.entity.TPoet;
import com.springboot.entity.TUser;
import com.springboot.service.PoetService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.util.List;
//诗人管理
@Controller
@CrossOrigin(origins = {"http://localhost:3000"},allowCredentials = "true",allowedHeaders = {"X-Custom-Header"},
        maxAge = 3600L, methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.HEAD})
public class PoetController {
    @Autowired
    PoetService poetService;

    @GetMapping("/listpoets")
    @ResponseBody
    public Result listallpoets(String kw){
        if (kw!=null) kw="%"+kw+"%";
        if (kw==null) kw="%%";
        Result result = new Result();
        //通过输入的关键字展示信息
        List<TPoet> listpoes= poetService.showAll(kw);
        //放到data中
        result.setData(listpoes);
        return result;
    }
    @PostMapping("/listpoets")
    @ResponseBody
    public Result listallpoetsbykw(@RequestBody String kw, HttpSession session){
        if (kw!=null) kw="%"+kw+"%";
        if (kw==null) kw="%%";
        System.out.println(kw);
        TUser usersession=(TUser) (session.getAttribute("usersession"));
        String email=usersession.getEmail();
        Result result = new Result();
        //通过输入的关键字展示信息
        List<TPoet> listpoes= poetService.showAll(kw);
        //放到data中
        result.setData(listpoes);
        return result;
    }

    @PostMapping("/deletepoet")
    @ResponseBody
    public Result delete(@RequestBody String id,HttpSession session){
        TUser usersession=(TUser) (session.getAttribute("usersession"));
        String email=usersession.getEmail();
        Result result = new Result();
        Integer id1=Integer.valueOf(id);
        poetService.deleteById(id1);
        result.setDescription("删除成功");//添加返回信息描述
        //添加返回数据
        String kw="%%";
        //通过输入的关键字展示信息
        List<TPoet> listpoes= poetService.showAll(kw);
        //放到data中
        result.setData(listpoes);
        return result;
    }
    @GetMapping("/getpoet")
    @ResponseBody
    public Result getpoet(){
        String kw="%%";
        Result result = new Result();
        //通过输入的关键字展示信息
        List<TPoet> listpoes= poetService.showAll(kw);
        //放到data中
        result.setData(listpoes);
        return result;
    }

    @PostMapping("/displaypoetbyid")
    @ResponseBody
    public Result displaypoetbyid(@RequestBody String id,HttpSession session){
        Result result = new Result();
        Integer id1=Integer.valueOf(id);
        List<TPoet> listpoes= poetService.displayPoet(id1);
        //放到data中
        result.setData(listpoes);
        return result;
    }
}
