package com.springboot.controller;
import com.springboot.bean.Result;
import com.springboot.entity.TPoet;
import com.springboot.entity.TPoetry;
import com.springboot.entity.VPoetry;
import com.springboot.service.PoetryService;
import com.springboot.service.VPoetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
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


    //管理员功能
    @GetMapping("/admin/listpoetry")
    @ResponseBody
    public Result adminlistallpoetry(String kw, Model model){
        if (kw!=null) kw="%"+kw+"%";
        if (kw==null) kw="%%";
        Result result = new Result();
        //通过关键字查询
        List<VPoetry> listcomms= vPoetryService.adminshowAll(kw);
        //放到data中
        result.setData(listcomms);
        return result;
    }
    @PostMapping("/admin/listpoetry")
    @ResponseBody
    public Result adminlistallpoetrybykw(@RequestBody String kw){
        if (kw!=null) kw="%"+kw+"%";
        if (kw==null) kw="%%";
        System.out.println(kw);
        Result result = new Result();
        //通过关键字查询
        List<VPoetry> listcomms= vPoetryService.adminshowAll(kw);
        //放到data中
        result.setData(listcomms);
        return result;
    }

    @PostMapping("/admin/deletepoetry")
    @ResponseBody
    public Result admindelete(@RequestBody String id){
        Result result = new Result();
        Integer id1=Integer.valueOf(id);
        poetryService.deleteById(id1);
        result.setDescription("删除成功");//添加返回信息描述
        //添加返回数据
        String kw="%%";
        //通过关键字查询
        List<VPoetry> listcomms= vPoetryService.adminshowAll(kw);
        //放到data中
        result.setData(listcomms);
        return result;
    }


    //管理员添加诗词
    @PostMapping("/admin/addpoetry")
    @ResponseBody
    public Result addpoetry(TPoetry poetry) throws ParseException {
        Result result=new Result();
        poetryService.AddPoetry(poetry);
        result.setDescription("添加成功");//添加返回信息描述
        result.setData(poetry);
        return  result;
    }

    //管理员修改诗词
    @PostMapping("/admin/modifypoetry")
    @ResponseBody
    public Result modifypoetry(TPoetry poetry) throws ParseException {
        Result result=new Result();
        System.out.println("前端传来的数据poetry====="+poetry.toString());//前端传来的数据
        poetryService.AddPoetry(poetry);
        result.setDescription("添加成功");//添加返回信息描述
        result.setData(poetry);
        return  result;
    }
}
