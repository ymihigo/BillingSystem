
package ui;

import dao.GenericDao;
import domains.Agent;
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
    
    DateTimeFormatter formatter=DateTimeFormatter.ofPattern("MM-dd-yyyy");
    
    AdminServices as=new AdminServicesImpl();
    
    GenericDao gd=new GenericDao();
    
    Agent agent=new Agent();
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
      case "View_Price_Detail":
        try{
          
          JSONObject obj=new JSONObject();
          int id=Integer.parseInt(request.getParameter("id"));
          Pricing p=(Pricing) gd.getObject(pricing, id);
          
          if(p== null){
            obj.put("price", "");
            obj.put("date", "");
            list.add(obj);
            out.print(list.toJSONString());
            out.flush();
          }
          
          
          else{
            obj.put("price", String.valueOf(p.getPrice()));
            
            String formatedDate=p.getUpdateOn().format(formatter);
            obj.put("date", formatedDate);
            list.add(obj);
            out.print(list.toJSONString());
            out.flush();
          }
          
        }catch(Exception ex){
          response.setStatus(400);
          out.print(ex.getMessage());
          out.flush();
        }
        break;
        
      case "newAgent":
        try{
          
          String nid=request.getParameter("nid");
          String lname=request.getParameter("name");
          String fname=request.getParameter("surname");
          String phone=request.getParameter("phone");
          String street=request.getParameter("street");
          String uname=request.getParameter("username");
          String pwd=request.getParameter("password");
          
          String msg=as.registerAgent(nid, fname, lname, phone, street, uname, pwd);
          
          response.setStatus(200);
          
          out.print(msg);
          out.flush();
        }catch(Exception ex){
          response.setStatus(400);
          out.print(ex.getMessage());
          out.flush();
        }
        break;
        
      case "allAgent":
        try{
          
          List<Agent> liag=(List<Agent>) gd.printAll(agent);
          
          for(Agent ag:liag){
            JSONObject obj=new JSONObject();
            obj.put("id",String.valueOf(ag.getId()));
            obj.put("names", ag.getLname()+" "+ag.getFname());
            obj.put("nid", ag.getNID());
            obj.put("phone", ag.getPhone());
            obj.put("street", ag.getStreet());
            obj.put("username", ag.getUsername());
            obj.put("password", ag.getPassword());
            
            list.add(obj);
          }
          
          response.setStatus(200);
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
