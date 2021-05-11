
package services;

import dao.GenericDao;
import domains.Agent;
import domains.Customer;
import domains.Meter;
import domains.Pricing;


public class AdminServicesImpl implements AdminServices {
  
  Pricing pricing=new Pricing();
  GenericDao gd=new GenericDao();
  Customer customer=new Customer();
  Meter meter=new Meter();
  Agent agent=new Agent();
  
  @Override
  public String registerAgent(String NID, String fname, String lname, String phone, String street, String username,String password) {
    try{
      if(NID.isEmpty() || fname.isEmpty() || lname.isEmpty() || phone.isEmpty() || street.isEmpty() || username.isEmpty() || password.isEmpty()){
        throw new RuntimeException("please fill all requirements");
      }
      
       if(!NID.matches("^119\\d{13}$")){
        throw new RuntimeException("invalid national id format");
      }
       
       Agent ag=gd.searchAgentByNID(NID);
       
       if(ag!=null){
         throw new RuntimeException("the entered National Id already exist in system");
       }
      
      if(!lname.matches("^[a-zA-Z]*$")){
        throw new RuntimeException("invalid name");
      }
      
      if(!fname.matches("^[a-zA-Z]*$")){
        throw new RuntimeException("invalid first name");
      }
      
      if(!phone.matches("^07[3,8,2]?\\d{7}$")){
        throw new RuntimeException("invalid phone number");
      }
      
      if(!street.matches("^[A-Z]{2}[1-9]{3}[a-z]{2}$")){
        throw new RuntimeException("invalid street format ex:KK314st");
      }
      
      if(!username.matches("^[a-z]{6,15}$")){
        throw new RuntimeException("username must be in lowercase and lenght must be between 6 and 15");
      }
      if(!password.matches("^(?=.*?[A-Z])(?=.*?[a-z])(?=.*?[0-9])(?=.*?[#?!@$ %^&*-]).{8,}$")){
        throw new RuntimeException("invalid password format");
      }
      
      if(gd.searchByusername(username)!=null){
        throw new RuntimeException("username taken");
      }
      
     String msg=gd.create(new Agent(NID, fname, lname, phone, street, username, password));
     return msg;
     
    }catch(Exception ex){
      throw ex;
    }
  }

  @Override
  public String registerCustomer(String NID, String fname, String lname, String phone, String street) {
    try{
      if(NID.isEmpty() || fname.isEmpty() || lname.isEmpty() || phone.isEmpty() || street.isEmpty()){
        throw new RuntimeException("please fill all requirements");
      }
      
      if(!NID.matches("^119\\d{13}$")){
        throw new RuntimeException("invalid national id format");
      }
      
      if(!lname.matches("^[a-zA-Z]*$")){
        throw new RuntimeException("invalid name");
      }
      
      if(!fname.matches("^[a-zA-Z]*$")){
        throw new RuntimeException("invalid first name");
      }
      
      if(!phone.matches("^07[3,8,2]?\\d{7}$")){
        throw new RuntimeException("invalid phone number");
      }
      
      if(!street.matches("^[A-Z]{2}[1-9]{3}[a-z]{2}$")){
        throw new RuntimeException("invalid street format ex:KK314st");
      }
      
      String msg=gd.create(new Customer(NID, fname.substring(0,1).toUpperCase()+fname.substring(1).toLowerCase(), lname.toUpperCase(), phone, street));
      return msg;
    }catch(Exception ex){
      throw ex;
    }
  }

  @Override
  public String registerMeter(String customerNID,String meterNumber) {
    try{
      if(customerNID.isEmpty()||meterNumber.isEmpty()){
        throw new RuntimeException("please fill all requirements");
      }
      if(!customerNID.matches("^119\\d{13}$")){
        throw new RuntimeException("invalid customer National id format");
      }
      if(!meterNumber.matches("^\\d{10}$")){
        throw new RuntimeException("invalid meter number format");
      }
      
      Customer cu=gd.searchByNID(customerNID);
      
      if(cu==null){
        throw new RuntimeException("customer doesn't exist");
      }
      
      Meter meter=gd.searchMeter(meterNumber);
      
      if(meter != null){
        throw new RuntimeException("meter already exist");
      }
      
      String msg=gd.create(new Meter(cu, meterNumber));
      return msg;
      
    }catch(Exception ex){
      throw ex;
    }
  }

  @Override
  public String setPrice(String amount) {
      try{
      if(amount.isEmpty()){
        throw new RuntimeException("fill all requirement");
      }
      double amt=Double.parseDouble(amount);
      
      if(amt<=0){
        throw new RuntimeException("invalid amount");
      }
      
      if(gd.desactivateLastPricing().equals("success")){
        return gd.create(new Pricing(amt));
      }
      return null;
    }catch(Exception ex){
      throw ex;
    }
  }
  
}
