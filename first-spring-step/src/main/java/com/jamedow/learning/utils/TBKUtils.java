package com.jamedow.learning.utils;

import com.jamedow.learning.utils.category.ItemcatsGetRequest;
import com.jamedow.learning.utils.category.ItemcatsGetResponse;
import com.jamedow.learning.utils.tbk.TbkItemDetailGetRequest;
import com.jamedow.learning.utils.tbk.TbkItemDetailGetResponse;
import com.jamedow.learning.utils.tbk.TbkItemGetRequest;
import com.jamedow.learning.utils.tbk.TbkItemGetResponse;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Date;

/**
 * Created by 365 on 2016/12/19 0019.
 */
public class TBKUtils {

    private static Logger logger = LoggerFactory.getLogger(TBKUtils.class);

    private static String url = "http://gw.api.tbsandbox.com/router/rest";

    private static String appkey = "1023456633";

    private static String secret = "sandboxde69ac9a8e65921ff87f92f9b";


    public static void getProductDetail() {
        try {
            TbkItemDetailGetRequest req = new TbkItemDetailGetRequest();
            req.setFields("num_iid,title,pict_url,small_images,reserve_price,zk_final_price,user_type,provcity,description,item_click_url,shop_click_url");
            req.setNumIids("520824671763,533161287181");
            req.setPlatform(1L);
            req.setAdzoneId(123L);
//            req.setUnid("demo");

            TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
            TbkItemDetailGetResponse rsp = (TbkItemDetailGetResponse) client.execute(req);
            System.out.println(rsp.getBody());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static void getProducts() {
        try {
            TbkItemGetRequest req = new TbkItemGetRequest();
            req.setFields("num_iid,title,pict_url,small_images,reserve_price,zk_final_price,user_type,provcity,item_url,seller_id,volume,nick");
            req.setQ("零食");
            req.setCat("50008613,50008058,50009832,50009556,50008059,50010535,50008063,50009898,50003874");
            req.setItemloc("杭州");
            req.setSort("tk_rate_des");
            req.setTmall(false);
            req.setOverseas(false);
            req.setStartPrice(10L);
            req.setEndPrice(10L);
            req.setStartTkRate(123L);
            req.setEndTkRate(123L);
            req.setPlatform(1L);
            req.setPageNo(123L);
            req.setPageSize(20L);

            req.setTimestamp((new Date()).getTime());

            TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
            TbkItemGetResponse rsp = (TbkItemGetResponse) client.execute(req);
            System.out.println(rsp.getBody());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static void getCategories() {
        try {
            ItemcatsGetRequest req = new ItemcatsGetRequest();
            req.setCids("50008613,50008058,50009832,50009556,50008059,50010535,50008063,50009898,50003874");
            req.setFields("cid,parent_cid,name,is_parent");
            //食品\茶叶\零食\特产：50002766
            req.setParentCid(50002766L);
            req.setTimestamp((new Date()).getTime());

            TaobaoClient client = new DefaultTaobaoClient(url, appkey, secret);
            ItemcatsGetResponse rsp = (ItemcatsGetResponse) client.execute(req);
            System.out.println(rsp.getBody());
        } catch (Exception e) {
            logger.error(e.getMessage(), e);
        }
    }

    public static void main(String[] args) {
//        getProducts();
//        getCategories();
        getProductDetail();
    }
}
