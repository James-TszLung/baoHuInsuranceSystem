package com.xiaobaozi.commons.lisener;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;

/**
 * Created by Kowalski on 2017/11/9
 * Updated by Kowalski on 2017/11/9
 */
@Controller
public class TestController {

    @RequestMapping("/hello")
    @ResponseBody
    public String hello(){
        return "Hello 小瓶子!";
    }
}
