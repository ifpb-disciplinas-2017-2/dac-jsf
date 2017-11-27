package br.edu.ifpb.web.jsf.validator;

import br.edu.ifpb.domain.model.banda.CPF;
import java.util.Arrays;
import java.util.List;
import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

/**
 * @author Ricardo Job
 * @mail ricardo.job@ifpb.edu.br
 * @since 26/11/2017, 18:34:19
 */
@FacesValidator("validador.CPFValidador")
public class CPFValidador implements Validator {

    @Override
    public void validate(
            FacesContext context, 
            UIComponent component, 
            Object value) throws ValidatorException {


        CPF cpf = (CPF) value;
        
        if(!cpf.isValid()){
            FacesMessage message = new FacesMessage("CPF inválido. Tamanho incorreto!");
//            FacesMessage message2 = new FacesMessage("CPF inválido. Valor incorreto");
//            List<FacesMessage> list = Arrays.asList(message,message2);
            List<FacesMessage> list = Arrays.asList(message);
            throw  new ValidatorException(list);
        }
     
    }
}
