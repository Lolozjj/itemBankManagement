package com.itembankmanagement.controller;


import com.itembankmanagement.dao.Paper;
import com.itembankmanagement.service.TitleService;
import com.itembankmanagement.util.Result;
import org.springframework.beans.factory.annotation.Autowired;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.awt.*;
import java.util.List;
import java.util.Map;

@RequestMapping("/title")
@RestController
public class TitleController {



    @Autowired
    TitleService titleService;

    @PostMapping("/add")
    Result titleAdd(String content,String answer,Long typeId,Long chapterId){
        return titleService.add(content,answer,typeId,chapterId);
    }

    @PostMapping("/modification")
    Result titleModificaition(Long id,String content,String answer,Long typeId,Long chapterId){
        return titleService.modification(id,content,answer,typeId,chapterId);
    }

    @GetMapping("/getByLesson")
    Result adminGetByLesson(Long id){
        return titleService.getByLesson(id);
    }

    @GetMapping("/get")
    Result adminGet(Long id){
        return titleService.get(id);
    }

    @GetMapping("/getAll")
    Result adminGetAll(){
        return titleService.getAll();
    }

    @PostMapping("/delete")
    Result admindelete(Long id){
        return titleService.delete(id);
    }

//    @PostMapping("/getPaper")
//    ResponseEntity GeneratingTestPapers(@RequestBody Paper paper)throws Exception{//@RequestParam("ids[]") List<Long> chapterIdList,@RequestBody Map<Long,Integer> titleTypeIntegerMap
//        List<Long> chapterIdList=paper.getChapterIdList();
//        Map<Long,Integer> titleTypeIntegerMap=paper.getTitleTypeIntegerMap();
//        return titleService.GeneratingTestPapers(chapterIdList,titleTypeIntegerMap);
//    }
    @GetMapping("/downloadTest")
    ResponseEntity downloadTest(String fileName) throws Exception {
        return titleService.downloadTest(fileName);
    }

    @PostMapping("/getTest")
    Result GeneratingTestPapers2(@RequestBody Paper paper)throws Exception{
        List<String> chapterIdList=paper.getChapterIdList();
        Map<Long,Integer> titleTypeIntegerMap=paper.getTitleTypeIntegerMap();
        return titleService.GeneratingTestPapers2(chapterIdList,titleTypeIntegerMap);
    }

