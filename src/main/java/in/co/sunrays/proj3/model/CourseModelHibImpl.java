package in.co.sunrays.proj3.model;




import in.co.sunrays.proj3.dto.CourseDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DatabaseException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.util.HibDataSource;

import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

/**
 * Hibernate Implementation of CollegeModel
 *
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */

public class CourseModelHibImpl implements CourseModelInt{
	   private static Logger log = Logger.getLogger(CourseModelHibImpl.class);

	    /**
	     * Add a College
	     *
	     * @param dto
	     * @throws DatabaseException
	     *
	     */

	public long add(CourseDTO dto) throws ApplicationException, DuplicateRecordException {
		 log.debug("Model add Started");
	        long pk = 0;
	        CourseDTO duplicateCollegeName = findByName(dto.getName());

	        if (duplicateCollegeName != null) {
	            throw new DuplicateRecordException("Course Name already exists");
	        }

	        Session session = HibDataSource.getSession();
	        Transaction transaction = null;
	        try {
	            transaction = session.beginTransaction();
	            session.save(dto);
	            pk = dto.getId();
	            transaction.commit();
	        } catch (HibernateException e) {
	            e.printStackTrace();
	            log.error("Database Exception..", e);
	            if (transaction != null) {
	                transaction.rollback();
	            }
	            throw new ApplicationException("Exception in Add Course "
	                    + e.getMessage());
	        } finally {
	            session.close();
	        }

	        log.debug("Model add End");
	        return dto.getId();
	}
	

	 /**
     * Update a Collage
     *
     * @param dto
     * @throws DatabaseException
     */
	public void update(CourseDTO dto) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
        Session session = null;
        Transaction transaction = null;

        CourseDTO dtoExist = findByName(dto.getName());

        // Check if updated College already exist
        if (dtoExist != null && dtoExist.getId() != dto.getId()) {
            throw new DuplicateRecordException("Course is already exist");
        }

        try {

            session = HibDataSource.getSession();
            transaction = session.beginTransaction();
            session.update(dto);
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();
                throw new ApplicationException("Exception in Course Update"
                        + e.getMessage());
            }
        } finally {
            session.close();
        }
        log.debug("Model update End");
	
	}


    /**
     * Delete a College
     *
     * @param dto
     * @throws DatabaseException
     */
    	public void delete(CourseDTO dto) throws ApplicationException {
    		 log.debug("Model delete Started");
    	        Session session = null;
    	        Transaction transaction = null;
    	        try {
    	            session = HibDataSource.getSession();
    	            transaction = session.beginTransaction();
    	            session.delete(dto);
    	            transaction.commit();
    	        } catch (HibernateException e) {
    	            log.error("Database Exception..", e);
    	            if (transaction != null) {
    	                transaction.rollback();
    	            }
    	            throw new ApplicationException("Exception in college Delete"
    	                    + e.getMessage());
    	        } finally {
    	            session.close();
    	        }
    	        log.debug("Model delete End");
    	   	}

	public CourseDTO findByEmailId(String emailId) throws ApplicationException {
		// TODO Auto-generated method stub
		return null;
	}

	
	/**
     * Find Collage by PK
     *
     * @param pk
     *            : get parameter
     * @return dto
     * @throws DatabaseException
     */
    
	public CourseDTO findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
        Session session = null;
        CourseDTO dto = null;
        try {
            session = HibDataSource.getSession();
            dto = (CourseDTO) session.get(CourseDTO.class, pk);
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            throw new ApplicationException(
                    "Exception : Exception in getting College by pk");
        } finally {
            session.close();
        }

        log.debug("Model findByPK End");
        return dto;	}

	
	
	 /**
     * Searches Colleges with pagination
     *
     * @return list : List of Colleges
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
		log.debug("Model search Started");
        Session session = null;
        List list = null;
        try {
            session = HibDataSource.getSession();
            Criteria criteria = session.createCriteria(CourseDTO.class);

            if(dto!=null) {
            	if (dto.getId() > 0) {
                    criteria.add(Restrictions.eq("id", dto.getId()));
                }
                if (dto.getName() != null && dto.getName().length() > 0) {
                    criteria.add(Restrictions.like("name", dto.getName() + "%"));
                }

            	
            }
            
            
           /*            */
            // if page size is greater than zero the apply pagination
            if (pageSize > 0) {
                criteria.setFirstResult(((pageNo - 1) * pageSize));
                criteria.setMaxResults(pageSize);
            }

            list = criteria.list();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            throw new ApplicationException("Exception in college search");
        } finally {
            session.close();
        }

        log.debug("Model search End");
        return list;	}

	
	/**
     * Search College
     *
     * @param dto
     *            : Search Parameters
     * @throws DatabaseException
     */
  
	public List search(CourseDTO dto) throws ApplicationException {
		// TODO Auto-generated method stub
		  return search(dto, 0, 0);
	}

	
	 /**
     * Gets List of College
     *
     * @return list : List of College
     * @throws DatabaseException
     */
   
	public List list() throws ApplicationException {
		// TODO Auto-generated method stub
		  return list(0, 0);
	}

	
	/**
     * get List of College with pagination
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
	        Session session = null;
	        List list = null;
	        try {
	            session = HibDataSource.getSession();
	            Criteria criteria = session.createCriteria(CourseDTO.class);

	            // if page size is greater than zero then apply pagination
	            if (pageSize > 0) {
	                pageNo = ((pageNo - 1) * pageSize) + 1;
	                criteria.setFirstResult(pageNo);
	                criteria.setMaxResults(pageSize);
	            }

	            list = criteria.list();
	        } catch (HibernateException e) {
	            log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception : Exception in  College list");
	        } finally {
	            session.close();
	        }

	        log.debug("Model list End");
	        return list;
	}

	public CourseDTO findByName(String name) throws ApplicationException {
		 log.debug("Model findByName Started");
	        Session session = null;
	        CourseDTO dto = null;
	        try {
	            session = HibDataSource.getSession();
	            Criteria criteria = session.createCriteria(CourseDTO.class);
	            criteria.add(Restrictions.eq("name", name));
	            List list = criteria.list();
	            if (list.size() > 0) {
	                dto = (CourseDTO) list.get(0);
	            }

	        } catch (HibernateException e) {
	            log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception in getting User by Login " + e.getMessage());

	        } finally {
	            session.close();
	        }

	        log.debug("Model findByName End");
	        return dto;	}

}
