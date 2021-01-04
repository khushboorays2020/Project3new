package in.co.sunrays.proj3.test;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;

import in.co.sunrays.proj3.dto.CourseDTO;
import in.co.sunrays.proj3.dto.MarksheetDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.model.CourseModelHibImpl;
import in.co.sunrays.proj3.model.CourseModelInt;
import in.co.sunrays.proj3.model.MarksheetModelHibImpl;
import in.co.sunrays.proj3.model.MarksheetModelInt; 

public class MarksheetModelTest {
	
	/** 
     * Model object to test 
     */ 
 
 public static MarksheetModelInt model = new MarksheetModelHibImpl(); 
 
   //public static UserModelInt model = new UserModelJDBCImpl(); 
 
    /** 
     * Main method to call test methods. 
     *  
     * @param args 
     * @throws ParseException 
     */ 
    public static void main(String[] args) throws ParseException{ 
      // testAdd(); 
        //testDelete(); 
      // testUpdate(); 
        //testFindByPK(); 
    	//testFindByRollNo(); 
        testSearch(); 
       // testList(); 
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
            MarksheetDTO dto = new MarksheetDTO(); 
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
                dto = (MarksheetDTO) it.next(); 
                System.out.println(dto.getId()); 
                System.out.println(dto.getRollNo()); 
                System.out.println(dto.getPhysics());
                System.out.println(dto.getChemistry());
                System.out.println(dto.getMaths());
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
            MarksheetDTO dto = new MarksheetDTO(); 
            List list = new ArrayList(); 
            list = model.list(0, 10); 
            if (list.size() < 0) { 
                System.out.println("Test list fail"); 
            } 
            Iterator it = list.iterator(); 
            while (it.hasNext()) { 
                dto = (MarksheetDTO) it.next(); 
                System.out.println(dto.getId()); 
                System.out.println(dto.getRollNo()); 
                System.out.println(dto.getPhysics());
                System.out.println(dto.getChemistry());
                System.out.println(dto.getMaths());
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
    public static void testFindByRollNo() { 
       MarksheetDTO dto = new MarksheetDTO(); 
        try { 
            dto = model.findByRollNo("101"); 
            if (dto == null) { 
                System.out.println("Test Find By PK fail"); 
            } 
            System.out.println(dto.getId()); 
            System.out.println(dto.getRollNo()); 
            System.out.println(dto.getPhysics());
            System.out.println(dto.getChemistry());
            System.out.println(dto.getMaths());
            System.out.println(dto.getCreatedBy()); 
            System.out.println(dto.getModifiedBy()); 
    } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } 
    } 
 
    /** 
     * Tests find a User by PK. 
     */ 
    public static void testFindByPK() { 
        try { 
            MarksheetDTO dto = new MarksheetDTO(); 
            long pk = 3L; 
            dto = model.findByPK(pk); 
            if (dto == null) { 
                System.out.println("Test Find By PK fail"); 
            } 
            System.out.println(dto.getId()); 
            System.out.println(dto.getRollNo()); 
            System.out.println(dto.getPhysics());
            System.out.println(dto.getChemistry());
            System.out.println(dto.getMaths());
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
 
        try { 
            MarksheetDTO dto = new MarksheetDTO(); 
         	System.out.println("dto par");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); 
 
            // dto.setId(8L); 
            dto.setRollNo("101");
            dto.setName("balaji");
            dto.setPhysics(56);;; 
            dto.setChemistry(78);;
            dto.setMaths(89);
             dto.setCreatedBy("Admin"); 
            dto.setModifiedBy("Admin"); 
            dto.setCreatedDatetime(new Timestamp(new Date().getTime())); 
            dto.setModifiedDatetime(new Timestamp(new Date().getTime())); 
         	System.out.println("sare dto par");
            long pk = model.add(dto); 
            System.out.println("Successfully add"); 
          
            MarksheetDTO addedDto = model.findByPK(pk); 
            if (addedDto == null) { 
                System.out.println("Test add fail"); 
            } 
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } catch (DuplicateRecordException e) { 
            e.printStackTrace(); 
        }catch (HibernateException e) {
		e.printStackTrace();	// TODO: handle exception
		}
 
    } 
 
    /** 
     * Tests delete a User 
     */ 
    public static void testDelete() { 
 
        try { 
            MarksheetDTO dto = new MarksheetDTO(); 
            long pk = 2L; 
            dto.setId(pk); 
            model.delete(dto); 
            MarksheetDTO deletedDto = model.findByPK(pk); 
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
            MarksheetDTO dto = model.findByRollNo("101");
            dto.setPhysics(87);;;
            dto.setCreatedBy("deepak");
                      model.update(dto); 
 
            MarksheetDTO updatedDTO = model 
                    .findByRollNo("101"); 
            if ("".equals(updatedDTO.getName())) { 
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
