
package ejb;

import entities.Account;
import java.util.List;
import javax.ejb.Local;

/**
 *
 * @author Joakim
 */
@Local
public interface LoginLocal {
    
    public Account login(String username, String pass);
    public String addAccount(Account acc);
    public void removeAccount(int id);
    public List<Account> getAllAccounts();
    
}
