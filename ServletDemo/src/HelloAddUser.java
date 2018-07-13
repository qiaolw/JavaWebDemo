import com.google.gson.Gson;
import com.mysql.jdbc.Connection;
import com.mysql.jdbc.PreparedStatement;
import com.mysql.jdbc.Statement;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

@WebServlet(name = "HelloAddUser")
public class HelloAddUser extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        // 处理中文
        String tName = request.getParameter("name");
        String tId = request.getParameter("id");
        String tAge = request.getParameter("age");
        String tSex = request.getParameter("sex");

        System.out.println(tName + " " + tId + " " + tAge + " " + tSex);

        Connection conn = null;
        PreparedStatement stmt = null;
        response.setHeader("content-type", "application/json;charset=UTF-8");

        try {
            Class.forName(HelloUserList.JDBC_DRIVER);
            conn = (Connection) DriverManager.getConnection(HelloUserList.DB_URL, HelloUserList.USER, HelloUserList.PASS);
            String sqlStr = "INSERT INTO qob_userinfo (userId, userName, userAge, userSex) VALUES(?,?,?,?)";
            stmt = (PreparedStatement) conn.prepareStatement(sqlStr);
            stmt.setInt(1, Integer.parseInt(tId));
            stmt.setString(2, tName);
            stmt.setInt(3, Integer.parseInt(tAge));
            stmt.setInt(4, Integer.parseInt(tSex));

            //INSERT INTO qob_userinfo (userId, userName, userAge, userSex) VALUES (100,'aaaa',50,1);
            System.out.println("sqlStr " + sqlStr);
            stmt.executeUpdate();

            stmt.close();
            conn.close();
        }catch (Exception ex){
            ex.printStackTrace();
        }finally{
            // 最后是用于关闭资源的块
            try{
                if(stmt!=null)
                    stmt.close();
            }catch(SQLException se2){
            }
            try{
                if(conn!=null)
                    conn.close();
            }catch(SQLException se){
                se.printStackTrace();
            }
        }
        out.write("0");
        out.flush();
        out.close();
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }
}
