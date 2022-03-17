package cn.mx.webfile.service;

import cn.mx.webfile.mapper.TFileMapper;
import cn.mx.webfile.model.TFile;
import com.baomidou.mybatisplus.extension.service.IService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.github.tobato.fastdfs.domain.StorePath;
import com.github.tobato.fastdfs.service.FastFileStorageClient;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.ByteArrayInputStream;
import java.io.IOException;
import java.util.Date;

@Service
public class TFileService extends ServiceImpl<TFileMapper,TFile> {

    @Resource
    private TFileMapper tFileMapper;
    @Resource
    private FastFileStorageClient fastFileStorageClient;
    @Value("${file-http}")
    private String fileHttp;


    public String uploadFile(MultipartFile file) throws IOException {
        byte[] bytes = file.getBytes();
        String originalFileName = file.getOriginalFilename();
        String extension = originalFileName.substring(originalFileName.lastIndexOf(".") + 1);
        String fileName = file.getName();
        long fileSize = file.getSize();
        System.out.println(originalFileName + "==========" + fileName + "===========" + fileSize + "===============" + extension + "====" + bytes.length);

        String result = uploadFileByFDSFS(bytes, fileSize, extension);
        //保存记录到数据库
        TFile tFile = new TFile();
        tFile.setFileName(fileName);
        tFile.setCreateTime(new Date());
        tFile.setUrl(result);
        this.save(tFile);
        return result;
    }

    /**
     * 文件上传
     *
     * @param bytes     文件字节
     * @param fileSize  文件大小
     * @param extension 文件扩展名
     * @return fastDfs路径
     */
    private String uploadFileByFDSFS(byte[] bytes, long fileSize, String extension) {
        ByteArrayInputStream byteArrayInputStream = new ByteArrayInputStream(bytes);
        StorePath storePath = fastFileStorageClient.uploadFile(byteArrayInputStream, fileSize, extension, null);
        System.out.println(storePath.getGroup() + "===" + storePath.getPath() + "======" + storePath.getFullPath());
        return fileHttp + storePath.getFullPath();
    }
}
