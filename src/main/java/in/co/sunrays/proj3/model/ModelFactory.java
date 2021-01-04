package in.co.sunrays.proj3.model;

import java.util.HashMap;



import java.util.ResourceBundle;



/**
 * Factory of Model classes

 *
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 *
 */

/**
 * @author 123
 *
 */
public class ModelFactory {

    private static ResourceBundle bundle = ResourceBundle.getBundle("in.co.sunrays.proj3.bundle.system");

    private static final String DATABASE = bundle.getString("DATABASE");
    
    private static ModelFactory mFactory = null;
    /**
     * Cache of Model classes
     */
    private static HashMap modelCache = new HashMap();

    /**
     * Constructor is private so no other class can create instance of Model 
     * Locator
     */
    private ModelFactory() {

    }

    /**
     * Get the instance of Model Locator
     *
     * @return mFactory
     */
    public static ModelFactory getInstance() {
        if (mFactory == null) {
            mFactory = new ModelFactory();
        }
        return mFactory;
    }

    /**
     * Get instance of Marksheet Model
     *
     * @return marksheetModel
     */
    
   public MarksheetModelInt getMarksheetModel() {
          MarksheetModelInt marksheetModel = (MarksheetModelInt) modelCache
                .get("marksheetModel");// meaning smjhna h
      
        if (marksheetModel == null) {
        	System.out.println(DATABASE);
            if ("Hibernate".equals(DATABASE)) {
                marksheetModel = new MarksheetModelHibImpl();
            }
            if ("JDBC".equals(DATABASE)) {
                marksheetModel = new MarksheetModelJDBCImpl();
            }
            modelCache.put("marksheetModel", marksheetModel);
        }

        return marksheetModel;
    }

    /**
     * Get instance of User Model
     *
     * @return userModel
     */
    public UserModelInt getUserModel() {
        UserModelInt userModel = (UserModelInt) modelCache
                .get("userModel");
        System.out.println("inside model factory");
        if (userModel == null) {
            if ("Hibernate".equals(DATABASE)) {
            	 System.out.println("inside hibernate");
                userModel = new UserModelHibImpl();
                System.out.println("out hibernate");
            }
            if ("JDBC".equals(DATABASE)) {
                userModel = new UserModelJDBCImpl();
            }
            modelCache.put("userModel", userModel);
        }

        return userModel;
    }
    /**
     * Get instance of Collage Model
     *
     * @return collage
     */
    public CollegeModelInt getCollegeModel1() {
        CollegeModelInt collegeModel = (CollegeModelInt) modelCache
                .get("collegeModel");
        if (collegeModel == null) {
            if ("Hibernate".equals(DATABASE)) {
                collegeModel = new CollegeModelHibImpl();
            }
            if ("JDBC".equals(DATABASE)) {
                collegeModel = new CollegeModelJDBCImpl();
            }
            modelCache.put("collegeModel", collegeModel);
        }

        return collegeModel;
    }

    /**
     * Get instance of Student Model
     *
     * @return Student
     */
   public StudentModelInt getStudentModel() {
        StudentModelInt studentModel = (StudentModelInt) modelCache
                .get("StudentModel");
        if (studentModel == null) {
            if ("Hibernate".equals(DATABASE)) {
                studentModel = new StudentModelHibImpl();
            }
            if ("JDBC".equals(DATABASE)) {
                studentModel = new StudentModelJDBCImpl();
            }
            modelCache.put("studentModel", studentModel);
        }

        return studentModel;
    }


    /**
     * Get instance of Collage Model
     *
     * @return collage
     */
    public CollegeModelInt getCollegeModel() {
        CollegeModelInt collegeModel = (CollegeModelInt) modelCache
                .get("collegeModel");
        if (collegeModel == null) {
            if ("Hibernate".equals(DATABASE)) {
                collegeModel = new CollegeModelHibImpl();
            }
            if ("JDBC".equals(DATABASE)) {
                collegeModel = new CollegeModelHibImpl();
            }
            modelCache.put("collegeModel", collegeModel);
        }

        return collegeModel;
    }

   

    /**
     * Get instance of Role Model
     *
     * @return Student
     */
   public RoleModelInt getRoleModel() {
        RoleModelInt roleModel = (RoleModelInt) modelCache
                .get("roleModel");
        if (roleModel == null) {
            if ("Hibernate".equals(DATABASE)) {
                roleModel = new RoleModelHibImpl();
            }
            if ("JDBC".equals(DATABASE)) {
                roleModel = new RoleModelJDBCImpl();
            }
            modelCache.put("roleModel", roleModel);
        }

        return roleModel;

    }
   /**
    * Get instance of timetable Model
    *
    * @return Student
    */
  public TimeTableModelInt getTimetableModel() {
       TimeTableModelInt TimetableModel = ( TimeTableModelInt) modelCache
               .get("TimetableModel");
       if (TimetableModel == null) {
           if ("Hibernate".equals(DATABASE)) {
        	   TimetableModel = new TimeTableModelHibImpl();
           }
           if ("JDBC".equals(DATABASE)) {
        	   TimetableModel= new TimeTableModelJDBCImpl();
           }
           modelCache.put("TimetableModel",TimetableModel);
       }

       return TimetableModel;

   }

  
  /**
   * Get instance of Course Model
   *
   * @return Student
   */
 public CourseModelInt getCourseModel() {
      CourseModelInt CourseModel = ( CourseModelInt) modelCache.get("CourseModel");
      if (CourseModel == null) {
          if ("Hibernate".equals(DATABASE)) {
       	   CourseModel = new CourseModelHibImpl();
          }
          if ("JDBC".equals(DATABASE)) {
       	   CourseModel= new CourseModelHibImpl();
          }
          modelCache.put("CourseModel",CourseModel);
      }

      return CourseModel;

  }


 /**
  * Get instance of Subject Model
  *
  * @return Student
  */
public SubjectModelInt getSubjectModel() {
     SubjectModelInt SubjectModel = (SubjectModelInt) modelCache
             .get("SubjectModel");
     if (SubjectModel == null) {
         if ("Hibernate".equals(DATABASE)) {
      	   SubjectModel = new SubjectModelHibImpl();
         }
         if ("JDBC".equals(DATABASE)) {
      	   SubjectModel= new SubjectModelHibImpl();
         }
         modelCache.put("SubjectModel",SubjectModel);
     }

     return SubjectModel;

 }



/**
 * Get instance of Faculty Model
 *
 * @return Student
 */
public FacultyModelInt getFacultyModel() {
    FacultyModelInt FacultyModel = (FacultyModelInt) modelCache
            .get("FacultyModel");
    if (FacultyModel == null) {
        if ("Hibernate".equals(DATABASE)) {
     	   FacultyModel = new FacultyModelHibImpl();
        }
        if ("JDBC".equals(DATABASE)) {
     	   FacultyModel= new FacultyModelHibImpl();
        }
        modelCache.put("FacultyModel",FacultyModel);
    }

    return FacultyModel;

}




}
   
  