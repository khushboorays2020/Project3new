package in.co.sunrays.proj3.model;




import java.util.List;

import org.apache.log4j.Logger;
import org.hibernate.Criteria;
import org.hibernate.HibernateException;
import org.hibernate.Session;
import org.hibernate.Transaction;
import org.hibernate.criterion.Restrictions;

import in.co.sunrays.proj3.dto.CollegeDTO;
import in.co.sunrays.proj3.dto.CourseDTO;
import in.co.sunrays.proj3.dto.FacultyDTO;
import in.co.sunrays.proj3.dto.SubjectDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DatabaseException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.util.HibDataSource;

/**
 * Hibernate Implementation of CollegeModel
 *
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */

public class FacultyModelHibImpl implements FacultyModelInt {
	
	private static Logger log = Logger.getLogger(FacultyModelHibImpl.class);

	
    /**
     * Add a College
     *
     * @param dto
     * @throws DatabaseException
     *
     */
  public long add(FacultyDTO dto) throws ApplicationException, DuplicateRecordException {
	  log.debug("Model add Started");
	    System.out.println("Cou Id  ");
      long pk = 0;
      FacultyDTO duplicateCollegeName = findByEmailId(dto.getLogin_Id());

      if (duplicateCollegeName != null) {
          throw new DuplicateRecordException("College Name already exists");
      }
      
      

      
       System.out.println("Cou Id  "+dto.getCource_Id());
      CourseModelInt model = ModelFactory.getInstance().getCourseModel();
      CourseDTO cbean=model.findByPK(dto.getCource_Id());
      dto.setCource_Name(cbean.getName());
      //System.out.println("Cou Name  "+bean.getCollege_Name());
      CollegeModelInt model1=ModelFactory.getInstance().getCollegeModel();
       CollegeDTO cobean=model1.findByPK(dto.getCollege_Id());
      dto.setCollege_Name(cobean.getName());

      SubjectModelInt model2=ModelFactory.getInstance().getSubjectModel();
       SubjectDTO sbean=model2.findByPK(dto.getSubject_Id());
      dto.setSubject_Name(sbean.getSubjectName());        

      
      
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

	public void update(FacultyDTO dto) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
        Session session = null;
        Transaction transaction = null;

       FacultyDTO dtoExist = findByEmailId(dto.getLogin_Id());

        // Check if updated College already exist
        if (dtoExist != null && dtoExist.getId() != dto.getId()) {
            throw new DuplicateRecordException("faculty is already exist");
        }
        
        CourseModelInt model = ModelFactory.getInstance().getCourseModel();
        CourseDTO cbean=model.findByPK(dto.getCource_Id());
        dto.setCource_Name(cbean.getName());
        //System.out.println("Cou Name  "+bean.getCollege_Name());
        CollegeModelInt model1=ModelFactory.getInstance().getCollegeModel();
         CollegeDTO cobean=model1.findByPK(dto.getCollege_Id());
        dto.setCollege_Name(cobean.getName());

        SubjectModelInt model2=ModelFactory.getInstance().getSubjectModel();
         SubjectDTO sbean=model2.findByPK(dto.getSubject_Id());
        dto.setSubject_Name(sbean.getSubjectName());        



        try {

            session = HibDataSource.getSession();
            transaction = session.beginTransaction();
            session.update(dto);
            transaction.commit();
        } catch (HibernateException e) {
            log.error("Database Exception..", e);
            if (transaction != null) {
                transaction.rollback();
                throw new ApplicationException("Exception in faculty Update"
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
   
	public void delete(FacultyDTO dto) throws ApplicationException {
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

	
	/**
     * Find faculty by emailid
     *
     * @param collage
     *            : get parameter
     * @return dto
     * @throws DatabaseException
     */
    
	public FacultyDTO findByEmailId(String emailId) throws ApplicationException {
		 log.debug("Model findByName Started");
	        Session session = null;
	        FacultyDTO dto = null;
	        try {
	            session = HibDataSource.getSession();
	            Criteria criteria = session.createCriteria(FacultyDTO.class);
	            criteria.add(Restrictions.eq("Login_Id",emailId));
	            List list = criteria.list();
	            if (list.size() > 0) {
	                dto = (FacultyDTO) list.get(0);
	            }

	        } catch (HibernateException e) {
	            log.error("Database Exception..", e);
	            throw new ApplicationException(
	                    "Exception in getting User by Login " + e.getMessage());

	        } finally {
	            session.close();
	        }

	        log.debug("Model findByName End");
	        return dto;
	}

	
	
	 /**
     * Find Collage by PK
     *
     * @param pk
     *            : get parameter
     * @return dto
     * @throws DatabaseException
     */
   
	public FacultyDTO findByPK(long pk) throws ApplicationException {
		 log.debug("Model findByPK Started");
	        Session session = null;
	        FacultyDTO dto = null;
	        try {
	            session = HibDataSource.getSession();
	            dto = (FacultyDTO) session.get(FacultyDTO.class, pk);
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
   public List search(FacultyDTO dto, int pageNo, int pageSize) throws ApplicationException {
		 log.debug("Model search Started");
	        Session session = null;
	        List list = null;
	        try {
	            session = HibDataSource.getSession();
	            Criteria criteria = session.createCriteria(FacultyDTO.class);
	            
	       
	            if(dto!=null)
	            {
	            	
	            	if (dto.getFirst_Name() != null && dto.getFirst_Name().length() > 0) {
		                criteria.add(Restrictions.like("First_Name", dto.getFirst_Name() + "%"));
		            }
		            if (dto.getLogin_Id() != null && dto.getLogin_Id().length() > 0) {
		                criteria.add(Restrictions.like("Login_Id", dto.getLogin_Id()
		                        + "%"));
		            }	
	            }	
	          
	            // if page size is greater than zero the apply pagination
	            if (pageSize > 0) {
	                criteria.setFirstResult(((pageNo - 1) * pageSize));
	                criteria.setMaxResults(pageSize);
	            }

	            list = criteria.list();
	        } catch (HibernateException e) {
	        	e.printStackTrace();
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
 public List search(FacultyDTO dto) throws ApplicationException {
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
	            Criteria criteria = session.createCriteria(FacultyDTO.class);

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

}
