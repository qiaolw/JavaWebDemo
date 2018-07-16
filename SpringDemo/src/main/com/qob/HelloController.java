package com.qob;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

@Controller
@RequestMapping("/hi")
public class HelloController {
    @RequestMapping(value = "/say", method = RequestMethod.GET)
    public void printHello(HttpServletResponse response) {
        System.out.println("printHello");
        response.setHeader("content-type", "application/json;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.write("OK");
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
   //     return "hello";
    }

    @RequestMapping("/getUserInfo")
    public void getUserInfo(HttpServletRequest request,
                            HttpServletResponse response){
        System.out.println("getUserInfo");
        response.setHeader("content-type", "application/json;charset=UTF-8");
        try {
            PrintWriter out = response.getWriter();
            out.write("0");
            out.flush();
            out.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
