/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package services;

import dao.HibernateUtil;
import org.testng.annotations.Test;


public class AdminServicesImplNGTest {
  
  @Test
  public void init(){
    HibernateUtil.getSessionFactory();
  }

  @Test
  public void testRegisterAgent() {
  }

  @Test
  public void testRegisterCustomer() {
  }

  @Test
  public void testRegisterMeter() {
  }

  @Test
  public void testSetPrice() {
  }
  
}
