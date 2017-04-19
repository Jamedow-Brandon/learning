package com.jamedow.learning.utils.webcollector;

import cn.edu.hfut.dmic.webcollector.model.CrawlDatums;
import cn.edu.hfut.dmic.webcollector.model.Page;
import cn.edu.hfut.dmic.webcollector.plugin.berkeley.BreadthCrawler;
import org.jsoup.nodes.Document;

/**
 * Crawling news from hfut news
 *
 * @author jamedow
 */
public class NewsCrawler extends BreadthCrawler {

    /**
     * @param crawlPath crawlPath is the path of the directory which maintains
     *                  information of this crawler
     * @param autoParse if autoParse is true,BreadthCrawler will auto extract
     *                  links which match regex rules from pag
     */
    public NewsCrawler(String crawlPath, boolean autoParse) {
        super(crawlPath, autoParse);
    /*start page*/
        this.addSeed("http://news.hfut.edu.cn/list-1-1.html");

    /*fetch url like http://news.hfut.edu.cn/show-xxxxxxhtml*/
        this.addRegex("http://news.hfut.edu.cn/show-.*html");
    /*do not fetch jpg|png|gif*/
        this.addRegex("-.*\\.(jpg|png|gif).*");
    /*do not fetch url contains #*/
        this.addRegex("-.*#.*");
    }

    public static void main(String[] args) throws Exception {
        NewsCrawler crawler = new NewsCrawler("crawl", true);
        crawler.setThreads(50);
        crawler.setTopN(100);
        //crawler.setResumable(true);
    /*start crawl with depth of 4*/
        crawler.start(4);
    }

    @Override
    public void visit(Page page, CrawlDatums next) {
        String url = page.getUrl();
    /*if page is news page*/
        if (page.matchUrl("http://space.bilibili.com/*.html")) {
        /*we use jsoup to parse page*/
            Document doc = page.getDoc();

        /*extract title and content of news by css selector*/
            String name = page.select("div[id=h-name]").first().text();
            String imgUrl = page.select("img[id=h-avatar]").first().text();
            String focusCount = page.select("a[class=n-data n-gz]", 0).text();
            String fansCount = page.select("a[class=n-data n-gz]", 0).text();
            String videoCount = page.select("div[class=n-data n-gz]", 0).text();

            System.out.println("URL:\n" + url);
            System.out.println("name:\n" + name);
            System.out.println("imgUrl:\n" + imgUrl);

        /*If you want to add urls to crawl,add them to nextLink*/
        /*WebCollector automatically filters links that have been fetched before*/
        /*If autoParse is true and the link you add to nextLinks does not
          match the regex rules,the link will also been filtered.*/
            //next.add("http://xxxxxx.com");
        }
    }

}