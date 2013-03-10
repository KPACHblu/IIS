package iis.web;

import iis.domain.Role;

import java.util.Collection;
import java.util.Iterator;
import java.util.Set;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIViewParameter;
import javax.faces.context.FacesContext;
import javax.faces.view.ViewMetadata;

import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.User;

public abstract class BaseBean {
	private static final String ROLE_ADMIN = "ROLE_ADMIN";
	
	protected String getLogin()
    {
        String login = "";
        Object principal = SecurityContextHolder.getContext().getAuthentication().getPrincipal();
        login = (principal instanceof User)? ((User)principal).getUsername() : "";
        return login;
    }

    protected boolean isUserAdmin(iis.domain.User user)
    {
        boolean isAdmin = false;
        Set<Role> userRoles = user.getRoles();
        Iterator<Role> it = userRoles.iterator();
        do
        {
            if(!it.hasNext())
                break;
            Role roleDTO = (Role)it.next();
            if(ROLE_ADMIN.equals(roleDTO.getName()))
            {
                isAdmin = true;
                break;
            }
            isAdmin = false;
        } while(true);
        return isAdmin;
    }

    protected String getRealPath(String relativePath)
    {
        return FacesContext.getCurrentInstance().getExternalContext().getRealPath(relativePath);
    }

    protected void addMessage(javax.faces.application.FacesMessage.Severity severity, String text, String textDetail)
    {
        FacesContext.getCurrentInstance().addMessage(null, new FacesMessage(severity, text, textDetail));
    }

    protected Object getViewParameter(String key)
    {
        Collection<UIViewParameter> viewParams = ViewMetadata.getViewParameters(FacesContext.getCurrentInstance().getViewRoot());
        for(Iterator<UIViewParameter> it = viewParams.iterator(); it.hasNext();)
        {
            UIViewParameter viewParam = (UIViewParameter)it.next();
            String name = viewParam.getName();
            if(key.equals(name))
                return viewParam.getValue();
        }

        return null;
    }

}
