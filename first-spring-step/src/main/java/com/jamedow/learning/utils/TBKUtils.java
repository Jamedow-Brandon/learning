package com.jamedow.learning.utils;

import com.jamedow.learning.utils.tbk.TbkItemGetRequest;
import com.jamedow.learning.utils.tbk.TbkItemGetResponse;
import com.taobao.api.DefaultTaobaoClient;
import com.taobao.api.TaobaoClient;

import java.util.Date;

/**
 * Created by 365 on 2016/12/19 0019.
 */
public class TBKUtils {

    private static String url = "http://gw.api.tbsandbox.com/router/rest";

    private static String appkey = "1023456633";

    private static String secret = "sandboxde69ac9a8e65921ff87f92f9b";

    public static void execute() {
        try {
            TbkItemGetRequest req = new TbkItemGetRequest();
            req.setFields("num_iid,title,pict_url,small_images,reserve_price,zk_final_price,user_type,provcity,item_url,seller_id,volume,nick");
            req.setQ("女装");
            req.setCat("16,18");
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
            System.out.println("error");
        }
    }

    public static void main(String[] args) {
        execute();
    }
}
