package in.co.sunrays.proj3.test;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Date;
import java.util.Iterator;
import java.util.List;

import org.hibernate.Session;
import org.hibernate.Transaction;

import in.co.sunrays.proj3.dto.CollegeDTO;
import in.co.sunrays.proj3.dto.RoleDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.model.CollegeModelHibImpl;
import in.co.sunrays.proj3.model.CollegeModelInt;
import in.co.sunrays.proj3.util.HibDataSource;

public class CollegeModelTest1 {

    public static CollegeModelInt model = new CollegeModelHibImpl();

	public static void main(String[] args) throws ApplicationException, DuplicateRecordException {
	     testAdd();
		//testUpdate();
		//testDelete();
		//testFindByName();
		//testFindByPK();
		//testSearch();
		//testList();
		
	}
	public static void testAdd() throws ApplicationException, DuplicateRecordException{
		CollegeDTO dto = new CollegeDTO(); 
       dto.setId(1L); 
        dto.setName("ISjjjjjI"); 
        dto.setAddress("Kolkata"); 
        dto.setState("WB"); 
        dto.setCity("Kolkata"); 
        dto.setMobile("07376877267"); 
        dto.setCreatedBy("Admin"); 
        dto.setModifiedBy("Admin"); 
        dto.setCreatedDatetime(new Timestamp(new Date().getTime())); 
        dto.setModifiedDatetime(new Timestamp(new Date().getTime())); 
        long pk = model.add(dto); 
        System.out.println("Test add succ"); 
        CollegeDTO addedDto = model.findByPK(pk); 
        if (addedDto == null) { 
            System.out.println("Test add fail"); 
        } 
    
}    
         /*long pk = model.add(dto); */
         	
	public static void testDelete() throws ApplicationException{
		CollegeDTO dto=new CollegeDTO();
		dto.setId(1l);
		model.delete(dto);
		System.out.println("deleted successfully");
	}
    public static void testUpdate() throws ApplicationException, DuplicateRecordException{
    	CollegeDTO dto=new CollegeDTO();
		dto=model.findByPK(1);
		 dto.setName("vashnav");
         dto.setAddress("indore");
         dto.setState("mp");
         dto.setCity("indore");
         dto.setMobile("073124879");
         dto.setCreatedBy("Admin");
         dto.setModifiedBy("Admin");
         dto.setCreatedDatetime(new Timestamp(new Date().getTime()));
         dto.setModifiedDatetime(new Timestamp(new Date().getTime()));
         model.update(dto);
         System.out.println("test update successfull");
	}
    public static void testFindByName() {
    	 
        try {
            CollegeDTO dto = model.findByName("cdgi");
            if (dto == null) {
                System.out.println("Test Find By Name fail");
            }
            System.out.println(dto.getId());
            System.out.println(dto.getName());
            System.out.println(dto.getAddress());
            System.out.println(dto.getState());
            System.out.println(dto.getCity());
            System.out.println(dto.getMobile());
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
            CollegeDTO dto = new CollegeDTO();
            long pk = 2L;
            dto = model.findByPK(pk);
            if (dto == null) {
                System.out.println("Test Find By PK fail");
            }
            System.out.println(dto.getId());
            System.out.println(dto.getName());
            System.out.println(dto.getAddress());
            System.out.println(dto.getState());
            System.out.println(dto.getCity());
            System.out.println(dto.getMobile());
            System.out.println(dto.getCreatedBy());
            System.out.println(dto.getCreatedDatetime());
            System.out.println(dto.getModifiedBy());
            System.out.println(dto.getModifiedDatetime());
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
 
    }
    public static void testSearch() {
        try {
            CollegeDTO dto = new CollegeDTO();
            List list = new ArrayList();
            dto.setName("svps");
            list = model.search(dto, 1, 10);
            System.out.println(list.size());
            if (list.size() < 0) {
                System.out.println("Test Search fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                dto = (CollegeDTO) it.next();
                System.out.println(dto.getId());
                System.out.println(dto.getName());
                System.out.println(dto.getAddress());
                System.out.println(dto.getState());
                System.out.println(dto.getCity());
                System.out.println(dto.getMobile());
                System.out.println(dto.getCreatedBy());
                System.out.println(dto.getCreatedDatetime());
                System.out.println(dto.getModifiedBy());
                System.out.println(dto.getModifiedDatetime());
            }
        } catch (ApplicationException e) {
            e.printStackTrace();
        }
    }
    public static void testList() {
    	 
        try {
            CollegeDTO dto = new CollegeDTO();
            List list = new ArrayList();
            list = model.list(1, 10);
            if (list.size() < 0) {
                System.out.println("Test list fail");
            }
            Iterator it = list.iterator();
            while (it.hasNext()) {
                dto = (CollegeDTO) it.next();
                System.out.println(dto.getId());
                System.out.println(dto.getName());
                System.out.println(dto.getAddress());
                System.out.println(dto.getState());
                System.out.println(dto.getCity());
                System.out.println(dto.getMobile());
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
