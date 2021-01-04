package in.co.sunrays.proj3.model;

import java.util.List;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;


import in.co.sunrays.proj3.dto.CourseDTO;
import in.co.sunrays.proj3.dto.SubjectDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DatabaseException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.util.JDBCDataSource;
import org.apache.log4j.Logger;



public class SubjectModelJDBCImpl implements SubjectModelInt {
	
	
	/** The log. */
	private static Logger log = Logger.getLogger(CourseModelJDBCImpl.class);	
	
/**
 * Next pk.
 *
 * @return the integer
 * @throws DatabaseException the database exception
 */
public Integer nextPk() throws DatabaseException{
	
	log.debug("Model nextPK Started");
	
	Connection conn=null;
	int pk=0;
	
	try {
		conn=JDBCDataSource.getConnection();
		
	PreparedStatement p=conn.prepareStatement("SELECT MAX(ID) FROM ST_SUBJECT");
	
	ResultSet rs=p.executeQuery();
	 
	while(rs.next()){
		
		pk=rs.getInt(1);
	}
	rs.close();
	} catch (Exception e) {
		 log.error("Database Exception..", e);
         throw new DatabaseException("Exception : Exception in getting PK");
  }
	finally {
        JDBCDataSource.closeConnection(conn);
    }
    log.debug("Model nextPK End");
   
	return pk+1;
    
}

/**
 * Adds the.
 *
 * @param bean the bean
 * @return the long
 * @throws ApplicationException the application exception
 * @throws DuplicateRecordException the duplicate record exception
 */
	public long add(SubjectDTO dto) throws ApplicationException, DuplicateRecordException {
		log.debug("Model nextPK Started");
		
		  CourseModelJDBCImpl model=new CourseModelJDBCImpl();
	      CourseDTO cbean=model.findByPK(dto.getCourceId());
	      //SubjectModel model1=new SubjectModel();
	     //SubjectBean sbean=model1.findByPk(bean.getsubjectId());//subject name and cource name yaha se set hoge
	      
	     dto.setCourceName(cbean.getName());
	   // bean.setSubjectName(sbean.getSubjectName());

	     SubjectDTO duplisub=findBySubjectName(dto.getSubjectName());
	     
	     if(duplisub!=null){
	    	 throw new DuplicateRecordException("subject name already exists");
	    	 
	     }
		
		Connection conn=null;
		
		int pk=0;
		
		
		
		try {
			pk=nextPk();
			conn=JDBCDataSource.getConnection();
			
			conn.setAutoCommit(false);
			
			PreparedStatement p=conn.prepareStatement("INSERT INTO ST_SUBJECT VALUES (?,?,?,?,?,?,?,?,?,?)");
			
			p.setLong(1,pk);
			p.setLong(2,dto.getCourceId());
			p.setString(3,dto.getCourceName());
			p.setLong(4, dto.getsubjectId());
			p.setString(5,dto.getSubjectName());
			p.setString(6,dto.getDescription());
			p.setString(7,dto.getCreatedBy());
			p.setString(8,dto.getModifiedBy());
			p.setTimestamp(9,dto.getCreatedDatetime());
			p.setTimestamp(10,dto.getModifiedDatetime());
	        p.executeUpdate();
			conn.commit();
			p.close();
				
		} catch (Exception e) {
			// TODO Auto-generated catch block
			try {
				conn.rollback();
			} catch (Exception e1) {
				 throw new ApplicationException(
			                "Exception : add rollback exception " + e1.getMessage());
				 }
			e.printStackTrace();
		    throw new ApplicationException(
		            "Exception : Exception in add SUBJECT");	 
		}
		 finally {
		     JDBCDataSource.closeConnection(conn);
		 }
		 log.debug("Model add End");
		 return pk;	}

	
	/**
	 * Update.
	 *
	 * @param bean the bean
	 * @throws ApplicationException the application exception
	 * @throws DuplicateRecordException the duplicate record exception
	 */
	public void update(SubjectDTO dto) throws ApplicationException, DuplicateRecordException {
		// TODO Auto-generated method stub

		log.debug("Model update Started");
		
		Connection conn = null;
		
		try {
			conn=JDBCDataSource.getConnection();
			System.out.println("coon par");
			conn.setAutoCommit(false);
			
			PreparedStatement p=conn.prepareStatement("UPDATE ST_SUBJECT SET COURCE_ID=?,COURCE_NAME=?,SUBJECT_ID=?,SUBJECT_NAME=?,DESCRIPTION=?,CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
			System.out.println("preap par");
			p.setLong(1,dto.getCourceId());
			p.setString(2,dto.getCourceName());
			p.setLong(3,dto.getsubjectId());
			p.setString(4,dto.getSubjectName());
			p.setString(5,dto.getDescription());
			p.setString(6, dto.getCreatedBy());
			p.setString(7,dto.getModifiedBy());
			p.setTimestamp(8,dto.getCreatedDatetime());
			p.setTimestamp(9,dto.getModifiedDatetime());
			p.setLong(10,dto.getId());
			System.out.println("p par");
			int i=p.executeUpdate();
			System.out.println("exec par  "+i);
			conn.commit();
			p.close();
			}
		 catch (Exception e) {
			 //e.printStackTrace();
			 
			 log.error("Database Exception..", e);
	         try {
	             conn.rollback();
	         } catch (Exception ex) {
	        	 
	            throw new ApplicationException(
	                     "Exception : Delete rollback exception "
	                             + ex.getMessage());
	         }
	         throw new ApplicationException("Exception in updating subject ");
	        
		}
		finally {
	        JDBCDataSource.closeConnection(conn);
	    }
	    log.debug("Model update End");

	}

	
	/**
	 * Delete.
	 *
	 * @param bean the bean
	 * @throws ApplicationException the application exception
	 */

	public void delete(SubjectDTO dto) throws ApplicationException {
		
		// TODO Auto-generated method stub
		log.debug("Model delete Started");
	    Connection conn = null;
	    try {
	        conn = JDBCDataSource.getConnection();
	        conn.setAutoCommit(false); // Begin transaction
	        PreparedStatement pstmt = conn
	                .prepareStatement("DELETE FROM ST_SUBJECT WHERE ID=?");
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
	                "Exception : Exception in delete SUBJECT ");
	    } finally {
	        JDBCDataSource.closeConnection(conn);
	    }
	    log.debug("Model delete Started");

	}

