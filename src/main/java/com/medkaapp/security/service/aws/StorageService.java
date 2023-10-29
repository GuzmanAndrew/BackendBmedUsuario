package com.medkaapp.security.service.aws;

import org.apache.commons.io.FilenameUtils;
import org.apache.tomcat.util.http.fileupload.ByteArrayOutputStream;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;
import software.amazon.awssdk.core.sync.RequestBody;
import software.amazon.awssdk.core.sync.ResponseTransformer;
import software.amazon.awssdk.regions.Region;
import software.amazon.awssdk.services.s3.S3Client;
import software.amazon.awssdk.services.s3.model.GetObjectRequest;
import software.amazon.awssdk.services.s3.model.ListObjectsV2Request;
import software.amazon.awssdk.services.s3.model.PutObjectRequest;
import software.amazon.awssdk.services.s3.model.S3Object;

import java.io.IOException;
import java.io.InputStream;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class StorageService {

    private final S3Client s3Client;
    private final String bucketName = "upload-documents-users";

    @Autowired
    public StorageService() {
        this.s3Client = S3Client.builder().region(Region.US_EAST_1).build();
    }

    public String uploadFile(MultipartFile file) throws IOException {
        String fileName = file.getOriginalFilename();
        s3Client.putObject(PutObjectRequest.builder().bucket(bucketName).key(fileName).build(),
                RequestBody.fromInputStream(file.getInputStream(), file.getSize()));
        return fileName;
    }

    public byte[] downloadFile(String fileName) throws IOException {
        try (InputStream is = s3Client.getObject(GetObjectRequest.builder().bucket(bucketName).key(fileName).build(), ResponseTransformer.toInputStream());
             ByteArrayOutputStream os = new ByteArrayOutputStream()) {
            byte[] buffer = new byte[1024];
            int length;
            while ((length = is.read(buffer)) != -1) {
                os.write(buffer, 0, length);
            }
            return os.toByteArray();
        }
    }

    public List<String> listFiles() {
        ListObjectsV2Request listObjectsReq = ListObjectsV2Request.builder()
                .bucket(bucketName)
                .build();

        List<S3Object> s3Objects = s3Client.listObjectsV2(listObjectsReq).contents();

        return s3Objects.stream()
                .map(S3Object::key)
                .collect(Collectors.toList());
    }

    public MediaType getContentType(String fileName) {
        String fileExtension = FilenameUtils.getExtension(fileName).toLowerCase();

        switch (fileExtension) {
            case "xml":
                return MediaType.APPLICATION_XML;
            case "csv":
                return new MediaType("text", "csv");
            case "jpeg":
                return MediaType.IMAGE_JPEG;
            case "tiff":
                return new MediaType("image", "tiff");
            case "doc":
                return new MediaType("application", "msword");
            case "docx":
                return new MediaType("application", "vnd.openxmlformats-officedocument.wordprocessingml.document");
            case "xls":
                return new MediaType("application", "vnd.ms-excel");
            case "xlsx":
                return new MediaType("application", "vnd.openxmlformats-officedocument.spreadsheetml.sheet");
            case "png":
                return MediaType.IMAGE_PNG;
            case "pdf":
                return MediaType.APPLICATION_PDF;
            default:
                return MediaType.APPLICATION_OCTET_STREAM; // Tipo genérico para datos binarios
        }
    }

}
