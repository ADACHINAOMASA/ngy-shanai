package lotdsp.entity.master.yoyaku;

import lotdsp.entity.oldframework.EntityAccessorAbstract;

public class  YoyakuTableAccessor extends EntityAccessorAbstract< YoyakuTable,  YoyakuTableKey> {

  public YoyakuTableAccessor() {
      super();
  }

  @Override
  protected Class< YoyakuTable> getEntityClass() {
      return  YoyakuTable.class;
  }

  public YoyakuTable create(String yoyakuId) {
      return create(new YoyakuTableKey(yoyakuId));
  }

  public YoyakuTable find(String yoyakuId) {
      return find(new YoyakuTableKey(yoyakuId));
  }

  public boolean exist(String yoyakuId) {
      return exist(new YoyakuTableKey(yoyakuId));
  }

}

