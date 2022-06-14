package lotdsp.entity.master.user;

import lotdsp.entity.oldframework.EntityAccessorAbstract;

public class  MUserAccessor extends EntityAccessorAbstract< MUser,  MUserKey> {

  public MUserAccessor() {
      super();
  }

  @Override
  protected Class< MUser> getEntityClass() {
      return  MUser.class;
  }

  public MUser create(String userCd, String password) {
      return create(new MUserKey(userCd, password));
  }

  public MUser find(String userCd, String password) {
      return find(new MUserKey(userCd, password));
  }

  public boolean exist(String userCd, String password) {
      return exist(new MUserKey(userCd, password));
  }

}

