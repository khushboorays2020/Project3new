package in.co.sunrays.proj3.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import javax.mail.MessagingException;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.co.sunrays.proj3.dto.UserDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.exception.RecordNotFoundException;
import in.co.sunrays.proj3.model.UserModelHibImpl;
import in.co.sunrays.proj3.model.UserModelInt;
import in.co.sunrays.proj3.util.HibDataSource;

public class UserModelTest1 {
	
	  static UserModelInt model=new UserModelHibImpl();

	
public static void main(String[] args) throws DuplicateRecordException, ApplicationException, ParseException, RecordNotFoundException {
	    testAdd();
		//testDelete();
	//	testUpdate();
		//testFindByPK();
		//testFindByLogin();
		//testList();
		//testSearch();
		//testAuthenticate();
		//testChangePassword();
		//testForgetPassword();
		//testRegisterUser();
		
	}
	public static Long testAdd() throws DuplicateRecordException, ApplicationException, ParseException{
		
		UserDTO dto=new UserDTO();
		
		SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy");
		 
        dto.setId(8L);
        dto.setFirstName("lalu");
        dto.setLastName("jain");
        dto.setLogin("sawantcddkhushboo@gmail.com");
        dto.setPassword("123");
        dto.setDob(sdf.parse("31-12-1990"));
        dto.setRoleId(1);
        dto.setUnSuccessfulLogin(4);
        dto.setGender("Male");
        dto.setLastLogin(new Timestamp(new Date().getTime()));
        dto.setLastLoginIP("sdfghj");
        dto.setConfirmPassword("123");
        dto.setMobileNo("1234567890");
        dto.setCreatedBy("Admin");
        dto.setModifiedBy("Admin");
        dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
        dto.setModifiedDatetime(new Timestamp(new Date().getTime()));

        Session session = HibDataSource.getSession();
        Transaction transaction = null;
        try {
            transaction = session.beginTransaction();
            session.save(dto);
          long  pk = dto.getId();
            transaction.commit();
            
            if (transaction != null) {
                transaction.rollback();
            }
            throw new ApplicationException("Exception in User Add "
                 );
        } finally {
            session.close();
        }
    }

	public static void testDelete() throws ApplicationException{
		
		UserDTO dto=new UserDTO();
		dto.setId(3l);
		model.delete(dto);
		System.out.println("Deleted successfully");
		
	}
	public static void testUpdate() throws ApplicationException, DuplicateRecordException{
		
		UserDTO dto=new UserDTO();
		dto.setId(7l);
		dto.setFirstName("monu");
		dto.setLastName("gupta");
		model.update(dto);
		System.out.println("updated successfully");
	}
	public static void testFindByPK() throws ApplicationException{
		
		UserDTO dto= new UserDTO();
		//System.out.println(dto.getId());
		dto=model.findByPK(1);
		System.out.println(dto.getFirstName());
		System.out.println(dto.getLastName());

		System.out.println("testFindByPK is successful");
	}
	public static void testFindByLogin() throws ApplicationException{
		UserDTO dto= new UserDTO();
		dto=model.findByLogin("anjug1592@gmail.com");
		 System.out.println(dto.getId());
         System.out.println(dto.getFirstName());
         System.out.println(dto.getLastName());
         System.out.println(dto.getLogin());
         System.out.println(dto.getPassword());
         System.out.println(dto.getDob());
         System.out.println(dto.getRoleId());
         System.out.println(dto.getUnSuccessfulLogin());
         System.out.println(dto.getGender());
         System.out.println(dto.getLastLogin());

         System.out.println(dto.getMobileNo());
         System.out.println(dto.getCreatedBy());
         System.out.println(dto.getModifiedBy());
         System.out.println(dto.getCreatedDatetime());
         System.out.println(dto.getModifiedDatetime());
         System.out.println("Test findbylogin pass");

	}
	
