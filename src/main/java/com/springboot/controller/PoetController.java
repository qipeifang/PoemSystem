package com.springboot.controller;

import com.springboot.bean.Result;
import com.springboot.entity.TPoet;
import com.springboot.entity.TUser;
import com.springboot.entity.VPoet;
import com.springboot.entity.VPoetry;
import com.springboot.security.SHA1Test;
import com.springboot.service.DynastyService;
import com.springboot.service.PoetService;
import com.springboot.service.VPoetService;
import com.springboot.service.VPoetryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;
//诗人管理
@Controller
@CrossOrigin(origins = {"http://localhost:3000"},allowCredentials = "true",allowedHeaders = {"X-Custom-Header"},
        maxAge = 3600L, methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.HEAD})
public class PoetController {
    @Autowired
    PoetService poetService;
    @Autowired
    VPoetService vPoetService;
    @Autowired
    DynastyService dynastyService;
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

    //管理员功能
    @GetMapping("/admin/listpoet")
    @ResponseBody
    public Result adminlistallpoet(String kw, Model model){
        if (kw!=null) kw="%"+kw+"%";
        if (kw==null) kw="%%";
        Result result = new Result();
        //通过关键字查询
        List<VPoet> listcomms= vPoetService.adminshowAll(kw);
        //放到data中
        result.setData(listcomms);
        return result;
    }
    @PostMapping("/admin/listpoet")
    @ResponseBody
    public Result adminlistallpoetbykw(@RequestBody String kw){
        if (kw!=null) kw="%"+kw+"%";
        if (kw==null) kw="%%";
        System.out.println(kw);
        Result result = new Result();
        //通过关键字查询
        List<VPoet> listcomms= vPoetService.adminshowAll(kw);
        //放到data中
        result.setData(listcomms);
        return result;
    }

    @PostMapping("/admin/deletepoet")
    @ResponseBody
    public Result admindelete(@RequestBody String id){
        Result result = new Result();
        Integer id1=Integer.valueOf(id);
        poetService.deleteById(id1);
        result.setDescription("删除成功");//添加返回信息描述
        //添加返回数据
        String kw="%%";
        //通过关键字查询
        List<VPoet> listcomms= vPoetService.adminshowAll(kw);
        //放到data中
        result.setData(listcomms);
        return result;
    }


    //管理员添加诗人
    @PostMapping("/admin/addpoet")
    @ResponseBody
//    public Result addpoet(TPoet poet) throws ParseException {
    public Result addpoet(VPoet vpoet) throws ParseException {
        Result result=new Result();
        TPoet poet=new TPoet();
        poet.setName(vpoet.getName());
        poet.setName_zi(vpoet.getName_zi());
        poet.setName_hao(vpoet.getName_hao());
        poet.setGender(vpoet.getGender());
        poet.setBirthday(vpoet.getBirthday());
        poet.setDeathday(vpoet.getDeathday());
        poet.setDynastyid(dynastyService.dynasty_exist(vpoet.getDynastyname()));//朝代id
        poet.setIntro(vpoet.getIntro());//简介
        poet.setMasterwork(vpoet.getMasterwork());//主要作品

        poetService.AddPoet(poet);
        result.setDescription("添加成功");//添加返回信息描述
        result.setData(poet);
        return  result;
    }

    //管理员编辑修改诗人信息
    @PostMapping("/admin/modifypoet")
    @ResponseBody
    public Result ModifyPoet(VPoet vpoet) throws NoSuchAlgorithmException {
        Result result=new Result();
        TPoet poet=new TPoet();
        poet.setId(vpoet.getId());
        poet.setName(vpoet.getName());
        poet.setName_zi(vpoet.getName_zi());
        poet.setName_hao(vpoet.getName_hao());
        poet.setGender(vpoet.getGender());
        poet.setBirthday(vpoet.getBirthday());
        poet.setDeathday(vpoet.getDeathday());
        poet.setDynastyid(dynastyService.dynasty_exist(vpoet.getDynastyname()));//朝代id
        poet.setIntro(vpoet.getIntro());//简介
        poet.setMasterwork(vpoet.getMasterwork());//主要作品

        poetService.AddPoet(poet);
        result.setDescription("添加成功");//添加返回信息描述
        result.setData(poet);
        return  result;
    }

    //    public Result ModifyPoet(TPoet poet) throws NoSuchAlgorithmException {
//        Result result = new Result();
//        System.out.println("前端传来的数据poet====="+poet.toString());//前端传来的数据
//        poetService.modifyPoet(poet);
//        result.setDescription("修改成功");//添加返回信息描述
//        result.setData(poet);
//        return result;

}
