
package managebeans;

import ejb.LoginLocal;
import entities.Account;
import javax.ejb.EJB;
import javax.faces.bean.ManagedBean;
import javax.faces.bean.ManagedProperty;
import javax.faces.bean.RequestScoped;
import org.jsoup.Jsoup;
import org.jsoup.safety.Whitelist;

/**
 *
 * @author Joakim
 */
@ManagedBean(name = "LoginBean")
@RequestScoped
public class LoginBean {
    
    @EJB LoginLocal loginService;
    
    @ManagedProperty(value="#{SettingsBean}")
    private SettingsBean SB;
    
    private String username;
    private String password;
    private boolean failed = false;
    
    public String login(){
        
        System.out.println("USERNAME: "+getUsername());
        System.out.println("PASSWORD: "+getPassword());
        
        String cleanUsername = Jsoup.clean(getUsername(), Whitelist.basic());
        String cleanPassword = Jsoup.clean(getPassword(), Whitelist.basic());
        
        Account acc = loginService.login(cleanUsername, cleanPassword);
        
        if(acc != null){
            SB.setLoggedin(true);
            if(acc.getAdminrights() == 1){
                SB.setAdminMode(true);
            }else{
                SB.setAdminMode(false);
            }
            return "success";
        }else{
            setFailed(true);
            return "";
        }
        
    }
    
    public String addProfile(){
        
        System.out.println("USERNAME: "+getUsername());
        System.out.println("PASSWORD: "+getPassword());
        
        Account acc = new Account();
        acc.setUsername(Jsoup.clean(getUsername(), Whitelist.basic()));
        acc.setCryptpass(Jsoup.clean(getPassword(), Whitelist.basic()));
        acc.setAdminrights(0);
        acc.setComputerspec("Default");
        
        
        
        return loginService.addAccount(acc);
        
    }
    
    public String logout(){
        
        setUsername("");
        setPassword("");
        SB.setAdminMode(false);
        SB.setLoggedin(false);
        setFailed(false);
        
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

    public boolean isFailed() {
        return failed;
    }

    public void setFailed(boolean failed) {
        this.failed = failed;
    }

    public SettingsBean getSB() {
        return SB;
    }

    public void setSB(SettingsBean SB) {
        this.SB = SB;
    }
    
}
