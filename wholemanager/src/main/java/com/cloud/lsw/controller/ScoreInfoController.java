package com.cloud.lsw.controller;

import java.util.Arrays;
import java.util.Date;
import java.util.List;
import java.util.Map;

import com.cloud.lsw.common.utils.PageUtils;
import com.cloud.lsw.common.utils.R;
import com.cloud.lsw.entity.NoticecontentEntity;
import com.cloud.lsw.service.impl.ScoreinfoServiceImpl;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.cloud.lsw.entity.ScoreinfoEntity;
import com.cloud.lsw.service.ScoreinfoService;
import org.springframework.web.servlet.ModelAndView;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;


/**
 *
 *
 * @author lsw
 * @email cloud_lsw@163.com
 * @date 2021-03-23 17:57:32
 */
@RestController
@RequestMapping("scoreInfo")
public class ScoreInfoController {
    @Resource
    private ScoreinfoServiceImpl scoreinfoService;


    @RequestMapping("/insert")
    public R insertScoreInfo(@RequestParam Map<String,Object> map, HttpServletRequest request){
        map.put("updateUser", scoreinfoService.getLoginUserName(request));
        map.put("updateTime", new Date());
        try {
            Integer count = scoreinfoService.insertScoreInfo(map);
        } catch (Exception e) {
            return R.error(400, "插入异常，请检查学生是否存在！");
        }

        return R.ok().put("info", "插入成功！");
    }

    @RequestMapping("scoreList")
    public ModelAndView getNoticeList(@RequestParam(value = "pn",defaultValue = "1")Integer pn
            , @RequestParam Map<String,Object> map, ModelAndView modelAndView,
                                      HttpServletRequest request){
        map.put("user", scoreinfoService.getLoginUserName(request));
        List<ScoreinfoEntity> list = scoreinfoService.getScoreInfoWithUser(map);
        PageHelper.startPage(pn,5);
        PageInfo<ScoreinfoEntity> pageInfos = new PageInfo<ScoreinfoEntity>(list);
        if (list == null){
            modelAndView.addObject("msg", "查询失败，请稍后重试!");
            return modelAndView;
        }
        modelAndView.addObject("pageInfo", pageInfos);
        modelAndView.setViewName("views/teaViews/scoreList");
        return modelAndView;
    }

    @RequestMapping("/stu/scoreList")
    public ModelAndView getStuNoticeList(@RequestParam(value = "pn",defaultValue = "1")Integer pn
            , @RequestParam Map<String,Object> map, ModelAndView modelAndView,
                                      HttpServletRequest request){
        PageHelper.startPage(pn,5);
        List<ScoreinfoEntity> list = scoreinfoService.getStuScoreInfo(scoreinfoService.getLoginUserName(request));
        PageInfo<ScoreinfoEntity> pageInfos = new PageInfo<ScoreinfoEntity>(list);
        if (list == null){
            modelAndView.addObject("msg", "查询失败，请稍后重试!");
            return modelAndView;
        }
        modelAndView.addObject("pageInfo", pageInfos);
        modelAndView.setViewName("views/stuViews/viewScore");
        return modelAndView;
    }

    /**获取显示模态框的基本信息*/
    @RequestMapping("updateInfo")
    public R getInfoById(@RequestParam(value = "id") Integer id){
        ScoreinfoEntity scoreinfoEntity = scoreinfoService.getInfoById(id);
        return R.ok().put("scoreInfo", scoreinfoEntity);
    }


    @RequestMapping("/updateScoreInfo")
    public R updateScoreInfo(@RequestParam Map<String,Object> map){
        map.put("modifiedTime", new Date());
        Integer count = scoreinfoService.updateScoreInfo(map);
        if (count < 1 ){
            return R.error(400, "更新失败!");
        }
        return R.ok();
    }

    @RequestMapping("/deleteScore")
    public R deleteScoreInfo(@RequestParam(value = "id") Integer id){
        Integer count = scoreinfoService.deleteScoreInfo(id);
        if (count < 1 ){
            return R.error(400, "更新失败!");
        }
        return R.ok();
    }
}
