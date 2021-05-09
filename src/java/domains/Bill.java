package domains;

import java.io.Serializable;
import java.time.LocalDate;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Bill implements Serializable{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private int used;
  private double amount;
  @ManyToOne
  private Meter meter;
  private boolean isPaid=false;
  private long version=1;
  private LocalDate billDate=LocalDate.now();

  public Bill() {
  }


  public Bill(int id, int used, double amount, Meter meter, boolean isPaid,LocalDate billDate) {
    this.id = id;
    this.used = used;
    this.amount = amount;
    this.meter = meter;
    this.isPaid = isPaid;
    this.billDate= billDate;
  }

  public Bill(int used, double amount, Meter meter, boolean isPaid) {
    this.used = used;
    this.amount = amount;
    this.meter = meter;
    this.isPaid = isPaid;
  }
  
  public Bill(int used, double amount, Meter meter) {
    this.used = used;
    this.amount = amount;
    this.meter = meter;
  }

//  public Bill(int used, Meter meter) {
//    this.used = used;
//    this.meter = meter;
//  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public int getUsed() {
    return used;
  }

  public void setUsed(int used) {
    this.used = used;
  }

  public double getAmount() {
    return amount;
  }

  public void setAmount(double amount) {
    this.amount = amount;
  }

  public Meter getMeter() {
    return meter;
  }

  public void setMeter(Meter meter) {
    this.meter = meter;
  }

  public boolean isIsPaid() {
    return isPaid;
  }

  public void setIsPaid(boolean isPaid) {
    this.isPaid = isPaid;
  }

  public long getVersion() {
    return version;
  }

  public void setVersion(long version) {
    this.version = version;
  }

  public LocalDate getBillDate() {
    return billDate;
  }

  public void setBillDate(LocalDate billDate) {
    this.billDate = billDate;
  }
  
  

}
