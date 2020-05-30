package com.springboot.controller;

import com.springboot.bean.Result;
import com.springboot.entity.TPoet;
import com.springboot.entity.TUser;

import com.springboot.security.SHA1Test;
import com.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;
import java.security.NoSuchAlgorithmException;
import java.text.ParseException;
import java.util.List;

@Controller
@CrossOrigin(origins = {"http://localhost:3000"},allowCredentials = "true",allowedHeaders = {"X-Custom-Header"},
        maxAge = 3600L, methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.HEAD})
public class AdminController {
    @Autowired
    private UserService userService;
//
//    @PostMapping("/testdj")
//    public List<TUser> testdj() {
//        return (List<TUser>) userService.findAll();//返回界面
//    }


    @GetMapping("/admin/listusers")
    @ResponseBody
    public Result adminlistalluser(String kw, Model model){
        if (kw!=null) kw="%"+kw+"%";
        if (kw==null) kw="%%";
        Result result = new Result();
        //通过关键字查询
        List<TUser> listusers= userService.adminshowAll(kw);
        //放到data中
        result.setData(listusers);
        System.out.println("listusers====="+listusers.toString());
        return result;
    }
    @PostMapping("/admin/listusers")
    @ResponseBody
    public Result adminlistalluserbykw(@RequestBody String kw){
        if (kw!=null) kw="%"+kw+"%";
        if (kw==null) kw="%%";
        System.out.println(kw);
        Result result = new Result();
        //通过关键字查询
        List<TUser> listusers= userService.adminshowAll(kw);
        //放到data中
        result.setData(listusers);
        return result;
    }

    @PostMapping("/admin/deleteuser")
    @ResponseBody
   public Result admindelete(@RequestBody String id){
        Result result = new Result();
        Integer id1=Integer.valueOf(id);
        userService.deleteById(id1);
        result.setDescription("删除成功");//添加返回信息描述
        //添加返回数据
        String kw="%%";
        //通过关键字查询
        List<TUser> listcomms= userService.adminshowAll(kw);
        //放到data中
        result.setData(listcomms);
        return result;
    }


//
//    @PostMapping("/admin/deleteuser")
//    @ResponseBody
//    public Result admindelete(@RequestBody String email){
//        Result result = new Result();
////        Integer id1=Integer.valueOf(id);
////        userService.deleteById(id1);
//        userService.deleteByEmail(email);
//        result.setDescription("删除成功");//添加返回信息描述
//        //添加返回数据
//        String kw="%%";
//        //通过关键字查询
//        List<TUser> listusers= userService.adminshowAll(kw);
//        //放到data中
//        result.setData(listusers);
//        return result;
//    }


