
package app.authentication.beans;

import java.io.IOException;
import java.io.Serializable;
import java.security.Principal;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpServletRequest;

@ManagedBean
@SessionScoped
public class LoginBean implements Serializable
{
    public int checkLaws() {
        int laws;
        FacesContext context = FacesContext.getCurrentInstance();
        HttpServletRequest request = (HttpServletRequest) context.getExternalContext().getRequest();
        if (request.isUserInRole("admin_role")) {    
            return laws = 3;
        } else if (request.isUserInRole("user_role")) {
            return laws = 2;
        } else if (request.isUserInRole("inactive_role")) {
            return laws = 1;
        } else { // niezalogowany
            return laws = 0;
        }
    }    
    public String getLogin() {

        Principal p = FacesContext.getCurrentInstance().getExternalContext().getUserPrincipal();
        return (null != p ? p.getName():"NONE");
    }
    
    public void logout() throws IOException {
        System.out.println("Before: " + FacesContext.getCurrentInstance().getExternalContext().getSessionId(true));
        FacesContext.getCurrentInstance().getExternalContext().invalidateSession();
        System.out.println("After: " + FacesContext.getCurrentInstance().getExternalContext().getSessionId(true));
        FacesContext.getCurrentInstance().getExternalContext().redirect("/System/faces/logout.xhtml");
    }
}
