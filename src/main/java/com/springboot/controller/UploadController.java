package com.springboot.controller;

import com.springboot.bean.Result;
import com.springboot.entity.TPoetry;
import com.springboot.entity.TUpload;
import com.springboot.entity.TUser;
import com.springboot.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@CrossOrigin(origins = {"http://localhost:3000"},allowCredentials = "true",allowedHeaders = {"X-Custom-Header"},
        maxAge = 3600L, methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.HEAD})
public class UploadController {
    @Autowired
    UploadService uploadService;
    //上传资源
    @PostMapping("/upload")
    @ResponseBody
    public Result upload(TUpload upload, HttpSession session){
        TUser user=(TUser) session.getAttribute("usersession");
        upload.setUseremail(user.getEmail());
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        upload.setUploadtime(date);
        uploadService.add(upload);
        Result result = new Result();
        result.setDescription("上传资源成功，请等待审核！");
        return result;
    }
    //审核资源
    @PostMapping("/check")
    @ResponseBody
    public Result check(TUpload upload,HttpSession session){
        TUser admin=(TUser) session.getAttribute("usersession");
        upload.setAdminemail(admin.getEmail());
        upload.setStatus(true);
        uploadService.add(upload);
        Result result = new Result();
        result.setDescription("上传资源成功，请等待审核！");
        return result;
    }
    //查询所有上传资源
    @GetMapping("/listalluploads")
    @ResponseBody
    public Result listalluploads() {
        List<TUpload> list=uploadService.findAll();
        Result result = new Result();
        result.setData(list);
        return result;
    }
    //根据关键字查找
    @PostMapping("/listalluploadsbykw")
    @ResponseBody
    public Result listalluploadsbykw(@RequestBody String kw) {
        if (kw!=null) kw="%"+kw+"%";
        if (kw==null) kw="%%";
        List<TUpload> list=uploadService.findAllByKw(kw);
        Result result = new Result();
        result.setData(list);
        return result;
    }
    //查找未审核的
    @GetMapping("/listalluploadsbystatus")
    @ResponseBody
    public Result listalluploadsbystatus() {
        List<TUpload> list=uploadService.findAllByStatus(false);
        Result result = new Result();
        result.setData(list);
        return result;
    }
}
