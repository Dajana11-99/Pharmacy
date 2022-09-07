package com.example.apotekasmilje.service.impl;

import com.example.apotekasmilje.model.products.Image;
import com.example.apotekasmilje.service.FirebaseService;
import com.example.apotekasmilje.service.ProductService;
import com.google.cloud.storage.BlobId;
import com.google.cloud.storage.BlobInfo;
import com.google.firebase.cloud.StorageClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.URLEncoder;
import java.nio.charset.StandardCharsets;
import java.nio.file.Files;

import java.util.List;
import java.util.UUID;
import com.google.auth.Credentials;
import com.google.auth.oauth2.GoogleCredentials;
import com.google.cloud.storage.*;
@Service
public class FirebaseServiceImpl implements FirebaseService {
    private final Logger logger= LoggerFactory.getLogger(FirebaseServiceImpl.class);
    private static final String DOWNLOAD_URL ="https://firebasestorage.googleapis.com/v0/b/pharmacy-566b9.appspot/o/%s?alt=media";
    @Autowired
    private ProductService productService;

    private String uploadFile(File file, String fileName) throws IOException {
        String currentDirectory = System.getProperty("user.dir");
        BlobId blobId = BlobId.of("pharmacy-566b9.appspot.com", fileName);
        BlobInfo blobInfo = BlobInfo.newBuilder(blobId).setContentType("media").build();
        Credentials credentials = GoogleCredentials.fromStream(new FileInputStream(currentDirectory+"/pharmacy-firebase.json"));
        Storage storage = StorageOptions.newBuilder().setCredentials(credentials).build().getService();
        storage.create(blobInfo, Files.readAllBytes(file.toPath()));
        return String.format(DOWNLOAD_URL, URLEncoder.encode(fileName, StandardCharsets.UTF_8));
    }
    private File convertToFile(MultipartFile multipartFile, String fileName) throws IOException
    {
        File tempFile = new File(fileName);
        try (FileOutputStream fos = new FileOutputStream(tempFile)) //NOSONAR
        {
            fos.write(multipartFile.getBytes());
        }
        return tempFile;
    }

    private String getExtension(String fileName) {
        return fileName.substring(fileName.lastIndexOf("."));
    }

    private String upload(MultipartFile multipartFile) {

        try {
            String fileName = multipartFile.getOriginalFilename();
            fileName = UUID.randomUUID().toString().concat(this.getExtension(fileName));

            File file = this.convertToFile(multipartFile, fileName);
            this.uploadFile(file, fileName);
            file.delete(); //NOSONAR
            return fileName;
        } catch (Exception e) {
            logger.error(e.toString());
            return null;
        }

    }

    public String uploadProductImage(MultipartFile newImage,String name)  {
        String newFileName= upload(newImage);
        Image image = new Image(null,newFileName);
        productService.addNewImage(name,image);
        return newFileName;
    }




}
