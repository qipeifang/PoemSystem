package com.springboot.controller;

import com.springboot.bean.Result;
import com.springboot.entity.TComment;
import com.springboot.entity.TNotice;
import com.springboot.entity.TUser;
import com.springboot.service.NoticeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import javax.xml.crypto.Data;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//通知管理
@Controller
@CrossOrigin(origins = {"http://localhost:3000"},allowCredentials = "true",allowedHeaders = {"X-Custom-Header"},
        maxAge = 3600L, methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.HEAD})
public class NoticeController {
    @Autowired
    NoticeService noticeService;

    @GetMapping("/admin/listnotics")
    @ResponseBody
    public Result listallnotice(String kw, Model model, HttpSession session){
        if (kw!=null) kw="%"+kw+"%";
        if (kw==null) kw="%%";
        TUser usersession=(TUser) (session.getAttribute("usersession"));
        String email=usersession.getEmail();
        Result result = new Result();
        //通过用户邮箱和输入的关键字展示收藏
        List<TNotice> listcomms= noticeService.showAll(email,kw);
        //放到data中
        result.setData(listcomms);
        return result;
    }
    @PostMapping("/admin/listnotics")
    @ResponseBody
    public Result listallnoticebykw(@RequestBody String kw,HttpSession session){
        if (kw!=null) kw="%"+kw+"%";
        if (kw==null) kw="%%";
        System.out.println(kw);
        TUser usersession=(TUser) (session.getAttribute("usersession"));
        String email=usersession.getEmail();
        Result result = new Result();
        //通过用户邮箱和输入的关键字展示收藏
        List<TNotice> listcomms= noticeService.showAll(email,kw);
        //放到data中
        result.setData(listcomms);
        return result;
    }

    @PostMapping("/admin/deletenotic")
    @ResponseBody
    public Result delete(@RequestBody String id,HttpSession session){
        TUser usersession=(TUser) (session.getAttribute("usersession"));
        String email=usersession.getEmail();
        Result result = new Result();
        Integer id1=Integer.valueOf(id);
        noticeService.deleteById(id1);
        result.setDescription("删除成功");//添加返回信息描述
        //添加返回数据
        String kw="%%";
        //通过用户邮箱和输入的关键字展示收藏
        List<TNotice> listcolls= noticeService.showAll(email,kw);
        //放到data中
        result.setData(listcolls);
        return result;
    }


//    public Result admindelete(@RequestBody String id){
//        Result result = new Result();
//        Integer id1=Integer.valueOf(id);
//        noticeService.deleteById(id1);
//        result.setDescription("删除成功");//添加返回信息描述
//        //添加返回数据
//        String kw="%%";
//        //通过关键字查询
//        List<TNotice> listcomms= noticeService.adminshowAll(kw);
//        //放到data中
//        result.setData(listcomms);
//        return result;
//    }




    //管理员发布通知
    @PostMapping("/admin/writenotice")
    @ResponseBody
    public Result write(TNotice notice,HttpSession session) throws ParseException {
        Result result=new Result();
        TUser usersession=(TUser) (session.getAttribute("usersession"));
        notice.setEmail(usersession.getEmail());
        //获取当前时间
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");
        String time=sdf.format(date);
        notice.setTime(sdf.parse(time));

        noticeService.WriteNotice(notice);
        result.setData(notice);
        return  result;
    }


    //用户只能查看通知，没有任何操作
    @GetMapping("/listnotics")
    @ResponseBody
    public Result userlistallcoll(){
        Result result = new Result();
        //通过用户邮箱和输入的关键字展示通知
        List<TNotice> listnots= noticeService.usershowAll();
        //放到data中
        result.setData(listnots);
        return result;
    }

}
