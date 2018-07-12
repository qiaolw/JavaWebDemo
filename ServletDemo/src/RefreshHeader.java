import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "RefreshHeader")
public class RefreshHeader extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        // 设置刷新自动加载时间为 5 秒
        response.setIntHeader("Refresh", 5);
        // 设置响应内容类型
        response.setContentType("text/plain;charset=UTF-8");

        //使用默认时区和语言环境获得一个日历
        Calendar cale = Calendar.getInstance();
        //将Calendar类型转换成Date类型
        Date tasktime = cale.getTime();
        //设置日期输出的格式
        SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        //格式化输出
        String nowTime = df.format(tasktime);
        System.out.println("nowTime " + nowTime);
        PrintWriter out = response.getWriter();

        out.write(nowTime);
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
