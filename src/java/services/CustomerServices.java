
package services;

import domains.Bill;
import java.util.List;


public interface CustomerServices {
  public Bill checkLastBill(String meterNumber);
  public String payBill(String billId,String amounts);
  public List<Bill> allBills(String meterNumber);
}
