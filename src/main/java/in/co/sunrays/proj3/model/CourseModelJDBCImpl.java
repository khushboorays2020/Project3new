 package in.co.sunrays.proj3.model;



import in.co.sunrays.proj3.dto.CourseDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DatabaseException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.util.JDBCDataSource;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

/**
 * JDBC Implementation of CollegeModel
 *
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */
public class CourseModelJDBCImpl implements CourseModelInt {

    private static Logger log = Logger.getLogger(CollegeModelJDBCImpl.class);

    /**
     * Find next PK of College
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
                    .prepareStatement("SELECT MAX(ID) FROM ST_COURSE");
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
     * Add a College
     *
     * @param dto
     * @throws DatabaseException
     *
     */
    public long add(CourseDTO dto) throws ApplicationException,
            DuplicateRecordException {
        log.debug("Model add Started");
        Connection conn = null;
        int pk = 0;

        CourseDTO duplicateCollegeName = findByName(dto.getName());

        if (duplicateCollegeName != null) {
            throw new DuplicateRecordException("Course Name already exists");
        }

        try {
            conn = JDBCDataSource.getConnection();
            pk = nextPK();
            // Get auto-generated next primary key
            System.out.println(pk + " in ModelJDBC");
            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement p = conn
                    .prepareStatement("INSERT INTO ST_COURSE VALUES(?,?,?,?,?,?,?,?)");
            p.setLong(1,pk);
        	p.setString(2,dto.getName());
        	p.setLong(3,dto.getCourceId());
        	p.setInt(4,dto.getDuration());
        	p.setString(5,dto.getCreatedBy());
        	p.setString(6,dto.getModifiedBy());
        	p.setTimestamp(7,dto.getCreatedDatetime());
        	p.setTimestamp(8,dto.getModifiedDatetime());
        	
 p.executeUpdate();
            conn.commit(); // End transaction
            p.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            try {
                conn.rollback();
            } catch (Exception ex) {
                ex.printStackTrace();
                throw new ApplicationException(
                        "Exception : add rollback exception " + ex.getMessage());
            }
            throw new ApplicationException(
                    "Exception : Exception in add College");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model add End");
        return pk;
    }

    /**
     * Delete a College
     *
     * @param dto
     * @throws DatabaseException
     */
    public void delete(CourseDTO dto) throws ApplicationException {
        log.debug("Model delete Started");
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            conn.setAutoCommit(false); // Begin transaction
            PreparedStatement pstmt = conn
                    .prepareStatement("DELETE FROM ST_COURSE WHERE ID=?");
            pstmt.setLong(1, dto.getId());
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
                    "Exception : Exception in delete college");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model delete Started");
    }

    /**
     * Find User by College
     *
     * @param login
     *            : get parameter
     * @return dto
     * @throws DatabaseException
     */
   
    public CourseDTO findByName(String name) throws ApplicationException {
        log.debug("Model findByName Started");
        StringBuffer sql = new StringBuffer(
                "SELECT * FROM ST_COURSE WHERE NAME=?");
        CourseDTO dto = null;
        Connection conn = null;
        try {
            conn = JDBCDataSource.getConnection();
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            pstmt.setString(1, name);
            ResultSet rs = pstmt.executeQuery();
            while (rs.next()) {
                dto = new CourseDTO();
                dto.setId(rs.getLong(1));
            	dto.setName(rs.getString(2));
            	dto.setCourceId(rs.getLong(3));
            	dto.setDuration(rs.getInt(4));
            	dto.setCreatedBy(rs.getString(5));
            	dto.setModifiedBy(rs.getString(6));
            	dto.setCreatedDatetime(rs.getTimestamp(7));
            	dto.setModifiedDatetime(rs.getTimestamp(8));


            }
            rs.close();
        } catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting College by Name");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }
        log.debug("Model findByName End");
        return dto;
    }

    
    
 
   
   
    
    /**
     * Get List of College
     *
     * @return list : List of College
     * @throws DatabaseException
     */
    
