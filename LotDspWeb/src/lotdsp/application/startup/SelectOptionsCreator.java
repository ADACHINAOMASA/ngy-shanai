package lotdsp.application.startup;

import java.util.List;

import lotdsp.application.LogicExecutor;
import nis.framework.web.option.Option;

public interface SelectOptionsCreator {

	public List<Option> create(LogicExecutor ejb);

}
