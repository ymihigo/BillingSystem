
package dao;

import domains.Admin;
import domains.Agent;
import domains.Bill;
import domains.Customer;
import domains.Meter;
import domains.Pricing;
import java.util.List;
import org.hibernate.*;


public class GenericDao {
  SessionFactory sf=HibernateUtil.getSessionFactory();
  Session ss=null;
  Transaction tx=null;
   public String create(Object o){
    try{
      ss=sf.openSession();
      tx=ss.beginTransaction();
      ss.save(o);
      tx.commit();
      return "Success";
    }catch(HibernateException ex){
      tx.rollback();
      throw ex;
    }finally{
      ss.close();
    }
  }
  public Object getObject(Object o,int id){
    try{
      ss=sf.openSession();
      Object ob=(Object) ss.get(o.getClass(), id);
      return ob;
    }catch(HibernateException ex){
      throw ex;
    }finally{
      ss.close();
    }
  }
  
  public String updateObject(Object o){
  try{
      ss=sf.openSession();
      tx=ss.beginTransaction();
      ss.update(o);
      tx.commit();
      
      return "Success";
    }catch(HibernateException ex){
      tx.rollback();
      throw ex;
    }finally{
      ss.close();
    }
  }
  
  public List printAll(Object o){
    try{
      ss=sf.openSession();
      List<Object> lio=(List<Object>) ss.createCriteria(o.getClass()).list();
      return lio;
    }catch(HibernateException ex){
      throw ex;
    }finally{
      ss.close();
    }
  }
  
  public String desactivateLastPricing(){
    try{
      ss=sf.openSession();
      tx=ss.beginTransaction();
      Query query=ss.createQuery("update "+Pricing.class.getName()+" set isactive = 'N '");
      query.executeUpdate();
      tx.commit();
      return "success";
    }catch(HibernateException ex){
      throw ex;
    }finally{
      ss.close();
    }
  }
  
  public Pricing currentPrice(){
    try{
      
      ss=sf.openSession();
      String hql="from "+Pricing.class.getName()+" where isactive ='Y '";
      Query query=ss.createQuery(hql);
      
      return (Pricing) query.uniqueResult();
      
    }catch(Exception ex){
      throw ex;
    }finally{
      ss.close();
    }
  }
  
  public Customer searchByNID(String nid){
    try{
      ss=sf.openSession();
      String hql="from "+Customer.class.getName()+" where nid='"+nid+"'";
      Query query=ss.createQuery(hql);
      return(Customer) query.uniqueResult();
    }catch(HibernateException ex){
      throw ex;
    }finally{
      ss.close();
    }
  }
  
  public Meter searchMeter(String meterNumber){
    try{
      ss=sf.openSession();
      String hql="from "+Meter.class.getName()+" where meternumber = '"+meterNumber+"'";
      Query query=ss.createQuery(hql);
      
      return (Meter) query.uniqueResult();
      
    }catch(HibernateException ex){
      throw ex;
    }finally{
      ss.close();
    }
  }
  
  public Agent searchByusername(String username){
    try{
      ss=sf.openSession();
      String hql="from "+Admin.class.getName()+" where username = '"+username+"'";
      Query query=ss.createQuery(hql);
      return (Agent) query.uniqueResult();
    }catch(Exception ex){
      throw ex;
    }finally{
      ss.close();
    }
  }
  
  public List<Bill> allBillPerMeter(int meterID){
    try{  
      ss=sf.openSession();
      String hql="from "+Bill.class.getName()+" where meter_id = "+meterID+"";
      Query query=ss.createQuery(hql);
      return (List<Bill>) query.list();
    }catch(HibernateException ex){
      throw ex;
    }finally{
      ss.close();
    }
  }
}
