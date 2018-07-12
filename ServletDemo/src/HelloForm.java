import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "HelloForm")
public class HelloForm extends HttpServlet {

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type", "application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String title = "使用 Post 方法读取表单数据";
        // 处理中文
        String name = new String(request.getParameter("name").getBytes("ISO8859-1"),"UTF-8");
        String tUrl = request.getParameter("url");

        System.out.println("HelloForm doPost name " + name + " url " + tUrl);

        Map<String, String> result = new HashMap<>();
        result.put("title", title);
        result.put("name", name);
        result.put("url", tUrl);
        result.put("code", "0");

        Gson gs = new Gson();
        String resultJsonStr = gs.toJson(result);
        System.out.println("resultJson " + resultJsonStr);

        out.write(resultJsonStr);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setContentType("text/html;charset=UTF-8");
        PrintWriter out = response.getWriter();
        String title = "使用 GET 方法读取表单数据";
        // 处理中文
        String name =new String(request.getParameter("name").getBytes("ISO8859-1"),"UTF-8");
        String docType = "<!DOCTYPE html> \n";
        out.println(docType +
                "<html>\n" +
                "<head><title>" + title + "</title></head>\n" +
                "<body bgcolor=\"#f0f0f0\">\n" +
                "<h1 align=\"center\">" + title + "</h1>\n" +
                "<ul>\n" +
                "  <li><b>站点名</b>："
                + name + "\n" +
                "  <li><b>网址</b>："
                + request.getParameter("url") + "\n" +
                "</ul>\n" +
                "</body></html>");
    }
}
