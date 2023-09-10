package com.huike.controller.common;

import com.huike.common.config.HuiKeConfig;
import com.huike.controller.core.BaseController;
import com.huike.domain.common.AjaxResult;
import com.huike.utils.StringUtils;
import com.huike.utils.file.FileUtils;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import lombok.extern.slf4j.Slf4j;
import storage.springboot.starter.template.MinioTemplate;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import storage.springboot.starter.template.StorageTemplate;

import javax.annotation.Resource;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.UUID;

/**
 * 通用请求处理
 */
@Slf4j
@Api(tags = "通用服务")
@RestController
public class CommonController extends BaseController {

    @Resource
    private StorageTemplate minioService;

    @PostMapping("common//upload")
    public AjaxResult uploadFile(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            assert fileName != null;

            // 根据业务设计，设置存储路径：按天创建目录
            String objectName = new SimpleDateFormat("yyyy-MM-dd/").format(new Date())
                    + UUID.randomUUID().toString()
                    + fileName.substring(fileName.lastIndexOf("."));

            minioService.upload(file);
            log.info("文件格式为:{}", file.getContentType());
            log.info("文件原名称为:{}", fileName);
            log.info("文件对象路径为:{}", objectName);
            AjaxResult ajaxResult = new AjaxResult();
            ajaxResult.put("code", 200);
            ajaxResult.put("msg", "上传成功");
            ajaxResult.put("url", objectName);
            ajaxResult.put("fileName", fileName);

            return ajaxResult;
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("上传失败");
        }
    }

/*    @Resource
    private MinioService minioService;

    @PostMapping("common//upload")
    public AjaxResult uploadFile(MultipartFile file) {
        try {
            String fileName = file.getOriginalFilename();
            assert fileName != null;

            // 根据业务设计，设置存储路径：按天创建目录
            String objectName = new SimpleDateFormat("yyyy-MM-dd/").format(new Date())
                    + UUID.randomUUID().toString()
                    + fileName.substring(fileName.lastIndexOf("."));

            minioService.upload(file);
            log.info("文件格式为:{}", file.getContentType());
            log.info("文件原名称为:{}", fileName);
            log.info("文件对象路径为:{}", objectName);
            AjaxResult ajaxResult = new AjaxResult();
            ajaxResult.put("code", 200);
            ajaxResult.put("msg", "上传成功");
            ajaxResult.put("url", objectName);
            ajaxResult.put("fileName", fileName);

            return ajaxResult;
        } catch (Exception e) {
            e.printStackTrace();
            return AjaxResult.error("上传失败");
        }
    }*/
    /**
     * 通用下载请求
     *
     * @param fileName 文件名称
     * @param delete   是否删除
     */
    @ApiOperation("文件-下载")
    @GetMapping("/common/download")
    public void fileDownload(String fileName, Boolean delete, HttpServletResponse response, HttpServletRequest request) {
        try {
            if (!FileUtils.checkAllowDownload(fileName)) {
                throw new Exception(StringUtils.format("文件名称({})非法，不允许下载。 ", fileName));
            }
            String realFileName = System.currentTimeMillis() + fileName.substring(fileName.indexOf("_") + 1);
            String filePath = HuiKeConfig.getDownloadPath() + fileName;
            log.info("下载路径 {}", filePath);

            response.setContentType(MediaType.APPLICATION_OCTET_STREAM_VALUE);
            FileUtils.setAttachmentResponseHeader(response, realFileName);
            FileUtils.writeBytes(filePath, response.getOutputStream());
            if (delete) {
                FileUtils.deleteFile(filePath);
            }
        } catch (Exception e) {
            log.error("下载文件失败", e);
        }
    }
}
