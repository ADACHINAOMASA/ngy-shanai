package ngyshanai.domain.logic.options;

import java.util.List;

import javax.inject.Inject;

import ngyshanai.common.msg.options.GetSimpleSelectOptionsRequestInfo;
import ngyshanai.domain.query.options.GetSimpleSelectOptionQuery;
import nis.framework.ejb.Executable;
import nis.framework.sql.NisQueryExecutor;
import nis.framework.web.option.Option;

public class GetSimpleSelectOptionsLogic implements Executable<GetSimpleSelectOptionsRequestInfo, List<Option>> {
	@Inject
	private NisQueryExecutor queryExecutor;

	@Override
	public List<Option> execute(GetSimpleSelectOptionsRequestInfo in) {
		return queryExecutor.executeQuery(new GetSimpleSelectOptionQuery(in));
	}

}
