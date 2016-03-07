
package managebeans;

import ejb.LoginLocal;
import entities.Account;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/**
 *
 * @author Joakim
 */
@ManagedBean(name = "LoginBean")
@SessionScoped
public class LoginBean {
    
    @EJB LoginLocal loginService;
    
    private String username;
    private String password;
    private boolean adminMode = false;
    private boolean loggedin = false;
    
    public String login(){
        
        String cleanUsername = Jsoup.clean(getUsername(), Whitelist.basic());
        String cleanPassword = Jsoup.clean(getPassword(), Whitelist.basic());
        
        Account acc = loginService.login(cleanUsername, cleanPassword);
        
        if(acc != null){
            loggedin = true;
            if(acc.getAdminrights() == 1){
                setAdminMode(true);
            }else{
                setAdminMode(false);
            }
            return "success";
        }else{
            return "";
        }
        
    }
    
    public String addProfile(){
        
        Account acc = new Account();
        acc.setUsername(Jsoup.clean(getUsername(), Whitelist.basic()));
        acc.setCryptpass(Jsoup.clean(getPassword(), Whitelist.basic()));
        acc.setAdminrights(0);
        acc.setComputerspec("Default");
        
        setUsername("");
        setPassword("");
        
        return loginService.addAccount(acc);
        
    }
    
    public String logout(){
        
        setUsername("");
        setPassword("");
        setAdminMode(false);
        setLoggedin(false);
        
        return "logout";
        
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public boolean isAdminMode() {
        return adminMode;
    }

    public void setAdminMode(boolean adminCheck) {
        this.adminMode = adminCheck;
    }

    public boolean isLoggedin() {
        return loggedin;
    }

    public void setLoggedin(boolean loggedin) {
        this.loggedin = loggedin;
    }
    
}
