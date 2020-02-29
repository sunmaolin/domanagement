package com.qlu.edu.domanagement.controller;

import com.qlu.edu.domanagement.controller.ex.FileIoException;
import com.qlu.edu.domanagement.controller.ex.FileTypeException;
import com.qlu.edu.domanagement.service.ex.ServiceException;
import com.qlu.edu.domanagement.util.JsonResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

/**
 * 控制器的基类
 */
public abstract class BaseController {
    /**
     * 响应状态：成功
     */
    protected static final int OK=2000;

    /**
     * 允许上传的文件的类型
     */
    public static final List<String> IMAGE_TYPES = new ArrayList<>();

    static {
        IMAGE_TYPES.add("image/jpeg");
        IMAGE_TYPES.add("image/png");
        // IMAGE_TYPES.add("application/x-zip-compressed");
        // IMAGE_TYPES.add("image/gif");
        // IMAGE_TYPES.add("image/bmp");
    }

    /**
     * 处理异常相关信息
     * @param e 程序运行时的异常
     * @return  主异常信息
     */
    @ExceptionHandler({ServiceException.class,Exception.class})
    public JsonResult<Void> handleException(Throwable e){
        JsonResult<Void> jr=new JsonResult<>(e);
        return jr;
    }

    public String photoUpload(MultipartFile photo){
        if (!IMAGE_TYPES.contains(photo.getContentType())){
            throw new FileTypeException("文件格式异常,支持jpg/jpeg/png");
        }
        //获取原始图片名
        String filename=photo.getOriginalFilename();
        //文件名
        String suffix = "";
        int beginIndex = filename.lastIndexOf(".");
        if (beginIndex > 0) {
            suffix = filename.substring(beginIndex);
        }
        //生成唯一的图片名
        filename = UUID.randomUUID().toString() + suffix;
        //获取容器的文件夹,getRealPath是不可移植的，将照片保存到tomcat服务器中
//        String parentPath = session.getServletContext().getRealPath("/images/upload");
//        File parent = new File(parentPath);
        //将文件保存在当前项目中
        File parent = new File("src/main/resources/static/images/upload");

        //判断该文件夹是否存在
        if (!parent.exists()) {
            parent.mkdirs();
        }

        // 用于保存上传的文件的对象
        File dest = new File(parent.getAbsolutePath()+"/"+filename);
        // 保存客户端上传的文件
        try {
            //文件转存
            photo.transferTo(dest);
        } catch (IOException e) {
            e.printStackTrace();
            throw new FileIoException("上传时出现读写错误，请重新上传");
        }
        return filename;
    }
}
