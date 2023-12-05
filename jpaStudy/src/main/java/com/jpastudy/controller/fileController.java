package com.jpastudy.controller;


import com.jpastudy.dto.UploadFileDTO;
import io.swagger.annotations.ApiOperation;
import lombok.extern.log4j.Log4j2;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Log4j2
public class fileController {
    @ApiOperation(value = "Upload POST", notes = "POST 방식으로 등록")
    @PostMapping(value = "/upload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE)
    public String upload(UploadFileDTO uploadFileDTO) {
        log.info(uploadFileDTO);
        return null;
    }
}
