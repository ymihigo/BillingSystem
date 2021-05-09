
package domains;

import java.io.Serializable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Customer extends Person implements Serializable{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  private long version=1;

  public Customer() {
  }

  public Customer(int id, String NID, String fname, String lname, String phone, String street) {
    super(NID, fname, lname, phone, street);
    this.id = id;
  }

  public Customer(String NID, String fname, String lname, String phone, String street) {
    super(NID, fname, lname, phone, street);
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }
  
  
}
