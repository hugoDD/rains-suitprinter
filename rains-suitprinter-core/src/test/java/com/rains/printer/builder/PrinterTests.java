package com.rains.printer.builder;

import com.rains.printer.*;
import org.apache.avalon.framework.configuration.DefaultConfigurationBuilder;
import org.hswebframework.printer.*;
import com.rains.printer.executor.DefaultPrintable;
import com.rains.printer.layer.TextLayer;
import com.rains.utils.file.FileUtils;
import org.junit.Before;
import org.junit.Test;

import javax.imageio.ImageIO;
import javax.print.*;
import javax.print.attribute.HashPrintRequestAttributeSet;
import javax.print.attribute.standard.MediaPrintableArea;
import javax.print.attribute.standard.MediaSizeName;
import javax.print.attribute.standard.PrintQuality;
import java.awt.*;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.List;

import static org.junit.Assert.*;

/**
 * @author zhouhao
 * @since 2.0
 */
public class PrinterTests {
    static String json = "{\"layers\":[{\"type\":\"rect\",\"rp\":\"rp72\",\"x\":20,\"y\":78,\"width\":559,\"height\":181,\"fill\":\"rgba(0,0,0,0)\",\"color\":\"#ff0000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\"},{\"type\":\"line\",\"rp\":\"rp72\",\"x1\":20,\"y1\":100,\"x2\":580,\"y2\":100,\"color\":\"#ff0000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\"},{\"type\":\"line\",\"rp\":\"rp72\",\"x1\":20,\"y1\":120,\"x2\":579,\"y2\":120,\"color\":\"#ff0000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\"},{\"type\":\"line\",\"rp\":\"rp72\",\"x1\":81,\"y1\":140,\"x2\":299,\"y2\":140,\"color\":\"#ff0000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\"},{\"type\":\"line\",\"rp\":\"rp72\",\"x1\":138,\"y1\":78,\"x2\":138,\"y2\":200,\"color\":\"#ff0000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\"},{\"type\":\"line\",\"rp\":\"rp72\",\"x1\":300,\"y1\":77,\"x2\":300,\"y2\":179,\"color\":\"#ff0000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\"},{\"type\":\"line\",\"rp\":\"rp72\",\"x1\":419,\"y1\":79,\"x2\":419,\"y2\":179,\"color\":\"#ff0000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\"},{\"type\":\"line\",\"rp\":\"rp72\",\"x1\":82,\"y1\":160,\"x2\":299,\"y2\":160,\"color\":\"#ff0000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\"},{\"type\":\"line\",\"rp\":\"rp72\",\"x1\":20,\"y1\":180,\"x2\":580,\"y2\":180,\"color\":\"#ff0000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\"},{\"type\":\"line\",\"rp\":\"rp72\",\"x1\":362,\"y1\":140,\"x2\":580,\"y2\":140,\"color\":\"#ff0000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\"},{\"type\":\"line\",\"rp\":\"rp72\",\"x1\":362,\"y1\":160,\"x2\":579,\"y2\":160,\"color\":\"#ff0000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\"},{\"type\":\"line\",\"rp\":\"rp72\",\"x1\":20,\"y1\":200,\"x2\":581,\"y2\":200,\"color\":\"#ff0000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\"},{\"type\":\"line\",\"rp\":\"rp72\",\"x1\":299,\"y1\":201,\"x2\":299,\"y2\":259,\"color\":\"#ff0000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\"},{\"type\":\"line\",\"rp\":\"rp72\",\"x1\":81,\"y1\":121,\"x2\":81,\"y2\":181,\"color\":\"#ff0000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\"},{\"type\":\"line\",\"rp\":\"rp72\",\"x1\":361,\"y1\":120,\"x2\":361,\"y2\":180,\"color\":\"#ff0000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\"},{\"fontSize\":22,\"type\":\"text\",\"rp\":\"rp72\",\"x\":202,\"y\":13,\"originalY\":7.40625,\"text\":\"转账交易电子回单\",\"fontFamily\":\"仿宋_GB2312\",\"fill\":\"#000000\",\"color\":\"#000000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\",\"position\":\"left\",\"maxWidth\":\"\"},{\"fontSize\":14,\"type\":\"text\",\"rp\":\"rp72\",\"x\":15,\"y\":54.796875,\"originalY\":51.609375,\"text\":\"入账日期:20180315\",\"fontFamily\":\"仿宋_GB2312\",\"fill\":\"#000000\",\"color\":\"#000000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\",\"position\":\"left\",\"maxWidth\":\"\"},{\"fontSize\":18,\"type\":\"text\",\"rp\":\"rp72\",\"x\":35,\"y\":77,\"originalY\":72.609375,\"text\":\"电子回单号\",\"fontFamily\":\"仿宋_GB2312\",\"fill\":\"#000000\",\"color\":\"#000000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\",\"position\":\"left\",\"maxWidth\":\"\"},{\"fontSize\":18,\"type\":\"text\",\"rp\":\"rp72\",\"x\":23,\"y\":137,\"originalY\":132.609375,\"text\":\"付款人\",\"fontFamily\":\"仿宋_GB2312\",\"fill\":\"#000000\",\"color\":\"#000000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\",\"position\":\"left\",\"maxWidth\":\"\"},{\"fontSize\":18,\"type\":\"text\",\"rp\":\"rp72\",\"x\":304,\"y\":136,\"originalY\":131.609375,\"text\":\"收款人\",\"fontFamily\":\"仿宋_GB2312\",\"fill\":\"#000000\",\"color\":\"#000000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\",\"position\":\"left\",\"maxWidth\":\"\"},{\"fontSize\":18,\"type\":\"text\",\"rp\":\"rp72\",\"x\":142,\"y\":77,\"originalY\":72.609375,\"text\":\"张三\",\"fontFamily\":\"仿宋_GB2312\",\"fill\":\"#000000\",\"color\":\"#000000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\",\"position\":\"left\",\"maxWidth\":\"\"},{\"fontSize\":18,\"type\":\"text\",\"rp\":\"rp72\",\"x\":316,\"y\":77,\"originalY\":72.609375,\"text\":\"交易类型\",\"fontFamily\":\"仿宋_GB2312\",\"fill\":\"#000000\",\"color\":\"#000000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\",\"position\":\"left\",\"maxWidth\":\"\"},{\"fontSize\":18,\"type\":\"text\",\"rp\":\"rp72\",\"x\":423,\"y\":76,\"originalY\":71.609375,\"text\":\"测试\",\"fontFamily\":\"仿宋_GB2312\",\"fill\":\"#000000\",\"color\":\"#000000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\",\"position\":\"left\",\"maxWidth\":\"\"},{\"fontSize\":18,\"type\":\"text\",\"rp\":\"rp72\",\"x\":37,\"y\":96.75,\"originalY\":92.359375,\"text\":\"交易流水\",\"fontFamily\":\"仿宋_GB2312\",\"fill\":\"#000000\",\"color\":\"#000000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\",\"position\":\"left\",\"maxWidth\":\"\"},{\"fontSize\":18,\"type\":\"text\",\"rp\":\"rp72\",\"x\":319,\"y\":97,\"originalY\":92.609375,\"text\":\"交易渠道\",\"fontFamily\":\"仿宋_GB2312\",\"fill\":\"#000000\",\"color\":\"#000000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\",\"position\":\"left\",\"maxWidth\":\"\"},{\"fontSize\":18,\"type\":\"text\",\"rp\":\"rp72\",\"x\":82,\"y\":118,\"originalY\":113.609375,\"text\":\"全称\",\"fontFamily\":\"仿宋_GB2312\",\"fill\":\"#000000\",\"color\":\"#000000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\",\"position\":\"left\",\"maxWidth\":\"\"},{\"fontSize\":18,\"type\":\"text\",\"rp\":\"rp72\",\"x\":82,\"y\":138,\"originalY\":133.609375,\"text\":\"帐号\",\"fontFamily\":\"仿宋_GB2312\",\"fill\":\"#000000\",\"color\":\"#000000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\",\"position\":\"left\",\"maxWidth\":\"\"},{\"fontSize\":18,\"type\":\"text\",\"rp\":\"rp72\",\"x\":82,\"y\":158,\"originalY\":153.609375,\"text\":\"开户行\",\"fontFamily\":\"仿宋_GB2312\",\"fill\":\"#000000\",\"color\":\"#000000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\",\"position\":\"left\",\"maxWidth\":\"\"},{\"fontSize\":18,\"type\":\"text\",\"rp\":\"rp72\",\"x\":362,\"y\":117,\"originalY\":112.609375,\"text\":\"全称\",\"fontFamily\":\"仿宋_GB2312\",\"fill\":\"#000000\",\"color\":\"#000000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\",\"position\":\"left\",\"maxWidth\":\"\"},{\"fontSize\":18,\"type\":\"text\",\"rp\":\"rp72\",\"x\":361,\"y\":138,\"originalY\":133.609375,\"text\":\"帐号\",\"fontFamily\":\"仿宋_GB2312\",\"fill\":\"#000000\",\"color\":\"#000000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\",\"position\":\"left\",\"maxWidth\":\"\"},{\"fontSize\":18,\"type\":\"text\",\"rp\":\"rp72\",\"x\":364,\"y\":158,\"originalY\":153.609375,\"text\":\"开户行\",\"fontFamily\":\"仿宋_GB2312\",\"fill\":\"#000000\",\"color\":\"#000000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\",\"position\":\"left\",\"maxWidth\":\"\"},{\"fontSize\":18,\"type\":\"text\",\"rp\":\"rp72\",\"x\":40,\"y\":178,\"originalY\":173.609375,\"text\":\"交易金额\",\"fontFamily\":\"仿宋_GB2312\",\"fill\":\"#000000\",\"color\":\"#000000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\",\"position\":\"left\",\"maxWidth\":\"\"},{\"fontSize\":18,\"type\":\"text\",\"rp\":\"rp72\",\"x\":144,\"y\":178,\"originalY\":173.609375,\"text\":\"大写：\",\"fontFamily\":\"仿宋_GB2312\",\"fill\":\"#000000\",\"color\":\"#000000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\",\"position\":\"left\",\"maxWidth\":\"\"},{\"fontSize\":18,\"type\":\"text\",\"rp\":\"rp72\",\"x\":378,\"y\":179,\"originalY\":174.609375,\"text\":\"小写：\",\"fontFamily\":\"仿宋_GB2312\",\"fill\":\"#000000\",\"color\":\"#000000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\",\"position\":\"left\",\"maxWidth\":\"\"},{\"fontSize\":13,\"type\":\"text\",\"rp\":\"rp72\",\"x\":21,\"y\":199,\"originalY\":196.109375,\"text\":\"摘要\",\"fontFamily\":\"仿宋_GB2312\",\"fill\":\"#000000\",\"color\":\"#000000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\",\"position\":\"left\",\"maxWidth\":\"\"},{\"fontSize\":13,\"type\":\"text\",\"rp\":\"rp72\",\"x\":21,\"y\":214,\"originalY\":211.109375,\"text\":\"附言\",\"fontFamily\":\"仿宋_GB2312\",\"fill\":\"#000000\",\"color\":\"#000000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\",\"position\":\"left\",\"maxWidth\":\"\"},{\"fontSize\":13,\"type\":\"text\",\"rp\":\"rp72\",\"x\":21,\"y\":229,\"originalY\":226.109375,\"text\":\"其他信息\",\"fontFamily\":\"仿宋_GB2312\",\"fill\":\"#000000\",\"color\":\"#000000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\",\"position\":\"left\",\"maxWidth\":\"\"},{\"fontSize\":15,\"type\":\"text\",\"rp\":\"rp72\",\"x\":23,\"y\":260.3125,\"originalY\":256.8125,\"text\":\"打印机构：\",\"fontFamily\":\"仿宋_GB2312\",\"fill\":\"#000000\",\"color\":\"#000000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\",\"position\":\"left\",\"maxWidth\":\"\"},{\"fontSize\":15,\"type\":\"text\",\"rp\":\"rp72\",\"x\":239,\"y\":259,\"originalY\":255.5,\"text\":\"打印次数：\",\"fontFamily\":\"仿宋_GB2312\",\"fill\":\"#000000\",\"color\":\"#000000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\",\"position\":\"left\",\"maxWidth\":\"\"},{\"fontSize\":15,\"type\":\"text\",\"rp\":\"rp72\",\"x\":380,\"y\":259,\"originalY\":255.5,\"text\":\"打印时间：\",\"fontFamily\":\"仿宋_GB2312\",\"fill\":\"#000000\",\"color\":\"#000000\",\"strokeWidth\":\"1\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\",\"position\":\"left\",\"maxWidth\":\"\"},{\"type\":\"img\",\"rp\":\"rp72\",\"x\":510,\"y\":2,\"width\":79,\"height\":61,\"fill\":\"rgba(184,184,184,1)\",\"imgData\":\"http://www.hsweb.me\",\"imgType\":\"qrCode\",\"loopDirection\":\"\",\"loopSpacing\":0,\"loopData\":\"\",\"loopNum\":\"\",\"visible\":\"\"}]}";

