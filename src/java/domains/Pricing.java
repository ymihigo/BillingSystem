
package domains;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;
import org.hibernate.annotations.Type;

@Entity
@XmlRootElement
public class Pricing implements Serializable{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private LocalDate updateOn=LocalDate.now();
  private double Price;
  @Type(type="yes_no")
  private boolean isActive=true;
  @Version
  private long version=1;

  public Pricing(int id, LocalDate updateOn, double Price, boolean isActive) {
    this.id = id;
    this.updateOn = updateOn;
    this.Price = Price;
    this.isActive = isActive;
  }

  public Pricing(LocalDate updateOn, double Price, boolean isActive) {
    this.updateOn = updateOn;
    this.Price = Price;
    this.isActive = isActive;
  }

  public Pricing(double Price) {
    this.Price = Price;
  }
  
  

  public Pricing() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public LocalDate getUpdateOn() {
    return updateOn;
  }

  public void setUpdateOn(LocalDate updateOn) {
    this.updateOn = updateOn;
  }

  public double getPrice() {
    return Price;
  }

  public void setPrice(double Price) {
    this.Price = Price;
  }

  public boolean isIsActive() {
    return isActive;
  }

  public void setIsActive(boolean isActive) {
    this.isActive = isActive;
  }
  
}
