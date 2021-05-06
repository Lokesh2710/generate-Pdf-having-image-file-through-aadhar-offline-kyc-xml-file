package com.jocata.A___ABC.helper;

import java.io.File;
import java.io.FileOutputStream;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;

import org.springframework.web.multipart.MultipartFile;

public class FileUploadHelper {
	
	public final String UPLOAD_DIR="C:\\lokesh_data";
	
	public boolean uploadFile(MultipartFile multipartFile)
	{
		boolean f = false;
		try
		{
			//read
			/*InputStream is = multipartFile.getInputStream();
			byte data[] = new byte[is.available()];
			is.read(data);
			
			//write
			FileOutputStream fos = new FileOutputStream(UPLOAD_DIR+File.separator+multipartFile.getOriginalFilename());
			fos.write(data);
			fos.flush();
			fos.close();
			f=true;*/
			
			Files.copy(multipartFile.getInputStream(),Paths.get(UPLOAD_DIR+File.separator+multipartFile.getOriginalFilename()),StandardCopyOption.REPLACE_EXISTING);
			 
			f=true;
			
		}
		catch(Exception e)
		{
			e.printStackTrace();
		}
		
		return f;
	}
	

}
