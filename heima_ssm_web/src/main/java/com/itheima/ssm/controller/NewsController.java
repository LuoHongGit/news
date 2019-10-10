package com.itheima.ssm.controller;

import com.itheima.ssm.domain.News;
import org.dom4j.Document;
import org.dom4j.Element;
import org.dom4j.io.SAXReader;
import org.dom4j.io.XMLWriter;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.servlet.ModelAndView;

import java.io.File;
import java.io.FileOutputStream;
import java.io.OutputStreamWriter;
import java.net.URLDecoder;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/news")
public class NewsController {
    /**
     * 查询所有新闻
     * @return
     * @throws Exception
     */
    @RequestMapping("/findAll.do")
    public ModelAndView findAll() throws Exception{
        //创建ModelAndView对象
        ModelAndView mv = new ModelAndView();

        //获取根元素节点
        Element root = getRootElementByFileName("E:\\MyTest\\news.xml");

        //获取其他节点
        List<Element> elements = root.elements();

        //创建集合
        List<News> newsList = new ArrayList<News>();

        //初始化news对象
        News news = null;

        //遍历节点
        for (Element element : elements) {
            //创建news对象
            news = new News();

            //属性封装
            news.setNews_id(element.elementText("news_id"));
            news.setTitle(element.elementText("title"));
            String contentStr = URLDecoder.decode(element.element("content").asXML());
            news.setContent(contentStr.substring(9,contentStr.length() - 10));
            news.setAutor(element.elementText("autor"));
            news.setSubmitDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(element.elementText("submitDate")));
            news.setCatalog_id(Integer.parseInt(element.elementText("catalog_id")));
            news.setKeywords(element.elementText("keywords"));
            news.setClickcount(Integer.parseInt(element.elementText("clickcount")));
            news.setCover_pic(element.elementText("cover_pic"));
            news.setStatus(Integer.parseInt(element.elementText("status")));

            //加入集合
            newsList.add(news);
        }

        //将数据加入mv
        mv.addObject("newsList",newsList);

        //设置视图
        mv.setViewName("news-list");