    static JsonPageBuilder builder = new JsonPageBuilder();


    @Before
    public void init(){
        new File("target").mkdir();
    }
    @Test
    public void testAutoNewLine() throws Exception {
        Pager pager=new Pager();
        TextLayer layer=new TextLayer();
        layer.setX(0);
        layer.setY(200);
        layer.setColor(Color.RED);
        layer.setWidth(100);
        layer.setText("打印\n打印吧\n打印文字\n打印文字吧\n打印1a2B3c4D★☆符号打印");
        layer.setAlign(TextLayer.Align.both);


        TextLayer layer1=new TextLayer();
        layer1.setX(120);
        layer1.setY(200);
        layer1.setColor(Color.BLUE);
        layer1.setWidth(100);
        layer1.setHeight(200);
        layer1.setVerticalAlign(TextLayer.VerticalAlign.top);
        layer1.setText("打印\n打印吧\n打印文字\n打印文字吧\n打印1a2B3c4D★☆符号打印");
        layer1.setAlign(TextLayer.Align.left);


        TextLayer layer2=new TextLayer();
        layer2.setX(240);
        layer2.setY(200);
        layer2.setColor(Color.BLACK);
        layer2.setHeight(200);
        layer2.setVerticalAlign(TextLayer.VerticalAlign.center);
        layer2.setWidth(100);
        layer2.setText("打印\n打印吧\n打印文字\n打印文字吧\n打印1a2B3c4D★☆符号打印");
        layer2.setAlign(TextLayer.Align.right);

        TextLayer layer3=new TextLayer();
        layer3.setX(360);
        layer3.setY(200);
        layer3.setHeight(200);
        layer3.setVerticalAlign(TextLayer.VerticalAlign.bottom);
        layer3.setColor(Color.GREEN);
        layer3.setWidth(100);
        layer3.setText("打印\n打印吧\n打印文字\n打印文字吧\n打印1a2B3c4D★☆符号打印");
        layer3.setAlign(TextLayer.Align.center);

        layer3.setFont(new Font("宋体",Font.PLAIN,20));

        pager.setLayers(Arrays.asList(layer,layer1,layer2,layer3));
        pager.setOrientation(0);
        List<String> svgs = PrinterUtils.printToSvg(Arrays.asList(pager));

        System.out.println(svgs.get(0));

        PrinterUtils
                .printToPdf(Arrays.asList(pager)
                        , new PixelPaper(72, Paper.A4)
                        , new FileOutputStream("./target/test.pdf")
                        , new DefaultConfigurationBuilder().build("./config/fop-configuration.xml"));


    }

