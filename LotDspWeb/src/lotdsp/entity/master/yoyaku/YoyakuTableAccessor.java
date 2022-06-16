package lotdsp.entity.master.yoyaku;

import java.util.Date;

import lotdsp.entity.oldframework.EntityAccessorAbstract;

public class  YoyakuTableAccessor extends EntityAccessorAbstract< YoyakuTable,  YoyakuTableKey> {

  public YoyakuTableAccessor() {
      super();
  }

  @Override
  protected Class< YoyakuTable> getEntityClass() {
      return  YoyakuTable.class;
  }

  public YoyakuTable create(String kaigishitsuCd, Date yoyakuDate, String yoyakuBlockStart) {
      return create(new YoyakuTableKey(kaigishitsuCd, yoyakuDate, yoyakuBlockStart));
  }

  public YoyakuTable find(String kaigishitsuCd, Date yoyakuDate, String yoyakuBlockStart) {
      return find(new YoyakuTableKey(kaigishitsuCd, yoyakuDate, yoyakuBlockStart));
  }

  public boolean exist(String kaigishitsuCd, Date yoyakuDate, String yoyakuBlockStart) {
      return exist(new YoyakuTableKey(kaigishitsuCd, yoyakuDate, yoyakuBlockStart));
  }

}

