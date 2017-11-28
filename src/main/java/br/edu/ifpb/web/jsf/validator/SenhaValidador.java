package br.edu.ifpb.web.jsf.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

// validar se a senha está ou não vazia
/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 27/11/2017, 08:53:08
 */
@FacesValidator(value = "validator.Senha")
public class SenhaValidador implements Validator{
    
    @Override
    public void validate(FacesContext context, UIComponent component, Object value) 
            throws ValidatorException {
        
        if(value==null){
            throw new ValidatorException(new FacesMessage("a senha está nula"));
        }
        
        String senha = value.toString();
        
        if("".equals(senha.trim())){
//            context.addMessage("senha", new FacesMessage("a senha está vazia"));
            throw new ValidatorException(new FacesMessage("a senha está vazia"));
        }
        
        

    }

}
