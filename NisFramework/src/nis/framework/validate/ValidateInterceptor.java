package nis.framework.validate;

import java.util.List;

import javax.inject.Inject;
import javax.interceptor.AroundInvoke;
import javax.interceptor.Interceptor;
import javax.interceptor.InvocationContext;

import nis.framework.cdi.CdiBeansAccessor;
import nis.framework.dictionary.InputModel;
import nis.framework.ejb.logic.ServiceContext;

@Interceptor
@Validate
public class ValidateInterceptor {

	@Inject
	private ServiceContext svContext;

	@Inject
	private CdiBeansAccessor cdiBeansAc;

	@Inject
	private ValidateContext nvContext;

	@AroundInvoke
	public Object intercept(InvocationContext ic) throws Exception {
		Validate validateAn = ic.getMethod().getAnnotation(Validate.class);

		InputModelAllValidator validator = (InputModelAllValidator)cdiBeansAc.find(validateAn.validator());

		// 複数対応
		for (Object obj : ic.getParameters()) {
			if (obj.getClass().isAnnotationPresent(InputModel.class)) {
				validator.validate(obj);
			}
			if (nvContext.isValidateBreak()) {
				break;
			}
		}

		if (nvContext.hasMsgs()) {
			List<String> msgs = nvContext.getMsgs();
			int cnt = 0;
			for (String msg : msgs) {
				svContext.getAlerts().addDanger(msg);
				cnt++;
				if (cnt == 5) {
					break;
				}
			}
			if (nvContext.getMsgs().size() > 5) {
				svContext.getAlerts().addDanger("入力値エラー 他" + (nvContext.getMsgs().size() - 5) + "件...");
			}
			svContext.toStatusError();
			return null;
		}

		return ic.proceed();
	}

}
