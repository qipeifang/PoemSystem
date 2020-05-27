package com.springboot.controller;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;
import com.springboot.bean.Result;
import com.springboot.entity.TUser;

import com.springboot.security.SHA1Test;
import com.springboot.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.ui.ModelMap;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import javax.validation.Valid;
import java.util.ArrayList;
import java.util.List;

//@Controller
@RestController
//@CrossOrigin
@CrossOrigin(origins = {"http://localhost:3000"},allowCredentials = "true",allowedHeaders = {"X-Custom-Header"},
        maxAge = 3600L, methods={RequestMethod.GET,RequestMethod.POST,RequestMethod.HEAD})
public class AdminController {
    @Autowired
    private UserService userService;
//    /**
//     *根据条件查询用户信息
//     * @param kw 查询关键字即条件
//     * @param model 模型对象，也是视图（界面）的上下文环境对象
//     * @param pageable 分页信息对象，包含了分页需要的基本信息，如当前页码，每页需要的数据
//     * @return 字符串，代表了界面文件
//     */
//    @GetMapping("/listusers")
//    public String listall(String kw, Model model, Pageable pageable){
//        model.addAttribute("kw",kw);
//        if (kw!=null) kw="%"+kw+"%";
//        if (kw==null) kw="%%";
//        Page<TUser> ppu=userService.findAll(kw, pageable);//默认分页从0页面（第一页），取每页20条数据
//        model.addAttribute("pages",ppu);
//        return "listusers";//返回界面
//    }
//    @PostMapping("/listusers")
//    public String listuserbykw(String kw, Model model, Pageable pageable){
//        model.addAttribute("kw",kw);
//        if (kw!=null) kw="%"+kw+"%";
//        if (kw==null) kw="%%";
//        Page<TUser> ppu=userService.findAll(kw, pageable);//默认分页从0页面（第一页），取每页20条数据
//        model.addAttribute("pages",ppu);
//        return "listusers";//返回界面
//    }

    @PostMapping("/testdj")
    public List<TUser> testdj() {
        return (List<TUser>) userService.findAll();//返回界面
    }

    /**
     * edituser 添加用户
     * edituser/{id} 修改用户id的信息
     * name="id",required = false -->id不是必须的
     * //     * @param id
     * //     * @param model
     *
     * @return
     */
//    @GetMapping({"/edituser","/edituser/{id}"})
//    public String edit(@PathVariable(name="id",required = false)Integer id, Model model,String email) {
//        TUser u = new TUser();
//        if (id != null && id > 0) {//即路径为edituser/{id}  编辑用户
//            u = userService.findById(id);
//        }
//        model.addAttribute("sexes",TUser.Sex.toList());
//        model.addAttribute("user", u);
//        return "edituser";
//    }
//    @PostMapping("/saveuser")
//    public String save(@Valid TUser user, BindingResult result, RedirectAttributes attr){
//        try {
//            if(result.hasErrors()){
//                System.out.println(result.getFieldError().toString());
//                return "redirect:/edituser";
//            }
//            //如果id为0 jpa的save方法起新增的作用;如果save不为0 那么jpa save方法起update作用
//            if (user.getId()==0){
//                //检查邮箱是否已注册
//                if(userService.findEmail(user.getEmail()).size()!=0){
//                    attr.addFlashAttribute("message","该邮箱已注册");
//                    return "redirect:/edituser";
//                }
//                //密码加密
//                SHA1Test sha1Test = new SHA1Test();
//                user.setPassword(sha1Test.toHexString(user.getPassword()));
//            }
//            userService.save(user);
//            attr.addFlashAttribute("ok","保存成功");
//            return "redirect:/listusers";
//        }catch (Exception ex){
//            return "redirect:/edituser";
//        }
//
//    }

//    @GetMapping("/deleteuser/{id}")
//    public String delete(@PathVariable("id")Integer id){
//        userService.deleteById(id);
//        return "redirect:/listusers";
//    }
//
//    @PostMapping("/deleteusers")
//    public String deletes(String ids){
//        System.out.println("======"+ids);
//        List<TUser> users=new ArrayList<>();
//        JSONObject json=JSONObject.parseObject(ids);
//        JSONArray arr=json.getJSONArray("ids");//前端传递时使用uods作为json数据的键
//        int ilen=arr.size();
//        for (int i=0;i<ilen;i++){//每次循环ilen次来执行ilen个查询，再去删除
//            users.add(userService.findById(arr.getInteger(i)));
//        }
//        userService.deletes(users);
//        return "redirect:/listusers";
//
//    }


//
//    @RequestMapping("/listusers")
//    public Page<TUser> listall(String kw, ModelMap modelMap, @RequestParam(value = "page", defaultValue = "0") Integer page,
//                               @RequestParam(value = "size", defaultValue = "5") Integer size){
//        Page<TUser> datas = userService.findBookNoCriteria(page, size);
//        modelMap.addAttribute("datas", datas);
//        return datas;
//        //return "listusers";
//    }
    @RequestMapping("/listusers")
//    @PostMapping("/listusers")
//    @RequestMapping(value = "/listusers",method = {RequestMethod.GET,RequestMethod.POST})
    public Result listuserbykw(String kw, ModelMap modelMap, @RequestParam(value = "page", defaultValue = "0") Integer page,
                               @RequestParam(value = "size", defaultValue = "5") Integer size) {
        Result result=new Result();
        modelMap.addAttribute("kw", kw);
        if (kw != null) kw = "%" + kw + "%";
        if (kw == null) kw = "%%";
        Page<TUser> datas = userService.findBookCriteria(page, size, kw);//默认分页从0页面（第一页），取每页20条数据
        modelMap.addAttribute("datas", datas);
//        return "listusers";//返回界面
//        if (bindingResult.hasErrors()){
//            result.setDescription("查询失败");
//            result.setCode(400);
//            result.setNextAction("/listusers");
//            return result;
//        }
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
//        return "redirect:/listusers";
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




