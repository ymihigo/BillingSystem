
package ui;

import dao.GenericDao;
import domains.Pricing;
import java.io.IOException;
import java.io.PrintWriter;
import java.time.format.DateTimeFormatter;
import java.util.List;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.swing.text.DateFormatter;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import services.AdminServices;
import services.AdminServicesImpl;

@WebServlet("/AdminPanel")
public class AdminPanel extends HttpServlet{
  
  @Override
  public void service(HttpServletRequest request,HttpServletResponse response) throws IOException{
    
    PrintWriter out=response.getWriter();
    
    JSONArray list=new JSONArray();
    JSONObject job=new JSONObject();
    
    AdminServices as=new AdminServicesImpl();
    
    GenericDao gd=new GenericDao();
    
    Pricing pricing=new Pricing();
    
    String a=request.getParameter("a");
    
    switch(a){
      case "newPrice":
        
        try{
          String pr=request.getParameter("price");
          
          String msg=as.setPrice(pr);
         
          response.setStatus(200);
          out.print(msg);
          out.flush();
          
        }catch(Exception ex){
          response.setStatus(400);
          out.print(ex.getMessage());
          out.flush();
        }
        break;
      case "allPrices":
        try{
          List<Pricing> lip=(List<Pricing>)gd.printAll(pricing);
          
          for(Pricing p:lip){
            JSONObject obj=new JSONObject();
            
            obj.put("amount", Double.toString(p.getPrice()));
            DateTimeFormatter formatter=DateTimeFormatter.ofPattern("MM-dd-yyyy");
            String formatString=p.getUpdateOn().format(formatter);
            
            obj.put("updatedDate", formatString);
            obj.put("status", String.valueOf(p.isIsActive()));
            obj.put("id", String.valueOf(p.getId()));
            
            list.add(obj);
          }
          
          out.print(list.toJSONString());
          out.flush();
          
        }catch(Exception ex){
          response.setStatus(400);
          out.print(ex.getMessage());
          out.flush();
        }
        break;
        
      case "current_price":
        try{
          JSONObject obj=new JSONObject();
          Pricing p=gd.currentPrice();
          
          DateTimeFormatter formatter=DateTimeFormatter.ofPattern("MM-dd-yyyy");
          String formattedDate=p.getUpdateOn().format(formatter);
          
          obj.put("price", String.valueOf(p.getPrice()));
          obj.put("date",  formattedDate);
          
          list.add(obj);
          
          out.print(list.toJSONString());
          out.flush();
          
        }catch(Exception ex){
          response.setStatus(400);
          out.print(ex.getMessage());
          out.flush();
        }
        break;
    }
    
  }
  
}
