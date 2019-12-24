package util;

import com.google.gson.Gson;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import serviceImpl.WxuserServiceImpl;

@RestController
public class GetWeChatSession {
    @Autowired
    RestTemplateBean rtb;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WxuserServiceImpl wsi;

    public WeChatSession weChatSession(String code){
        rtb.setCode(code);

        String openid = null;

        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + rtb.getAppid() +
                "&secret=" + rtb.getSecret() + "&js_code=" + rtb.getCode() + "&grant_type=" + rtb.getGrant_type();

        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
        if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
            String sessionData = responseEntity.getBody();
            Gson gson = new Gson();
            //解析从微信服务器获得的openid和session_key;
            WeChatSession weChatSession = gson.fromJson(sessionData, WeChatSession.class);
            //获取用户的唯一标识
            openid = weChatSession.getOpenid();
            //获取会话秘钥
            String session_key = weChatSession.getSession_key();
            //下面就可以写自己的业务代码了
            //最后要返回一个自定义的登录态,用来做后续数据传输的验证

            System.out.println("====" + openid + "*****" + session_key);
            return weChatSession;
        }
        return null;
    }

}
