package in.co.sunrays.proj3.model;



import in.co.sunrays.proj3.dto.FacultyDTO;
import in.co.sunrays.proj3.dto.FacultyDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;
import in.co.sunrays.proj3.exception.RecordNotFoundException;

import java.util.List;

/**
 * Data Access Object of Facultys
 * 
 * @author Decorator
 * @version 1.0
 * @Copyright (c) Decorator
 */

public interface FacultyModelInt {

    /**
     * Add a user
     * 
     * @param dto
     * @throws ApplicationException
     * @throws DuplicateRecordException
     *             : throws when user already exists
     */
    public long add(FacultyDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Update a Faculty
     * 
     * @param dto
     * @throws ApplicationException
     * @throws DuplicateRecordException
     *             : if updated user record is already exist
     */
    public void update(FacultyDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Delete a user
     * 
     * @param dto
     * @throws ApplicationException
     */
    public void delete(FacultyDTO dto) throws ApplicationException;

    /**
     * Find user by login
     * 
     * @param login
     *            : get parameter
     * @return dto
     * @throws ApplicationException
     * @throws DuplicateRecordException
     */
    public FacultyDTO findByEmailId(String login) throws ApplicationException;

    /**
     * Find user by PK
     * 
     * @param pk
     *            : get parameter
     * @return dto
     * @throws ApplicationException
     */
    public FacultyDTO findByPK(long pk) throws ApplicationException;

    /**
     * Search Faculties
     * 
     * @return list : List of Faculties
     * @param dto
     *            : Search Parameters
     * @throws ApplicationException
     */
    public List search(FacultyDTO dto) throws ApplicationException;

    /**
     * Search Faculties with pagination
     * 
     * @return list : List of Faculties
     * @param dto
     *            : Search Parameters
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws ApplicationException
     */
    public List search(FacultyDTO dto, int pageNo, int pageSize)
            throws ApplicationException;

    /**
     * Get List of Faculties
     * 
     * @return list : List of Faculties
     * @throws DatabaseException
     */
    public List list() throws ApplicationException;

    /**
     * Get List of Faculties with pagination
     * 
     * @return list : List of Faculties 
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws ApplicationException
     */
    public List list(int pageNo, int pageSize) throws ApplicationException;

}