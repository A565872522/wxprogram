package serviceImpl;

import entity.Wxuser;
import mapper.WxuserMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import service.WxuserService;

@Service
public class WxuserServiceImpl implements WxuserService {
    @Autowired
    WxuserMapper wm;

    @Override
    public Wxuser selectByOpenId(String openid) {
        return wm.selectByOpenId(openid);
    }

    @Override
    public int deleteByPrimaryKey(Integer id) {
        return wm.deleteByPrimaryKey(id);
    }

    @Override
    public int insert(Wxuser record) {
        return wm.insert(record);
    }

    @Override
    public int insertSelective(Wxuser record) {
        return wm.insertSelective(record);
    }

    @Override
    public Wxuser selectByPrimaryKey(Integer id) {
        return wm.selectByPrimaryKey(id);
    }

    @Override
    public int updateByPrimaryKeySelective(Wxuser record) {
        return wm.updateByPrimaryKeySelective(record);
    }

    @Override
    public int updateByPrimaryKey(Wxuser record) {
        return wm.updateByPrimaryKey(record);
    }
}
