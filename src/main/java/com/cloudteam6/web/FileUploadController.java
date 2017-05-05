package com.cloudteam6.web;

import java.io.File;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.nio.file.StandardCopyOption;
import java.security.Principal;

import javax.servlet.http.HttpServletRequest;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;
import org.springframework.web.servlet.ModelAndView;

import com.cloudteam6.repository.AppRepository;
import com.cloudteam6.service.UserService;
import com.cloudteam6.model.App;
import com.cloudteam6.model.User;

@Controller
public class FileUploadController {
	private static String destDir = System.getProperty("catalina.base") + "/webapps";
	
	@Autowired
	private UserService userService;
	
	@Autowired 
	private AppRepository appRepository;
	
	private static final Logger logger = LoggerFactory.getLogger(FileUploadController.class);
	private static String SAVE_DIR = "uploadedFiles";
		
	@RequestMapping(value = "/upload", method = RequestMethod.POST)
	public @ResponseBody ModelAndView uploadFileHandler(@RequestParam("name") String name, @RequestParam("file") MultipartFile file, @RequestParam("imagefile") MultipartFile imagefile, HttpServletRequest req, Principal principal) {
		
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
		        
		        Files.move(path, Paths.get(destDir + File.separator + file.getOriginalFilename()), StandardCopyOption.REPLACE_EXISTING);
		        
				String appName = name;
				String appURL = "/" + file.getOriginalFilename().substring(0, file.getOriginalFilename().length() - 4);
				String appImageURL = "/cloudteam6/uploadedFiles/images/" + imagefile.getOriginalFilename();
				User user = userService.findByUsername(principal.getName());
				App a = new App(appName, appURL, appImageURL, false, user);
				appRepository.save(a);
				return new ModelAndView("redirect:/welcome");
				
			} catch (Exception e) {
				logger.info("Upload failed");
				return new ModelAndView("redirect:/welcome");
			}
		} else {
			logger.info("You failed to upload" + name + "because the file was empty.");
			return new ModelAndView("redirect:/welcome");
		}
	}
}