        return mv;
    }

    /**
     * 根据用户名查询新闻
     */
    @RequestMapping("findNewsByUsername.do")
    public ModelAndView findNewsByUsername() throws Exception{
        //创建ModelAndView对象
        ModelAndView mv = new ModelAndView();

        //获取根元素节点
        Element root = getRootElementByFileName("E:\\MyTest\\news.xml");

        //获取其他节点
        List<Element> elements = root.elements();

        //创建集合
        List<News> newsList = new ArrayList<News>();

        //初始化news对象
        News news = null;

        //遍历节点
        for (Element element : elements) {
            //创建news对象
            news = new News();

            //获取XML文档中作者信息
            news.setAutor(element.elementText("autor"));

            //获取当前登录用户信息
            User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
            String username = user.getUsername();

            //判断作者
            if(!news.getAutor().equals(username)){
                continue;
            }

            //属性封装
            news.setNews_id(element.elementText("news_id"));
            news.setTitle(element.elementText("title"));
            String contentStr = URLDecoder.decode(element.element("content").asXML());
            news.setContent(contentStr.substring(9,contentStr.length() - 10));
            news.setSubmitDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(element.elementText("submitDate")));
            news.setCatalog_id(Integer.parseInt(element.elementText("catalog_id")));
            news.setKeywords(element.elementText("keywords"));
            news.setClickcount(Integer.parseInt(element.elementText("clickcount")));
            news.setCover_pic(element.elementText("cover_pic"));
            news.setStatus(Integer.parseInt(element.elementText("status")));

            //加入集合
            newsList.add(news);
        }

        //将数据加入mv
        mv.addObject("newsList",newsList);

        //设置视图
        mv.setViewName("news-list");

        return mv;
    }

    /**
     * 保存新闻
     */
    @RequestMapping("/save.do")
    public String save(News news) throws Exception{
        //手工补全News信息
        news.setNews_id(UUID.randomUUID().toString().replace("-","").toUpperCase());
        User user = (User) SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        news.setAutor(user.getUsername());
        news.setSubmitDate(new Date());
        news.setCatalog_id(0);
        news.setStatus(1);

        //获取根元素节点
        Element root = getRootElementByFileName("E:\\MyTest\\news.xml");

        //添加子元素
        Element newsElement = root.addElement("news");

        //添加id属性
        newsElement.addAttribute("id",news.getNews_id());

        //添加属性
        Element news_idElement = newsElement.addElement("news_id");
        news_idElement.setText(news.getNews_id());

        Element titleElement = newsElement.addElement("title");
        titleElement.setText(news.getTitle());

        Element contentElement = newsElement.addElement("content");
        contentElement.setText(news.getContent());

        Element autorElement = newsElement.addElement("autor");
        autorElement.setText(news.getAutor());

        Element submitDateElement = newsElement.addElement("submitDate");
        submitDateElement.setText(news.getSubmitDate().toLocaleString());

        Element catalog_idElement = newsElement.addElement("catalog_id");
        catalog_idElement.setText((news.getCatalog_id().toString()));

        Element keywordsElement = newsElement.addElement("keywords");
        keywordsElement.setText(news.getKeywords());

        Element clickcountElement = newsElement.addElement("clickcount");
        clickcountElement.setText("0");

        Element cover_picElement = newsElement.addElement("cover_pic");
        cover_picElement.setText("");

        Element statusElement = newsElement.addElement("status");
        statusElement.setText("1");

        XMLWriter writer = new XMLWriter( new OutputStreamWriter(new FileOutputStream("E:\\MyTest\\news.xml"),"UTF-8"));

        writer.write(root.getDocument());

        writer.close();

        return "redirect:/news/findNewsByUsername.do";
    }

    /**
     * 通过id查询新闻详情
     */
    @RequestMapping("/findById.do")
    public ModelAndView findById(@RequestParam("id") String id) throws Exception{
        //创建模型视图对象
        ModelAndView mv = new ModelAndView();

        //获取文档对象
        Document document = getDocumentByFileName("E:\\MyTest\\news.xml");

        //使用Xpath获取指定id的元素节点
        Element element = (Element)document.selectSingleNode("//news[@id='"+ id +"']");

        //创建news对象
        News news = new News();

        //属性封装
        element2News(element,news);

        //将新闻对象加入模型视图
        mv.addObject("news",news);

        //设置视图
        mv.setViewName("news-detail");

        //返回
        return mv;
    }

    /**
     * 通用方法获取根元素节点
     */
    public Element getRootElementByFileName(String fileName) throws Exception{
        //创建核心对象
        SAXReader saxReader = new SAXReader();

        //读取xml文件生成dom树
        Document document = saxReader.read(new File("E:\\MyTest\\news.xml"));

        //获取根元素节点
        Element root = document.getRootElement();

        return root;
    }

    /**
     * 通用方法获取文档对象
     */
    public Document getDocumentByFileName(String fileName) throws Exception{
        //创建核心对象
        SAXReader saxReader = new SAXReader();

        //读取xml文件生成dom树
        Document document = saxReader.read(new File("E:\\MyTest\\news.xml"));

        return document;
    }

    /**
     * 通用方法 将element对象中的数据封装到News对象
     */
    public void element2News(Element element,News news) throws Exception{
        news.setNews_id(element.elementText("news_id"));
        news.setTitle(element.elementText("title"));
        String contentStr = URLDecoder.decode(element.element("content").asXML());
        news.setContent(contentStr.substring(9,contentStr.length() - 10));
        news.setAutor(element.elementText("autor"));
        news.setSubmitDate(new SimpleDateFormat("yyyy-MM-dd HH:mm:ss").parse(element.elementText("submitDate")));
        news.setCatalog_id(Integer.parseInt(element.elementText("catalog_id")));
        news.setKeywords(element.elementText("keywords"));
        news.setClickcount(Integer.parseInt(element.elementText("clickcount")));
        news.setCover_pic(element.elementText("cover_pic"));
        news.setStatus(Integer.parseInt(element.elementText("status")));
    }
}
