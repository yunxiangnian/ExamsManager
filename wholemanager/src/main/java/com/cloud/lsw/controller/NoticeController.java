package com.cloud.lsw.controller;

import java.util.*;

import com.cloud.lsw.common.utils.R;
import com.cloud.lsw.entity.NoticecontentEntity;
import com.cloud.lsw.service.impl.NoticeServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 *
 *
 * @author lsw
 * @email cloud_lsw@163.com
 * @date 2021-03-23 17:57:33
 */
@RestController
@RequestMapping("notice")
public class NoticeController {

    @Resource
    private NoticeServiceImpl noticeService;



    @RequestMapping("/insert")
    public R insertNoticeInfo(@RequestParam Map<String,Object> map, HttpServletRequest request){

        map.put("createUser",noticeService.getLoginUserName(request));
        map.put("createTime", new Date());
        int info = noticeService.insertNoticeInfo(map);
        if (info == 0){
            return R.error().put("msg", "插入失败!,请稍后再试");
        }

        return R.ok().put("msg", "插入成功！");
    }


    @RequestMapping("noticeList")
    public ModelAndView getNoticeList(@RequestParam(value = "pn",defaultValue = "1")Integer pn
            ,@RequestParam Map<String,Object> map, ModelAndView modelAndView){
        PageHelper.startPage(pn,5);
        List<NoticecontentEntity> list = noticeService.getNoticeList(map);
        PageInfo<NoticecontentEntity> pageInfos = new PageInfo<NoticecontentEntity>(list);
        if (list == null){
            modelAndView.addObject("msg", "查询失败，请稍后重试!");
            return modelAndView;
        }
        modelAndView.addObject("pageInfo", pageInfos);
        if ("1".equals(map.get("stu"))){
            modelAndView.setViewName("views/stuViews/noticeList");
        }
        else {
            modelAndView.setViewName("views/teaViews/noticeList");
        }
        return modelAndView;
    }



    @RequestMapping("/updateInfo")
    public R getUpdateInfo(@RequestParam("id") Integer id){
        NoticecontentEntity entityById = noticeService.getEntityById(id);
        return R.ok().put("entity", entityById);
    }

    @RequestMapping("/updateNotice")
    public R updateNotice(@RequestParam Map<String,Object> map,HttpServletRequest request){
        map.put("modifiedUser", noticeService.getLoginUserName(request));
        map.put("modifiedTime", new Date());
        Integer count = noticeService.updateNotice(map);
        if (count < 1 ){
            return R.error(400, "更新失败!");
        }
        return R.ok();
    }

    @RequestMapping("/deleteNotice")
    public R deleteNotice(@RequestParam("id") Integer id){
        Integer flag = noticeService.deleteNotice(id);
        if(flag == 0){
            return R.error(400, "删除失败，请稍后重试!");
        }
        return R.ok();
    }

    @RequestMapping("/qualifiedAudit")
    public ModelAndView qualifiedAudit(@RequestParam Map<String,Object> map,
                                       ModelAndView modelAndView,
                                       HttpServletRequest request,
                                       @RequestParam(value = "pn",defaultValue = "1")Integer pn){
        map.put("user", noticeService.getLoginUserName(request));
        PageHelper.startPage(pn,5);
        List<HashMap<String,Object>> list = noticeService.getNoticeListWithCreater(map);
        PageInfo<HashMap<String,Object>> pageInfos = new PageInfo<HashMap<String,Object>>(list);
        modelAndView.addObject("pageInfo", pageInfos);
        modelAndView.setViewName("views/teaViews/qualifiedAudit");
        return modelAndView;
    }

}
