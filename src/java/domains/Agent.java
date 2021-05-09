
package domains;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Version;
import javax.xml.bind.annotation.XmlRootElement;

@Entity
@XmlRootElement
public class Agent extends Person{
  @Id
  @GeneratedValue(strategy = GenerationType.AUTO)
  private int id;
  @Column(unique = true)
  private String username;
  @Column(nullable = false)
  private String password;
  @Version
  private long version=1;

  public Agent(int id, String NID, String fname, String lname, String phone, String street,String username,String password) {
    super(NID, fname, lname, phone, street);
    this.id = id;
    this.username=username;
    this.password=password;
  }

  public Agent(String NID, String fname, String lname, String phone, String street, String username,String password) {
    super(NID, fname, lname, phone, street);
    this.username=username;
    this.password=password;
  }

  public Agent() {
  }

  public int getId() {
    return id;
  }

  public void setId(int id) {
    this.id = id;
  }

  public String getUsername() {
    return username;
  }

  public void setUsername(String username) {
    this.username = username;
  }

  public String getPassword() {
    return password;
  }

  public void setPassword(String password) {
    this.password = password;
  }
  
  
  
  
}
