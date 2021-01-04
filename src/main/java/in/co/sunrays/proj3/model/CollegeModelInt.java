package in.co.sunrays.proj3.model;



import in.co.sunrays.proj3.dto.CollegeDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DatabaseException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;

import java.util.List;

/**
 * Data Access Object of college
 *
 * @author SUNRAYS Technologies
 * @version 1.0

 */

public interface CollegeModelInt {

    /**
     * Add a college
     *@param  dto : get data 
  
     * 
     *         @throws  ApplicationException : throws when course already exists
           @throws DuplicateRecordException  : throws when course already exists
           @return : data 
     */
    public long add(CollegeDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Update a college
     *
     * @param dto : get data
     * @throws ApplicationException
     * : if updated college record is already exist
     * @throws DuplicateRecordException
     *             : if updated college record is already exist
     */
    public void update(CollegeDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Delete a college
     *
     * @param dto : get attribute
     * @throws ApplicationException : not found 
     */
    public void delete(CollegeDTO dto) throws ApplicationException;

    /**
     * Find college by email
     *@param  emailId : data 
     
     * @return dto : get attribute
     * @throws ApplicationException : not found 
     */
    public CollegeDTO  findByEmailId(String emailId) throws ApplicationException;

    /**
     * Find college by PK
     *
     * @param pk
     *            : get parameter
     * @return dto
     * @throws ApplicationException : not found 
     */
    public CollegeDTO findByPK(long pk) throws ApplicationException;
    
    
    /**
     * Find college by PK
     *
   
     * @param name
     *            : get parameter
     * @return dto
     * * @param findbyname
     *            : get parameter
     * @throws ApplicationException : not found
     */
    public CollegeDTO findByName(String name) throws ApplicationException;


    /**
     * Search college with pagination
     *
     * @return list : List of course
     * @param dto
     *            : Search Parameters
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws ApplicationException : not found 
     */
    public List search(CollegeDTO dto, int pageNo, int pageSize)
            throws ApplicationException;

    /**
     * Search college
     *
     * @return list : List of course
     * @param dto
     *            : Search Parameters
     * @throws ApplicationException : not found
     */
    public List search(CollegeDTO dto) throws ApplicationException;

    /**
     * Gets List of college
     *
     * @return list : List of course
     * @throws ApplicationException : not found
     */
    public List list() throws ApplicationException;
    /**
     * get List of college with pagination
     *
     * @return list : List of course
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     *            @throws ApplicationException : not found
     */
    public List list(int pageNo, int pageSize) throws ApplicationException;

}


	

