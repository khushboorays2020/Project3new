package in.co.sunrays.proj3.model;


import in.co.sunrays.proj3.dto.CourseDTO;

import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.exception.RecordNotFoundException;

import java.util.List;

/**
 * Data Access Object of Courses
 * 
 * @author Singleton
 * @version 1.0
 * @Copyright (c) SunilOS
 */

public interface CourseModelInt {

    /**
     * Add a course
     * 
     * @param dto
     * @throws ApplicationException
     * @throws DuplicateRecordException
     *             : throws when user already exists
     */
    public long add(CourseDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Update a Course
     * 
     * @param dto
     * @throws ApplicationException
     * @throws DuplicateRecordException
     *             : if updated user record is already exist
     */
    public void update(CourseDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Delete a  course
     * 
     * @param dto
     * @throws ApplicationException
     */
    public void delete(CourseDTO dto) throws ApplicationException;

   /* *//**
     * Find user by name
     * 
     * @param name
     *            : get parameter
     * @return dto
     * @throws ApplicationException
     * @throws DuplicateRecordException
     */
    public CourseDTO findByName(String name) throws ApplicationException;

    
    
    /**
     * Find user by PK
     * 
     * @param pk
     *            : get parameter
     * @return dto
     * @throws ApplicationException
     */
    public CourseDTO findByPK(long pk) throws ApplicationException;

    /**
     * Search Courses
     * 
     * @return list : List of Courses
     * @param dto
     *            : Search Parameters
     * @throws ApplicationException
     */
    public List search(CourseDTO dto) throws ApplicationException;

    /**
     * Search Courses with pagination
     * 
     * @return list : List of Courses
     * @param dto
     *            : Search Parameters
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws ApplicationException
     */
    public List search(CourseDTO dto, int pageNo, int pageSize)
            throws ApplicationException;

    /**
     * Get List of Courses
     * 
     * @return list : List of Courses
     * @throws DatabaseException
     */
    public List list() throws ApplicationException;

    /**
     * Get List of Courses with pagination
     * 
     * @return list : List of Courses
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws ApplicationException
     */
    public List list(int pageNo, int pageSize) throws ApplicationException;

}