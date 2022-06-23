package edu.team5.finalproject.service;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import edu.team5.finalproject.exception.ExceptionMessages;

@Service
public class ImageService {
    
    private static final String DIRECTORY = "src/main/resources/static/uploads";
    private static final String DEFAULT_PROFILE_IMAGE = "default-profile-image.png";
    
    public String defaultImage(){
        return DEFAULT_PROFILE_IMAGE;
    }

    public String imageToString(MultipartFile image){
        try{
            String imageName = image.getOriginalFilename();
            
            if(!validateSizeImage(image, 1)) throw new IllegalArgumentException(ExceptionMessages.ERROR_SIZE_IMAGE.get());
            if(!validateFormatImage(imageName)) throw new IllegalArgumentException(ExceptionMessages.ERROR_FORMAT_IMAGE.get());

            Path imagePath = Paths.get(DIRECTORY, imageName).toAbsolutePath();
            Files.copy(image.getInputStream(), imagePath, StandardCopyOption.REPLACE_EXISTING);
            return imageName;
        }catch(IOException e){
            throw new IllegalArgumentException(ExceptionMessages.ERROR_SAVING_IMAGE.get());
        }
    }

    public List<String> imagesToString(List<MultipartFile> imageList){
        try{
            List<String> imageNames = imageList.stream().map(e -> e.getOriginalFilename()).collect(Collectors.toList());

            if(!validateSizeImage(imageList, 1)) throw new IllegalArgumentException(ExceptionMessages.ERROR_SIZE_IMAGE.get());
            if(!validateFormatImage(imageNames)) throw new IllegalArgumentException(ExceptionMessages.ERROR_FORMAT_IMAGE.get());

            List<Path> imagePaths = new ArrayList<>();

            for(String p : imageNames) {imagePaths.add(Paths.get(DIRECTORY, p));}

            for(MultipartFile m : imageList){
                for(Path p : imagePaths)
                Files.copy(m.getInputStream(), p);
            }            
            return imageNames;
            
    } catch(IOException e){
        throw new IllegalArgumentException(ExceptionMessages.ERROR_SAVING_IMAGE.get());
    }
    }

    private boolean validateFormatImage(String imageName){
         if(imageName.endsWith(".png") || imageName.endsWith(".jpg") || imageName.endsWith(".jpeg")){
            return true;
         }
         return false;
    }

    private boolean validateSizeImage(List<MultipartFile> images, int size){
        for (MultipartFile e : images) {
            if(e.getSize() <= (size * 1000000)){
                return false;
             }
        }
        return true;
    }

    private boolean validateFormatImage(List<String> imageNames){
        for (String e : imageNames) {
            if(!e.endsWith(".png") || e.endsWith(".jpg")){
                return false;
             }
        }
        return true;
   }

   private boolean validateSizeImage(MultipartFile image, int size){
       if(image.getSize() <= (size * 1000000)){
           return true;
       }
       return false;
   }

}
