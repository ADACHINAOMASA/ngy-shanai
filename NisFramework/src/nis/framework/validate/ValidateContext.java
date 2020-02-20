package nis.framework.validate;

import java.io.Serializable;
import java.lang.annotation.Annotation;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.enterprise.context.RequestScoped;
import javax.inject.Inject;

import nis.framework.cdi.CdiBeansAccessor;

@RequestScoped
public class ValidateContext implements Serializable {

	private static final long serialVersionUID = 1L;

	@Inject
	private CdiBeansAccessor cdiBeansAc;

	private List<String> msgs = new ArrayList<String>();

	private boolean validateBreak = false;

	private Map<Class<? extends FieldValidator<? extends Annotation>>, FieldValidator<? extends Annotation>> fieldValidators
			= new HashMap<Class<? extends FieldValidator<? extends Annotation>>, FieldValidator<? extends Annotation>>();

	public void addMsg(String msg) {
		msgs.add(msg);
	}

	public void clearMsgs() {
		msgs.clear();
	}

	public List<String> getMsgs() {
		return msgs;
	}

	public boolean hasMsgs() {
		return !msgs.isEmpty();
	}

	public void toValidateBreak() {
		validateBreak = true;
	}

	public boolean isValidateBreak() {
		return validateBreak;
	}

	@SuppressWarnings({ "unchecked", "unlikely-arg-type" })
	public <T extends Annotation> FieldValidator<T> getFieldValidator (T anno) {
		FieldValidator<T> fieldValidator = (FieldValidator<T>)fieldValidators.get(anno);
		if (fieldValidator == null) {
			Class<? extends FieldValidator<T>> fieldValidatorClazz = FieldValidatorFactory.get(anno);
			fieldValidator = cdiBeansAc.find(fieldValidatorClazz);
			fieldValidators.put(fieldValidatorClazz, fieldValidator);
		}
		return fieldValidator;
	}

}
