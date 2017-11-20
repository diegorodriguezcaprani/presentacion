package servicios;

import java.io.IOException;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.context.FacesContext;
import javax.servlet.http.HttpSession;
import javax.validation.constraints.Size;

@ManagedBean(name="loginBean")
public class LoginBean {

	@Size(min = 2, max = 50)
    private String username;
    @Size(min = 2, max = 50)
    private String password;
 
    /**
     * @return the username
     */
    public String getUsername() {
        return username;
    }
 
    /**
     * @param username the username to set
     */
    public void setUsername(String username) {
        this.username = username;
    }
 
    /**
     * @return the password
     */
    public String getPassword() {
        return password;
    }
 
    /**
     * @param password the password to set
     */
    public void setPassword(String password) {
        this.password = password;
    }
 
    public String login() {
      //  TODO
        return "logout";
    }
 
    //logout event, invalidate session
    public String logout() {
        return "login";
    }
 
    /*//validate login
    public String validateUsernamePassword() throws IOException{
        if (username.trim().equals("admin") && password.trim().equals("admin")) {
            HttpSession session = SessionUtils.getSession();
                return "admin";
        } else {
            FacesContext.getCurrentInstance().addMessage(
                    null,
                    new FacesMessage(FacesMessage.SEVERITY_WARN,
                            "Incorrect Username and Passowrd",
                            "Please enter correct username and Password"));
            return "login";
        }
    }*/
}
