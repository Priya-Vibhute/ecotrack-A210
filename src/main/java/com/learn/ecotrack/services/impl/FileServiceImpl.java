package com.learn.ecotrack.services.impl;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.UUID;

import org.jspecify.annotations.Nullable;
import org.springframework.stereotype.Service;
import org.springframework.web.multipart.MultipartFile;

import com.learn.ecotrack.services.FileService;

@Service
public class FileServiceImpl implements FileService {

	@Override
	public String uploadFile(MultipartFile file, String path) {
		
//		untitled.jpeg
		String originalFilename = file.getOriginalFilename();
//      .jpeg	
		String extension = originalFilename.substring(originalFilename.lastIndexOf('.'));
		
		String fileName = UUID.randomUUID().toString();
//		76c3ef44-8613-45d5-bc94-696a3723753f.jpeg
		String newFileName=fileName+extension;
		

		File folder = new File(path);
		
		if(!folder.exists())
		{
			folder.mkdirs();
		}
		
		
		try {
			Files.copy(file.getInputStream(),Paths.get(path+newFileName));
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

		
		return newFileName;
	}

	@Override
	public InputStream getResource(String path, String name) {
		
		FileInputStream inputStream=null;
		try {
			inputStream=new FileInputStream(path+name);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return inputStream;
	}

}
