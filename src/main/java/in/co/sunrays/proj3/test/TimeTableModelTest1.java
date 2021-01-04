package in.co.sunrays.proj3.test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

import org.hibernate.Session;

import in.co.sunrays.proj3.dto.TimeTableDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.model.TimeTableModelHibImpl;
import in.co.sunrays.proj3.model.TimeTableModelInt;
import in.co.sunrays.proj3.util.HibDataSource;

public class TimeTableModelTest1 {

    /** 
     * Model object to test 
     */ 
 
     public static TimeTableModelInt model = new TimeTableModelHibImpl(); 
 
     // public static StudentModelInt model = new StudentModelJDBCImpl(); 
 
    /** 
     * Main method to call test methods. 
     *  
     * @param args 
     * @throws ParseException 
     */ 
    public static void main(String[] args) throws ParseException { 
   testAdd(); 
       //  testDelete(); 
       // testUpdate(); 
       //testFindByPK(); 
  
     //  testSearch(); 
         //testList(); 
 
    }

	private static void testDelete() {
		// TODO Auto-generated method stub
		 try { 
	            TimeTableDTO dto = new TimeTableDTO(); 
	            long pk = 1L; 
	            dto.setId(pk); 
	            model.delete(dto); 
	            System.out.println("Test Delete succ"); 
	            TimeTableDTO deletedDto = model.findByPK(pk); 
	            if (deletedDto != null) { 
	                System.out.println("Test Delete fail"); 
	            } 
	        } catch (ApplicationException e) { 
	            e.printStackTrace(); 
	        } 
	}

	private static void testUpdate() {
		try { 
            System.out.println("Test Update scc111"); 
            TimeTableDTO dto = model.findByPK(1L); 
            dto.setSubjectName("new"); 
          
             
            model.update(dto); 
            TimeTableDTO updatedDTO = model.findByPK(1L); 
            if (!"new".equals(updatedDTO.getSubjectName())) { 
                System.out.println("Test Update fail"); 
            } 
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } catch (DuplicateRecordException e) { 
            e.printStackTrace(); 
        } 
		
	}

	private static void testFindByPK() {
		// TODO Auto-generated method stub
		SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); 
        try { 
            TimeTableDTO dto = new TimeTableDTO(); 
            long pk = 1l; 
            dto = model.findByPK(pk); 
            if (dto == null) { 
                System.out.println("Test Find By PK fail"); 
            } 
            System.out.println( dto.getId()); 
            System.out.println (dto.getSemester());
            System.out.println(dto.getExamDate());
            System.out.println(dto.getTime());
            System.out.println(dto.getSubjectId());
            System.out.println( dto.getSubjectName());
            System.out.println (dto.getCourseId());
            System.out.println(dto.getCourseName());
            
            System.out.println(dto.getCreatedBy()); 
            System.out.println(dto.getModifiedBy()); 
            System.out.println(dto.getCreatedDatetime()); 
            System.out.println(dto.getModifiedDatetime()); 
              
        } catch (ApplicationException e) { 
            e.printStackTrace(); 
        } 
 
	}

	private static void testAdd() throws ParseException {
		 try { 
	            TimeTableDTO dto = new TimeTableDTO(); 
	            SimpleDateFormat sdf = new SimpleDateFormat("MM/dd/yyyy"); 
	 
	            dto.setId(3L); 
	           dto. setExamDate(sdf.parse("12/06/2018"));
	            dto.setSubjectId(3l);
	            dto.setSubjectName("cc");
	            dto.setCourseId(3l);
	            dto.setCourseName("sckkkkccchbbhvd");
	            dto.setSemester("2nd year");
	           
	           
	            dto.setCreatedBy("Admin"); 
	            dto.setModifiedBy("Admin"); 
	            dto.setCreatedDatetime(new Timestamp(new Date().getTime())); 
	            dto.setModifiedDatetime(new Timestamp(new Date().getTime())); 
	            Session session = HibDataSource.getSession();
	            long pk = model.add(dto); 
	            System.out.println("Test add succ"); 
	           TimeTableDTO addedDto = model.findByPK(pk); 
	            if (addedDto == null) { 
	                System.out.println("Test add fail"); 
	            }
	        } catch (ApplicationException e) { 
	            e.printStackTrace(); 
	        } catch (DuplicateRecordException e) { 
	            e.printStackTrace(); 
	        } 
	 
	    		
	}
	
	
	

}