    @PostMapping("/getTitleByChapters")
    Result getTitleByChapters(@RequestBody List<String> chapters)throws Exception{

        return titleService.getTitleByChapters(chapters);
    }

////    PageOffice使用
//    @PostMapping("/word")
//    Result getWord(HttpServletRequest request, HttpServletResponse response){
//            PageOfficeCtrl poCtrl1 = new PageOfficeCtrl(request);
//        poCtrl1.setServerPage(request.getContextPath()+"/poserver.zz");     //创建WordDocument对象
//        poCtrl1.webOpen("d:\\test.doc", OpenModeType.docAdmin,"张三");
//        WordDocument doc = new WordDocument();    //设置内容标题    //创建DataRegion对象，PO_title为自动添加的书签名称,书签名称需以“PO_”为前缀，切书签名称不能重复    //三个参数分别为要新插入书签的名称、新书签的插入位置、相关联的书签名称（“[home]”代表Word文档的第一个位置）
//        DataRegion title = doc.createDataRegion("PO_title",            DataRegionInsertType.After, "[home]");    //给DataRegion对象赋值
//        title.setValue("C#中Socket多线程编程实例\n");    //设置字体：粗细、大小、字体名称、是否是斜体
//        title.getFont().setBold(true);
//        title.getFont().setSize(20);
//        title.getFont().setName("黑体");
//        title.getFont().setItalic(false);
//        //定义段落对象
//        ParagraphFormat titlePara = title.getParagraphFormat();
//        //设置段落对齐方式
//        titlePara.setAlignment(WdParagraphAlignment.wdAlignParagraphCenter);
//        //设置段落行间距
//        titlePara.setLineSpacingRule(WdLineSpacing.wdLineSpaceMultiple);
//        //设置内容    //第一段    //创建DataRegion对象，PO_body为自动添加的书签名称
//        DataRegion body = doc.createDataRegion("PO_body",DataRegionInsertType.After, "PO_title");
//        //设置字体：粗细、是否是斜体、大小、字体名称、字体颜色
//        body.getFont().setBold(false);
//        body.getFont().setItalic(true);
//        body.getFont().setSize(10);
//        //设置中文字体名称
//        body.getFont().setName("楷体");
//        //设置英文字体名称
//        body.getFont().setName("Times New Roman");
//        body.getFont().setColor(Color.RED);
//        //给DataRegion对象赋值
//        body.setValue("是微软随着VS.net新推出的一门语言。它作为一门新兴的语言，有着C++的强健，又有着VB等的RAD特性。而且，微软推出C#主要的目的是为了对抗Sun公司的Java。大家都知道Java语言的强大功能，尤其在网络编程方面。于是，C#在网络编程方面也自然不甘落后于人。本文就向大家介绍一下C#下实现套接字（Sockets）编程的一些基本知识，以期能使大家对此有个大致了解。首先，我向大家介绍一下套接字的概念。\n");
//        //创建ParagraphFormat对象
//        ParagraphFormat bodyPara = body.getParagraphFormat();
//        //设置段落的行间距、对齐方式、首行缩进
//        bodyPara.setLineSpacingRule(WdLineSpacing.wdLineSpaceAtLeast);
//        bodyPara.setAlignment(WdParagraphAlignment.wdAlignParagraphLeft);
//        bodyPara.setFirstLineIndent(21);
//        //第二段
//        DataRegion body2 = doc.createDataRegion("PO_body2",DataRegionInsertType.After, "PO_body");
//        body2.getFont().setBold(false);
//        body2.getFont().setSize(12);
//        body2.getFont().setName("黑体");
//        body2.setValue("套接字是通信的基石，是支持TCP/IP协议的网络通信的基本操作单元。可以将套接字看作不同主机间的进程进行双向通信的端点，它构成了单个主机内及整个网络间的编程界面。套接字存在于通信域中，通信域是为了处理一般的线程通过套接字通信而引进的一种抽象概念。套接字通常和同一个域中的套接字交换数据（数据交换也可能穿越域的界限，但这时一定要执行某种解释程序）。各种进程使用这个相同的域互相之间用Internet协议簇来进行通信。\n");
//        body2.setValue("[image]../images/logo.jpg[/image]");
//
//        ParagraphFormat bodyPara2 = body2.getParagraphFormat();
//        bodyPara2.setLineSpacingRule(WdLineSpacing.wdLineSpace1pt5);
//        bodyPara2.setAlignment(WdParagraphAlignment.wdAlignParagraphLeft);
//        bodyPara2.setFirstLineIndent(21);
//        //第三段
//        DataRegion body3 = doc.createDataRegion("PO_body3", DataRegionInsertType.After, "PO_body2");
//        body3.getFont().setBold(false);
//        body3.getFont().setColor(Color.getHSBColor(0, 128, 228));
//        body3.getFont().setSize(14);
//        body3.getFont().setName("华文彩云");
//        body3.setValue("套接字可以根据通信性质分类，这种性质对于用户是可见的。应用程序一般仅在同一类的套接字间进行通信。不过只要底层的通信协议允许，不同类型的套接字间也照样可以通信。套接字有两种不同的类型：流套接字和数据报套接字。\n");
//        ParagraphFormat bodyPara3 = body3.getParagraphFormat();
//        bodyPara3.setLineSpacingRule(WdLineSpacing.wdLineSpaceDouble);
//        bodyPara3.setAlignment(WdParagraphAlignment.wdAlignParagraphLeft);
//        bodyPara3.setFirstLineIndent(21);
//
//
//        String path = request.getServletContext().getRealPath("");
//
//        FileSaver fs = new FileSaver(request, response);
//        fs.saveToFile( "d:\\test.doc");
//        fs.close();
//        //第四段，插入图片    DataRegion body4 = doc.createDataRegion("PO_body4", DataRegionInsertType.After, "PO_body3");    body4.setValue("[image]doc/logo.png[/image]");    //body4.setValue("[word]doc/1.doc[/word]");//还可嵌入其他Word文件    ParagraphFormat bodyPara4 = body4.getParagraphFormat();    bodyPara4.setAlignment(WdParagraphAlignment.wdAlignParagraphCenter);    poCtrl1.setWriter(doc);    poCtrl1.webOpen("doc/template.doc", OpenModeType.docNormalEdit,"张三");
//        return Result.success();
//    }


//    @RequestMapping(value="/word", method=RequestMethod.GET)
//    public String openWord(HttpServletRequest request){
//        //--- PageOffice的调用代码 开始 -----
//        PageOfficeCtrl poCtrl=new PageOfficeCtrl(request);
//        poCtrl.setServerPage("/maven-springmvc/poserver.zz");//设置授权程序
//        poCtrl.addCustomToolButton("保存","Save",1); //添加自定义按钮
//        poCtrl.setSaveFilePage("/save");//设置保存的action
//        poCtrl.webOpen("d:\\test.doc", OpenModeType.docAdmin,"张三");
//        //map.put("pageoffice",poCtrl.getHtmlCode("PageOfficeCtrl1"));
//        //--- PageOffice的调用代码 结束 -----
//
//        return "word";
//    }

//    POI使用
    @PostMapping("/getTestWord")
    Result GeneratingByWord(@RequestBody Paper paper)throws Exception{
         List<String> chapterIdList=paper.getChapterIdList();
         Map<Long,Integer> titleTypeIntegerMap=paper.getTitleTypeIntegerMap();
         return titleService.GeneratingByWord(chapterIdList,titleTypeIntegerMap);
    }

}
