
package services;


public interface AdminServices {
  public String registerAgent(String NID, String fname, String lname, String phone, String street, String username,String password);
  public String registerCustomer(String NID, String fname, String lname, String phone, String street);
  public String registerMeter(String customerNID,String meterNumber);
  public String setPrice(String amount);
}