	public SubjectDTO findByEmailId(String emailId) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}
	
	
	
	
	/**
	 * Find by pk.
	 *
	 * @param pk the pk
	 * @return the subject bean
	 * @throws ApplicationException the application exception
	 */

	public SubjectDTO findByPK(long pk) throws ApplicationException {
		log.debug("Model findBy Email Started");
		
		 SubjectDTO dto = null;
	     Connection conn = null;
	     
	     try {
			conn=JDBCDataSource.getConnection();
			
		PreparedStatement p=conn.prepareStatement("SELECT * FROM ST_SUBJECT WHERE ID=?");
		
	p.setLong(1,pk);

	ResultSet rs=p.executeQuery();

	while(rs.next()){
		dto=new SubjectDTO();
		dto.setId(rs.getLong(1));
		dto.setCourceId(rs.getLong(2));
		dto.setCourceName(rs.getString(3));
		dto.setsubjectId(rs.getLong(4));
		dto.setSubjectName(rs.getString(5));
		dto.setDescription(rs.getString(6));
		dto.setCreatedBy(rs.getString(7));
		dto.setModifiedBy(rs.getString(8));
		dto.setCreatedDatetime(rs.getTimestamp(9));
		dto.setModifiedDatetime(rs.getTimestamp(10));

		
		}
	rs.close();

		} catch (Exception e) {
			// TODO Auto-generated catch block
			 log.error("Database Exception..", e);
	         throw new ApplicationException(
	                 "Exception : Exception in getting SUBJECT by pk");
	         
		}
	     finally {
	         JDBCDataSource.closeConnection(conn);
	     }
	     log.debug("Model findBy pk End");
	     return dto;	}

	
	/**
	 * Search.
	 *
	 * @param bean the bean
	 * @param pageNo the page no
	 * @param pageSize the page size
	 * @return the list
	 * @throws ApplicationException the application exception
	 */
    public List search(SubjectDTO dto, int pageNo, int pageSize) throws ApplicationException {
    	 log.debug("Model search Started");
    	    StringBuffer sql = new StringBuffer("SELECT * FROM ST_SUBJECT WHERE 1=1");
    	System.out.println("chalu hua");


    	    if (dto != null) {
    	        if (dto.getId() > 0) {
    	            sql.append(" AND ID = " + dto.getId());
    	        }
    	        
    	        if (dto.getCourceId()>0) {
    	            sql.append(" AND COURCE_ID = " + dto.getCourceId());
    	        }
    	        
    	     /*   if (bean.getCourceName() != null && bean.getCourceName().length() > 0) {
    	            sql.append(" AND COURCE_NAME like '" + bean.getCourceName() + "'");
    	        }
    	        
    	        if (bean.getsubjectId()>0) {
    	        	System.out.println("subject id="+bean.getsubjectId());
    	            sql.append(" AND SUBJECT_ID = " + bean.getsubjectId() + "");
    	        }
    	       
    	        if (bean.getSubjectName() != null && bean.getSubjectName().length() > 0) {
    	        	System.out.println("subject id="+bean.getSubjectName());
    	            sql.append(" AND SUBJECT_NAME like '" + bean.getSubjectName() + "%'");
    	        }
    	       
    	        
    	        if (bean.getDescription() != null && bean.getDescription().length() > 0) {
    	            sql.append(" AND DESCRIPTION like '" + bean.getDescription()+ "%'");
    	        }
    	*/    }
    	    

    	    // if page size is greater than zero then apply pagination
    	    if (pageSize > 0) {
    	        // Calculate start record index
    	        pageNo = (pageNo - 1) * pageSize;

    	        sql.append(" Limit " + pageNo + " ," + pageSize);
    	        // sql.append(" limit " + pageNo + "," + pageSize);
    	    }
    	System.out.println("if se bahar aaya    "+sql);
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
    	        	dto = new SubjectDTO();
    	        	 
    	        	dto=new SubjectDTO();
    	        	dto.setId(rs.getLong(1));
    	        	dto.setCourceId(rs.getLong(2));
    	        	dto.setCourceName(rs.getString(3));
    	        	dto.setsubjectId(rs.getLong(4));
    	        	dto.setSubjectName(rs.getString(5));
    	        	dto.setDescription(rs.getString(6));
    	        	dto.setCreatedBy(rs.getString(7));
    	        	dto.setModifiedBy(rs.getString(8));
    	        	dto.setCreatedDatetime(rs.getTimestamp(9));
    	        	dto.setModifiedDatetime(rs.getTimestamp(10));

    	 	
    	        	
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
     * Search.
     *
     * @param bean the bean
     * @return the list
     * @throws ApplicationException the application exception
     */
    public List search(SubjectDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		  return search(dto, 0, 0);
	}

    
  
    
    /**
     * List.
     *
     * @return the list
     * @throws ApplicationException the application exception
     */

    public List list() throws ApplicationException {
		// TODO Auto-generated method stub
    	return list(0, 0);
	}

  
     /**
     * List.
     *
     * @param pageNo the page no
     * @param pageSize the page size
     * @return the list
     * @throws ApplicationException the application exception
     */
         public List list(int pageNo, int pageSize) throws ApplicationException {
		 log.debug("Model list Started");
		    ArrayList list = new ArrayList();
		    StringBuffer sql = new StringBuffer("select * from ST_SUBJECT");
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
			
			
			SubjectDTO dto=new SubjectDTO();
			dto.setId(rs.getLong(1));
			dto.setCourceId(rs.getLong(2));
			dto.setCourceName(rs.getString(3));
			dto.setsubjectId(rs.getLong(4));
			dto.setSubjectName(rs.getString(5));
			dto.setDescription(rs.getString(6));
			dto.setCreatedBy(rs.getString(7));
			dto.setModifiedBy(rs.getString(8));
			dto.setCreatedDatetime(rs.getTimestamp(9));
			dto.setModifiedDatetime(rs.getTimestamp(10));


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

         /**
     	 * Find by subject name.
     	 *
     	 * @param Name the name
     	 * @return the subject bean
     	 * @throws ApplicationException the application exception
     	 */

	public SubjectDTO findBySubjectName(String Name) throws ApplicationException {
		log.debug("Model findBy Email Started");
		
		 SubjectDTO dto = null;
	     Connection conn = null;
	     
	     try {
			conn=JDBCDataSource.getConnection();
			
		PreparedStatement p=conn.prepareStatement("SELECT * FROM ST_SUBJECT WHERE SUBJECT_NAME=?");
		
	p.setString(1,Name);

	ResultSet rs=p.executeQuery();

	while(rs.next()){
		
		dto=new SubjectDTO();
		
		dto.setId(rs.getLong(1));
		dto.setCourceId(rs.getLong(2));
		dto.setCourceName(rs.getString(3));
		dto.setsubjectId(rs.getLong(4));
		dto.setSubjectName(rs.getString(5));
		dto.setDescription(rs.getString(6));
		dto.setCreatedBy(rs.getString(7));
		dto.setModifiedBy(rs.getString(8));
		dto.setCreatedDatetime(rs.getTimestamp(9));
		dto.setModifiedDatetime(rs.getTimestamp(10));

	}
	rs.close();
	}catch (Exception e) {
		// TODO Auto-generated catch block
		 log.error("Database Exception..", e);
	    throw new ApplicationException(
	            "Exception : Exception in getting User by Subject name");
	    
	}
	finally {
	    JDBCDataSource.closeConnection(conn);
	}
	log.debug("Model findBy Subject Name End");
	return dto;

	}

}
