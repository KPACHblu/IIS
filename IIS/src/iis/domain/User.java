package iis.domain;

import java.sql.Timestamp;
import java.util.HashSet;
import java.util.Set;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.JoinTable;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToMany;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery; 
import javax.persistence.Table;

@Entity
@Table(name = User.TABLE_NAME)
@NamedQueries({
	@NamedQuery(name=User.FIND_BY_LOGIN_QUERY, query = "SELECT OBJECT(e) FROM User AS e WHERE e.login = :p1"),
	@NamedQuery(name=User.FIND_BY_ROLE_ID, query = "SELECT OBJECT(e) FROM User AS e INNER JOIN e.roles r WHERE r.id = :p1"),
	@NamedQuery(name=User.FIND_BY_ROLE_Name, query = "SELECT OBJECT(e) FROM User AS e INNER JOIN e.roles r WHERE r.name = :p1")
})                                                                                
public class User {
	public final static String TABLE_NAME = "user";
	public final static String FIND_BY_LOGIN_QUERY = "findUserByLogin";
	public final static String FIND_BY_ROLE_ID = "findUsersByRoleId";
	public final static String FIND_BY_ROLE_Name = "findUsersByRoleName";
	
	public User() {	}
	
	public User(Long id, String login, String password, Boolean active,
			String fname, String mname, String lname, Boolean male,
			Timestamp birthday, String address, String phone, String phone2,
			String logged, Timestamp regDate, Set<Role> roles) {
		super();
		this.id = id;
		this.login = login;
		this.password = password;
		this.active = active;
		this.fname = fname;
		this.mname = mname;
		this.lname = lname;
		this.male = male;
		this.birthday = birthday;
		this.address = address;
		this.phone = phone;
		this.phone2 = phone2;
		this.logged = logged;
		this.regDate = regDate;
		this.roles = roles;
	}
	

	@Id
	@Column(name = "id")
	@GeneratedValue
	private Long id;
	
	@Column(name = "login")
	private String login;
	
	@Column(name = "password")
	private String password;
	
	@Column(name = "active")
	private Boolean active;
	
	@Column(name = "fname")
	private String fname;
	
	@Column(name = "mname")
	private String mname;
	
	@Column(name = "lname")
	private String lname;
	
	@Column(name = "male")
	private Boolean male;
	
	@Column(name = "birthday")
	private Timestamp birthday;
	
	@Column(name = "address")
	private String address;
	
	@Column(name = "phone")
	private String phone;
	
	@Column(name = "phone2")
	private String phone2;
	
	@Column(name = "logged")
	private String logged;
	
	@Column(name = "reg_date")
	private Timestamp regDate;
	
	@ManyToMany(cascade = CascadeType.MERGE, fetch = FetchType.EAGER)
	@JoinTable(name = "user_role", joinColumns = { @JoinColumn(name = "user_id") }, inverseJoinColumns = { @JoinColumn(name = "role_id") })
	private Set<Role> roles = new HashSet<Role>(0);

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getLogin() {
		return login;
	}

	public void setLogin(String login) {
		this.login = login;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

	public Boolean getActive() {
		return active;
	}

	public void setActive(Boolean active) {
		this.active = active;
	}

	public String getFname() {
		return fname;
	}

	public void setFname(String fname) {
		this.fname = fname;
	}

	public String getMname() {
		return mname;
	}

	public void setMname(String mname) {
		this.mname = mname;
	}

	public String getLname() {
		return lname;
	}

	public void setLname(String lname) {
		this.lname = lname;
	}

	public Boolean getMale() {
		return male;
	}

	public void setMale(Boolean male) {
		this.male = male;
	}

	public Timestamp getBirthday() {
		return birthday;
	}

	public void setBirthday(Timestamp birthday) {
		this.birthday = birthday;
	}

	public String getAddress() {
		return address;
	}

	public void setAddress(String address) {
		this.address = address;
	}

	public String getPhone() {
		return phone;
	}

	public void setPhone(String phone) {
		this.phone = phone;
	}

	public String getPhone2() {
		return phone2;
	}

	public void setPhone2(String phone2) {
		this.phone2 = phone2;
	}

	public String getLogged() {
		return logged;
	}

	public void setLogged(String logged) {
		this.logged = logged;
	}

	public Timestamp getRegDate() {
		return regDate;
	}

	public void setRegDate(Timestamp regDate) {
		this.regDate = regDate;
	}

	public Set<Role> getRoles() {
		return roles;
	}

	public void setRoles(Set<Role> roles) {
		this.roles = roles;
	}

	@Override
	public String toString() {
		return "User [id=" + id + ", login=" + login + ", password=" + password
				+ ", active=" + active + ", fname=" + fname + ", mname="
				+ mname + ", lname=" + lname + ", male=" + male + ", birthday="
				+ birthday + ", address=" + address + ", phone=" + phone
				+ ", phone2=" + phone2 + ", logged=" + logged + ", regDate="
				+ regDate + ", roles=" + roles + "]";
	}

}
