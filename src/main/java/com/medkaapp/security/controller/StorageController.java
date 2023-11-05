package com.medkaapp.security.controller;

import com.medkaapp.security.dto.EstructuraArchivoDto;
import com.medkaapp.security.service.aws.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.*;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.IOException;
import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/storage")
@CrossOrigin
public class StorageController {
    @Autowired
    private StorageService storageService;

    @PostMapping("/upload")
    public ResponseEntity<EstructuraArchivoDto> uploadFile(@RequestParam("file") MultipartFile file) throws IOException {
        String fileName = storageService.uploadFile(file);
        EstructuraArchivoDto estructuraArchivoDto = new EstructuraArchivoDto();
        estructuraArchivoDto.setNombreArchivo(fileName);
        return ResponseEntity.ok(estructuraArchivoDto);
    }

    @GetMapping("/download/{fileName}")
    public ResponseEntity<byte[]> downloadFile(@PathVariable String fileName) throws IOException {
        byte[] data = storageService.downloadFile(fileName);
        HttpHeaders headers = new HttpHeaders();
        headers.setContentType(storageService.getContentType(fileName));
        headers.setContentDisposition(ContentDisposition.builder("inline").filename(fileName).build());
        return new ResponseEntity<>(data, headers, HttpStatus.OK);
    }

    @GetMapping("/list")
    public ResponseEntity<List<EstructuraArchivoDto>> listFiles() {
        List<String> fileNames = storageService.listFiles();
        List<EstructuraArchivoDto> archivos = fileNames.stream()
                .map(fileName -> new EstructuraArchivoDto(fileName))
                .collect(Collectors.toList());
        return ResponseEntity.ok(archivos);
    }
}