    public List list() throws ApplicationException {
        return list(0, 0);
    }

    /**
     * Get List of College with pagination
     *
     * @return list : List of College
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws DatabaseException
     */
  
    public List list(int pageNo, int pageSize) throws ApplicationException {
    	log.debug("Model list Started");
        ArrayList list = new ArrayList();
        StringBuffer sql = new StringBuffer("select * from ST_COURCE");
        // if page size is greater than zero then apply pagination
        System.out.println("in hua");
        if (pageSize > 0) {
            // Calculate start record index
            pageNo = (pageNo - 1) * pageSize;
            sql.append(" limit " + pageNo + "," + pageSize);
        }
    System.out.println(sql);
        Connection conn = null;
        System.out.println("if se bahar hua");

        try {
            conn = JDBCDataSource.getConnection();
            System.out.println("conn creat hua");
            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
            System.out.println("preapred ");
            ResultSet rs = pstmt.executeQuery();
            System.out.println("resultset");
    while(rs.next()){
    	
    	
    	CourseDTO dto=new CourseDTO();
    	
    	dto.setId(rs.getLong(1));
        dto.setName(rs.getString(2));
        dto.setCourceId(rs.getLong(3));
        dto.setDuration(rs.getInt(4));
        dto.setCreatedBy(rs.getString(5));
        dto.setModifiedBy(rs.getString(6));
        dto.setCreatedDatetime(rs.getTimestamp(7));
        dto.setModifiedDatetime(rs.getTimestamp(8));
       list.add(dto);
       }
    rs.close();

    }
        catch (Exception e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting list of Cource");
        } finally {
            JDBCDataSource.closeConnection(conn);
        }

        log.debug("Model list End");
        return list;

    }

	
	public CourseDTO findByEmailId(String emailId) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	
	 /**
     * Find User by College
     *
     * @param pk
     *            : get parameter
     * @return dto
     * @throws DatabaseException
     */
   
