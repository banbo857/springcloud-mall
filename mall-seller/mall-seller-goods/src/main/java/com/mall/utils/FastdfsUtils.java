package com.mall.utils;

import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.proto.storage.DownloadByteArray;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import lombok.extern.slf4j.Slf4j;
import lombok.val;
import org.apache.commons.lang.StringUtils;
import org.springframework.stereotype.Component;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;

@Component
@Slf4j
public class FastdfsUtils {
    @Resource
    private FastFileStorageClient storageClient;

    public String upload(MultipartFile multipartFile) throws Exception {
        String originalFilename = multipartFile.getOriginalFilename().substring(multipartFile.getOriginalFilename().lastIndexOf(".") + 1);
        StorePath storePath = this.storageClient.uploadImageAndCrtThumbImage(multipartFile.getInputStream(),
                multipartFile.getSize(), originalFilename, null);
        System.out.println(storePath);
        return storePath.getFullPath();
    }

    public void deleteFile(String fileUrl) {
        if (StringUtils.isEmpty(fileUrl)) {
            log.info("fileUrl == >>文件路径为空...");
            return;
        }
        try {
            //对根据传入的url删除文件
            StorePath storePath = StorePath.praseFromUrl(fileUrl);
            storageClient.deleteFile(storePath.getGroup(), storePath.getPath());
            //删除上传所产生的缩略图
            val split = fileUrl.split("\\.");
            split[0] += "_150x150.";
            String fileUrl2 = split[0] + split[1];
            StorePath storePath2 = StorePath.praseFromUrl(fileUrl2);
            storageClient.deleteFile(storePath2.getGroup(), storePath2.getPath());
        } catch (Exception e) {
            log.info(e.getMessage());
        }
    }

    public byte[] download(String fileUrl) {
        String group = fileUrl.substring(0, fileUrl.indexOf("/"));
        String path = fileUrl.substring(fileUrl.indexOf("/") + 1);
        return storageClient.downloadFile(group, path, new DownloadByteArray());
    }

}
