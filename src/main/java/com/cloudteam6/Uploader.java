package com.cloudteam6;


import java.io.File;
import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

/**
 * Servlet implementation class Uploader
 */
@WebServlet("/upload")
@MultipartConfig(fileSizeThreshold=1024*1024*2, //2MB
					maxFileSize=1024*1024*10,    //10MB
					maxRequestSize=1024*1024*50)  //50MB
public class Uploader extends HttpServlet {

	private static final long serialVersionUID = 1L;
	private static final String SAVE_DIR = "uploadedFiles";
	
	
	private String extractFileName(Part part) {
		String contentDisp=part.getHeader("content-disposition");
		String[] items = contentDisp.split(";");
		for (String s : items) {
			if (s.trim().startsWith("filename")) {
			return s.substring(s.indexOf("=") + 2, s.length()-1);
			}
		}
		return "";
	}
	
	public void doPost(HttpServletRequest req, HttpServletResponse res) throws ServletException, IOException {
		String appPath = req.getServletContext().getRealPath("");
		String savePath = appPath + File.separator + SAVE_DIR;
		File fileSaveDir = new File(savePath);
		if (!fileSaveDir.exists()) fileSaveDir.mkdir();
		for (Part part : req.getParts()) {
			String fileName = extractFileName(part);
			String filePath = savePath + File.separator + fileName;
			part.write(savePath + File.separator + fileName);
			Extractor extractor = new Extractor();
			extractor.extractFile(filePath, fileName);
		}
		res.sendRedirect("index.jsp");
	}

}
