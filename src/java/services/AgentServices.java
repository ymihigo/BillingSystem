
package services;

import domains.Meter;


public interface AgentServices {
  public String makeBill(String used, String meterNumber);
}
