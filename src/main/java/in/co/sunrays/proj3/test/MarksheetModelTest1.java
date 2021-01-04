package in.co.sunrays.proj3.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import in.co.sunrays.proj3.dto.MarksheetDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.model.MarksheetModelHibImpl;
import in.co.sunrays.proj3.model.MarksheetModelInt;

public class MarksheetModelTest1 {

	static MarksheetModelInt model=new MarksheetModelHibImpl();
	public static void main(String[] args) throws ApplicationException, DuplicateRecordException {
     testAdd();		

	}

	public static void testAdd() throws ApplicationException, DuplicateRecordException{
		MarksheetDTO dto = new MarksheetDTO();
        dto.setId(10L);
        dto.setRollNo("103");
        dto.setName("khushboo");
        dto.setPhysics(88);
        dto.setChemistry(77);
        dto.setMaths(99);
        dto.setStudentId(2L);
   
        dto.setCreatedBy("Admin");
		dto.setModifiedBy("Admin");
		dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
	dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
        long pk = model.add(dto);
        System.out.println("Test add succ");
	} 
	 public static void testDelete() throws ApplicationException {
		 
	        
	            MarksheetDTO dto = new MarksheetDTO();
	            long pk = 2L;
	            dto.setId(pk);
	            model.delete(dto);
	            MarksheetDTO deletedDto = model.findByPK(pk);
	    	
}
	 public static void testUpdate() throws ApplicationException, DuplicateRecordException {
		 
	        
	            MarksheetDTO dto = model.findByPK(3L);
	            dto.setName("sweety");
	            dto.setChemistry(88);
	            dto.setMaths(88);
	            model.update(dto);
	            System.out.println("Test Update ");
	           
	 }
	 public static void testFindByRollNo() {
		 
	        try {
	            MarksheetDTO dto = model.findByRollNo("3");
	            if (dto == null) {
	                System.out.println("Test Find By RollNo fail");
	            }
	            System.out.println(dto.getId());
	            System.out.println(dto.getRollNo());
	            System.out.println(dto.getName());
	            System.out.println(dto.getPhysics());
	            System.out.println(dto.getChemistry());
	            System.out.println(dto.getMaths());
	            System.out.println(dto.getCreatedBy());
	            System.out.println(dto.getCreatedDatetime());
	            System.out.println(dto.getModifiedBy());
	            System.out.println(dto.getModifiedDatetime());
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	 
	    }
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
	            System.out.println(dto.getName());
	            System.out.println(dto.getPhysics());
	            System.out.println(dto.getChemistry());
	            System.out.println(dto.getMaths());
	            System.out.println(dto.getCreatedBy());
	            System.out.println(dto.getCreatedDatetime());
	            System.out.println(dto.getModifiedBy());
	            System.out.println(dto.getModifiedDatetime());
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	 
	    }
	 public static void testList() throws ApplicationException{
		 MarksheetDTO dto=new MarksheetDTO();
		 List list=new ArrayList();
		 list=model.list(0,10);
		 Iterator it=list.iterator();
		 while(it.hasNext()){
			 dto=(MarksheetDTO) it.next();
			 System.out.println(dto.getId());
             System.out.println(dto.getRollNo());
             System.out.println(dto.getName());
             System.out.println(dto.getPhysics());
             System.out.println(dto.getChemistry());
             System.out.println(dto.getMaths());
             System.out.println(dto.getCreatedBy());
             System.out.println(dto.getCreatedDatetime());
             System.out.println(dto.getModifiedBy());
             System.out.println(dto.getModifiedDatetime());

		 }
		 
	 }
	 public static void testSearch() throws ApplicationException{
		 MarksheetDTO dto=new MarksheetDTO();
		 List list=new ArrayList();
		 dto.setStudentId(1l);
		 list=model.search(dto,1,10);
		 Iterator it=list.iterator();
		 while(it.hasNext()){
			 dto=(MarksheetDTO) it.next();
			 System.out.println(dto.getId());
             System.out.println(dto.getRollNo());
             System.out.println(dto.getName());
             System.out.println(dto.getPhysics());
             System.out.println(dto.getChemistry());
             System.out.println(dto.getMaths());
             System.out.println(dto.getCreatedBy());
             System.out.println(dto.getCreatedDatetime());
             System.out.println(dto.getModifiedBy());
             System.out.println(dto.getModifiedDatetime());

		 }
		 
	 }
	 public static void testMeritList(){
		 
		 try {
	            MarksheetDTO dto = new MarksheetDTO();
	            List list = new ArrayList();
	            list = model.getMeritList(1, 5);
	            if (list.size() < 0) {
	                System.out.println("Test List fail");
	            }
	            Iterator it = list.iterator();
	            while (it.hasNext()) {
	                dto = (MarksheetDTO) it.next();
	                System.out.println(dto.getId());
	                System.out.println(dto.getRollNo());
	                System.out.println(dto.getName());
	                System.out.println(dto.getPhysics());
	                System.out.println(dto.getChemistry());
	                System.out.println(dto.getMaths());
	                System.out.println(dto.getCreatedBy());
	                System.out.println(dto.getCreatedDatetime());
	                System.out.println(dto.getModifiedBy());
	                System.out.println(dto.getModifiedDatetime());
	            }
	        } catch (ApplicationException e) {
	            e.printStackTrace();
	        }
	 
	    }
	 }
