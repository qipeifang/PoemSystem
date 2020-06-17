package com.springboot.controller;

import com.springboot.bean.Result;
import com.springboot.entity.TDynasty;
import com.springboot.entity.TPoetry;
import com.springboot.entity.TUpload;
import com.springboot.entity.TUser;
import com.springboot.img.Check;
import com.springboot.service.UploadService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.io.File;
import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

@Controller
@CrossOrigin(origins = {"http://localhost:3000"},allowCredentials = "true",allowedHeaders = {"X-Custom-Header"},
        maxAge = 3600L, methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.HEAD})
public class UploadController {
    @Autowired
    UploadService uploadService;
    @PostMapping("/imgupload")
    @ResponseBody
    public Result imgupload(@RequestParam("file") MultipartFile file) {//1. 接受上传的文件  @RequestParam("file") MultipartFile file
        Result result=new Result();
        String ans="";
        try {
            //2.根据时间戳创建新的文件名，这样即便是第二次上传相同名称的文件，也不会把第一次的文件覆盖了
            String fileName = System.currentTimeMillis() + file.getOriginalFilename();
            //3.通过req.getServletContext().getRealPath("") 获取当前项目的真实路径，然后拼接前面的文件名
            String destFileName = "D:\\" + "uploaded" + File.separator + fileName;
            //4.第一次运行的时候，这个文件所在的目录往往是不存在的，这里需要创建一下目录（创建到了webapp下uploaded文件夹下）
            File destFile = new File(destFileName);
            destFile.getParentFile().mkdirs();
            //5.把浏览器上传的文件复制到希望的位置
            file.transferTo(destFile);
            System.out.println(destFileName);
            Check check=new Check();
            ans=check.test( destFileName);
        } catch (IOException e) {
            e.printStackTrace();
            result.setDescription("上传图片失败");
            return result;
        }
        result.setData(ans);
        return result;
    }
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
    //修改
    @PostMapping("/modifyupload")
    @ResponseBody
    public Result modify(TUpload upload){
        Date date = new Date();
        SimpleDateFormat dateFormat= new SimpleDateFormat("yyyy-MM-dd :hh:mm:ss");
        upload.setUploadtime(date);
        upload.setStatus(false);
        uploadService.update(upload);
        Result result = new Result();
        result.setDescription("修改资源成功，请等待审核！");
        List<TUpload> list=uploadService.findAll();
        result.setData(list);
        return result;
    }
    //审核通过
    @PostMapping("/checktrue")
    @ResponseBody
    public Result checktrue(@RequestBody String id,HttpSession session){
        TUser admin=(TUser) session.getAttribute("usersession");
        long id1=Integer.valueOf(id);
        uploadService.check(admin.getEmail(),true,id1);
        Result result = new Result();
        result.setDescription("操作成功");
        List<TUpload> list=uploadService.findAll();
        result.setData(list);
        return result;
    }

    //不通过
   @PostMapping("/checkfalse")
    @ResponseBody
    public Result checkfalse(@RequestBody String id,HttpSession session){
       TUser admin=(TUser) session.getAttribute("usersession");
       long id1=Integer.valueOf(id);
       uploadService.check(admin.getEmail(),false,id1);
       Result result = new Result();
       result.setDescription("操作成功");
       List<TUpload> list=uploadService.findAll();
       result.setData(list);
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
    @GetMapping("/listalluploadsbyfalse")
    @ResponseBody
    public Result listalluploadsbystatus() {
        List<TUpload> list=uploadService.findAllByStatus(false);
        Result result = new Result();
        result.setData(list);
        return result;
    }
    //查找已审核的
    @GetMapping("/listallcheckuploads")
    @ResponseBody
    public Result listallcheckuploads() {
        List<TUpload> list=uploadService.findAllByStatus(true);
        Result result = new Result();
        result.setData(list);
        return result;
    }

    @PostMapping("/deleteupload")
    @ResponseBody
    public Result delete(@RequestBody String id){
        Result result = new Result();
        Integer id1=Integer.valueOf(id);
        uploadService.deleteById(id1);
        result.setDescription("删除成功");//添加返回信息描述
        //添加返回数据
        String kw="%%";
        //通过关键字查询
        List<TUpload> listcomms= uploadService.showAllByStatus(kw,true);
        //放到data中
        result.setData(listcomms);
        return result;
    }

}
