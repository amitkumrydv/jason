package JasonToJava.jason;

import java.io.File;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.codehaus.jackson.JsonGenerationException;
import org.codehaus.jackson.map.JsonMappingException;
import org.codehaus.jackson.map.ObjectMapper;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;

import com.google.gson.Gson;
import com.mysql.cj.jdbc.Driver;

/**
 * Hello world!
 *
 */
public class App 
{
    public static void main( String[] args ) throws ClassNotFoundException, SQLException, JsonGenerationException, JsonMappingException, IOException
    {
        
    	System.out.println("started ");
    	Class.forName("com.mysql.cj.jdbc.Driver");
   
    	System.out.println("started sqlDriver");
    	Connection conn=null;
    	ArrayList<CustomerInfo> a =new  ArrayList<CustomerInfo>();
    	JSONArray js= new JSONArray();

    	conn =DriverManager.getConnection("jdbc:mysql://localhost:3306/Business","root","admin");  
    	System.out.println("started sqlDriver connection");
    	
    	Statement st=conn.createStatement();
    	
    	ResultSet rs=st.executeQuery
    			("select * from CustomerInfo where purchasedDate=CURDATE() and Location ='Asia';");
    	
    	System.out.println("run sql quer");
    	
    	while (rs.next()) {
    		
    		System.out.println("test in while loop "+rs.getString(1));
        	CustomerInfo c = new CustomerInfo();
    		c.setCourseName(rs.getString(1));
    		c.setPurchasedDate(rs.getString(2));
    		c.setAmount(rs.getInt(3));
    		c.setLocation(rs.getString(4));
    		a.add(c);
            
    		//System.out.println("test "+rs.getCursorName());
    		
//    		System.out.println(rs.getString(1));
//    		System.out.println(rs.getString(2));
//    		System.out.println(rs.getInt(3));
//    		System.out.println(rs.getString(4));
    	}System.out.println("while loop close");
//    	
    	for(int i=0; i<a.size();i++) {
    		
    	
    	ObjectMapper o = new ObjectMapper();
    	o.writeValue(new File("C:\\Users\\admin\\Desktop\\AutomationTest\\jason\\CustomerInfo."+i+"json"),a.get(i));
    	Gson g = new Gson();
    	String jsonString = g.toJson(a.get(i));
    	js.add(jsonString);
    	
    	}
//    	 json  
    	JSONObject jo =new JSONObject();
    	jo.put("data", "");
    	
    System.out.println(jo.toJSONString());
    	
    	
    }
}
