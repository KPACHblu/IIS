package iis.web;

import iis.domain.Role;
import iis.domain.User;
import iis.service.RoleService;
import iis.service.UserService;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;

import javax.annotation.PostConstruct;
import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.SessionScoped;

@ManagedBean(name = "userBean")
@SessionScoped
public class UserBean extends BaseBean implements Serializable {
	
	private static final long serialVersionUID = -5992864093292196090L;

	@ManagedProperty(value = "#{userServiceImpl}")
	private UserService userService;

	@ManagedProperty(value = "#{roleServiceImpl}")
	private RoleService roleService;

	private User addUser = new User();
	private User selectedUser;
	private List<String> selectedRoles; 
	private Map<String, String> allRoles;
	private List<User> allUsers;
	private List<String> loggedTimeCurrentUser;
	
	@PostConstruct
	public void init() {
		allRoles = new HashMap<String,String>();
		allRoles.put("Администратор", Role.ROLE_ADMIN);
		allRoles.put("Абитуриент", Role.ROLE_ENROLLEE);
		
		setAllUsers(userService.findAll());
	}
	
	public void addEnrolle() {
		List<String> roleName = new ArrayList<String>();
		roleName.add(Role.ROLE_ENROLLEE);
		add(roleName);
	}
	
	public void addUser() {
		add(selectedRoles);
	}
	
	private void add(List<String> roleNames) {
		try {
			User user = userService.findByLogin(addUser.getLogin());
			if (user!=null) {
				addMessage(FacesMessage.SEVERITY_ERROR, "Пользователь с таким логином уже существует", "");
				return;
			}
			
			Set<Role> roles = convertRoleNameToEntity(roleNames);
			addUser.setRoles(roles);
			addUser.setRegDate(new Date());
			addUser.setActive(Boolean.valueOf(true));
			userService.create(addUser);
			addMessage(FacesMessage.SEVERITY_INFO, "Пользователь успешно добавлен", "");
			addUser = new User();
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(FacesMessage.SEVERITY_ERROR,"Возникла ошибка при добавлении пользователя","");
		}
	}

	public void update() {
		try {
			Set<Role> roles = convertRoleNameToEntity(selectedRoles);
			selectedUser.setRoles(roles);
			userService.update(selectedUser);
			addMessage(FacesMessage.SEVERITY_INFO, "Данные пользователя успешно обновлены","");
			selectedUser = null;
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(FacesMessage.SEVERITY_ERROR, "Возникла ошибка при обновлении данных пользователя","");
		}
	}

	public String delete() {
		try {
			userService.deleteById(selectedUser.getId());
			addMessage(FacesMessage.SEVERITY_INFO, "Запись \""+selectedUser.getLname()+"\" успешно удалена","");
			selectedUser = null;
			return "list?faces-redirect=true";
		} catch (Exception e) {
			e.printStackTrace();
			addMessage(FacesMessage.SEVERITY_ERROR, "Возникла ошибка при удалении записи","");
			return "";
		}
	}
	
	private Set<Role> convertRoleNameToEntity(List<String> roleNames) {
		Set<Role> roles = new HashSet<Role>();
		for (String roleName : roleNames) {
			Role role = roleService.findByName(roleName);
			roles.add(role);
		}
		return roles;
	}
	
	private List<String> convertRoleEntityToName(Set<Role> roles) {
		List<String> roleName = new ArrayList<String>();
		for (Role role : roles) {
			roleName.add(role.getName());
		}
		return roleName;
	}
	
	public String getCurrentUserLogin() {
		return getLogin();
	}

	public UserService getUserService() {
		return userService;
	}

	public void setUserService(UserService userService) {
		this.userService = userService;
	}

	public RoleService getRoleService() {
		return roleService;
	}

	public void setRoleService(RoleService roleService) {
		this.roleService = roleService;
	}

	public User getAddUser() {
		return addUser;
	}

	public void setAddUser(User addUser) {
		this.addUser = addUser;
	}

	public User getSelectedUser() {
		return selectedUser;
	}

	public void setSelectedUser(User selectedUser) {
		this.selectedUser = selectedUser;
	}

	public Map<String, String> getAllRoles() {
		return allRoles;
	}

	public void setAllRoles(Map<String, String> allRoles) {
		this.allRoles = allRoles;
	}

	public List<String> getSelectedRoles() {
		if (selectedUser!=null) {
			selectedRoles = convertRoleEntityToName(selectedUser.getRoles());
		}
		return selectedRoles;
	}

	public void setSelectedRoles(List<String> selectedRoles) {
		this.selectedRoles = selectedRoles;
	}

	public List<User> getAllUsers() {
		return allUsers;
	}

	public void setAllUsers(List<User> allUsers) {
		this.allUsers = allUsers;
	}

	public List<String> getLoggedTimeCurrentUser() {
		if (selectedUser!=null) {
			String loggedTime = selectedUser.getLogged();
			if (loggedTime!=null) {
				loggedTimeCurrentUser = Arrays.asList(selectedUser.getLogged().split(";"));
			}
		} else {
			loggedTimeCurrentUser = new ArrayList<>();
		}
		return loggedTimeCurrentUser;
	}

	public void setLoggedTimeCurrentUser(List<String> loggedTimeCurrentUser) {
		this.loggedTimeCurrentUser = loggedTimeCurrentUser;
	}

}
