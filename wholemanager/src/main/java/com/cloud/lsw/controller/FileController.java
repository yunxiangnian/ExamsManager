package com.cloud.lsw.controller;

import cn.hutool.core.io.file.FileNameUtil;
import cn.hutool.core.lang.UUID;
import cn.hutool.core.util.IdUtil;
import com.cloud.lsw.common.utils.R;
import com.cloud.lsw.dao.FileUploadDao;
import com.cloud.lsw.entity.FileEntity;
import com.cloud.lsw.service.FileUploadService;
import com.github.pagehelper.PageHelper;
import com.github.pagehelper.PageInfo;
import org.springframework.util.ResourceUtils;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;
import org.yaml.snakeyaml.events.Event;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
import java.util.Map;

/**
 * @author lisw
 * @create 2021/4/16 15:27
 */
@RestController()
@RequestMapping("file")
public class FileController {

    @Resource
    private FileUploadService fileUploadService;

    @PostMapping("/upload")
    public R upload(@RequestParam("stuFile") MultipartFile stuFile,@RequestParam("noticeName") String noticeName,
                    HttpSession session) throws IOException {
        String userName = (String) session.getAttribute("username");
        Integer count = fileUploadService.booleanMultiplyApply(noticeName,userName);
        if (count > 0){
            return R.error(500, "已经申请过该竞赛，不能重复申请");
        }
        /**返回不带-的UUID*/
        String UUID = IdUtil.simpleUUID();
        //获取文件的原始名称
        String oldFileName=stuFile.getOriginalFilename();
        //获取文件后缀
        String ext = "."+ FileNameUtil.extName(stuFile.getOriginalFilename());
        //生成新的文件名
        String newFileName=new SimpleDateFormat("yyyyMMddHHmmss").format(new Date())+ IdUtil.simpleUUID() + ext;
        //文件的大小
        Long size = stuFile.getSize();
        //文件类型
        String type = stuFile.getContentType();
        //根据日期生成目录
        String realPath = ResourceUtils.getURL("classpath:").getPath() + "static/stu/files";
        String dataFormat = new SimpleDateFormat("yyyy-MM-dd").format(new Date());
        String dateDirPath= realPath+"/"+ dataFormat;
        File dateDir=new File(dateDirPath);
        if (!dateDir.exists())
            dateDir.mkdirs();
        /**处理文件的上传*/
        stuFile.transferTo(new File(dateDir,newFileName));

        /**插入到数据库*/
        FileEntity fileEntity = new FileEntity();
        fileEntity.setFileName(newFileName);
        fileEntity.setFileType(ext);
        fileEntity.setFileSize(String.valueOf(size));
        fileEntity.setFileUrl(dateDirPath);
        fileEntity.setNoticeName(noticeName);
        fileEntity.setState("1");
        fileEntity.setUserName(userName);
        fileEntity.setOldName(oldFileName);
        fileEntity.setId(UUID);
        Integer integer = fileUploadService.insertIntoFileInfo(fileEntity);
        if (integer < 0) {
            return R.error(400, "文件保存到数据库失败，请重新尝试");
        }
        return R.ok();
    }

    @RequestMapping("/applyResult")
    public ModelAndView getStuApplyResult(@RequestParam(value = "pn",defaultValue = "1")Integer pn,
                                    HttpServletRequest request, ModelAndView modelAndView){
        PageHelper.startPage(1,5);
        String user = (String) request.getSession().getAttribute("username");
        List<FileEntity> list = fileUploadService.getApplyResult(user);
        PageInfo<FileEntity> pageInfos = new PageInfo<FileEntity>(list);
        modelAndView.addObject("pageInfo", pageInfos);
        modelAndView.setViewName("views/stuViews/applyResult");
        return modelAndView;
    }

    /**
     * 实现pdf的预览
     * @param UUID
     * @param response
     * @throws IOException
     */
    @RequestMapping("/previewPdf")
    public void toPreviewPdf(String UUID , HttpServletResponse response) throws IOException {
        InputStream in = null;
        OutputStream out = response.getOutputStream();
        try {
            File file = new File(UUID);
            if (file.exists()){
                response.reset();
                response.setContentType("application/x-download");
                response.setHeader("Content-Disposition", "attachment;filename=''");

                in = new FileInputStream(file);
                response.setContentLength(in.available());
                in = new BufferedInputStream(in);

                try {
                    int _bufSize = 1024;
                    byte[] buffer = new byte[_bufSize];
                    int len;
                    while ((len = in.read(buffer)) != -1) {
                        out.write(buffer, 0, len);
                    }
                } finally {
                    if(in!=null){
                        in.close();
                    }
                    if(out!=null){
                        out.flush();
                        out.close();
                    }
                }
            }
        }catch (Exception e){
            throw new RuntimeException("输出流出错！",e);
        }finally {
            try {
                if (in != null) {
                    in.close();
                    in = null;
                }
                if (out != null) {
                    out.close();
                    out = null;
                }
            } catch (Exception e1) {
                System.out.println("流关闭错误");
                throw new RuntimeException("流关闭错误",e1);
            } finally {
                try {
                    if (in != null) {
                        in.close();
                        in = null;
                    }
                    if (out != null) {
                        out.close();
                        out = null;
                    }
                } catch (Exception e1) {
                    System.out.println("流关闭错误");
                    throw new RuntimeException("流关闭错误",e1);
                }
            }
        }
    }

    /**
     * 插入老师的审批意见
     * @param map
     * @return
     */
    @PostMapping("/enterSomething")
    public R toEnterSomething(@RequestParam Map<String,Object> map){
        /**插入remark（审批意见）更新状态为2（通过）或者1（不通过）*/
        Integer count = fileUploadService.updateRemarkAndStateByUUID(map);
        if (count < 0){
            return R.error(400, "审批失败，请稍后尝试");
        }
        return R.ok();
    }
}
