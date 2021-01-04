package in.co.sunrays.proj3.model;


import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.sunrays.proj3.dto.CollegeDTO;
import in.co.sunrays.proj3.dto.CourseDTO;
import in.co.sunrays.proj3.dto.FacultyDTO;
import in.co.sunrays.proj3.dto.StudentDTO;
import in.co.sunrays.proj3.dto.SubjectDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DatabaseException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.util.JDBCDataSource;



public class FacultyModelJDBCImpl implements FacultyModelInt {
	
	 private static Logger log = Logger.getLogger(FacultyModelJDBCImpl.class);

	    /**
	     * Find next PK of Faculty
	     * 
	     * @throws DatabaseException
	     */
	    public Integer nextPK() throws DatabaseException {
	        log.debug("Model nextPK Started");
	        Connection conn = null;
	        int pk = 0;
	        try {
	            conn = JDBCDataSource.getConnection();
	            PreparedStatement pstmt = conn
	                    .prepareStatement("SELECT MAX(ID) FROM ST_FACULTY");
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	                pk = rs.getInt(1);
	            }
	            rs.close();

	        } catch (Exception e) {
	            log.error("Database Exception..", e);
	            throw new DatabaseException("Exception : Exception in getting PK");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	        log.debug("Model nextPK End");
	        return pk + 1;
	    }

	

	    /**
	     * Add a Faculty
	     * 
	     * @param bean
	     * @throws DatabaseException
	     * 
	     */

