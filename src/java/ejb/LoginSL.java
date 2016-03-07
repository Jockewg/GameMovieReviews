package ejb;

import entities.Account;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.ejb.Stateless;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import util.HashManager;

/**
 *
 * @author Joakim
 */
@Stateless
public class LoginSL implements LoginLocal {

    @PersistenceContext(name = "GameMovieReviewsPU")
    EntityManager em;

    HashManager hm = new HashManager();

    @Override
    public Account login(String username, String pass) {

        List<Account> accounts = getAllAccounts();
        
        for(Account acc : accounts){
            if(acc.getUsername().equalsIgnoreCase(username)){
                try {
                    String hash = HashManager.createHash(pass.toCharArray(), HashManager.fromHex(acc.getSalt()));
                    if(acc.getCryptpass().equals(hash)){
                        return acc;
                    }
                } catch(InvalidKeySpecException | NoSuchAlgorithmException ex){
                    Logger.getLogger(LoginSL.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }
        return null;
    }

    @Override
    public String addAccount(Account acc) {

        SecureRandom sr = new SecureRandom();
        byte[] salt = new byte[24];
        sr.nextBytes(salt);

        try {
            String hash = HashManager.createHash(acc.getCryptpass().toCharArray(), salt);
            acc.setCryptpass(hash);
            acc.setSalt(HashManager.toHex(salt));
        } catch (InvalidKeySpecException | NoSuchAlgorithmException ex) {
            Logger.getLogger(LoginSL.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        em.persist(acc);
        return "registered";

    }

    @Override
    public void removeAccount(int id) {

        Account acc = em.find(Account.class, id);
        
        if(em.contains(acc)){
            em.remove(acc);
        }
        
    }

    @Override
    public List<Account> getAllAccounts() {

        List<Account> accounts = em.createNamedQuery("Account.findAll", Account.class).getResultList();
        
        return accounts;
        
    }

}