    @Test
    public void testParse() {
        List<Pager> pagers = builder.build(json);
        assertNotNull(pagers);
        assertFalse(pagers.isEmpty());
        assertFalse(pagers.get(0).getLayers().isEmpty());
    }

    @Test
    public void testParseForImg() throws IOException {
        String json =FileUtils.reader2String("E:\\u\\git\\print\\hsweb-printer\\hsweb-printer-core\\src\\test\\1546873983945.rains");
        List<Pager> pagers = builder.build(json);
//        TextLayer layer=new TextLayer();
//        layer.setX(110);
//        layer.setY(197);
//        layer.setColor(Color.RED);
//        layer.setWidth(80);
//        layer.setHeight(18);
//        layer.setText("打印1");
//        layer.setAlign(TextLayer.Align.center);
//
//        TextLayer layer1=new TextLayer();
//        layer1.setX(110);
//        layer1.setY(242);
//        layer1.setColor(Color.RED);
//        layer1.setWidth(80);
//        layer1.setHeight(18);
//        layer1.setText("打印2");
//        layer1.setAlign(TextLayer.Align.center);
//
//
//        TextLayer layer2=new TextLayer();
//        layer2.setX(335);
//        layer2.setY(197);
//        layer2.setColor(Color.RED);
//        layer2.setWidth(80);
//        layer2.setHeight(18);
//        layer2.setText("打印3");
//        layer2.setAlign(TextLayer.Align.center);
//
//        TextLayer layer3=new TextLayer();
//        layer3.setX(335);
//        layer3.setY(242);
//        layer3.setColor(Color.RED);
//        layer3.setWidth(80);
//        layer3.setHeight(18);
//        layer3.setText("打印4");
//        layer3.setAlign(TextLayer.Align.center);
//
//
//        List<Pager> pagers = new ArrayList<>();
//        Pager pager = new Pager();
//        pager.setBaseImage("D:\\2\\timg1.jpg");
//        pager.setOrientation(180);
//        pager.setLayers(Arrays.asList(layer,layer1,layer2,layer3));
//
//        pagers.add(pager);

        pagers.stream().forEach(c->c.setBaseImage("D:\\2\\timg1.jpg"));



        BufferedImage image= PrinterUtils
                .printToImage(pagers
                        ,new PixelPaper(1024,500));
        ImageIO.write(image,"png",new FileOutputStream("./target/test1.png"));
    }