	public long add(FacultyDTO dto) throws ApplicationException, DuplicateRecordException {
		log.debug("Model add Started");
        Connection conn = null;

        // get College Name
                int pk = 0;
               // System.out.println("Cou Id  "+bean.getCource_Id());
                CourseModelJDBCImpl model=new CourseModelJDBCImpl();
                CourseDTO cbean=model.findByPK(dto.getCource_Id());
                dto.setCource_Name(cbean.getName());
                //System.out.println("Cou Name  "+bean.getCollege_Name());
                CollegeModelJDBCImpl model1=new CollegeModelJDBCImpl();
                CollegeDTO cobean=model1.findByPK(dto.getCollege_Id());
                dto.setCollege_Name(cobean.getName());

                SubjectModelJDBCImpl model2=new SubjectModelJDBCImpl();
               SubjectDTO sbean=model2.findByPK(dto.getSubject_Id());
                dto.setSubject_Name(sbean.getSubjectName());        
                 
        FacultyDTO duplicateLogin=findByEmailId(dto.getLogin_Id());
        System.out.println( );
        if(duplicateLogin!=null){
        	throw new DuplicateRecordException("logine id allready exists");
        	
        }
                
                
        try {
            conn = JDBCDataSource.getConnection();
            pk = nextPK();
            // Get auto-generated next primary key
            System.out.println(pk + " in ModelJDBC");
            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("INSERT INTO ST_FACULTY VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
            pstmt.setLong(1,pk);
            pstmt.setString(2, dto.getFirst_Name());
            pstmt.setString(3, dto.getLast_Name());
            pstmt.setString(4, dto.getGrnder());
            pstmt.setString(5, dto.getLogin_Id());
            pstmt.setDate(6,new java.sql.Date(dto.getDate_Of_joining().getTime()));
            pstmt.setString(7, dto.getMobile_No());
            pstmt.setString(8,dto.getAddress());
            pstmt.setString(9,dto.getQualification());
            pstmt.setInt(10, dto.getCource_Id());
            pstmt.setString(11, dto.getCource_Name());
            pstmt.setInt(12, dto.getCollege_Id());
            pstmt.setString(13, dto.getCollege_Name());
            pstmt.setInt(14, dto.getSubject_Id());
            pstmt.setString(15, dto.getSubject_Name());
            pstmt.setString(16, dto.getCreatedBy());
            pstmt.setString(17, dto.getModifiedBy());
            pstmt.setTimestamp(18,dto.getCreatedDatetime());
            pstmt.setTimestamp(19,dto.getModifiedDatetime());
           System.out.println("add k bahar");
            
            
          
             pstmt.executeUpdate();
            
            conn.commit(); // End transaction
            pstmt.close();
           
        } catch (Exception e) {
            log.error("Database Exception..", e);
            try {
                conn.rollback();
            } catch (Exception ex) {
                throw new ApplicationException(
                        "Exception : add rollback exception " + ex.getMessage());
            }
            e.printStackTrace();
            throw new ApplicationException(
                    "Exception : Exception in add faculty");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model add End");
        return pk;
}

    /**
     * Update a Student
     * 
     * @param bean
     * @throws DatabaseException
     */

	public void update(FacultyDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		
		 log.debug("Model update Started");
	        Connection conn = null;
	        
	        CourseModelJDBCImpl model=new CourseModelJDBCImpl();
	        CourseDTO cbean=model.findByPK(dto.getCource_Id());
	        dto.setCource_Name(cbean.getName());
	        //System.out.println("Cou Name  "+bean.getCollege_Name());
	        CollegeModelJDBCImpl model1=new CollegeModelJDBCImpl();
	        CollegeDTO cobean=model1.findByPK(dto.getCollege_Id());
	        dto.setCollege_Name(cobean.getName());

	        SubjectModelJDBCImpl model2=new SubjectModelJDBCImpl();
	       SubjectDTO sbean=model2.findByPK(dto.getSubject_Id());
	        dto.setSubject_Name(sbean.getSubjectName());
	      
	        try {

	            conn = JDBCDataSource.getConnection();

	            conn.setAutoCommit(false); // Begin transaction
	            PreparedStatement pstmt = conn
	                    .prepareStatement("UPDATE ST_FACULTY SET FIRST_NAME=?,LAST_NAME=?,GENDER=?,LOGIN_ID=?,DATE_OF_JOINING=?,MOBILE_NO=?,ADDRESS=?"
	                + "QUALIFICATION=?,COURCE_ID=?,COURCE_NAME=?,COLLEGE_ID=?,COLLEGE_NAME=?,SUBJECT_ID=?,SUBJECT_NAME=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
	            
	            pstmt.setString(1, dto.getFirst_Name());
	            pstmt.setString(2, dto.getLast_Name());
	            pstmt.setString(3, dto.getGrnder());
	            pstmt.setString(4, dto.getLogin_Id());
	            pstmt.setDate(5,  new java.sql.Date(dto.getDate_Of_joining().getTime()));
	            pstmt.setString(6, dto.getMobile_No());
	            pstmt.setString(7, dto.getAddress());
	            pstmt.setString(8, dto.getQualification());
	            pstmt.setInt(9, dto.getCource_Id());
	            pstmt.setString(10,dto.getCource_Name());
	            pstmt.setInt(11, dto.getCollege_Id());
	            pstmt.setString(12,dto.getCollege_Name());
	            pstmt.setInt(13, dto.getSubject_Id());
	            pstmt.setString(14,dto.getSubject_Name());
	            pstmt.setString(15, dto.getCreatedBy());
	            pstmt.setString(16,dto.getModifiedBy());
	            pstmt.setTimestamp(17,dto.getCreatedDatetime());
	            pstmt.setTimestamp(18, dto.getModifiedDatetime());
	            pstmt.setLong(19,dto.getId());
	          
	            
	            pstmt.executeUpdate();
	            conn.commit(); // End transaction
	            pstmt.close();
	        } catch (Exception e) {
	        	e.printStackTrace();
	            log.error("Database Exception..", e);
	           /* try {
	                conn.rollback();
	            } catch (Exception ex) {
	                throw new ApplicationException(
	                        "Exception : Delete rollback exception "
	                                + ex.getMessage());
	            }
	            throw new ApplicationException("Exception in updating Student ");
	        */} finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	        log.debug("Model update End");

		
	}
	
	/**
     * Delete a Student
     * 
     * @param bean
     * @throws DatabaseException
     */
   
	public void delete(FacultyDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		 log.debug("Model delete Started");
	        Connection conn = null;
	        try {
	            conn = JDBCDataSource.getConnection();
	            conn.setAutoCommit(false); // Begin transaction
	            PreparedStatement pstmt = conn
	                    .prepareStatement("DELETE FROM ST_FACULTY WHERE ID=?");
	            pstmt.setLong(1,dto.getId());
	           
	            pstmt.executeUpdate();
	           
	            conn.commit(); // End transaction
	           
	            pstmt.close();
	           

	        } catch (Exception e) {
	            log.error("Database Exception..", e);
	            try {
	                conn.rollback();
	            } catch (Exception ex) {
	                throw new ApplicationException(
	                        "Exception : Delete rollback exception "
	                                + ex.getMessage());
	            }
	            throw new ApplicationException(
	                    "Exception : Exception in delete Faculty");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	        log.debug("Model delete Started");

		
	}

	 /**
     * Find User by Student
     * 
     * @param Email
     *            : get parameter
     * @return bean
     * @throws DatabaseException
     */

	public FacultyDTO findByEmailId(String emailId) throws ApplicationException {
		log.debug("Model findBy Email Started");
        StringBuffer sql = new StringBuffer(
                "SELECT * FROM ST_FACULTY WHERE LOGIN_ID=?");
        FacultyDTO dto = null;
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, emailId);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                dto = new FacultyDTO();
                dto.setId(rs.getLong(1));
                dto.setFirst_Name(rs.getString(2));
                dto.setLast_Name(rs.getString(3));
                dto.setGrnder(rs.getString(4));
                dto.setLogin_Id(rs.getString(5));
                dto.setDate_Of_joining(rs.getDate(6));
                dto.setMobile_No(rs.getString(7));
                dto.setAddress(rs.getString(8));
                dto.setCource_Id(rs.getInt(9));
                dto.setCollege_Name(rs.getString(10));
               
                dto.setSubject_Name(rs.getString(11));
                dto.setCreatedBy(rs.getString(12));
                dto.setModifiedBy(rs.getString(13));;
                dto.setCreatedDatetime(rs.getTimestamp(14));
                dto.setModifiedDatetime(rs.getTimestamp(15));
                dto.setCollege_Id(rs.getInt(16));
                dto.setSubject_Id(rs.getInt(17));

            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting User by Email");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model findBy login_id End");
        return dto;	}
	
	
	 /**
     * Find faculty by name
     * 
     * @param Email
     *            : get parameter
     * @return bean
     * @throws DatabaseException
     */

	public FacultyDTO findByName(String name) throws ApplicationException {
		log.debug("Model findBy Email Started");
        StringBuffer sql = new StringBuffer(
                "SELECT * FROM ST_FACULTY WHERE FIRST_NAME=?");
        FacultyDTO dto = null;
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                dto = new FacultyDTO();
                dto.setId(rs.getLong(1));
                dto.setFirst_Name(rs.getString(2));
                dto.setLast_Name(rs.getString(3));
                dto.setGrnder(rs.getString(4));
                dto.setLogin_Id(rs.getString(5));
                dto.setDate_Of_joining(rs.getDate(6));
                dto.setMobile_No(rs.getString(7));
                dto.setAddress(rs.getString(8));
                dto.setCource_Id(rs.getInt(9));
                dto.setCollege_Name(rs.getString(10));
               
                dto.setSubject_Name(rs.getString(11));
                dto.setCreatedBy(rs.getString(12));
                dto.setModifiedBy(rs.getString(13));;
                dto.setCreatedDatetime(rs.getTimestamp(14));
                dto.setModifiedDatetime(rs.getTimestamp(15));
                dto.setCollege_Id(rs.getInt(16));
                dto.setSubject_Id(rs.getInt(17));

            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting User by Email");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model findBy login_id End");
        return dto;	}
	

	
	
	 /**
     * Find Student by PK
     * 
     * @param pk
     *            : get parameter
     * @return bean
     * @throws DatabaseException
     */

	public FacultyDTO findByPK(long pk) throws ApplicationException {
		 log.debug("Model findByPK Started");
	        StringBuffer sql = new StringBuffer("SELECT * FROM ST_FACULTY WHERE ID=?");
	        FacultyDTO dto = null;
	        Connection conn = null;
	        try {
	            conn = JDBCDataSource.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	            pstmt.setLong(1, pk);
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	            	 dto = new FacultyDTO();
	                 dto.setId(rs.getLong(1));
	                 dto.setFirst_Name(rs.getString(2));
	                 dto.setLast_Name(rs.getString(3));
	                 dto.setGrnder(rs.getString(4));
	                 dto.setLogin_Id(rs.getString(5));
	                 dto.setDate_Of_joining(rs.getDate(6));
	                 dto.setMobile_No(rs.getString(7));
	                 dto.setAddress(rs.getString(8)); 
	                 dto.setQualification(rs.getString(9));
	                 dto.setCource_Id(rs.getInt(10));
	                 dto.setCource_Name(rs.getString(11));
	                 dto.setCollege_Id(rs.getInt(12));
	                 dto.setCollege_Name(rs.getString(13));
	                 dto.setSubject_Id(rs.getInt(14));
	                 dto.setSubject_Name(rs.getString(15));
	                 dto.setCreatedBy(rs.getString(16));
	                 dto.setModifiedBy(rs.getString(17));;
	                 dto.setCreatedDatetime(rs.getTimestamp(18));
	                 dto.setModifiedDatetime(rs.getTimestamp(19));
	                
	            	
	               }
	            rs.close();
	        } catch (Exception e) {
	        	e.printStackTrace();
	            log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception : Exception in getting Faculty by pk");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	        log.debug("Model findByPK End");
	        return dto;
	}

	 /**
     * Search Student with pagination
     * 
     * @return list : List of Students
     * @param bean
     *            : Search Parameters
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * 
     * @throws DatabaseException
     */


	public List search(FacultyDTO dto, int pageNo, int pageSize) throws ApplicationException {
		 log.debug("Model search Started");
	        StringBuffer sql = new StringBuffer("SELECT * FROM ST_FACULTY WHERE 1=1");
	System.out.println("search start");
	        if (dto != null) {
	            if (dto.getId() > 0) {
	                sql.append(" AND id = " + dto.getId());
	            }
	            if (dto.getFirst_Name() != null && dto.getFirst_Name().length() > 0) {
	                sql.append(" AND FIRST_NAME like '" + dto.getFirst_Name()
	                        + "%'");
	                }  
	            
	            if (dto.getLast_Name() != null && dto.getLast_Name().length() > 0) {
	                sql.append(" AND LAST_NAME like '" + dto.getLast_Name() + "%'");
	            }
	            
	            if (dto.getGrnder() != null && dto.getGrnder().length() > 0) {
	                sql.append(" AND GENDER like '" + dto.getGrnder()
	                        + "%'");
	            }
	          
	            if (dto.getLogin_Id() != null && dto.getLogin_Id().length() > 0) {
	                sql.append(" AND LOGIN_ID like '" + dto.getLogin_Id()
	                        + "%'");
	            }
	            
	            if (dto.getDate_Of_joining() != null) {
	                sql.append(" AND DATE_OF_JOINING like '" + dto.getDate_Of_joining()
	                        + "%'");
	            }
	          
	            if (dto.getMobile_No() != null && dto.getMobile_No().length() > 0) {
	                sql.append(" AND MOBILE_NO like '" + dto.getMobile_No()
	                        + "%'");
	            }
	          
	            if (dto.getCource_Id()>0) {
	                sql.append(" AND COURCE_ID = " + dto.getCource_Id());
	            }
	            
	            if (dto.getCource_Name() != null && dto.getCource_Name().length() > 0) {
	                sql.append(" AND COURCE_NAME like '" + dto.getCource_Name()
	                        + "%'");
	            }
	           
	            
	            if (dto.getCollege_Id()>0) {
	                sql.append(" AND COLLEGE_ID = " +dto.getCollege_Id());
	            }
	           
	            if (dto.getCollege_Name() != null && dto.getCollege_Name().length() > 0) {
	                sql.append(" AND COLLEGE_NAME like '" + dto.getCollege_Name() + "%'");
	            }
	            
	            if (dto.getSubject_Id()>0) {
	                sql.append(" AND SUBJECT_ID = " + dto.getSubject_Id());
	            }
	           
	            if (dto.getSubject_Name() != null && dto.getSubject_Name().length() > 0) {
	                sql.append(" AND SUBJECT_NAME like '" + dto.getSubject_Name()
	                        + "%'");
	            }
	            System.out.println("search end");
	                   }

	        // if page size is greater than zero then apply pagination
	        if (pageSize > 0) {
	            // Calculate start record index
	            pageNo = (pageNo - 1) * pageSize;

	            sql.append(" Limit " + pageNo + ", " + pageSize);
	            // sql.append(" limit " + pageNo + "," + pageSize);
	        }

	        ArrayList list = new ArrayList();
	        Connection conn = null;
	        System.out.println("connection par");
	        try {
	            conn = JDBCDataSource.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	            System.out.println("preap par");
	            ResultSet rs = pstmt.executeQuery();
	            System.out.println("resultset par");
	           
	            while (rs.next()) {
	                dto = new FacultyDTO();
	                System.out.println("rs.next");
	                dto.setId(rs.getLong(1));
	                dto.setFirst_Name(rs.getString(2));
	                dto.setLast_Name(rs.getString(3));
	                dto.setGrnder(rs.getString(4));
	                dto.setLogin_Id(rs.getString(5));
	                dto.setDate_Of_joining(rs.getDate(6));
	                dto.setMobile_No(rs.getString(7));
	                dto.setAddress(rs.getString(8));
	                dto.setQualification(rs.getString(9));
	                dto.setCource_Id(rs.getInt(10));
	                dto.setCource_Name(rs.getString(11));
	                dto.setCollege_Id(rs.getInt(12));
	                dto.setCollege_Name(rs.getString(13));
	                dto.setSubject_Id(rs.getInt(14));
	                dto.setSubject_Name(rs.getString(15));
	                 dto.setCreatedBy(rs.getString(16));
	                dto.setModifiedBy(rs.getString(17));;
	                dto.setCreatedDatetime(rs.getTimestamp(18));
	                dto.setModifiedDatetime(rs.getTimestamp(19));
	           	
	              list.add(dto);
	            }
	            rs.close();
	        } catch (Exception e) {
	        	e.printStackTrace();
	            log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception : Exception in search Faculty");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }

	        log.debug("Model search End");
	        return list;
	}

	/**
     * Search Student
     * 
     * @param bean
     *            : Search Parameters
     * @throws DatabaseException
     */

	public List search(FacultyDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return search(dto, 0, 0);
	}

	
	 /**
     * Get List of faculty
     * 
     * @return list : List of Student
     * @throws DatabaseException
     */

	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return list(0, 0);
	}

	 /**
     * Get List of Student with pagination
     * 
     * @return list : List of Student
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws DatabaseException
     */

	public List list(int pageNo, int pageSize) throws ApplicationException {
		  log.debug("Model list Started");
	        ArrayList list = new ArrayList();
	        StringBuffer sql = new StringBuffer("select * from ST_FACULTY");
	        // if page size is greater than zero then apply pagination
	        if (pageSize > 0) {
	            // Calculate start record index
	            pageNo = (pageNo - 1) * pageSize;
	            sql.append(" limit " + pageNo + "," + pageSize);
	        }

	        Connection conn = null;

	        try {
	            conn = JDBCDataSource.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	                FacultyDTO bean=new FacultyDTO();
	                
	                bean.setId(rs.getLong(1));
	                bean.setFirst_Name(rs.getString(2));
	                bean.setLast_Name(rs.getString(3));
	                bean.setGrnder(rs.getString(4));
	                bean.setLogin_Id(rs.getString(5));
	                bean.setDate_Of_joining(rs.getDate(6));
	                bean.setMobile_No(rs.getString(7));
	                bean.setAddress(rs.getString(8));
	                bean.setQualification(rs.getString(9));
	                bean.setCource_Id(rs.getInt(10));
	                bean.setCource_Name(rs.getString(11));
	                bean.setCollege_Id(rs.getInt(12));
	                bean.setCollege_Name(rs.getString(13));
	                bean.setSubject_Id(rs.getInt(14));
	                bean.setSubject_Name(rs.getString(15));
	                 bean.setCreatedBy(rs.getString(16));
	                bean.setModifiedBy(rs.getString(17));
	                
	                bean.setCreatedDatetime(rs.getTimestamp(18));
	                bean.setModifiedDatetime(rs.getTimestamp(19));
	           	
	                               list.add(bean);
	            }
	            rs.close();
	        } catch (Exception e) {
	            log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception : Exception in getting list of Faculty");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }

	        log.debug("Model list End");
	        return list;
	}

}
