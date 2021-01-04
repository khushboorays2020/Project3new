package in.co.sunrays.proj3.model;

import java.sql.Connection;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.apache.log4j.Logger;
import java.sql.PreparedStatement;
import java.sql.ResultSet;

import in.co.sunrays.proj3.dto.TimeTableDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DatabaseException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.util.JDBCDataSource;

/**
 * JDBC Implementation of TimeTableModelJDBCImpl
 * 
 * @author MEMENTO
 * @version 1.0
 * @Copyright (c) SunilOS
 */
public class TimeTableModelJDBCImpl implements TimeTableModelInt {

	private static Logger log = Logger.getLogger(UserModelJDBCImpl.class);

	public Integer nextPK() throws in.co.sunrays.proj3.exception.DatabaseException {
		log.debug("Model nextPK started");
		Connection conn = null;
		int pk = 0;
		try {
			conn = in.co.sunrays.proj3.util.JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("select max(id) from st_timetable");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();

		} catch (Exception e) {
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}

	/**
	 * Find User by PK
	 * 
	 * @param pk
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 * @throws ApplicationException
	 */
	public TimeTableDTO findByPK1(long pk) throws ApplicationException {
		log.debug("Model nextPK started");
		StringBuffer sql = new StringBuffer("Select * from st_timetable where id=?");
		TimeTableDTO bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, pk);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimeTableDTO();
				bean.setId(rs.getLong(1));
				bean.setSubjectId(rs.getInt(2));
				bean.setCourseId(rs.getInt(3));
				bean.setExamDate(rs.getDate(4));
				bean.setTime(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
			}
			rs.close();
		} catch (Exception e) {
			
			throw new ApplicationException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return bean;

	}

	/**
	 * Add a Time Table
	 * 
	 * @param bean
	 * @throws ApplicationException
	 * @throws DuplicateRecordException 
	 * @throws DatabaseException
	 * 
	 */
	public long add(TimeTableDTO bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model Add Started");
		Connection conn = null;
		int pk = 0;
		TimeTableDTO existbean = findByPK1(bean.getId());

        if (existbean != null) {
            throw new DuplicateRecordException("Login Id already exists");
        }
        if(findByCourseAndSubjectId(bean)!=null){
        	throw new DuplicateRecordException("Time Table already exists");
        }
        if(findByCourseAndDate(bean)!=null){
        	throw new DuplicateRecordException("Time Table already exists");
        }
		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement("insert into st_timetable values(?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setLong(2, bean.getSubjectId());
			pstmt.setLong(3, bean.getCourseId());
			pstmt.setDate(4, new java.sql.Date(bean.getExamDate().getTime()));
			pstmt.setString(5, bean.getTime());
			pstmt.setString(6, bean.getCreatedBy());
			pstmt.setString(7, bean.getModifiedBy());
			pstmt.setTimestamp(8, bean.getCreatedDatetime());
			pstmt.setTimestamp(9, bean.getModifiedDatetime());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Time Table");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
		return pk;
	}
	/**
     * Find User by CourseAndSubjectId
     * 
     * @param login
     *            : get parameter
     * @return bean
     * @throws ApplicationException
     */

	public TimeTableDTO findByCourseAndSubjectId(TimeTableDTO tbean) throws ApplicationException {
		StringBuffer sql = new StringBuffer("Select * from st_timetable where course_id=? and subject_id=? and id!=?");
		TimeTableDTO bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, tbean.getCourseId());
			pstmt.setLong(2, tbean.getSubjectId());
			pstmt.setLong(3, tbean.getId());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimeTableDTO();
				bean.setId(rs.getLong(1));
				bean.setSubjectId(rs.getInt(2));
				bean.setCourseId(rs.getInt(3));
				bean.setExamDate(rs.getDate(4));
				bean.setTime(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
			}
			rs.close();
		} catch (Exception e) {
		
			throw new ApplicationException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return bean;

		
	}

	/**
     * Find User by CourseAndDate
     * 
     * @param login
     *            : get parameter
     * @return bean
     * @throws ApplicationException
     */

	public TimeTableDTO findByCourseAndDate(TimeTableDTO tbean) throws ApplicationException {
		StringBuffer sql = new StringBuffer("Select * from st_timetable where course_id=? and exam_date=? and id!=?");
		TimeTableDTO bean = null;
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, tbean.getCourseId());
			pstmt.setDate(2, new java.sql.Date(tbean.getExamDate().getTime()));
			pstmt.setLong(3, tbean.getId());
			System.out.println(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new TimeTableDTO();
				bean.setId(rs.getLong(1));
				bean.setSubjectId(rs.getInt(2));
				bean.setCourseId(rs.getInt(3));
				bean.setExamDate(rs.getDate(4));
				bean.setTime(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
			}
			rs.close();
		} catch (Exception e) {
		
			throw new ApplicationException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return bean;

		
	}