	public CourseDTO findByPK(long pk) throws ApplicationException {
		// TODO Auto-generated method stub
		 log.debug("Model findByPK Started");
	        StringBuffer sql = new StringBuffer(
	                "SELECT * FROM ST_COURSE WHERE ID=?");
	        CourseDTO dto = null;
	        Connection conn = null;
	        try {

	            conn = JDBCDataSource.getConnection();
	            PreparedStatement pstmt = conn.prepareStatement(sql.toString());
	            pstmt.setLong(1, pk);
	            ResultSet rs = pstmt.executeQuery();
	            while (rs.next()) {
	                dto = new CourseDTO();
	                dto.setId(rs.getLong(1));
	            	dto.setName(rs.getString(2));
	            	dto.setCourceId(rs.getLong(3));
	            	dto.setDuration(rs.getInt(4));
	                 dto.setCreatedBy(rs.getString(5));
	            	dto.setModifiedBy(rs.getString(6));
	            	dto.setCreatedDatetime(rs.getTimestamp(7));
	            	dto.setModifiedDatetime(rs.getTimestamp(8));
	            	
	            }
	            rs.close();
	        } catch (Exception e) {
	            log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception : Exception in getting College by pk");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	        log.debug("Model findByPK End");
	        return dto;

	}
	
	 /**
     * Search College with pagination
     *
     * @return list : List of Users
     * @param dto
     *            : Search Parameters
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     *
     * @throws DatabaseException
     */
   

	public List search(CourseDTO dto, int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		 log.debug("Model search Started");
		    StringBuffer sql = new StringBuffer("SELECT * FROM ST_COURCE WHERE 1=1");
		System.out.println("chalu hua");

		if(dto.getCourceId()>0){
		CourseModelJDBCImpl cmod = new CourseModelJDBCImpl();
		CourseDTO cbe = cmod.findByPK(dto.getCourceId());
		dto.setName(cbe.getName());
		}

		    if (dto != null) {
		        if (dto.getId() > 0) {
		            sql.append(" AND ID = " + dto.getId());
		        }
		        
		       /* if (bean.getName() != null && bean.getName().length() > 0) {
		            sql.append(" AND NAME like '" + bean.getName()
		                    + "%'");
		        }
		        
		        if (bean.getCourceId()>0) {
		            sql.append(" AND COURCE_ID like '" + bean.getCourceId() + "'");
		        }
		       
		        
		        if (bean.getDuration()>0) {
		            sql.append(" AND DURATION like '" + bean.getDuration() + "%'");
		        }*/
		       
		    }
		    

		    // if page size is greater than zero then apply pagination
		    if (pageSize > 0) {
		        // Calculate start record index
		        pageNo = (pageNo - 1) * pageSize;

		        sql.append(" Limit " + pageNo + ", " + pageSize);
		        // sql.append(" limit " + pageNo + "," + pageSize);
		    }
		System.out.println("query====   "+sql);
		    ArrayList list = new ArrayList();
		    Connection conn = null;
		    System.out.println("conn ke par hua");
		    try {
		        conn = JDBCDataSource.getConnection();
		        System.out.println("connection create hua");
		        PreparedStatement pstmt = conn.prepareStatement(sql.toString());
		        System.out.println("preap se bahar hua");
		        ResultSet rs = pstmt.executeQuery();
		        
		System.out.println("result set ke bahar aaya");
		        while(rs.next()){
		        	dto = new CourseDTO();	
		        dto.setId(rs.getLong(1));
		        dto.setName(rs.getString(2));
		        dto.setCourceId(rs.getLong(3));
		        dto.setDuration(rs.getInt(4));
		        dto.setCreatedBy(rs.getString(5));
		        dto.setModifiedBy(rs.getString(6));
		        dto.setCreatedDatetime(rs.getTimestamp(7));
		        dto.setModifiedDatetime(rs.getTimestamp(8));
		        list.add(dto);
		        System.out.println("khatam hua");
		         }
		        rs.close();
		        }
		    catch (Exception e) {
		        log.error("Database Exception..", e);
		        throw new ApplicationException(
		                "Exception : Exception in search Cource");
		    } finally {
		        JDBCDataSource.closeConnection(conn);
		    }

		    log.debug("Model search End");
		    return list;


	}

	 /**
     * Search College
     *
     * @param dto
     *            : Search Parameters
     * @throws DatabaseException
     */
  
  
	public List search(CourseDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	  /**
     * Update a College
     *
     * @param dto
     * @throws DatabaseException
     */
    
	public void update(CourseDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub
		 log.debug("Model update Started");
	        Connection conn = null;

	        CourseDTO dtoExist = findByName(dto.getName());

	        // Check if updated College already exist
	        if (dtoExist != null && dtoExist.getId() != dto.getId()) {

	            throw new DuplicateRecordException("Course is already exist");
	        }

	        try {

	            conn = JDBCDataSource.getConnection();

	            conn.setAutoCommit(false); // Begin transaction
	            PreparedStatement p = conn
	                    .prepareStatement("UPDATE ST_COURCE SET NAME=?,COURCE_ID=?,DURATION=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
	            p.setString(1,dto.getName());
	    		p.setLong(2,dto.getCourceId());
	    		p.setLong(3,dto.getDuration());
	    		p.setString(4,dto.getCreatedBy());
	    		p.setString(5,dto.getModifiedBy());
	    		p.setTimestamp(6,dto.getCreatedDatetime());
	    		p.setTimestamp(7,dto.getModifiedDatetime());
	    		p.setLong(8,dto.getId());
	 p.executeUpdate();
	            conn.commit(); // End transaction
	            p.close();
	        } catch (Exception e) {
	            log.error("Database Exception..", e);
	            try {
	                conn.rollback();
	            } catch (Exception ex) {
	                throw new ApplicationException(
	                        "Exception : Delete rollback exception "
	                                + ex.getMessage());
	            }
	            throw new ApplicationException("Exception in updating College ");
	        } finally {
	            JDBCDataSource.closeConnection(conn);
	        }
	        log.debug("Model update End");

		
	}

	
		}


