package com.springboot.controller;

import com.springboot.bean.Result;
import com.springboot.entity.TDynasty;
import com.springboot.entity.TPoetry;
import com.springboot.entity.VPoetry;
import com.springboot.service.DynastyService;
import com.springboot.service.VPoetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.text.ParseException;
import java.util.List;

@Controller
@CrossOrigin(origins = {"http://localhost:3000"},allowCredentials = "true",allowedHeaders = {"X-Custom-Header"},
        maxAge = 3600L, methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.HEAD})

public class DynastyController {
    @Autowired
    DynastyService dynastyService;
    @Autowired
    VPoetryService vPoetryService;

    @GetMapping("/listalldynasty")
    @ResponseBody
    public Result listallpoetrytype(){
        Result result = new Result();
        List<TDynasty> list= dynastyService.getAllDynasty();
        //放到data中
        result.setData(list);
        return result;
    }
    @PostMapping("/listalldynastybyid")
    @ResponseBody
    public Result listallpoetrytypebyid(@RequestBody String id){
        Result result = new Result();
        Integer id1=Integer.valueOf(id);
        List<VPoetry> list= vPoetryService.showAllByDynastyId(id1);
        //放到data中
        result.setData(list);
        return result;
    }

    //管理员功能
    @GetMapping("/admin/listdynasty")
    @ResponseBody
    public Result adminlistallcomment(String kw, Model model){
        if (kw!=null) kw="%"+kw+"%";
        if (kw==null) kw="%%";
        Result result = new Result();
        //通过关键字查询
        List<TDynasty> listcomms= dynastyService.adminshowAll(kw);
        //放到data中
        result.setData(listcomms);
        return result;
    }
    @PostMapping("/admin/listdynasty")
    @ResponseBody
    public Result adminlistallcommentbykw(@RequestBody String kw){
        if (kw!=null) kw="%"+kw+"%";
        if (kw==null) kw="%%";
        System.out.println(kw);
        Result result = new Result();
        //通过关键字查询
        List<TDynasty> listcomms= dynastyService.adminshowAll(kw);
        //放到data中
        result.setData(listcomms);
        return result;
    }

    @PostMapping("/admin/deletedynasty")
    @ResponseBody
    public Result admindelete(@RequestBody String id){
        Result result = new Result();
        Integer id1=Integer.valueOf(id);
        dynastyService.deleteById(id1);
        result.setDescription("删除成功");//添加返回信息描述
        //添加返回数据
        String kw="%%";
        //通过关键字查询
        List<TDynasty> listcomms= dynastyService.adminshowAll(kw);
        //放到data中
        result.setData(listcomms);
        return result;
    }

    //管理员添加朝代
    @PostMapping("/admin/adddynasty")
    @ResponseBody
    public Result addDynasty(TDynasty dynasty) throws ParseException {
        Result result=new Result();
        dynastyService.AddDynasty(dynasty);
        result.setDescription("添加成功");//添加返回信息描述
        result.setData(dynasty);
        return  result;
    }

}


