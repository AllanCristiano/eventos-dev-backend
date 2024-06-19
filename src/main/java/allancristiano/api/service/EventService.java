package allancristiano.api.service;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Date;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.amazonaws.services.s3.AmazonS3;

import allancristiano.api.domain.event.Event;
import allancristiano.api.domain.event.EventRequestDto;

@Service
public class EventService {
    @Autowired
    private AmazonS3 s3client;

    @Value("${aws.bucket.name}")
    private String bucketName;

    public Event createEvent(EventRequestDto data){
        String imgUrl = null;

        if (data.image() != null) {
            imgUrl = this.uploadImage(data.image());
        }

        Event newEvent = new Event();
        newEvent.setTitle(data.title());
        newEvent.setDescription(data.description());
        newEvent.setEventUrl(data.eventUrl());
        newEvent.setDate(new Date(data.date()));
        newEvent.setImgUrl(imgUrl);

        return newEvent;
    }

    private String uploadImage(MultipartFile multipartFile){
        String fileName = UUID.randomUUID() + "-" + multipartFile.getOriginalFilename();
        try {
            File file = this.convertMultipartFile(multipartFile);
            s3client.putObject(bucketName, fileName, file);
            file.delete();
            return s3client.getUrl(bucketName, fileName).toString();
        } catch (Exception e) {
            System.out.println("Erro ao subir o arquivo");
            return null;
        }
    }

    private File convertMultipartFile(MultipartFile multipartFile) throws IOException{
        File file = new File(multipartFile.getOriginalFilename());
        FileOutputStream fop = new FileOutputStream(file);
        fop.write(multipartFile.getBytes());
        fop.close();
        return file;
    }
}
