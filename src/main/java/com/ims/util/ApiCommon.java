package com.ims.util;

import com.ims.dao.IAttachmentDAO;
import com.ims.entity.Attachment;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

public class ApiCommon {

    public Map<String, Object> uploadFile(MultipartFile file,Map<String, Object> map,int attachtype,IAttachmentDAO attachmentDAO){

        //文件上传
        String path = "F:/ideaworkspace/hfqxm/IMS/src/main/webapp/assets/upload/";
        if(file!=null && !file.isEmpty()){
            Map<String, Object> f = new HashMap<String, Object>();
            System.out.println(file.getOriginalFilename());
            System.out.println(file.getName());
            System.out.println(file.getSize());
            f.put("filename",file.getOriginalFilename());
            f.put("filesize",file.getSize());
            f.put("id",1);
            f.put("system_path",path+file.getOriginalFilename());
            f.put("web_path","assets/upload/"+file.getOriginalFilename());
            //获取原始文件名
            String fileName = file.getOriginalFilename();
            //获取文件类型
            String fileType = fileName.substring(fileName.lastIndexOf("."), fileName.length());
            //获取当前时间戳作为文件名，防止上传的文件名出现重名而被覆盖的现象。
            fileName = new Date().getTime()+fileType;
            Attachment attachment = new Attachment();
            attachment.setFilename(fileName);
            attachment.setFilesize(file.getSize());
            attachment.setSystem_path(path+fileName);
            attachment.setWeb_path("assets/upload/"+fileName);
            attachment.setAttachtype(attachtype);
            attachmentDAO.insert(attachment);
            saveFile(file,path,fileName);

            Map<String, Object> fils = new HashMap<String, Object>();
            fils.put(String.valueOf(attachment.getId()),attachment);
            Map<String, Object> fi = new HashMap<String, Object>();
            fi.put("files",fils);
            map.put("files",fi);
            Map<String, Object> u = new HashMap<String, Object>();
            u.put("id",attachment.getId());
            map.put("upload",u);

        }
        return map;
    }

    public boolean saveFile(MultipartFile file, String path, String fileName) {
        // 判断文件是否为空
        if (!file.isEmpty()) {
            try {
                File filepath = new File(path);
                if (!filepath.exists())
                    filepath.mkdirs();
                // 文件保存路径
                String savePath = path + fileName;
                // 转存文件
                file.transferTo(new File(savePath));
                return true;
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        return false;
    }
}
