package com.bezkoder.spring.files.upload.controller;

import java.util.List;
import java.util.stream.Collectors;

import com.bezkoder.spring.files.upload.message.ResponseFile;
import com.bezkoder.spring.files.upload.model.Item;
import com.bezkoder.spring.files.upload.model.Product;
import com.bezkoder.spring.files.upload.repository.ItemRepository;
import com.bezkoder.spring.files.upload.repository.ProductRepository;
import com.bezkoder.spring.files.upload.service.FileStorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.multipart.MultipartFile;

import com.bezkoder.spring.files.upload.message.ResponseMessage;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

@RestController
@CrossOrigin("*")
public class FilesController {

 /* @Autowired
  FilesStorageService storageService;

  @PostMapping("/upload")
  public ResponseEntity<ResponseMessage> uploadFile(@RequestParam("file") MultipartFile file) {
    String message = "";
    try {
      storageService.save(file);

      message = "Uploaded the file successfully: " + file.getOriginalFilename();
      return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
    } catch (Exception e) {
      message = "Could not upload the file: " + file.getOriginalFilename() + "!";
      return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
    }
  }

  @GetMapping("/files")
  public ResponseEntity<List<FileInfo>> getListFiles() {
    List<FileInfo> fileInfos = storageService.loadAll().map(path -> {
      String filename = path.getFileName().toString();
      String url = MvcUriComponentsBuilder
          .fromMethodName(FilesController.class, "getFile", path.getFileName().toString()).build().toString();

      return new FileInfo(filename, url);
    }).collect(Collectors.toList());

    return ResponseEntity.status(HttpStatus.OK).body(fileInfos);
  }

  @GetMapping("/files/{filename:.+}")
  public ResponseEntity<Resource> getFile(@PathVariable String filename) {
    Resource file = storageService.load(filename);
    return ResponseEntity.ok()
        .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + file.getFilename() + "\"").body(file);
  }*/

   @Autowired
   private FileStorageService storageService;

   @Autowired
   private ItemRepository itemRepository;

    @Autowired
    private ProductRepository productRepository;

   @PostMapping("/upload")
   public Item  uploadFile(@RequestParam("file") MultipartFile file) {
     String message = "";
     try {
         message = "Uploaded the file successfully: " + file.getOriginalFilename();

         return storageService.store(file);
/*
       Item mP=new Item();
       mP.setName(file.getOriginalFilename());
       mP.setData(file.getBytes());
       mP.setType(file.getContentType());*/

      // itemRepository.save(mP);


      // return ResponseEntity.status(HttpStatus.OK).body(new ResponseMessage(message));
     } catch (Exception e) {
       message = "Could not upload the file: " + file.getOriginalFilename() + "!";
      return null;
       // return ResponseEntity.status(HttpStatus.EXPECTATION_FAILED).body(new ResponseMessage(message));
     }
   }

   @GetMapping("/files")
   public ResponseEntity<List<ResponseFile>> getListFiles() {
     List<ResponseFile> files = storageService.getAllFiles().map(dbFile -> {
       String fileDownloadUri = ServletUriComponentsBuilder
               .fromCurrentContextPath()
               .path("/files/")
               .path(dbFile.getId())
               .toUriString();

       return new ResponseFile(
               dbFile.getName(),
               fileDownloadUri,
               dbFile.getType(),
               dbFile.getData().length);
     }).collect(Collectors.toList());

     return ResponseEntity.status(HttpStatus.OK).body(files);
   }

   @GetMapping("/items")
   public List<Item> displayItem (){
     return itemRepository.findAll();
   }



   @GetMapping("/files/{id}")
   public ResponseEntity<byte[]> getFile(@PathVariable String id) {
     Item item = storageService.getFile(id);

     return ResponseEntity.ok()
             .header(HttpHeaders.CONTENT_DISPOSITION, "attachment; filename=\"" + item.getName() + "\"")
             .body(item.getData());
   }
}
