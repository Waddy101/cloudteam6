package com.cloudteam6.web;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.cloudteam6.classfiles.Extractor;
import com.cloudteam6.model.App;

@Controller
public class FileUploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	private static String SAVE_DIR = "uploadedFiles";
		
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody String uploadFileHandler(@RequestParam("name") String name, @RequestParam("file") MultipartFile file, @RequestParam("imagefile") MultipartFile imagefile, HttpServletRequest req) {
		
		if (!file.isEmpty()) {
			try {
				String appPath = req.getServletContext().getRealPath("");
				
				System.out.println(req.getServletContext().getContextPath());
				String savePath = appPath + File.separator + SAVE_DIR;
				File fileSaveDir = new File(savePath);
				if (!fileSaveDir.exists()) fileSaveDir.mkdir();
			
				byte[] bytes = file.getBytes();
		        Path path = Paths.get(savePath + File.separator + file.getOriginalFilename());
		        Files.write(path, bytes);
		        
		        byte[] imagebytes = imagefile.getBytes();
		        File imagefileSaveDir = new File(savePath + File.separator + "images");
				if (!imagefileSaveDir.exists()) imagefileSaveDir.mkdir();
				
		        Path imagePath = Paths.get(savePath + File.separator + "images" + File.separator + imagefile.getOriginalFilename());
		        Files.write(imagePath, imagebytes);
				Extractor extractor = new Extractor();
				System.out.println("failure 1");
				App extractedApp = extractor.extractFile(path.toString(), file.getOriginalFilename(), imagePath.toString(), imagefile.getOriginalFilename());
				System.out.println("failure 2");
				return extractedApp.getURL();
			} catch (Exception e) {
				logger.info("Upload failed");
				return "redirect:/welcome";
			}
		} else {
			logger.info("You failed to upload" + name + "because the file was empty.");
			return "redirect:/welcome";
		}
	}
}