	/**
	 * Update a Time Table
	 * 
	 * @param bean
	 * @throws ApplicationException
	 * @throws DuplicateRecordException
	 * 
	 */
	public void update(TimeTableDTO bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model Update Started");
		Connection conn = null;
		 if(findByCourseAndSubjectId(bean)!=null){
	        	throw new DuplicateRecordException("Time Table already exists");
	        }
		
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false);
			PreparedStatement pstmt = conn.prepareStatement(
					"update st_timetable set SUBJECT_ID=?, COURSE_ID=?, EXAM_DATE=?, EXAM_TIME=?, CREATED_BY=?,MODIFIED_BY=?,CREATED_DATETIME=?,MODIFIED_DATETIME=? WHERE ID=?");
			pstmt.setLong(1, bean.getSubjectId());
			pstmt.setLong(2, bean.getCourseId());
			pstmt.setDate(3, new java.sql.Date(bean.getExamDate().getTime()));
			pstmt.setString(4, bean.getTime());
			pstmt.setString(5, bean.getCreatedBy());
			pstmt.setString(6, bean.getModifiedBy());
			pstmt.setTimestamp(7, bean.getCreatedDatetime());
			pstmt.setTimestamp(8, bean.getModifiedDatetime());
			pstmt.setLong(9, bean.getId());
			pstmt.executeUpdate();
			conn.commit();
			pstmt.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add Time Table");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model add End");
	}

	/**
	 * Search Time Table
	 * 
	 * @param bean
	 *            : Search Parameters
	 * @throws DatabaseException
	 */

	public List<TimeTableDTO> search1(TimeTableDTO bean) throws ApplicationException {
		return search1(bean, 0, 0);
	}

	/**
	 * Search TimeTable with pagination
	 * 
	 * @return list : List of TimeTable
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws DatabaseException
	 */

	public List<TimeTableDTO> search1(TimeTableDTO bean, int pageNo, int pageSize) throws ApplicationException {

		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer(
				"SELECT stt.* ,s.NAME AS SUBJECT_NAME,sc.`NAME` AS COURSE_NAME FROM st_timetable stt INNER JOIN SUBJECT s ON stt.`SUBJECT_ID`=s.`ID` INNER JOIN st_course sc ON stt.COURSE_ID=sc.`ID` ");
		boolean flag=true;
		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append("AND stt.ID= " + bean.getId());
				sql.insert(12, ",(select count(*) from st_timetable stt where stt.ID= " + bean.getId()+")");
                flag=false;
			}
			if (bean.getCourseId() > 0) {
				sql.append(" AND stt.COURSE_ID = " + bean.getCourseId());
				sql.insert(12, ",(select count(*) from st_timetable stt where stt.COURSE_ID = " + bean.getCourseId()+")");
                flag=false;
			}
			if (bean.getSubjectId() > 0) {
				sql.append(" AND stt.SUBJECT_ID = " + bean.getSubjectId());
				sql.insert(12, ",(select count(*) from st_timetable stt where stt.SUBJECT_ID = " + bean.getSubjectId()+")");
                flag=false;
			}
			/*if (bean.getExamDate() != null) {
				sql.append(" AND stt.EXAM_DATE like '" + new java.sql.Date(bean.getExamDate().getTime())+"%'");
				sql.insert(12, ",(select count(*) from st_timetable stt where stt.EXAM_DATE like'" + new java.sql.Date(bean.getExamDate().getTime()) +"%')");
                flag=false;
			}*/
			/*if (bean.getTime() != null) {
				sql.append(" AND stt.EXAM_TIME LIKE '" + bean.getTime() + "%'");
			}
			if (bean.getExamDate() != null) {
				sql.append(" AND stt.EXAM_DATE =" + bean.getExamDate() );
			}*/
		}
		 if(flag==true) {
	        	sql.insert(12, ",(select count(*) from st_timetable stt)");
	        }
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		ArrayList<TimeTableDTO> list = new ArrayList<TimeTableDTO>();
		Connection conn = null;
		System.out.println(sql.toString());
		try {
			
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				
				bean = new TimeTableDTO();
				bean.setId(rs.getLong(1));
				bean.setSubjectId(rs.getInt(2));
				bean.setCourseId(rs.getInt(3));
				bean.setExamDate(rs.getDate(4));
				bean.setTime(rs.getString(5));
				bean.setCreatedBy(rs.getString(6));
				bean.setModifiedBy(rs.getString(7));
				bean.setCreatedDatetime(rs.getTimestamp(8));
				bean.setModifiedDatetime(rs.getTimestamp(9));
		
				bean.setSubjectName(rs.getString(11));
				bean.setCourseName(rs.getString(12));
				
				list.add(bean);
			}
			
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in search user");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model search End");
		return list;
	}
	/**
     * Delete a TimeTable
     * 
     * @param bean
     * @throws ApplicationException
     */
	public void delete(TimeTableDTO bean) throws ApplicationException {

		log.debug("Model delete Started");

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM ST_TIMETABLE WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();

		} catch (Exception e) {
			log.error(e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				log.error(ex);
				throw new ApplicationException("Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in delete time table");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model delete End");

	}

	public long add() throws ApplicationException,
			DuplicateRecordException {
		// TODO Auto-generated method stub
		return 0;
	}

	public void update( )
			throws ApplicationException,
			DuplicateRecordException {
		// TODO Auto-generated method stub
		
	}

	public void delete()
			throws ApplicationException {
		// TODO Auto-generated method stub
		
	}

	public TimeTableDTO findTimeTableDuplicacy(Long courseId, String Semester, Date examdate)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	public in.co.sunrays.proj3.dto.TimeTableDTO findTimeTableDuplicacy(Long courseId, String Semester, Long subjectId)
			throws in.co.sunrays.proj3.exception.ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	public in.co.sunrays.proj3.dto.TimeTableDTO findByPK(long pk)
			throws in.co.sunrays.proj3.exception.ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	public List search(in.co.sunrays.proj3.dto.TimeTableDTO dto)
			throws in.co.sunrays.proj3.exception.ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	public List search(TimeTableDTO dto, int pageNo, int pageSize)
			throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	public List list(int pageNo, int pageSize) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

}
