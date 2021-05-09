
package services;

import dao.GenericDao;
import domains.Bill;
import domains.Meter;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;


public class CustomerServicesImpl implements CustomerServices {

  GenericDao gd=new GenericDao();
  Bill bill=new Bill();
  
  
  @Override
  public Bill checkLastBill(String meterNumber) {
    try{
    if(meterNumber.isEmpty()){
      throw new RuntimeException("meter number can't be empty");
    }
    if(!meterNumber.matches("^\\d{10}$")){
        throw new RuntimeException("invalid meter number format");
      }
     Meter meter=gd.searchMeter(meterNumber);
      
      if(meter == null){
        throw new RuntimeException("meter doesn't exist");
      }
      
     List<Bill> bills=gd.allBillPerMeter(meter.getId());
     
     if(bills.isEmpty()){
       throw new RuntimeException("you have no bill yet");
     }
     
     Comparator<Bill> cmp = Comparator.comparing(Bill::getId);
     Bill bi=Collections.max(bills, cmp);
     
     return bi;
    }catch(Exception ex){
      throw ex;
    }
     
  }

  @Override
  public String payBill(String billId,String amounts) {
    try{
      if(billId.isEmpty()){
        throw new RuntimeException("invalid bii id");
      }
      
      int id=Integer.parseInt(billId);
      
      Bill b=(Bill) gd.getObject(bill, id);
      
      if(b==null){
        throw new RuntimeException("entered bill doesn't exist");
      }
      double amount=Double.parseDouble(amounts);
      if(b.getAmount()!=amount){
        throw new RuntimeException("amount must be equal to the bill amount");
      }
      
      b.setIsPaid(true);
      
      String msg=gd.updateObject(b);
      
      return msg;
      
    }catch(Exception ex){
      throw ex;
    }
  }

  @Override
  public List<Bill> allBills(String meterNumber) {
   try{
    if(meterNumber.isEmpty()){
      throw new RuntimeException("meter number can't be empty");
    }
    if(!meterNumber.matches("^\\d{10}$")){
        throw new RuntimeException("invalid meter number format");
      }
     Meter meter=gd.searchMeter(meterNumber);
      
      if(meter == null){
        throw new RuntimeException("meter doesn't exist");
      }
      
     List<Bill> bills=gd.allBillPerMeter(meter.getId());
     
     if(bills.isEmpty()){
       throw new RuntimeException("you have no bill yet");
     }
     
     
     return bills;
    }catch(Exception ex){
      throw ex;
    }
  }
  
}
