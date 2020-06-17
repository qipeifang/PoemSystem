package com.springboot.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.springboot.bean.Result;
import com.springboot.entity.PRecommend;
import com.springboot.entity.TCollection;
import com.springboot.entity.TUser;
import com.springboot.entity.VCollAndPoem;
import com.springboot.service.CollectionService;
import com.springboot.service.RecommendService;
import com.springboot.service.VCollAndPoemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpSession;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


@Controller
@CrossOrigin(origins = {"http://localhost:3000"},allowCredentials = "true",allowedHeaders = {"X-Custom-Header"},
        maxAge = 3600L, methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.HEAD})
public class CollectionController {

    @Autowired
    CollectionService collectionService;
    @Autowired
    VCollAndPoemService vCollAndPoemService;
    @Autowired
    RecommendService recommendService;
    @GetMapping("/listcollections")
    @ResponseBody
    public Result listallcoll(String kw,  Model model,HttpSession session){
        if (kw!=null) kw="%"+kw+"%";
        if (kw==null) kw="%%";
        TUser usersession=(TUser) (session.getAttribute("usersession"));
        String email=usersession.getEmail();
        Result result = new Result();
        //通过用户邮箱和输入的关键字展示收藏
        List<VCollAndPoem> listcolls= vCollAndPoemService.showAll(email,kw);
        //放到data中
        result.setData(listcolls);
        return result;
    }
    @PostMapping("/listcollections")
    @ResponseBody
    public Result listallcollbykw(@RequestBody String kw,HttpSession session){
        if (kw==null||kw.equals("")) kw="%%";
        if (kw!=null) kw="%"+kw+"%";
        System.out.println("testetstjdkcsdjf");
        System.out.println("hhhhhhhh"+kw);
        TUser usersession=(TUser) (session.getAttribute("usersession"));
        String email=usersession.getEmail();
        Result result = new Result();
        //通过用户邮箱和输入的关键字展示收藏
        List<VCollAndPoem> listcolls= vCollAndPoemService.showAll(email,kw);
        //放到data中
        result.setData(listcolls);
        return result;
    }
    @PostMapping("/deletecoll")
    @ResponseBody
    public Result delete(@RequestBody String id,HttpSession session){
        TUser usersession=(TUser) (session.getAttribute("usersession"));
        String email=usersession.getEmail();
        Result result = new Result();
        Integer id1=Integer.valueOf(id);
        collectionService.deleteById(id1);
        result.setDescription("删除成功");//添加返回信息描述
        //添加返回数据
        String kw="%%";
        //通过用户邮箱和输入的关键字展示收藏
        List<VCollAndPoem> listcolls= vCollAndPoemService.showAll(email,kw);
        //放到data中
        result.setData(listcolls);
        return result;
    }
    //批量删除收藏表
    @PostMapping("/deletecolls")
    public String deletes(String ids){
        List<TCollection> colls=new ArrayList<>();
        JSONObject json=JSONObject.parseObject(ids);
        JSONArray arr=json.getJSONArray("ids");//前端传递时使用uods作为json数据的键
        int ilen=arr.size();
        for (int i=0;i<ilen;i++){//每次循环ilen次来执行ilen个查询，再去删除
            colls.add(collectionService.findById(arr.getInteger(i)));
        }
        collectionService.deletes(colls);
        return "redirect:/listcollections";
    }
    @PostMapping("/addcoll")
    @ResponseBody
    public Result addcoll(@RequestBody String id,HttpSession session) throws SQLException{
        TUser usersession=(TUser) (session.getAttribute("usersession"));
        Result result = new Result();
        Integer id1=Integer.valueOf(id);
        String email=usersession.getEmail();
        SimpleDateFormat formatter= new SimpleDateFormat("yyyy-MM-dd 'at' HH:mm:ss z");
        Date date = new Date(System.currentTimeMillis());
        TCollection coll=new TCollection();
        coll.setEmail(email);
        coll.setPoetryid(id1);
        coll.setTime(date);
        result.setDescription("收藏成功");//添加返回信息描述
        try {
            collectionService.addColletion(coll);
        }catch (Exception e){
            e.getStackTrace();
            result.setDescription("请勿重复收藏同一个诗文");
        }
        List<PRecommend> list= recommendService.getAll(0);
        //放到data中
        result.setData(list);
        return result;
    }
}