    //添加、修改用户 id存在编辑用户信息，不存在则添加用户
    @PostMapping("/admin/saveuser")
    @ResponseBody
    public Result save(TUser user, RedirectAttributes attr) {
        Result result=new Result();
        try {
            //如果id为0 jpa的save方法起新增的作用;如果save不为0 那么jpa save方法起update作用
            if (user.getId() == 0) {
                //检查邮箱是否已注册
                if (userService.findEmail(user.getEmail()).size() != 0) {//如果该邮箱已注册
                    attr.addFlashAttribute("message", "该邮箱已注册");
                    result.setDescription("该邮箱已注册");
                    result.setCode(400);
                    result.setNextAction("/saveuser");
                    return result;
                }
                //密码加密
                SHA1Test sha1Test = new SHA1Test();
                user.setPassword(sha1Test.toHexString(user.getPassword()));
            }
            //密码加密
            SHA1Test sha1Test = new SHA1Test();
            user.setPassword(sha1Test.toHexString(user.getPassword()));
            userService.saveUser(user);
            result.setDescription("保存成功");
            result.setCode(200);
            result.setData(user);
//            result.setNextAction("/listusers");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }



    //编辑修改用户信息
    @PostMapping("/admin/modifyuser")
    @ResponseBody
    public Result ModifyUser(TUser user, RedirectAttributes attr) throws NoSuchAlgorithmException {
        Result result=new Result();
        System.out.println("前端传来的数据user====="+user.toString());//前端传来的数据
        //密码加密
        SHA1Test sha1Test = new SHA1Test();
        user.setPassword(sha1Test.toHexString(user.getPassword()));
        userService.save(user);
        result.setDescription("修改用户信息成功！");
        TUser newuser = userService.findById(user.getId());
        result.setData(newuser);
        return result;
    }


//
//    //个人信息管理界面中:更改信息后保存
//    @PostMapping("/admin/modifyuser")
//    @ResponseBody
//    public Result SavePersonalInfo(TUser user, HttpSession session) throws NoSuchAlgorithmException {
//        Result result = new Result();
//        TUser user2=userService.findByEmail(user.getEmail());
//        System.out.println("前端传来的数据user====="+user.toString());//前端修后传来重新写入的数据
//        System.out.println("前端修改前数据user2====="+user2.toString());//前端修改前数据
//        System.out.println("111");
////        if(userService.findByEmailNotId(user.getEmail(),user.getId()).size()!=0){
////            result.setDescription("该邮箱已绑定账号，修改失败！");
////            result.setCode(400);
////            System.out.println("222");
////            return result;
////        }
////        else {
////        if(userService.findByEmail(user.getEmail()))
////            SHA1Test sha1Test = new SHA1Test();
////            user.setPassword(sha1Test.toHexString(user.getPassword()));
//        user.setUsername(user2.getUsername());
//            System.out.println("444");
//            userService.modifyUser(user2);
//             System.out.println("555");
//            result.setDescription("修改个人信息成功！");
//            System.out.println("666");
//            TUser usersession = userService.findById(user2.getId());
////            session.setAttribute("usersession", usersession);
//            result.setData(usersession);
//            System.out.println("user2==="+user2.toString());
//            System.out.println("usersession==="+usersession.toString());
//            return result;
////        }
//    }
//



//    //管理员添加用户
//    @PostMapping("/admin/saveuser")
//    @ResponseBody
//    public Result addUser(TUser user) throws ParseException, NoSuchAlgorithmException {
//        Result result=new Result();
//        //密码加密
//        SHA1Test sha1Test = new SHA1Test();
//        user.setPassword(sha1Test.toHexString(user.getPassword()));
//        userService.save(user);
//        result.setDescription("添加成功");//添加返回信息描述
//        result.setData(user);
//        return  result;
//    }






    /*
    @RequestMapping("/listusers")
    public Result listuserbykw(String kw, ModelMap modelMap, @RequestParam(value = "page", defaultValue = "0") Integer page,
                               @RequestParam(value = "size", defaultValue = "5") Integer size) {
        Result result=new Result();
        modelMap.addAttribute("kw", kw);
        if (kw != null) kw = "%" + kw + "%";
        if (kw == null) kw = "%%";
        Page<TUser> datas = userService.findBookCriteria(page, size, kw);//默认分页从0页面（第一页），取每页20条数据
        modelMap.addAttribute("datas", datas);
        result.setDescription("查询成功");
        result.setCode(200);
        result.setData(datas);
        result.setNextAction("/listusers");
        return result;
//        return datas;
    }

    //添加、修改用户 id存在编辑用户信息，不存在则添加用户
    @PostMapping("/saveuser")
    @CrossOrigin
    public Result save(@RequestBody @Valid TUser user, RedirectAttributes attr) {
        Result result=new Result();
        try {
            //如果id为0 jpa的save方法起新增的作用;如果save不为0 那么jpa save方法起update作用
            if (user.getId() == 0) {
                //检查邮箱是否已注册
                if (userService.findEmail(user.getEmail()).size() != 0) {//如果该邮箱已注册
                    attr.addFlashAttribute("message", "该邮箱已注册");
                    result.setDescription("该邮箱已注册");
                    result.setCode(400);
                    result.setNextAction("/saveuser");
                    return result;
                }
                //密码加密
                SHA1Test sha1Test = new SHA1Test();
                user.setPassword(sha1Test.toHexString(user.getPassword()));
            }
            userService.saveUser(user);
            result.setDescription("保存成功");
            result.setCode(200);
            result.setData(user);
            result.setNextAction("/listusers");
            return result;
        } catch (Exception e) {
            e.printStackTrace();
        }
        return result;
    }

    //    @RequestMapping
    @GetMapping("/deleteuser/{id}")
    public Result delete(@PathVariable("id") Integer id) {
        Result result=new Result();
        //        userService.deleteById(id);
        if (userService.deleteById(id)) {
            result.setDescription("删除失败");
            result.setCode(400);
            result.setNextAction("/listusers");
            return result;
        }
        else {
            result.setDescription("删除成功");
            result.setCode(200);
            result.setData(id);
            result.setNextAction("/listusers");
            return result;
        }
    }

    @PostMapping("/deleteusers")
    public Result deletes(String ids) {
        Result result=new Result();
        System.out.println("======" + ids);
        List<TUser> users = new ArrayList<>();
        JSONObject json = JSONObject.parseObject(ids);
        JSONArray arr = json.getJSONArray("ids");//前端传递时使用uods作为json数据的键
        int ilen = arr.size();
        for (int i = 0; i < ilen; i++) {//每次循环ilen次来执行ilen个查询，再去删除
            users.add(userService.findById(arr.getInteger(i)));
        }
        if (userService.deletes(users)) {

            result.setDescription("删除失败");
            result.setCode(400);
            result.setNextAction("/listusers");
            return result;
        }
        else {
            result.setDescription("删除成功");
            result.setCode(200);
            result.setData(ids);
            result.setNextAction("/listusers");
            return result;
        }
    }

     */

//    @PostMapping("/delete")
//    public void delete(@PathVariable("id") Integer id,@RequestParam("ids") String ids) {
//        if (id!=null){
//            userService.deleteById(id);
//        }
//        else {
//            // 接收包含stuId的字符串，并将它分割成字符串数组
//            String[] idList = ids.split(",");
//            // 将字符串数组转为List<Intger> 类型
//            List<Integer> LString = new ArrayList<Integer>();
//            for (String idi : idList) {
//                LString.add(new Integer(idi));
//            }
//
//            int ilen = LString.size();
//            for (int i = 0; i < ilen; i++) {
//                userService.deleteById(LString.get(i));
//            }
//        }
//    }
}




