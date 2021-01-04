package in.co.sunrays.proj3.model;




import in.co.sunrays.proj3.dto.CourseDTO;
import in.co.sunrays.proj3.dto.SubjectDTO;
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

public class SubjectModelHibImpl implements SubjectModelInt {
	 private static Logger log = Logger.getLogger(FacultyModelHibImpl.class);

	   
	 
	 /**
	     * Add a College
	     *
	     * @param dto
	     * @throws DatabaseException
	     *
	     */
  public long add(SubjectDTO dto) throws ApplicationException, DuplicateRecordException {
	  log.debug("Model add Started");
      long pk = 0;
      SubjectDTO duplicateCollegeName = findBySubjectName(dto.getSubjectName());

      if (duplicateCollegeName != null) {
          throw new DuplicateRecordException("College Name already exists");
      }

      CourseModelInt model = ModelFactory.getInstance().getCourseModel();
      CourseDTO cbean=model.findByPK(dto.getCourceId());
           dto.setCourceName(cbean.getName());
        
      
      Session session = HibDataSource.getSession();
      Transaction transaction = null;
      try {
    	  System.out.println("in tery model");
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
          throw new ApplicationException("Exception in College Add "
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
public void update(SubjectDTO dto) throws ApplicationException, DuplicateRecordException {
	 log.debug("Model update Started");
     Session session = null;
     Transaction transaction = null;

     SubjectDTO dtoExist = findBySubjectName(dto.getSubjectName());

     // Check if updated College already exist
     if (dtoExist != null && dtoExist.getId() != dto.getId()) {
         throw new DuplicateRecordException("College is already exist");
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
             throw new ApplicationException("Exception in College Update"
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

public void delete(SubjectDTO dto) throws ApplicationException {
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

	public SubjectDTO findByEmailId(String emailId) throws ApplicationException {
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
    public SubjectDTO findByPK(long pk) throws ApplicationException {
		log.debug("Model findByPK Started");
        Session session = null;
        SubjectDTO dto = null;
        try {
            session = HibDataSource.getSession();
            dto = (SubjectDTO) session.get(SubjectDTO.class, pk);
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
   public List search(SubjectDTO dto, int pageNo, int pageSize) throws ApplicationException {
	   log.debug("Model search Started");
       Session session = null;
       List list = null;
       try {
           session = HibDataSource.getSession();
           Criteria criteria = session.createCriteria(SubjectDTO.class);
          
           if(dto!=null) {
        	   if (dto.getId() > 0) {
                   criteria.add(Restrictions.eq("id", dto.getId()));
               }
        	   
        	   if (dto.getCourceId() > 0) {
                   criteria.add(Restrictions.eq("courceId", dto.getCourceId()));
               }
              
               if (dto.getSubjectName() != null && dto.getSubjectName().length() > 0) {
                   criteria.add(Restrictions.like("subjectName", dto.getSubjectName() + "%"));
               }
               
               if (dto.getCourceName() != null && dto.getCourceName().length() > 0) {
                   criteria.add(Restrictions.like("courceName", dto.getCourceName() + "%"));
               }
   
    
           }
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
       return list;
	}

   
   /**
    * Search College
    *
    * @param dto
    *            : Search Parameters
    * @throws DatabaseException
    */
 
	public List search(SubjectDTO dto) throws ApplicationException {
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
        Criteria criteria = session.createCriteria(SubjectDTO.class);

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


/**
* Find User by College Name
*
* @param collage
*            : get parameter
* @return dto
* @throws DatabaseException
*/


public SubjectDTO findBySubjectName(String name) throws ApplicationException {
	 log.debug("Model findByName Started");
     Session session = null;
     SubjectDTO dto = null;
     try {
         session = HibDataSource.getSession();
         Criteria criteria = session.createCriteria(SubjectDTO.class);
         criteria.add(Restrictions.eq("subjectName", name));
         List list = criteria.list();
         if (list.size() > 0) {
             dto = (SubjectDTO) list.get(0);
         }

     } catch (HibernateException e) {
    	 e.printStackTrace();
         log.error("Database Exception..", e);
         throw new ApplicationException(
        		 
                 "Exception in getting User by Login " + e.getMessage());

     } finally {
         session.close();
     }

     log.debug("Model findByName End");
     return dto;
}

}
