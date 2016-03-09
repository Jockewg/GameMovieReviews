
package managebeans;

import javax.faces.application.FacesMessage;
import javax.faces.bean.ManagedBean;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.ValidatorException;

/**
 *
 * @author Joakim
 */

@ManagedBean (name = "ValidationBean")
public class ValidationBean {
    
    public void ValidatePassword(FacesContext context, UIComponent toValidate, Object value){
        
        String pass = (String) value;
        if(pass.length() < 3){
            
            FacesMessage message = new FacesMessage("Password is too short");
            throw new ValidatorException(message);
        }
        
    }
    
}
