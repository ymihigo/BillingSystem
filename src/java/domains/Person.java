package domains;

import java.io.Serializable;
import javax.persistence.Column;
import javax.persistence.MappedSuperclass;

@MappedSuperclass
public class Person implements Serializable{
  @Column(unique = true)
  private String NID;
  private String fname;
  private String lname;
  @Column(unique = true)
  private String phone;
  private String street;

  public Person(String NID, String fname, String lname, String phone, String street) {
    this.NID = NID;
    this.fname = fname;
    this.lname = lname;
    this.phone = phone;
    this.street = street;
  }

  public Person() {
  }

  public String getNID() {
    return NID;
  }

  public void setNID(String NID) {
    this.NID = NID;
  }

  public String getFname() {
    return fname;
  }

  public void setFname(String fname) {
    this.fname = fname;
  }

  public String getLname() {
    return lname;
  }

  public void setLname(String lname) {
    this.lname = lname;
  }

  public String getPhone() {
    return phone;
  }

  public void setPhone(String phone) {
    this.phone = phone;
  }

  public String getStreet() {
    return street;
  }

  public void setStreet(String street) {
    this.street = street;
  }
  
  
}
