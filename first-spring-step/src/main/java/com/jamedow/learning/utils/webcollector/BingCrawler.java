package com.jamedow.learning.utils.webcollector;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatum;
import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import org.jsoup.nodes.Element;
import org.jsoup.select.Elements;

import java.net.URLEncoder;

/**
 * Crawling news from hfut news
 *
 * @author jamedow
 */
public class BingCrawler extends BreadthCrawler {

    public BingCrawler(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
    }

    public static void main(String[] args) throws Exception {
        String keyword = "公司";
        int maxPageNum = 3;
        BingCrawler crawler = new BingCrawler("depth_crawler", false);
        for (int pageNum = 1; pageNum <= maxPageNum; pageNum++) {
            String url = createBingUrl(keyword, pageNum);
            crawler.addSeed(new CrawlDatum(url)
                    .putMetaData("keyword", keyword)
                    .putMetaData("pageNum", pageNum + "")
                    .putMetaData("pageType", "searchEngine")
                    .putMetaData("depth", "1"));
        }

        crawler.start(maxPageNum);
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

                /*
                我们希望继续爬取每条搜索结果指向的网页，这里统称为外链。
                我们希望在访问外链时仍然能够知道外链处于搜索引擎的第几页、第几条，
                所以将页号和排序信息放入后续的CrawlDatum中，为了能够区分外链和
                搜索引擎结果页面，我们将其pageType设置为outlink，这里的值完全由
                用户定义，可以设置一个任意的值

                在经典爬虫中，每个网页都有一个refer信息，表示当前网页的链接来源。
                例如我们首先访问新浪首页，然后从新浪首页中解析出了新的新闻链接，
                则这些网页的refer值都是新浪首页。WebCollector不直接保存refer值，
                但我们可以通过下面的方式，将refer信息保存在metaData中，达到同样的效果。
                经典爬虫中锚文本的存储也可以通过下面方式实现。

                在一些需求中，希望得到当前页面在遍历树中的深度，利用metaData很容易实现
                这个功能，在将CrawlDatum添加到next中时，将其depth设置为当前访问页面
                的depth+1即可。
                */
                CrawlDatum datum = new CrawlDatum(result.attr("abs:href"))
                        .putMetaData("keyword", keyword)
                        .putMetaData("pageNum", pageNum + "")
                        .putMetaData("rank", rank + "")
                        .putMetaData("pageType", "outlink")
                        .putMetaData("depth", (depth + 1) + "")
                        .putMetaData("refer", page.getUrl());
                next.add(datum);
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