package mapper;

import entity.Wxuser;

public interface WxuserMapper {
    Wxuser selectByOpenId(String openid);

    int deleteByPrimaryKey(Integer id);

    int insert(Wxuser record);

    int insertSelective(Wxuser record);

    Wxuser selectByPrimaryKey(Integer id);

    int updateByPrimaryKeySelective(Wxuser record);

    int updateByPrimaryKey(Wxuser record);
}