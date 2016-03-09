
package managebeans;

import javax.faces.bean.ManagedBean;
import javax.faces.bean.SessionScoped;

/**
 *
 * @author Joakim
 */
@ManagedBean(name = "SettingsBean")
@SessionScoped
public class SettingsBean {
    
    private boolean adminMode = false;
    private boolean loggedin = false;

    
    
    public boolean isAdminMode() {
        return adminMode;
    }

    public void setAdminMode(boolean adminMode) {
        this.adminMode = adminMode;
    }

    public boolean isLoggedin() {
        return loggedin;
    }

    public void setLoggedin(boolean loggedin) {
        this.loggedin = loggedin;
    }
    
    
    
}
