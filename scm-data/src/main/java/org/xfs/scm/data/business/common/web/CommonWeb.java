package org.xfs.scm.data.business.common.web;

import org.csource.common.NameValuePair;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.xfs.scm.common.base.model.JsonResponse;
import org.xfs.scm.platform.base.web.BaseWeb;
import org.xfs.scm.platform.config.shiro.realm.TokenManager;
import org.xfs.scm.platform.util.fastdfs.FastDFSFile;
import org.xfs.scm.platform.util.fastdfs.FileManager;
import org.xfs.scm.platform.util.string.StringUtil;

import java.io.IOException;

@Controller
@RequestMapping("/common/file/")
public class CommonWeb extends BaseWeb{
    private final static Logger logger = LoggerFactory.getLogger(CommonWeb.class);

    @ResponseBody
    @RequestMapping(value = "uploadFile",method = RequestMethod.POST)
    public JsonResponse<String> uploadFile(MultipartFile file){
        String msg="文件上传错误！";
       if(logger.isDebugEnabled()){
           logger.debug("upload file is:"+file);
       }
       if(file!=null ){
           String sourceFileName=file.getOriginalFilename();
           String ext=this.getFileExtName(sourceFileName);
           try {
               byte[] bytes = file.getBytes();
               FastDFSFile dfsFile =new FastDFSFile(bytes,ext);
               NameValuePair[] meta_list = new NameValuePair[4];
               meta_list[0] = new NameValuePair("fileName",sourceFileName);
               meta_list[1] = new NameValuePair("fileLength", bytes.length+"");
               meta_list[2] = new NameValuePair("fileExt", ext);
               meta_list[3] = new NameValuePair("fileAuthor", TokenManager.getUserName());
               String filePath = FileManager.upload(dfsFile,meta_list);
               System.out.println("result:"+filePath);
               if(StringUtil.isNotBlank(filePath)){
                    return JsonResponse.success("上传成功！",filePath);
               }
           } catch (IOException e) {
               msg=e.getMessage();
               e.printStackTrace();
           }

       }
       return JsonResponse.fail(msg);
    }

    @SuppressWarnings("unused")
	@RequestMapping(value = "uploadImg",method = RequestMethod.POST)
    public String uploadImg(MultipartFile file){
        String msg="文件上传错误！";
        String sourceFileName=file.getOriginalFilename();
        String extType=this.getFileExtName(sourceFileName);
        if(logger.isDebugEnabled()){
            logger.debug("upload file is:"+file);
        }
        if(file!=null ){
            try {
                byte[] bytes = file.getBytes();
                FastDFSFile dfsFile =new FastDFSFile(bytes,extType);
                NameValuePair[] meta_list = new NameValuePair[4];
                meta_list[0] = new NameValuePair("fileName", file.getName());
                meta_list[1] = new NameValuePair("fileLength", bytes.length+"");
                meta_list[2] = new NameValuePair("fileExt", extType);
                meta_list[3] = new NameValuePair("fileAuthor", "Jeken.Liu");
                String filePath = FileManager.upload(dfsFile,meta_list);
                System.out.println("result:"+filePath);

            } catch (IOException e) {
                msg=e.getMessage();
                e.printStackTrace();
            }

        }
        return "upload";
    }

    private String getFileExtName(String fileName) {
        if(fileName.lastIndexOf(".") > 0) {
            return fileName.substring(fileName.lastIndexOf(".") + 1);
        }
        return "";
    }
}
