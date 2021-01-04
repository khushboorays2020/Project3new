package in.co.sunrays.proj3.testModel;
import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;

import in.co.sunrays.proj3.dto.RoleDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.model.CourseModelHibImpl;
import in.co.sunrays.proj3.model.CourseModelInt;
import in.co.sunrays.proj3.model.RoleModelHibImpl;
import in.co.sunrays.proj3.model.RoleModelInt; 

public class RoleModelTest1 {
	

	/** 
     * Model object to test 
     */ 
 
 public static RoleModelInt model = new RoleModelHibImpl(); 
 
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
       // testFindByPK(); 
         //testFindByLogin(); 
        //testSearch(); 
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
            RoleDTO dto = new RoleDTO(); 
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
                dto = (RoleDTO) it.next(); 
                System.out.println(dto.getName()); 
                System.out.println(dto.getDescription()); 
                System.out.println(dto.getCreatedDatetime()); 
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
            RoleDTO dto = new RoleDTO(); 
            List list = new ArrayList(); 
            list = model.list(0, 10); 
            if (list.size() < 0) { 
                System.out.println("Test list fail"); 
            } 
            Iterator it = list.iterator(); 
            while (it.hasNext()) { 
                dto = (RoleDTO) it.next(); 
                System.out.println(dto.getName()); 
                System.out.println(dto.getDescription()); 
                System.out.println(dto.getCreatedDatetime()); 
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
       RoleDTO dto = new RoleDTO(); 
        try { 
            dto = model.findByName("admin"); 
            if (dto == null) { 
                System.out.println("Test Find By PK fail"); 
            } 
            System.out.println(dto.getName()); 
            System.out.println(dto.getDescription()); 
            System.out.println(dto.getCreatedDatetime()); 
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
           RoleDTO dto = new RoleDTO(); 
            long pk = 3L; 
            dto = model.findByPK(pk); 
            if (dto == null) { 
                System.out.println("Test Find By PK fail"); 
            } 
            System.out.println(dto.getName()); 
            System.out.println(dto.getDescription()); 
            System.out.println(dto.getCreatedDatetime()); 
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
            RoleDTO dto = new RoleDTO(); 
         	System.out.println("dto par");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); 
 
            // dto.setId(8L); 
            dto.setName("khushboo");;
            dto.setDescription("admin");; 
            
             dto.setCreatedBy("Admin"); 
            dto.setModifiedBy("Admin"); 
            dto.setCreatedDatetime(new Timestamp(new Date().getTime())); 
            dto.setModifiedDatetime(new Timestamp(new Date().getTime())); 
         	System.out.println("sare dto par");
            long pk = model.add(dto); 
            System.out.println("Successfully add"); 
          
            RoleDTO addedDto = model.findByPK(pk); 
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
            RoleDTO dto = new RoleDTO(); 
            long pk = 2L; 
            dto.setId(pk); 
            model.delete(dto); 
            RoleDTO deletedDto = model.findByPK(pk); 
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
            RoleDTO dto = model.findByPK(1);
            dto.setName("studenttttt");;
            dto.setCreatedBy("deepak");
                      model.update(dto); 
 
          /*  RoleDTO updatedDTO = model 
                    .findByName("admin"); 
            if ("".equals(updatedDTO.getName())) { 
                System.out.println("Test Update fail"); 
            } 
            else {
				System.out.println("update successfully");
			}
*/        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } catch (DuplicateRecordException e) { 
            e.printStackTrace(); 
        } 
    } 



}
