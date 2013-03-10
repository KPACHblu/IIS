package iis.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;

@Entity
@Table(name = Role.TABLE_NAME)
@NamedQueries({
	@NamedQuery(name=Role.FIND_BY_NAME_QUERY, query = "SELECT OBJECT(e) FROM Role AS e WHERE e.name = :p1")
})
public class Role {

	public final static String TABLE_NAME = "role";
	public final static String FIND_BY_NAME_QUERY = "findRoleByName";
	
	public final static String ROLE_ADMIN = "ROLE_ADMIN";
	public final static String ROLE_ENROLLEE = "ROLE_ENROLLEE";
	
	public Role() {}

	public Role(Long id, String name) {
		super();
		this.id = id;
		this.name = name;
	}

	@Id
	@Column(name = "id")
	@GeneratedValue 
	private Long id;

	@Column(name = "name")
	private String name;

	public Long getId() {
		return id;
	}

	public void setId(Long id) {
		this.id = id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	@Override
	public String toString() {
		return "Role [id=" + id + ", name=" + name + "]";
	}
	
}
