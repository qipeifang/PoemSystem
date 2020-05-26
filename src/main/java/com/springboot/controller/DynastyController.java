package com.springboot.controller;

import com.springboot.bean.Result;
import com.springboot.entity.TDynasty;
import com.springboot.entity.VPoetry;
import com.springboot.service.DynastyService;
import com.springboot.service.VPoetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

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
}


