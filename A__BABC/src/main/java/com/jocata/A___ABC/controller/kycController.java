package com.jocata.A___ABC.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import com.jocata.A___ABC.helper.FileUploadHelper;

@RestController
public class kycController {
	
	/*@Autowired
	private FileUploadHelper fileUploadHelper;

	@PostMapping("/uploadFile")
	public ResponseEntity<String> uploadFile(@RequestParam("file") MultipartFile file)
	{
		System.out.println(file.getOriginalFilename());
		System.out.println(file.getSize());
		System.out.println(file.isEmpty());
		System.out.println(file.getName());
		System.out.println(file.getContentType());
		
		
		try
		{
			boolean f = fileUploadHelper.uploadFile(file);
			if(f)
			{
				return ResponseEntity.ok("File is SuccesFully uploaded");
			}
			else
			{
				return ResponseEntity.ok("Working");
			}
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body("Something Went wrong !Try again");
		
	}*/
}
