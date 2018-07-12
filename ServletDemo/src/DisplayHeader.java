import com.google.gson.Gson;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.Enumeration;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "DisplayHeader")
public class DisplayHeader extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        response.setHeader("content-type", "application/json;charset=UTF-8");
        PrintWriter out = response.getWriter();
        System.out.println("DisplayHeader doGet");

        Map<String, String> result = new HashMap<>();

        Enumeration headerNames = request.getHeaderNames();
        while(headerNames.hasMoreElements()) {
            String paramName = (String)headerNames.nextElement();
            String paramValue = request.getHeader(paramName);

            result.put(paramName, paramValue);
        }

        result.put("code", "0");

        Gson gs = new Gson();
        String resultJsonStr = gs.toJson(result);

        out.write(resultJsonStr);
        out.flush();
        out.close();
    }
}
