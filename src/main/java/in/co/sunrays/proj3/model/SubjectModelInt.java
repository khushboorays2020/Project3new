package in.co.sunrays.proj3.model;

import in.co.sunrays.proj3.dto.SubjectDTO;
import in.co.sunrays.proj3.exception.ApplicationException;
import in.co.sunrays.proj3.exception.DatabaseException;
import in.co.sunrays.proj3.exception.DuplicateRecordException;

import java.util.List;

/**
 * Data Access Object of Subject
 *
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 */

public interface SubjectModelInt {

    /**
     * Add a Subject
     *
     * @param dto
     * @throws ApplicationException
     * @throws DuplicateRecordException
     *             : throws when Student already exists
     */
    public long add(SubjectDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Update a Subject
     *
     * @param dto
     * @throws ApplicationException
     * @throws DuplicateRecordException
     *             : if updated user record is already exist
     */
    public void update(SubjectDTO dto) throws ApplicationException,
            DuplicateRecordException;

    /**
     * Delete a Subject
     *
     * @param dto
     * @throws ApplicationException
     */
    public void delete(SubjectDTO dto) throws ApplicationException;

    /**
     * Find Subject by EmailId
     *
     * @param name
     *            : get parameter
     * @return dto
     * @throws ApplicationException
     */
    public SubjectDTO findBySubjectName(String emailId) throws ApplicationException;
    
    /**
     * Find Subject by EmailId
     *
     * @param name
     *            : get parameter
     * @return dto
     * @throws ApplicationException
     */
    public SubjectDTO findByEmailId(String emailId) throws ApplicationException;

    /**
     * Find Subject by PK
     *
     * @param pk
     *            : get parameter
     * @return dto
     * @throws ApplicationException
     */
    public SubjectDTO findByPK(long pk) throws ApplicationException;

    /**
     * Search Subject with pagination
     *
     * @return list : List of Subject
     * @param dto
     *            : Search Parameters
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws ApplicationException
     */
    public List search(SubjectDTO dto, int pageNo, int pageSize)
            throws ApplicationException;

    /**
     * Search Subject
     *
     * @return list : List of Subject
     * @param dto
     *            : Search Parameters
     * @throws ApplicationException
     */
    public List search(SubjectDTO dto) throws ApplicationException;

    /**
     * Gets List of Subject
     *
     * @return list : List of Subject
     * @throws DatabaseException
     */
    public List list() throws ApplicationException;

    /**
     * get List of College with pagination
     *
     * @return list : List of Subject
     * @param pageNo
     *            : Current Page No.
     * @param pageSize
     *            : Size of Page
     * @throws ApplicationException
     */
    public List list(int pageNo, int pageSize) throws ApplicationException;

}

