package util;

import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

/**
 * @author 0416
 * @date 2019/10/29
 **/
public class UploadImageUtil {

    public String upload(MultipartFile oldFile) throws IOException {
        // 获取文件名
        String fileName = oldFile.getOriginalFilename();
        // 获取图片后缀
        String extName = fileName.substring(fileName.lastIndexOf("."));

        // 自定义的文件名称
        String trueFileName = System.currentTimeMillis() + extName;
        // 设置存放图片文件的路径
        String path = "c:\\image\\" + trueFileName;
        // 开始上传
        oldFile.transferTo(new File(path));
        return path;
    }
}
