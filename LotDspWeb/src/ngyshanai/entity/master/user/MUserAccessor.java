package ngyshanai.entity.master.user;

import ngyshanai.entity.oldframework.EntityAccessorAbstract;

public class  MUserAccessor extends EntityAccessorAbstract< MUser,  MUserKey> {

  public MUserAccessor() {
      super();
  }

  @Override
  protected Class< MUser> getEntityClass() {
      return  MUser.class;
  }

  public MUser create(String userCd) {
      return create(new MUserKey(userCd));
  }

  public MUser find(String userCd) {
      return find(new MUserKey(userCd));
  }

  public boolean exist(String userCd) {
      return exist(new MUserKey(userCd));
  }

}

