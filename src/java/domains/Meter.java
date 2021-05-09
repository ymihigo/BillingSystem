
package domains;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Meter implements Serializable{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  @ManyToOne
  private Customer customer;
  @Column(unique = true)
  private String meterNumber;
  private int lastMeterNumber=0;
  private boolean isActive=true;
  @Version
  private long version=1;

  public Meter() {
  }

  public Meter(int id, Customer customer,String meterNumber, int lastMeterNumber, boolean isActive) {
    this.id = id;
    this.customer = customer;
    this.lastMeterNumber = lastMeterNumber;
    this.isActive = isActive;
    this.meterNumber=meterNumber;
  }

  public Meter( Customer customer,String meterNumber) {
    this.customer = customer;
    this.meterNumber=meterNumber;
  }
  
  

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public Customer getCustomer() {
    return customer;
  }

  public void setCustomer(Customer customer) {
    this.customer = customer;
  }

  public int getLastMeterNumber() {
    return lastMeterNumber;
  }

  public void setLastMeterNumber(int lastMeterNumber) {
    this.lastMeterNumber = lastMeterNumber;
  }

  public boolean isIsActive() {
    return isActive;
  }

  public void setIsActive(boolean isActive) {
    this.isActive = isActive;
  }

  public String getMeterNumber() {
    return meterNumber;
  }

  public void setMeterNumber(String meterNumber) {
    this.meterNumber = meterNumber;
  }

}
