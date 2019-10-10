package com.itheima.ssm.controller;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.itheima.ssm.domain.WangEditor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import javax.servlet.http.HttpServletRequest;
import java.io.File;
import java.io.IOException;
import java.util.UUID;

@RestController
@RequestMapping("/editor")
public class EditorController {
 
	private Logger log = LoggerFactory.getLogger(getClass());
 
	// 图片上传
	@RequestMapping(value = "/upload.do", method = RequestMethod.POST)
	public String uploadFile(
			@RequestParam("myFile") MultipartFile multipartFile,
			HttpServletRequest request) {
		try {
			// 先获取到要上传的文件目录
			String path = "E:\\MyTest\\upload";

			// 创建File对象，一会向该路径下上传文件
			File file = new File(path);

			// 判断路径是否存在，如果不存在，创建该路径
			if(!file.exists()) {
				file.mkdirs();
			}

			// 获取到上传文件的名称
			String filename = multipartFile.getOriginalFilename();

			//随机前缀
			String uuid = UUID.randomUUID().toString().replaceAll("-", "").toUpperCase();

			// 把文件的名称唯一化
			filename = uuid+"_"+filename;

			// 上传文件
			multipartFile.transferTo(new File(file,filename));

			// 返回图片访问路径
			String url = "http://localhost:9999/" + filename;

			String[] str = { url };
			WangEditor we = new WangEditor(str);
			return new ObjectMapper().writeValueAsString(we);
		} catch (IOException e) {
			log.error("上传文件失败", e);
		}
		return null;
	}

}
