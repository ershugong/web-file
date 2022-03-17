package cn.mx.webfile.controller;

import cn.mx.webfile.config.JsonResult;
import cn.mx.webfile.mapper.TFileMapper;
import cn.mx.webfile.model.TFile;
import cn.mx.webfile.service.TFileService;
import org.apache.ibatis.annotations.Param;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import javax.annotation.Resource;
import java.io.IOException;
import java.util.List;

@RestController
@RequestMapping("/file")
public class FileController {

    @Resource
    private TFileService tFileService;

    @PostMapping("/uploadFile")
    public JsonResult<String> uploadFile(@Param("file") MultipartFile file) throws IOException {
        //上传文件到fastdfs文件服务器
        return JsonResult.success(tFileService.uploadFile(file));
    }


    @GetMapping("/getList")
    public JsonResult<List<TFile>> getList(){
        return JsonResult.success(tFileService.list());
    }

    @GetMapping("/{id}")
    public JsonResult<String> getList(@PathVariable String id){
        return JsonResult.success(tFileService.getById(id));
    }
}
