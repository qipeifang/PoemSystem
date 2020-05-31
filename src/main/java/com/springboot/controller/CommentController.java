package com.springboot.controller;

import com.springboot.bean.Result;
import com.springboot.entity.TComment;
import com.springboot.entity.TUser;
import com.springboot.service.CommentService;
import com.springboot.service.PoetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

//评论管理
@Controller
@CrossOrigin(origins = {"http://localhost:3000"},allowCredentials = "true",allowedHeaders = {"X-Custom-Header"},
        maxAge = 3600L, methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.HEAD})
public class CommentController {
    @Autowired
    private CommentService commentService;
    @Autowired
    private PoetryService poetryService;

    @GetMapping("/listcomments")
    @ResponseBody
    public Result listallcomment(String kw, Model model, HttpSession session){
        if (kw!=null) kw="%"+kw+"%";
        if (kw==null) kw="%%";
        TUser usersession=(TUser) (session.getAttribute("usersession"));
        String email=usersession.getEmail();
        Result result = new Result();
        //通过用户邮箱和输入的关键字展示收藏
        List<TComment> listcomms= commentService.showAll(email,kw);
        //放到data中
        result.setData(listcomms);
        return result;
    }
    @PostMapping("/listcomments")
    @ResponseBody
    public Result listallcommentbykw(@RequestBody String kw,HttpSession session){
        if (kw!=null) kw="%"+kw+"%";
        if (kw==null) kw="%%";
        System.out.println(kw);
        TUser usersession=(TUser) (session.getAttribute("usersession"));
        String email=usersession.getEmail();
        Result result = new Result();
        //通过用户邮箱和输入的关键字展示收藏
        List<TComment> listcomms= commentService.showAll(email,kw);


        System.out.println("前端传来的数据kw====="+kw);//前端传来的数据
        System.out.println("后端传过去的数据listcomms====="+listcomms);//后端传过去的数据


        //放到data中
        result.setData(listcomms);
        return result;
    }

    @PostMapping("/deletecomment")
    @ResponseBody
    public Result delete(@RequestBody String id,HttpSession session){
        TUser usersession=(TUser) (session.getAttribute("usersession"));
        String email=usersession.getEmail();
        Result result = new Result();
        Integer id1=Integer.valueOf(id);
        commentService.deleteById(id1);
        result.setDescription("删除成功");//添加返回信息描述
        //添加返回数据
        String kw="%%";
        //通过用户邮箱和输入的关键字展示收藏
        List<TComment> listcomms= commentService.showAll(email,kw);
        //放到data中
        result.setData(listcomms);
        return result;
    }

    //用户发布评论
    @PostMapping("/writecomment")
    @ResponseBody
//    public Result write(@Valid TComment comment, Long poetryid,HttpSession session) throws ParseException {
    public Result write(TComment comment,HttpSession session) throws ParseException {
        Result result=new Result();
        TUser usersession=(TUser) (session.getAttribute("usersession"));
        comment.setEmail(usersession.getEmail());
        //获取当前时间
        Date date=new Date();
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd kk:mm:ss ");
        String time=sdf.format(date);
        comment.setTime(sdf.parse(time));
        //获取并设置诗词名
        //TPoetry poetry=poetryService.findById(poetryid);
        //comment.setPoetryid(poetryid);
       // comment.setPoetryname(poetry.getName());
        commentService.WriteComment(comment);
        result.setData(comment);
        return  result;
    }


    @GetMapping("/admin/listcomments")
    @ResponseBody
    public Result adminlistallcomment(String kw, Model model){
        if (kw!=null) kw="%"+kw+"%";
        if (kw==null) kw="%%";
        Result result = new Result();
        //通过关键字查询
        List<TComment> listcomms= commentService.adminshowAll(kw);
        //放到data中
        result.setData(listcomms);
        return result;
    }
    @PostMapping("/admin/listcomments")
    @ResponseBody
    public Result adminlistallcommentbykw(@RequestBody String kw){
        if (kw!=null) kw="%"+kw+"%";
        if (kw==null) kw="%%";
        System.out.println(kw);
        Result result = new Result();
        //通过关键字查询
        List<TComment> listcomms= commentService.adminshowAll(kw);
        //放到data中
        result.setData(listcomms);
        return result;
    }

    @PostMapping("/admin/deletecomment")
    @ResponseBody
    public Result admindelete(@RequestBody String id){
        Result result = new Result();
        Integer id1=Integer.valueOf(id);
        commentService.deleteById(id1);
        result.setDescription("删除成功");//添加返回信息描述
        //添加返回数据
        String kw="%%";
        //通过关键字查询
        List<TComment> listcomms= commentService.adminshowAll(kw);
        //放到data中
        result.setData(listcomms);
        return result;
    }


    //诗词下显示评论
    @PostMapping("/poetry/listcomments")
    @ResponseBody
    public Result poetry_listcomments(@RequestBody String poetryname){
        Result result=new Result();
        if (poetryname!=null) poetryname="%"+poetryname+"%";
        if (poetryname==null) poetryname="%%";
        System.out.println(poetryname);
        System.out.println("前端传来的数据poetryname====="+poetryname);//前端传来的数据
        List<TComment> listcomms=commentService.poetry_listcomments(poetryname);


        //通过关键字查询
//        List<TComment> listcomms= commentService.adminshowAll(poetryname);


        System.out.println("后端传过去的数据listcomms====="+listcomms.toString());//后端传过去的数据
        result.setData(listcomms);
        return result;
    }
}