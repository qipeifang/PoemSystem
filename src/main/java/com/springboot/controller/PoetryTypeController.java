package com.springboot.controller;

import com.springboot.bean.Result;
import com.springboot.entity.TPoetryType;
import com.springboot.entity.VPoetry;
import com.springboot.service.PoetryTypeService;
import com.springboot.service.VPoetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@CrossOrigin(origins = {"http://localhost:3000"},allowCredentials = "true",allowedHeaders = {"X-Custom-Header"},
        maxAge = 3600L, methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.HEAD})
public class PoetryTypeController {
    @Autowired
    PoetryTypeService poetryTypeService;
    @Autowired
    VPoetryService vPoetryService;
    @GetMapping("/listallpoetrytype")
    @ResponseBody
    public Result listallpoetrytype(){
        Result result = new Result();
        List<TPoetryType> list= poetryTypeService.getAllPoetryType();
        //放到data中
        result.setData(list);
        return result;
    }
    @PostMapping("/listallpoetrytypebyid")
    @ResponseBody
    public Result listallpoetrytypebyid(@RequestBody String id){
        Result result = new Result();
        Integer id1=Integer.valueOf(id);
        List<VPoetry> list= vPoetryService.showAllByTypeId(id1);
        //放到data中
        result.setData(list);
        return result;
    }
}
