package com.jamedow.learning.utils.webcollector;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import com.jamedow.learning.utils.rabbitmq.RabbitMQUtils;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;
import org.springframework.beans.factory.annotation.Autowired;

import java.net.URLEncoder;

/**
 * Crawling news from hfut news
 *
 * @author jamedow
 */
public class BingCrawler extends BreadthCrawler {

    private String VIRTUAL_HOST = "webcollector";
    private String QUEUE = "queue";

    @Autowired
    public BingCrawler(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
    }

//    @Override
//    protected void afterParse(Page page, CrawlDatums next) {
//        //当前页面的depth为x，则从当前页面解析的后续任务的depth为x+1
//        int depth;
//        //如果在添加种子时忘记添加depth信息，可以通过这种方式保证程序不出错
//        if (page.meta("depth") == null) {
//            depth = 1;
//        } else {
//            depth = Integer.valueOf(page.meta("depth"));
//        }
//        depth++;
//        for (CrawlDatum datum : next) {
//            datum.meta("depth", depth + "");
//        }
//    }

    /**
     * 根据关键词和页号拼接Bing搜索对应的URL
     */
    public static String createBingUrl(String keyword, int pageNum) throws Exception {
        int first = pageNum * 10 - 9;
        keyword = URLEncoder.encode(keyword, "utf-8");
        return String.format("http://cn.bing.com/search?q=%s&first=%s", keyword, first);
    }

    @Override
    public void visit(Page page, CrawlDatums next) {

        String keyword = page.getMetaData("keyword");
        String pageType = page.getMetaData("pageType");
        int depth = Integer.valueOf(page.getMetaData("depth"));
        if (pageType.equals("searchEngine")) {
            int pageNum = Integer.valueOf(page.getMetaData("pageNum"));
            System.out.println("成功抓取关键词" + keyword + "的第" + pageNum + "页搜索结果");
            Elements results = page.select("li.b_ans h2>a,li.b_algo h2>a");
            for (int rank = 0; rank < results.size(); rank++) {
                Element result = results.get(rank);

                //向消息队列推送消息
                RabbitMQUtils.sendMessage(VIRTUAL_HOST, QUEUE, result.attr("abs:href"));
            }

        } else if (pageType.equals("outlink")) {
            int pageNum = Integer.valueOf(page.getMetaData("pageNum"));
            int rank = Integer.valueOf(page.getMetaData("rank"));
            String refer = page.getMetaData("refer");

            String line = String.format("第%s页第%s个结果:%s(%s字节)\tdepth=%s\trefer=%s",
                    pageNum, rank + 1, page.getDoc().title(), page.getContent().length, depth, refer);
            System.out.println(line);

            try {
                CompanyCrawler crawler = new CompanyCrawler("crawler", false);
                crawler.addSeed(refer);

                crawler.setThreads(1);
                crawler.start(1);
            } catch (Exception e) {
                e.printStackTrace();
            }


        }
    }
}