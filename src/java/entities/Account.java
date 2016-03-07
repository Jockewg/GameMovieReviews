
package entities;

import java.io.Serializable;
import javax.persistence.Basic;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.NamedQueries;
import javax.persistence.NamedQuery;
import javax.persistence.Table;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;
import javax.xml.bind.annotation.XmlRootElement;

/**
 *
 * @author Joakim
 */
@Entity
@Table(name = "account")
@XmlRootElement
@NamedQueries({
    @NamedQuery(name = "Account.findAll", query = "SELECT a FROM Account a"),
    @NamedQuery(name = "Account.findByAccountId", query = "SELECT a FROM Account a WHERE a.accountId = :accountId"),
    @NamedQuery(name = "Account.findByUsername", query = "SELECT a FROM Account a WHERE a.username = :username"),
    @NamedQuery(name = "Account.findByCryptpass", query = "SELECT a FROM Account a WHERE a.cryptpass = :cryptpass"),
    @NamedQuery(name = "Account.findBySalt", query = "SELECT a FROM Account a WHERE a.salt = :salt"),
    @NamedQuery(name = "Account.findByAdminrights", query = "SELECT a FROM Account a WHERE a.adminrights = :adminrights"),
    @NamedQuery(name = "Account.findByComputerspec", query = "SELECT a FROM Account a WHERE a.computerspec = :computerspec")})
public class Account implements Serializable {
    private static final long serialVersionUID = 1L;
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Basic(optional = false)
    @Column(name = "account_id")
    private Integer accountId;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 45)
    @Column(name = "username")
    private String username;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "cryptpass")
    private String cryptpass;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "salt")
    private String salt;
    @Basic(optional = false)
    @NotNull
    @Column(name = "adminrights")
    private int adminrights;
    @Basic(optional = false)
    @NotNull
    @Size(min = 1, max = 200)
    @Column(name = "computerspec")
    private String computerspec;

    public Account() {
    }

    public Account(Integer accountId) {
        this.accountId = accountId;
    }

    public Account(Integer accountId, String username, String cryptpass, String salt, int adminrights, String computerspec) {
        this.accountId = accountId;
        this.username = username;
        this.cryptpass = cryptpass;
        this.salt = salt;
        this.adminrights = adminrights;
        this.computerspec = computerspec;
    }

    public Integer getAccountId() {
        return accountId;
    }

    public void setAccountId(Integer accountId) {
        this.accountId = accountId;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCryptpass() {
        return cryptpass;
    }

    public void setCryptpass(String cryptpass) {
        this.cryptpass = cryptpass;
    }

    public String getSalt() {
        return salt;
    }

    public void setSalt(String salt) {
        this.salt = salt;
    }

    public int getAdminrights() {
        return adminrights;
    }

    public void setAdminrights(int adminrights) {
        this.adminrights = adminrights;
    }

    public String getComputerspec() {
        return computerspec;
    }

    public void setComputerspec(String computerspec) {
        this.computerspec = computerspec;
    }

    @Override
    public int hashCode() {
        int hash = 0;
        hash += (accountId != null ? accountId.hashCode() : 0);
        return hash;
    }

    @Override
    public boolean equals(Object object) {
        // TODO: Warning - this method won't work in the case the id fields are not set
        if (!(object instanceof Account)) {
            return false;
        }
        Account other = (Account) object;
        if ((this.accountId == null && other.accountId != null) || (this.accountId != null && !this.accountId.equals(other.accountId))) {
            return false;
        }
        return true;
    }

    @Override
    public String toString() {
        return "entities.Account[ accountId=" + accountId + " ]";
    }
    
}
