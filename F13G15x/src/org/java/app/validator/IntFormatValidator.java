package org.java.app.validator;

import javax.faces.application.FacesMessage;
import javax.faces.component.UIComponent;
import javax.faces.context.FacesContext;
import javax.faces.validator.FacesValidator;
import javax.faces.validator.Validator;
import javax.faces.validator.ValidatorException;

@FacesValidator("org.java.app.validator.IntFormatValidator")
public class IntFormatValidator implements Validator {

		@Override
		public void validate(FacesContext ctx, UIComponent comp, Object obj)
				throws ValidatorException {
			String str = (String) obj;
			FacesMessage message = new FacesMessage();
			
			if (str.contains("/*!@#$%^&*()\"{}_[]|\\?/<>,.")) {
				message.setDetail("This field cannot have special characters");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(message);
			}else if (str.length() < 5 || str.length() > 10){
				message.setDetail("User Length Invalid");
				message.setSeverity(FacesMessage.SEVERITY_ERROR);
				throw new ValidatorException(message);
			}else if (!str.matches(".*\\d.*")){
				message.setDetail("This field cannot have characters");
			}
		}
			
}