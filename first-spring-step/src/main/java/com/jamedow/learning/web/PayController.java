package com.jamedow.learning.web;

import com.alipay.api.AlipayClient;
import com.alipay.api.DefaultAlipayClient;
import com.jamedow.learning.service.UsersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

/**
 * Created by yoyo on 2017/2/15.
 */
@Controller
@RequestMapping("/pay")
public class PayController {

    @Autowired
    private UsersService usersService;


    @RequestMapping
    public void pay() {
        AlipayClient alipayClient = new
                DefaultAlipayClient("https://openapi.alipaydev.com/gateway.do", "2016080200150052", "请复制上一步中生成的密钥中的商户应用私钥", "json", "utf-8",
                "MIIBIjANBgkqhkiG9w0BAQEFAAOCAQ8AMIIBCgKCAQEA0Fx7UMhVLIQHcIGDQeWut35QP+HtqhpoAI1Lzn/VBu38f3z+FkGrxQEuX/cZeIR48pwoXTXnGD1g+ZjjgLwmsRhujmYzhVjGXBDAS3T+LKoBh25UV+/7E6ok4NhqdDo27RNvF0M4Ls6gyPJZP8gyPSe2niLQKlqh4dohFV9Y/W8mZ/dN93yka2E+dRJW6cTGedTZ5hLAhMUkx+WZG4ZkxaJpHrqVtL+/Niv5Mm7MjPqHWAVv/xPJ6R3drSccNWAuD8vCZvysEZMDDH0l4mf4ouk40YO6Klg/tluWrL9On4FkbWj41uxecS35vjyoqeCcoboIA5pEQvluwNuInxtpMQIDAQAB", "RSA2");
    }

}
