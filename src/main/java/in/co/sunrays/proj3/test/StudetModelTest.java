package in.co.sunrays.proj3.test;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;

import in.co.sunrays.proj3.dto.StudentDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.model.CourseModelHibImpl;
import in.co.sunrays.proj3.model.CourseModelInt;
import in.co.sunrays.proj3.model.StudentModelHibImpl;
import in.co.sunrays.proj3.model.StudentModelInt;
import in.co.sunrays.proj3.util.HibDataSource; 


public class StudetModelTest {

	/** 
     * Model object to test 
     */ 
 
 public static StudentModelInt model = new StudentModelHibImpl(); 
 
   //public static UserModelInt model = new UserModelJDBCImpl(); 
 
    /** 
     * Main method to call test methods. 
     *  
     * @param args 
     * @throws ParseException 
     */ 
    public static void main(String[] args) throws ParseException{ 
       testAdd(); 
        //testDelete(); 
       //testUpdate(); 
        //testFindByPK(); 
        // testFindByLogin();  finb by login aur update chalana h 
      // testSearch(); 
        //testList(); 
       // testGetRoles(); 
       //testAuthenticate(); 
         //testchangePassword(); 
       // testRegisterUser(); 
       // testforgetPassword(); 
       // testresetPassword(); 
    } 
    
    

    /**UserDTO dto = new UserDTO(); 
     * Tests get Search 
     */ 
    public static void testSearch() { 
 
        try { 
            StudentDTO dto = new StudentDTO(); 
            List list = new ArrayList(); 
            // dto.setFirstName("ranjit"); 
            // dto.setLastName("ch"); 
           // dto.setLogin("ankur44@gmail.com"); 
 
            list = model.search(dto, 0, 0); 
            if (list.size() < 0) { 
                System.out.println("Test Serach fail"); 
            } 
            Iterator it = list.iterator(); 
            while (it.hasNext()) { 
                dto = (StudentDTO) it.next(); 
                System.out.println(dto.getId()); 
                System.out.println(dto.getFirstName()); 
                System.out.println(dto.getLastName()); 
                System.out.println(dto.getDob()); 
                System.out.println(dto.getMobileNo()); 
                System.out.println(dto.getEmail()); 
                
                System.out.println(dto.getCreatedBy()); 
                System.out.println(dto.getModifiedBy()); 
                } 
 
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } 
 
    } 
 
    /** 
     * Tests get List. 
     */ 
    public static void testList() { 
 
        try { 
            StudentDTO dto = new StudentDTO(); 
            List list = new ArrayList(); 
            list = model.list(0, 10); 
            if (list.size() < 0) { 
                System.out.println("Test list fail"); 
            } 
            Iterator it = list.iterator(); 
            while (it.hasNext()) { 
                dto = (StudentDTO) it.next(); 
                System.out.println(dto.getId()); 
                System.out.println(dto.getFirstName()); 
                System.out.println(dto.getLastName()); 
                System.out.println(dto.getDob()); 
                System.out.println(dto.getMobileNo()); 
                System.out.println(dto.getEmail()); 
                
                System.out.println(dto.getCreatedBy()); 
                System.out.println(dto.getModifiedBy()); 
                           } 
 
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } 
    } 
 
    
    /** 
     * Tests find a User by Login. 
     */ 
    public static void testFindByLogin() { 
       StudentDTO dto = new StudentDTO(); 
        try { 
            dto = model.findByEmailId("sagar34@gmail.com"); 
            if (dto == null) { 
                System.out.println("Test Find By PK fail"); 
            } 
            System.out.println(dto.getId()); 
            System.out.println(dto.getFirstName()); 
            System.out.println(dto.getLastName()); 
            System.out.println(dto.getDob()); 
            System.out.println(dto.getMobileNo()); 
            System.out.println(dto.getEmail()); 
            
            System.out.println(dto.getCreatedBy()); 
            System.out.println(dto.getModifiedBy()); 
                       }
        
        catch (ApplicationException e) { 
            e.printStackTrace(); 
        }
        catch (HibernateException e) {
        	e.printStackTrace();
			// TODO: handle exception
		}
    } 
 
    /** 
     * Tests find a User by PK. 
     */ 
    public static void testFindByPK() { 
        try { 
            StudentDTO dto = new StudentDTO(); 
            long pk = 3L; 
            dto = model.findByPK(pk); 
            if (dto == null) { 
                System.out.println("Test Find By PK fail"); 
            } 
            System.out.println(dto.getId()); 
            System.out.println(dto.getFirstName()); 
            System.out.println(dto.getLastName()); 
            System.out.println(dto.getDob()); 
            System.out.println(dto.getMobileNo()); 
            System.out.println(dto.getEmail()); 
            
            System.out.println(dto.getCreatedBy()); 
            System.out.println(dto.getModifiedBy()); 
              } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } 
 
    } 
 
 
    /** 
     * Tests add a User 
     *  
     * @throws ParseException 
     */ 
    public static void testAdd() throws ParseException { 
    	System.out.println("add k aander");
 
        
        	  long pk = 0;
           StudentDTO dto = new StudentDTO(); 
         	System.out.println("dto par");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); 
 
            dto.setId(1L); 
            dto.setFirstName("sagar");;
            dto.setLastName("dhote");; 
            dto.setCollegeName("tit");; 
            dto.setCollegeId(1l);;
            dto.setDob(sdf.parse("13-4-1996"));
            dto.setEmail("sagar34@gmail.com");;
            dto.setMobileNo("9875678676");
           
            
             dto.setCreatedBy("Admin"); 
            dto.setModifiedBy("Admin"); 
            dto.setCreatedDatetime(new Timestamp(new Date().getTime())); 
            dto.setModifiedDatetime(new Timestamp(new Date().getTime())); 
         	System.out.println("sare dto par");
            
            Session session = HibDataSource.getSession();
            Transaction transaction = null;
            
                transaction = session.beginTransaction();
                session.save(dto);
            
                transaction.commit();
        
    }
    /** 
     * Tests delete a User 
     */ 
    public static void testDelete() { 
 
        try { 
            StudentDTO dto = new StudentDTO(); 
            long pk = 2L; 
            dto.setId(pk); 
            model.delete(dto); 
            StudentDTO deletedDto = model.findByPK(pk); 
            if (deletedDto != null) { 
                System.out.println("Test Delete fail"); 
            } 
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } 
    } 
    /** 
     * Tests update a User 
     */ 
    public static void testUpdate() throws ParseException { 
 
        try { 
            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); 
            StudentDTO dto = model.findByEmailId("sagar34@gmail.com");
            dto.setFirstName("sagarrrr");;
            dto.setCreatedBy("deepak");
                      model.update(dto); 
 
            StudentDTO updatedDTO = model 
                    .findByEmailId("sagar34@gmail.com"); 
            if ("".equals(updatedDTO.getEmail())) { 
                System.out.println("Test Update fail"); 
            } 
            else {
				System.out.println("update successfully");
			}
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } catch (DuplicateRecordException e) { 
            e.printStackTrace(); 
        } 
    } 


	
}
