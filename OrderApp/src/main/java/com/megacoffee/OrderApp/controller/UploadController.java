package com.megacoffee.OrderApp.controller;


import com.megacoffee.OrderApp.dto.ResultDto;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.File;
import java.io.IOException;
import java.util.UUID;

@Controller
public class UploadController {

   // private static final String UPLOAD_DIR = "C:/Users/EZEN/Documents/git-blog/MegaProject/OrderApp/src/main/resources/static/upload/";
    private static final String UPLOAD_DIR = "C:/Users/yurim/OneDrive/문서/GitHub/MegaProject/OrderApp/src/main/resources/static/upload/";

    @PostMapping("/upload")
    @ResponseBody
    public ResultDto upload(@RequestParam MultipartFile file) throws IOException {

        String newFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();

        if( !file.isEmpty() ){
            File newFile = new File(UPLOAD_DIR + newFileName);
            file.transferTo( newFile );
        }else {
            ResultDto resultDto = ResultDto.builder()
                    .status("ok")
                    .result(0)
                    .build();

            return resultDto;
        }

        ResultDto resultDto = ResultDto.builder()
                .status("ok")
                .result(1)
                .uploadFileName(newFileName)
                .build();

        return resultDto;
    }

    @PostMapping("/upload2")
    @ResponseBody
    public ResultDto upload2(@RequestParam("file") MultipartFile file, @RequestParam("file2") MultipartFile file2) throws IOException {
        String newFileName = UUID.randomUUID().toString() + "_" + file.getOriginalFilename();
        String newFileName2 = UUID.randomUUID().toString() + "_" + file2.getOriginalFilename();

        if (!file.isEmpty()) {
            File newFile = new File(UPLOAD_DIR + newFileName);
            file.transferTo(newFile);
        }

        if (!file2.isEmpty()) {
            File newFile2 = new File(UPLOAD_DIR + newFileName2);
            file2.transferTo(newFile2);
        }

        ResultDto resultDto = ResultDto.builder()
                .status("ok")
                .result(1)
                .uploadFileName(newFileName)
                .uploadFileName2(newFileName2)
                .build();

        return resultDto;
    }

}
