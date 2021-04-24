package com.cloud.lsw.controller;

import com.alibaba.fastjson.JSONObject;
import com.baomidou.mybatisplus.extension.plugins.pagination.Page;
import com.cloud.lsw.common.utils.R;
import com.cloud.lsw.entity.FileEntity;
import com.cloud.lsw.entity.NoticecontentEntity;
import com.cloud.lsw.entity.ScoreinfoEntity;
import com.cloud.lsw.entity.UserEntity;
import com.cloud.lsw.service.FileUploadService;
import com.cloud.lsw.service.NoticecontentService;
import com.cloud.lsw.service.ScoreinfoService;
import com.cloud.lsw.service.UserService;
import com.cloud.lsw.service.impl.NoticeServiceImpl;
import com.cloud.lsw.service.impl.ScoreinfoServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.apache.http.HttpRequest;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpSession;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.WeakHashMap;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;
import java.util.concurrent.locks.Condition;

/**
 * @author lisw
 * @create 2021/3/25 13:28
 */
@Controller
@RequestMapping("html")
public class HtmlController {

    @Resource
    private UserService userService;

    @Resource
    private NoticecontentService service;

    @Resource
    private ScoreinfoServiceImpl scoreinfoService;

    @Resource
    private FileUploadService fileUploadService;

    @RequestMapping("/register")
    public String loginToRegister(){
        return "register";
    }

    @RequestMapping("/index")
    public String ToIndex(){
        return "index";
    }

    @RequestMapping("/Main")
    public String ToMain(){
        return "views/Main";
    }

    /**用户登录跳转到主页面的验证*/
    @RequestMapping("/loginValid")
    @ResponseBody
    public JSONObject loginHtmlForward(@RequestParam Map<String,Object> map, HttpServletRequest request){
        JSONObject jsonObject = new JSONObject();
        String phoneNumber = (String) map.get("phoneNumber");
        String password = (String) map.get("password");
        UserEntity userEntity = userService.getInfoByPhoneNumber(phoneNumber);
        /**如果用户获取不到，表示不存在*/
        if (userEntity == null){
            jsonObject.put("code", "403");
            return jsonObject;
        }
        HttpSession session = request.getSession();
        request.getSession().setAttribute("username", userEntity.getUsername());
        /**1 是老师 0 是学生*/
        request.getSession().setAttribute("role", userEntity.getIsTea());
        request.getSession().setAttribute("role", userEntity.getIsTea());
        if (password.equals(userEntity.getPassword())) {
            jsonObject.put("code", 200);
        }else{
            jsonObject.put("code", "403");
        }
        return jsonObject;
    }

    /**验证注册信息的合法性*/
    @RequestMapping("/register/valid")
    @ResponseBody
    public JSONObject registerValid(@RequestParam  Map<String,Object> map, ModelAndView model){
        JSONObject jsonObject = new JSONObject(map);
        UserEntity user = jsonObject.toJavaObject(UserEntity.class);
        String phoneNumber =  user.getPhoneNumber();
        Integer integer = userService.registerValid(phoneNumber);
        /**如果存在该用户，返回值为1，否则为0*/
        if (integer > 0){
            jsonObject.put("existed",1);
        }else {
            jsonObject.put("existed", 0);
        }
        userService.insertEntity(user);
        return jsonObject;
    }

    /**用户登出*/
    @RequestMapping("/logout")
    public ModelAndView userLogout(ModelAndView modelAndView,HttpServletRequest request){
        HttpSession session = request.getSession();
        session.removeAttribute("role");
        session.removeAttribute("username");
        modelAndView.setViewName("/index");
        return modelAndView;
    }


    /**跳转到发布竞赛页面*/
    @RequestMapping("publishGame")
    public String publishGame(){
        return "views/teaViews/publishGame";
    }


    /**跳转到竞赛列表页面*/
    @RequestMapping("noticeList")
    public String noticeList(@RequestParam Map<String,Object> map, HttpServletRequest request){
        PageHelper.startPage(1,5);
        List<NoticecontentEntity> list = service.getNoticeList(map);
        PageInfo<NoticecontentEntity> pageInfos = new PageInfo<NoticecontentEntity>(list);
        request.setAttribute("pageInfo", pageInfos);
        return "views/teaViews/noticeList";
    }

    /**跳转到录入成绩页面*/
    @RequestMapping("enterGrades")
    public String enterGrades(HttpServletRequest request){
        /**获取该用户的所有创建的通告*/
        List<String> noticeNames = scoreinfoService.getNoticeNameByUser(scoreinfoService.getLoginUserName(request));
        request.setAttribute("notices", noticeNames);
        return "views/teaViews/enterGrades";
    }

    /**跳转到查看成绩的页面*/
    @RequestMapping("viewGrades")
    public String viewGrades(HttpServletRequest request){
        HashMap<String, Object> map = new HashMap<>(16);
        map.put("user",scoreinfoService.getLoginUserName(request));
        PageHelper.startPage(1,5);
        List<ScoreinfoEntity> scoreLists = scoreinfoService.getScoreInfoWithUser(map);
        PageInfo<ScoreinfoEntity> pageInfos = new PageInfo<ScoreinfoEntity>(scoreLists);
        /**获取该用户的可以管理的所有的成绩信息*/
        request.setAttribute("pageInfo", pageInfos);
        return "views/teaViews/scoreList";
    }

    /**跳转到学生界面的竞赛列表*/
    @RequestMapping("stu/noticeList")
    public String toStuNoticeList(@RequestParam Map<String,Object> map, HttpServletRequest request){
        PageHelper.startPage(1,5);
        List<NoticecontentEntity> list = service.getNoticeList(map);
        PageInfo<NoticecontentEntity> pageInfos = new PageInfo<NoticecontentEntity>(list);
        request.setAttribute("pageInfo", pageInfos);
        return "views/stuViews/noticeList";
    }

    /**跳转到学生界面的查看申请状态*/
    @RequestMapping("/stu/applyResult")
    public String toStuApplyResult(HttpServletRequest request){
        PageHelper.startPage(1,5);
        List<FileEntity> list = fileUploadService.getApplyResult(getLoginUser(request));
        PageInfo<FileEntity> pageInfos = new PageInfo<FileEntity>(list);
        request.setAttribute("pageInfo", pageInfos);
        return "views/stuViews/applyResult";
    }

    /**跳转到学生查看竞赛成绩的页面*/
    @RequestMapping("/stu/viewScore")
    public String toViewScore(HttpServletRequest request){
        PageHelper.startPage(1,5);
        List<ScoreinfoEntity> list = scoreinfoService.getStuScoreInfo(getLoginUser(request));
        PageInfo<ScoreinfoEntity> pageInfos = new PageInfo<ScoreinfoEntity>(list);
        request.setAttribute("pageInfo", pageInfos);
        return "views/stuViews/viewScore";
    }

    /**跳转到教师审核成绩页面*/
    @RequestMapping("/tea/qualifiedAudit")
    public String toQualifiedAudit(HttpServletRequest request){
        HashMap<String, Object> map = new HashMap<>(16);
        map.put("user", getLoginUser(request));
        PageHelper.startPage(1,5);
        List<HashMap<String,Object>> list = service.getNoticeListWithCreater(map);
        PageInfo<HashMap<String,Object>> pageInfos = new PageInfo<HashMap<String,Object>>(list);
        request.setAttribute("pageInfo", pageInfos);
        return "views/teaViews/qualifiedAudit";
    }



    private String getLoginUser(HttpServletRequest request){
        return (String) request.getSession().getAttribute("username");
    }

}
