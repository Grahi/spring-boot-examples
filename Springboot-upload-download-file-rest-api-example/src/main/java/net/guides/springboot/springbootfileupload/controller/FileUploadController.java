package net.guides.springboot.springbootfileupload.controller;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import net.guides.springboot.springbootfileupload.payload.Response;
import net.guides.springboot.springbootfileupload.service.FileStorageService;

@RestController
public class FileUploadController {
	
@GetMapping("/hello")
public String getRes() {
	return "Hello";
}


  @Autowired private FileStorageService fileStorageService;
  
  @PostMapping("/uploadFile") 
  public Response uploadFile(@RequestParam("file")  MultipartFile file) { 
	  String fileName = fileStorageService.storeFile(file);
  
  System.out.println(fileName.toString());
  
  String fileDownloadUri = ServletUriComponentsBuilder.fromCurrentContextPath()
  .path("/downloadFile/") .path(fileName) .toUriString();
  
  return new Response(fileName, fileDownloadUri,file.getContentType(),  file.getSize()); 
  }
  
	
  @PostMapping("/uploadMultipleFiles") public List <Response>
  uploadMultipleFiles(@RequestParam("files") MultipartFile[] files) { 
	  return  Arrays.asList(files).stream().map(file -> uploadFile(file))
  .collect(Collectors.toList()); }
	 
 
	
}
