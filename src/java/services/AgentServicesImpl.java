
package services;

import dao.GenericDao;
import domains.Bill;
import domains.Meter;
import domains.Pricing;


public class AgentServicesImpl implements AgentServices {
  
  Meter meter=new Meter();
  GenericDao gd=new GenericDao();
  
  @Override
  public String makeBill(String used, String meterNumber) {
  try{
    if(used.isEmpty() || meterNumber.isEmpty()){
      throw new RuntimeException("please fill all requirements");
    }
    
    int usd=Integer.parseInt(used);
    
    if(usd<0){
      throw new RuntimeException("used meter can't be less than zero");
    }
    
    Meter mt=gd.searchMeter(meterNumber);
    
    if(mt == null){
      throw new RuntimeException("invalid meter number");
    }
    
    if(mt.getLastMeterNumber()>usd){
      throw new RuntimeException("invalid last meter");
    }
    //Current used
    int cused=usd-mt.getLastMeterNumber();
    
    //Get current price
    
    Pricing pr=gd.currentPrice();
    
    //Calculate amount
    
    double amt=pr.getPrice()*cused;
    
    //update meter last number
    
    mt.setLastMeterNumber(usd);
    
    if(!gd.updateObject(mt).equals("Success")){
      throw new RuntimeException("failed");
    }
    
    //make a bill
    
    String msg=gd.create(new Bill(cused, amt, mt));
    
    return msg;
    
  }catch(Exception ex){
    throw ex;
  }
  }
  
}