	public static void testList() {
		 
        try {
            UserDTO dto = new UserDTO();
            List list = new ArrayList();
            list = model.list(1, 10);
            
            if (list.size() < 0) {
                System.out.println("Test list fail");
            }
            Iterator it = list.iterator();
            System.out.println(list.size());
            System.out.println("iterator");
            while (it.hasNext()) {
            	System.out.println("inside while");
                dto = (UserDTO) it.next();
                System.out.println(dto.getId());
                System.out.println(dto.getFirstName());
                System.out.println(dto.getLastName());
                System.out.println(dto.getLogin());
                System.out.println(dto.getPassword());
                System.out.println(dto.getDob());
                System.out.println(dto.getRoleId());
                System.out.println(dto.getUnSuccessfulLogin());
                System.out.println(dto.getGender());
                System.out.println(dto.getLastLogin());

                System.out.println(dto.getMobileNo());
                System.out.println(dto.getCreatedBy());
                System.out.println(dto.getModifiedBy());
                System.out.println(dto.getCreatedDatetime());
                System.out.println(dto.getModifiedDatetime());
                System.out.println("Test list pass");
            }
 
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
	 public static void testSearch() {
		 
	        try {
	            UserDTO dto = new UserDTO();
	            List list = new ArrayList();
	            // dto.setFirstName("ranjit");
	            // dto.setLastName("ch");
	            dto.setLogin("anjug1592@gmail.com");
	 
	            list = model.search(dto, 0, 0);
	            if (list.size() < 0) {
	                System.out.println("Test Serach fail");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                dto = (UserDTO) it.next();
	                System.out.println(dto.getId());
	                System.out.println(dto.getFirstName());
	                System.out.println(dto.getLastName());
	                System.out.println(dto.getLogin());
	                System.out.println(dto.getPassword());
	                System.out.println(dto.getDob());
	                System.out.println(dto.getRoleId());
	                System.out.println(dto.getUnSuccessfulLogin());
	                System.out.println(dto.getGender());
	                System.out.println(dto.getLastLogin());

	                System.out.println(dto.getMobileNo());
	                System.out.println(dto.getCreatedBy());
	                System.out.println(dto.getModifiedBy());
	                System.out.println(dto.getCreatedDatetime());
	                System.out.println(dto.getModifiedDatetime());
	            }
	 
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	 
	    }
	 public static void testAuthenticate() throws ApplicationException, RecordNotFoundException{

		 try { 
	            UserDTO dto = new UserDTO(); 
	            dto.setLogin("sawantkhushboo@gmail.com"); 
	            dto.setPassword("123"); 
	            dto = model.authenticate(dto.getLogin(), dto.getPassword()); 
	            if (dto != null) { 
	                System.out.println("Successfully login"); 
	 
	            } else { 
	                System.out.println("Invalied login Id & password"); 
	            } 
	 
	        } catch (ApplicationException e) { 
	            e.printStackTrace(); 
	        } 
	    } 
  public  static void testChangePassword() throws ApplicationException, MessagingException{
	  UserDTO dto=new UserDTO();
	  
	  
	  String oldpassword=dto.getPassword();
	  
	 // dto.setPassword("555");
	//  String newPassword = dto.getPassword();
      try {
          model.changePassword(2L, "123", "55555");
      } catch (RecordNotFoundException e) {
          e.printStackTrace();
      }
  }
  public static void testForgetPassword() throws ApplicationException, RecordNotFoundException{
	  boolean b = model.forgetPassword("anjug1592@gmail.com");
	  
      System.out.println("Suucess : Test Forget Password Success");

  }
  public static void testRegisterUser() throws ApplicationException, DuplicateRecordException, ParseException, MessagingException{
	  UserDTO dto = new UserDTO();
      SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy");

      // dto.setId(8L);
      dto.setFirstName("mini");
      dto.setLastName("kumawat");
      dto.setLogin("mini0@gmail.com");
      dto.setPassword("4444");
      dto.setConfirmPassword("4444");
      dto.setDob(sdf.parse("11/20/2010"));
      dto.setGender("M");
      dto.setRoleId(2);
      long pk = model.registerUser(dto);
      System.out.println("Successfully register");
	  
  }
}