    @Test
    public void testPrintSvg() {
        List<Pager> pagers = builder.build(json);
        List<String> svgs = PrinterUtils.printToSvg(pagers);
        assertFalse(svgs.isEmpty());
        System.out.println(svgs.get(0));
    }

    @Test
    public void testPrintImage() throws Exception {
        new File("./target").mkdirs();
        List<Pager> pagers = builder.build(json);

       BufferedImage image= PrinterUtils
                .printToImage(pagers
                        , new PixelPaper(72, Paper.A4));
        ImageIO.write(image,"png",new FileOutputStream("./target/test.png"));

        assertTrue(new File("./target/test.png").exists());
        assertTrue(new File("./target/test.png").length() > 0);
    }
    @Test
    public void testPrintPdf() throws Exception {
        new File("./target").mkdirs();
        List<Pager> pagers = builder.build(json);
        PrinterUtils
                .printToPdf(pagers
                        , new PixelPaper(72, Paper.A4)
                        , new FileOutputStream("./target/test.pdf")
                        , new DefaultConfigurationBuilder().build("./config/fop-configuration.xml"));
        assertTrue(new File("./target/test.pdf").exists());
        assertTrue(new File("./target/test.pdf").length() > 0);
    }

    public static void main(String[] args) throws PrintException {
        DocFlavor flavor = DocFlavor.SERVICE_FORMATTED.PRINTABLE;
        HashPrintRequestAttributeSet pras = new HashPrintRequestAttributeSet();

        //获取打印服务对象
        PrintService printServices[] = PrintServiceLookup.lookupPrintServices(flavor, pras);
        PrintService defaultService = PrintServiceLookup.lookupDefaultPrintService();
        //弹出提示,选择打印机
        PrintService printService = ServiceUI.printDialog(null, 200, 200, printServices,
                defaultService, flavor, pras);
        if (printService == null) return;

        List<Pager> pagers = builder.build(json);
        DocPrintJob job = printService.createPrintJob();
        // pras.add(new PrinterName("test", Locale.getDefault()));
        pras.add(PrintQuality.HIGH);
        pras.add(MediaSizeName.ISO_A4);
        //pras.add(OrientationRequested.LANDSCAPE);
        PrintCommand command = new PrintCommand();
        command.setPagers(pagers);
        command.setPaper(new PixelPaper(72, Paper.A4));

        DefaultPrintable defaultPrintable = new DefaultPrintable(command);
        pras.add(new MediaPrintableArea(20, 20, Paper.A4.getWidth(), Paper.A4.getHeight(), MediaPrintableArea.MM));
        Doc doc = new SimpleDoc(defaultPrintable, flavor, null);
        job.print(doc, pras);

    }
}