package in.co.sunrays.proj3.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.HibernateException;


import in.co.sunrays.proj3.dto.FacultyDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.model.FacultyModelHibImpl;
import in.co.sunrays.proj3.model.FacultyModelInt; 



public class FacultyModelTest {
	/** 
     * Model object to test 
     */ 
 
 public static FacultyModelInt model = new FacultyModelHibImpl(); 
 
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
     //  testFindByLogin(); 
        //testSearch(); 
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
            FacultyDTO dto = new FacultyDTO(); 
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
                dto = (FacultyDTO) it.next(); 
                System.out.println(dto.getId());
                System.out.println(dto.getFirst_Name());
                System.out.println(dto.getLast_Name());
                System.out.println(dto.getDate_Of_joining());
                System.out.println(dto.getMobile_No());
                System.out.println(dto.getLogin_Id());
                System.out.println(dto.getCource_Id());
                
                System.out.println(dto.getSubject_Name());
                System.out.println(dto.getGrnder());
                
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
           FacultyDTO dto = new FacultyDTO(); 
            List list = new ArrayList(); 
            list = model.list(0, 10); 
            if (list.size() < 0) { 
                System.out.println("Test list fail"); 
            } 
            Iterator it = list.iterator(); 
            while (it.hasNext()) { 
                dto = (FacultyDTO) it.next(); 
                System.out.println(dto.getId());
                System.out.println(dto.getFirst_Name());
                System.out.println(dto.getLast_Name());
                System.out.println(dto.getDate_Of_joining());
                System.out.println(dto.getMobile_No());
                System.out.println(dto.getLogin_Id());
                System.out.println(dto.getCource_Id());
                
                System.out.println(dto.getSubject_Name());
                System.out.println(dto.getGrnder());
                           } 
 
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } 
    } 
 
    
    /** 
     * Tests find a User by Login. 
     */ 
    public static void testFindByLogin() { 
       FacultyDTO dto = new FacultyDTO(); 
        try { 
            dto = model.findByEmailId("rameshkumar45@gmail.com"); 
            if (dto == null) { 
                System.out.println("Test Find By PK fail"); 
            } 
            System.out.println(dto.getId());
            System.out.println(dto.getFirst_Name());
            System.out.println(dto.getLast_Name());
            System.out.println(dto.getDate_Of_joining());
            System.out.println(dto.getMobile_No());
            System.out.println(dto.getLogin_Id());
            System.out.println(dto.getCource_Id());
            
            System.out.println(dto.getSubject_Name());
            System.out.println(dto.getGrnder());
              } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } 
    } 
 
    /** 
     * Tests find a User by PK. 
     */ 
    public static void testFindByPK() { 
        try { 
           FacultyDTO dto = new FacultyDTO(); 
            long pk = 3L; 
            dto = model.findByPK(pk); 
            if (dto == null) { 
                System.out.println("Test Find By PK fail"); 
            } 
            System.out.println(dto.getId());
            System.out.println(dto.getFirst_Name());
            System.out.println(dto.getLast_Name());
            System.out.println(dto.getDate_Of_joining());
            System.out.println(dto.getMobile_No());
            System.out.println(dto.getLogin_Id());
            System.out.println(dto.getCource_Id());
            
            System.out.println(dto.getSubject_Name());
            System.out.println(dto.getGrnder());
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
            FacultyDTO dto = new FacultyDTO(); 
         	System.out.println("dto par");
            SimpleDateFormat sdf = new SimpleDateFormat("dd-MM-yyyy"); 
 
             dto.setId(8L); 
            dto.setFirst_Name("saroj");
            dto.setLast_Name("tiwari");
            dto.setGrnder("M");
            dto.setLogin_Id("sanas56@gmail.com");
            dto.setDate_Of_joining(sdf.parse("06-06-2013"));
            dto.setMobile_No("8665432345");
            dto.setCource_Id(457);
            dto.setCollege_Name("patel institute");
            dto.setQualification("phd");
            dto.setCollege_Name("bsd");
            dto.setSubject_Name("computer science");
            dto.setCreatedBy("Admin");
            dto.setModifiedBy("Admin");
            dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
            dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
        	System.out.println("dto parrrrrrrrrr");
           long pk = model.add(dto); 
            System.out.println("Successfully add"); 
          
            FacultyDTO addedDto = model.findByPK(pk); 
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
            FacultyDTO dto = new FacultyDTO(); 
            long pk = 2L; 
            dto.setId(pk); 
            model.delete(dto); 
            FacultyDTO deletedDto = model.findByPK(pk); 
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
            FacultyDTO dto = model.findByPK(1);
            dto.setFirst_Name("sankar");;
            dto.setCreatedBy("deepak");
                      model.update(dto); 
 
            FacultyDTO updatedDTO = model 
                    .findByPK(1); 
            if ("".equals(updatedDTO.getId())) { 
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
