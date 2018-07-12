import com.google.gson.Gson;
import com.mysql.jdbc.Connection;
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

@WebServlet(name = "HelloUserList")
public class HelloUserList extends HttpServlet {
    // JDBC 驱动名及数据库 URL
    static final String JDBC_DRIVER = "com.mysql.jdbc.Driver";
    static final String DB_URL = "jdbc:mysql://localhost:3306/qobtestdb";

    // 数据库的用户名与密码，需要根据自己的设置
    static final String USER = "root";
    static final String PASS = "123456";

    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        Connection conn = null;
        Statement stmt = null;

        response.setHeader("content-type", "application/json;charset=UTF-8");
        try {
            Class.forName(JDBC_DRIVER);
            conn = (Connection) DriverManager.getConnection(DB_URL, USER, PASS);
            stmt = (Statement) conn.createStatement();
            String sqlStr = "SELECT * FROM qob_userinfo";
            ResultSet rs = stmt.executeQuery(sqlStr);
            ArrayList tUsrList = new ArrayList();
            // 展开结果集数据库
            while(rs.next()){
                Map<String, Object> tUserMap = new HashMap<>();
                // 通过字段检索
                int id  = rs.getInt("userId");
                String name = rs.getString("userName");
                int age = rs.getInt("userAge");
                int sex = rs.getInt("userSex");

                tUserMap.put("userId", id);
                tUserMap.put("userName", name);
                tUserMap.put("userAge", age);
                tUserMap.put("userSex", sex);

                tUsrList.add(tUserMap);
            }

            rs.close();
            stmt.close();
            conn.close();

            Gson gs = new Gson();
            String resultJsonStr = gs.toJson(tUsrList);
            PrintWriter out = response.getWriter();
            out.write(resultJsonStr);
            out.flush();
            out.close();

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
    }
}
