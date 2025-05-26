package com.studyhub.sth.api.controllers;

import com.studyhub.sth.application.services.R2StorageService;
import io.swagger.v3.oas.annotations.Parameter;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;

@RestController
@RequestMapping("/upload")
public class UploadController {

    private final R2StorageService r2StorageService;

    public UploadController(R2StorageService r2StorageService) {
        this.r2StorageService = r2StorageService;
    }

    @ApiResponses(value = {
            @ApiResponse(responseCode = "200", description = "Arquivo enviado com sucesso"),
            @ApiResponse(responseCode = "500", description = "Erro ao enviar o arquivo")
    })
    @PostMapping(consumes = "multipart/form-data")
    public String uploadFile(
            @Parameter(description = "Arquivo a ser enviado", required = true,
                    content = @Content(mediaType = "application/octet-stream"))
            @RequestParam("file")
            MultipartFile file
    ) {
        try {
            String fileName = r2StorageService.uploadFile(file);
            return "Arquivo enviado com sucesso: " + fileName;
        } catch (IOException e) {
            System.out.println("Erro ao enviar o arquivo: " + e.getMessage());
            return "Erro ao enviar o arquivo: " + e.getMessage();
        }
    }
}
