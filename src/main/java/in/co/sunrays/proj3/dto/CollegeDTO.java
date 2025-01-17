package in.co.sunrays.proj3.dto;

/**
 * Collage DTO classes
 *
 * @author SUNRAYS Technologies
 * @version 1.0
 * @Copyright (c) SUNRAYS Technologies
 *
 */

public class CollegeDTO extends BaseDTO {

     /**
	 * 
	 */
	private static final long serialVersionUID = 1L;
	/**
     * Name of College
     */
    private String name;
    /**
     * Address of College
     */
    private String address;
    /**
     * State of College
     */
    private String state;
    /**
     * City of College
     */
    private String city;
    /**
     * Phoneno of College
     */
    private String mobile;
    /**
     * accessor
     */
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getState() {
        return state;
    }

    public void setState(String state) {
        this.state = state;
    }

    public String getCity() {
        return city;
    }

    public void setCity(String city) {
        this.city = city;
    }

  

	public String getMobile() {
		return mobile;
	}

	public void setMobile(String mobile) {
		this.mobile = mobile;
	}

	public String getKey() {
		// TODO Auto-generated method stub
		return id + "";
	}

	public String getValue() {
		// TODO Auto-generated method stub
		return name;
	}

    
}

