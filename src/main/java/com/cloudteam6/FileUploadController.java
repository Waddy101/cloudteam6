package com.cloudteam6;

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

@Controller
public class FileUploadController {
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	private static String SAVE_DIR = "uploadedFiles";

	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody String uploadFileHandler(@RequestParam("name") String name, @RequestParam("file") MultipartFile file, HttpServletRequest req) {
		
		if (!file.isEmpty()) {
			try {
				String appPath = req.getServletContext().getRealPath("");
				String savePath = appPath + File.separator + SAVE_DIR;
				File fileSaveDir = new File(savePath);
				if (!fileSaveDir.exists()) fileSaveDir.mkdir();
				
				byte[] bytes = file.getBytes();
				
		        Path path = Paths.get(savePath + file.getOriginalFilename());
		        Files.write(path, bytes);
				
				Extractor extractor = new Extractor();
				extractor.extractFile(path.toString(), file.getOriginalFilename());
				
				return "redirect:/welcome";
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
