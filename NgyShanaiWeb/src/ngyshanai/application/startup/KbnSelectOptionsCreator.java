package ngyshanai.application.startup;

import java.util.ArrayList;
import java.util.List;

import ngyshanai.application.LogicExecutor;
import nis.framework.web.option.KbnEnum;
import nis.framework.web.option.Option;

public class KbnSelectOptionsCreator implements SelectOptionsCreator{

	public Class<Enum<? extends KbnEnum>> kbn;

	public KbnSelectOptionsCreator(Class<Enum<? extends KbnEnum>> kbn) {
		this.kbn = kbn;
	}

	@Override
	public List<Option> create(LogicExecutor ejb) {
		try {
			List<Option> options = new ArrayList<Option>();
			Enum<? extends KbnEnum>[] values = (Enum<? extends KbnEnum>[])kbn.getEnumConstants();
			for (Enum<? extends KbnEnum> value : values){
				KbnEnum kbn = KbnEnum.class.cast(value);
				options.add(new Option(kbn.getName(), kbn.getCode()));
			}
			return options;
		} catch (Exception e) {
			throw new IllegalStateException(e);
		}
	}
}
