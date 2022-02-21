package in.co.hospital.mgt.sys.model;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.util.ArrayList;
import java.util.List;

import org.apache.log4j.Logger;

import in.co.hospital.mgt.sys.bean.PatientBean;
import in.co.hospital.mgt.sys.bean.UserBean;
import in.co.hospital.mgt.sys.exception.ApplicationException;
import in.co.hospital.mgt.sys.exception.DatabaseException;
import in.co.hospital.mgt.sys.exception.DuplicateRecordException;
import in.co.hospital.mgt.sys.util.JDBCDataSource;

public class PatientModel {

	private static Logger log = Logger.getLogger(PatientModel.class);

	public Integer nextPK() throws DatabaseException {
		log.debug("Model nextPK Started");
		Connection conn = null;
		int pk = 0;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement("SELECT MAX(ID) FROM H_PATIENT");
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				pk = rs.getInt(1);
			}
			rs.close();

		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new DatabaseException("Exception : Exception in getting PK");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model nextPK End");
		return pk + 1;
	}

	/**
	 * Find User by Login
	 * 
	 * @param login
	 *            : get parameter
	 * @return bean
	 * @throws DatabaseException
	 */

	public PatientBean findByLogin(String login) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM H_PAtient WHERE emailId=?");
		PatientBean bean = null;
		Connection conn = null;
		System.out.println("sql" + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setString(1, login);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new PatientBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setDob(rs.getDate(4));
				bean.setMobileNo(rs.getString(5));
				bean.setGender(rs.getString(6));
				bean.setAge(rs.getString(7));
				bean.setBloodGroup(rs.getString(8));
				bean.setAddress(rs.getString(9));
				bean.setCity(rs.getString(10));
				bean.setCNIC(rs.getString(11));
				bean.setMaritialStatus(rs.getString(12));
				bean.setEmailId(rs.getString(13));
				bean.setDoctorName(rs.getString(14));
				bean.setDeceased(rs.getString(15));
				bean.setCreatedBy(rs.getString(16));
				bean.setModifiedBy(rs.getString(17));
				bean.setCreatedDatetime(rs.getTimestamp(18));
				bean.setModifiedDatetime(rs.getTimestamp(19));
				bean.setDoctorId(rs.getLong(20));
			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by login");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByLogin End");
		return bean;
	}

	public PatientBean findByPk(long id) throws ApplicationException {
		log.debug("Model findByLogin Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM H_PAtient WHERE Id=?");
		PatientBean bean = null;
		Connection conn = null;
		System.out.println("sql" + sql);

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			pstmt.setLong(1, id);
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new PatientBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setDob(rs.getDate(4));
				bean.setMobileNo(rs.getString(5));
				bean.setGender(rs.getString(6));
				bean.setAge(rs.getString(7));
				bean.setBloodGroup(rs.getString(8));
				bean.setAddress(rs.getString(9));
				bean.setCity(rs.getString(10));
				bean.setCNIC(rs.getString(11));
				bean.setMaritialStatus(rs.getString(12));
				bean.setEmailId(rs.getString(13));
				bean.setDoctorName(rs.getString(14));
				bean.setDeceased(rs.getString(15));
				bean.setCreatedBy(rs.getString(16));
				bean.setModifiedBy(rs.getString(17));
				bean.setCreatedDatetime(rs.getTimestamp(18));
				bean.setModifiedDatetime(rs.getTimestamp(19));
				bean.setDoctorId(rs.getLong(20));

			}
			rs.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting User by login");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model findByLogin End");
		return bean;
	}

	public long add(PatientBean bean) throws ApplicationException, DuplicateRecordException {

		Connection conn = null;
		int pk = 0;

		PatientBean existbean = findByLogin(bean.getEmailId());

		if (existbean != null) {
			throw new DuplicateRecordException("Email Id already exists");
		}
		
		UserBean uBean=new UserModel().findByPK(bean.getDoctorId());
		bean.setDoctorName(uBean.getFirstName()+" "+uBean.getLastName());

		try {
			conn = JDBCDataSource.getConnection();
			pk = nextPK();
			// Get auto-generated next primary key
			System.out.println(pk + " in ModelJDBC");
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn
					.prepareStatement("INSERT INTO H_Patient VALUES(?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)");
			pstmt.setInt(1, pk);
			pstmt.setString(2, bean.getFirstName());
			pstmt.setString(3, bean.getLastName());
			pstmt.setDate(4, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(5, bean.getMobileNo());
			pstmt.setString(6, bean.getGender());
			pstmt.setString(7, bean.getAge());
			pstmt.setString(8, bean.getBloodGroup());
			pstmt.setString(9, bean.getAddress());
			pstmt.setString(10, bean.getCity());
			pstmt.setString(11, bean.getCNIC());
			pstmt.setString(12, bean.getMaritialStatus());
			pstmt.setString(13, bean.getEmailId());
			pstmt.setString(14, bean.getDoctorName());
			pstmt.setString(15, bean.getDeceased());
			pstmt.setString(16, bean.getCreatedBy());
			pstmt.setString(17, bean.getModifiedBy());
			pstmt.setTimestamp(18, bean.getCreatedDatetime());
			pstmt.setTimestamp(19, bean.getModifiedDatetime());
			pstmt.setLong(20, bean.getDoctorId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			try {
				conn.rollback();
			} catch (Exception ex) {
				ex.printStackTrace();
				throw new ApplicationException("Exception : add rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in add User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		return pk;
	}

	public void delete(PatientBean bean) throws ApplicationException {

		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement("DELETE FROM H_Patient WHERE ID=?");
			pstmt.setLong(1, bean.getId());
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();

		} catch (Exception e) {

			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception : Exception in delete User");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

	}
	
	public List search(PatientBean bean) throws ApplicationException {
		return search(bean, 0, 0);
	}

	/**
	 * Search User with pagination
	 * 
	 * @return list : List of Users
	 * @param bean
	 *            : Search Parameters
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * 
	 * @throws DatabaseException
	 */

	public List search(PatientBean bean, int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model search Started");
		StringBuffer sql = new StringBuffer("SELECT * FROM H_Patient WHERE 1=1");

		if (bean != null) {
			if (bean.getId() > 0) {
				sql.append(" AND id = " + bean.getId());
			}
			if (bean.getId() > 0) {
				sql.append(" AND doctorId = " + bean.getDoctorId());
			}
			if (bean.getFirstName() != null && bean.getFirstName().length() > 0) {
				sql.append(" AND FIRSTNAME like '" + bean.getFirstName() + "%'");
			}
			if (bean.getLastName() != null && bean.getLastName().length() > 0) {
				sql.append(" AND LASTNAME like '" + bean.getLastName() + "%'");
			}
			if (bean.getEmailId() != null && bean.getEmailId().length() > 0) {
				sql.append(" AND emailId like '" + bean.getEmailId() + "%'");
			}
			
			if (bean.getDob() != null && bean.getDob().getDate() > 0) {
				sql.append(" AND DOB = " + bean.getGender());
			}
			if (bean.getMobileNo() != null && bean.getMobileNo().length() > 0) {
				sql.append(" AND MOBILENO = " + bean.getMobileNo());
			}
			
			if (bean.getDoctorName() != null && bean.getDoctorName().length() > 0) {
				sql.append(" AND DoctorName = " + bean.getDoctorName());
			}
			
			

		}

		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;

			sql.append(" Limit " + pageNo + ", " + pageSize);
			// sql.append(" limit " + pageNo + "," + pageSize);
		}

		System.out.println("user model search  :"+sql);
		ArrayList list = new ArrayList();
		Connection conn = null;
		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				bean = new PatientBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setDob(rs.getDate(4));
				bean.setMobileNo(rs.getString(5));
				bean.setGender(rs.getString(6));
				bean.setAge(rs.getString(7));
				bean.setBloodGroup(rs.getString(8));
				bean.setAddress(rs.getString(9));
				bean.setCity(rs.getString(10));
				bean.setCNIC(rs.getString(11));
				bean.setMaritialStatus(rs.getString(12));
				bean.setEmailId(rs.getString(13));
				bean.setDoctorName(rs.getString(14));
				bean.setDeceased(rs.getString(15));
				bean.setCreatedBy(rs.getString(16));
				bean.setModifiedBy(rs.getString(17));
				bean.setCreatedDatetime(rs.getTimestamp(18));
				bean.setModifiedDatetime(rs.getTimestamp(19));
				bean.setDoctorId(rs.getLong(20));

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
	
	public List list() throws ApplicationException {
		return list(0, 0);
	}

	/**
	 * Get List of User with pagination
	 * 
	 * @return list : List of users
	 * @param pageNo
	 *            : Current Page No.
	 * @param pageSize
	 *            : Size of Page
	 * @throws DatabaseException
	 */

	public List list(int pageNo, int pageSize) throws ApplicationException {
		log.debug("Model list Started");
		ArrayList list = new ArrayList();
		StringBuffer sql = new StringBuffer("select * from H_Patient");
		// if page size is greater than zero then apply pagination
		if (pageSize > 0) {
			// Calculate start record index
			pageNo = (pageNo - 1) * pageSize;
			sql.append(" limit " + pageNo + "," + pageSize);
		}

		
		System.out.println("sql in list user :"+sql);
		Connection conn = null;

		try {
			conn = JDBCDataSource.getConnection();
			PreparedStatement pstmt = conn.prepareStatement(sql.toString());
			ResultSet rs = pstmt.executeQuery();
			while (rs.next()) {
				PatientBean bean = new PatientBean();
				bean.setId(rs.getLong(1));
				bean.setFirstName(rs.getString(2));
				bean.setLastName(rs.getString(3));
				bean.setDob(rs.getDate(4));
				bean.setMobileNo(rs.getString(5));
				bean.setGender(rs.getString(6));
				bean.setAge(rs.getString(7));
				bean.setBloodGroup(rs.getString(8));
				bean.setAddress(rs.getString(9));
				bean.setCity(rs.getString(10));
				bean.setCNIC(rs.getString(11));
				bean.setMaritialStatus(rs.getString(12));
				bean.setEmailId(rs.getString(13));
				bean.setDoctorName(rs.getString(14));
				bean.setDeceased(rs.getString(15));
				bean.setCreatedBy(rs.getString(16));
				bean.setModifiedBy(rs.getString(17));
				bean.setCreatedDatetime(rs.getTimestamp(18));
				bean.setModifiedDatetime(rs.getTimestamp(19));
				bean.setDoctorId(rs.getLong(20));
			}
			rs.close();
		} catch (Exception e) {
			log.error("Database Exception..", e);
			throw new ApplicationException("Exception : Exception in getting list of users");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}

		log.debug("Model list End");
		return list;

	}
	
	
	public void update(PatientBean bean) throws ApplicationException, DuplicateRecordException {
		log.debug("Model update Started");
		Connection conn = null;

		PatientBean beanExist = findByLogin(bean.getEmailId());
		// Check if updated LoginId already exist
		if (beanExist != null && !(beanExist.getId() == bean.getId())) {
			throw new DuplicateRecordException("EmailId is already exist");
		}
		
		UserBean uBean=new UserModel().findByPK(bean.getDoctorId());
		bean.setDoctorName(uBean.getFirstName()+" "+uBean.getLastName());

		try {
			conn = JDBCDataSource.getConnection();
			conn.setAutoCommit(false); // Begin transaction
			PreparedStatement pstmt = conn.prepareStatement(
					"UPDATE H_Patient SET FIRSTNAME=?,LASTNAME=?,DOB=?,MOBILENO=?,"
					+ "GENDER=?,age=?,BloodGroup=?,Address=?,city=?,CNIC=?,MaritialStatus=?,emailId=?,DoctorName=?,Deceased=?,"
					+ "CREATEDBY=?,MODIFIEDBY=?,CREATEDDATETIME=?,MODIFIEDDATETIME=?,doctorId=? WHERE ID=?");
			pstmt.setString(1, bean.getFirstName());
			pstmt.setString(2, bean.getLastName());
			pstmt.setDate(3, new java.sql.Date(bean.getDob().getTime()));
			pstmt.setString(4, bean.getMobileNo());
			pstmt.setString(5, bean.getGender());
			pstmt.setString(6, bean.getAge());
			pstmt.setString(7, bean.getBloodGroup());
			pstmt.setString(8, bean.getAddress());
			pstmt.setString(9, bean.getCity());
			pstmt.setString(10, bean.getCNIC());
			pstmt.setString(11, bean.getMaritialStatus());
			pstmt.setString(12, bean.getEmailId());
			pstmt.setString(13, bean.getDoctorName());
			pstmt.setString(14, bean.getDeceased());
			pstmt.setString(15, bean.getCreatedBy());
			pstmt.setString(16, bean.getModifiedBy());
			pstmt.setTimestamp(17, bean.getCreatedDatetime());
			pstmt.setTimestamp(18, bean.getModifiedDatetime());
			pstmt.setLong(19, bean.getDoctorId());
			pstmt.setLong(20, bean.getId());
			
			pstmt.executeUpdate();
			conn.commit(); // End transaction
			pstmt.close();
		} catch (Exception e) {
			e.printStackTrace();
			log.error("Database Exception..", e);
			try {
				conn.rollback();
			} catch (Exception ex) {
				throw new ApplicationException("Exception : Delete rollback exception " + ex.getMessage());
			}
			throw new ApplicationException("Exception in updating User ");
		} finally {
			JDBCDataSource.closeConnection(conn);
		}
		log.debug("Model update End");
	}

}
