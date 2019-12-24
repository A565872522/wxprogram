package controller;

import entity.Wxuser;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;
import serviceImpl.WxuserServiceImpl;
import util.GetWeChatSession;
import util.RestTemplateBean;
import util.WeChatSession;

import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.text.SimpleDateFormat;
import java.util.Date;

@RestController
public class WxuserController {

    @Autowired
    RestTemplateBean rtb;

    @Autowired
    RestTemplate restTemplate;

    @Autowired
    WxuserServiceImpl wsi;

    @Autowired
    GetWeChatSession gws;

    @RequestMapping(value="/wxlogin",method= RequestMethod.POST)
    public WeChatSession wxlogin(String code, String nickName, String city, String country, Integer gender, String province, String avatarUrl, String language, HttpServletResponse resp) {
//        System.out.println("****************" + code);
//        System.out.println("****************" + nickName);
//        System.out.println("****************" + city);
//        System.out.println("****************" + country);
//        System.out.println("****************" + gender);
//        System.out.println("****************" + province);
//        System.out.println("****************" + avatarUrl);
//        System.out.println("****************" + language);
//        rtb.setCode(code);
//
//        String openid = null;
//
//        String url = "https://api.weixin.qq.com/sns/jscode2session?appid=" + rtb.getAppid() +
//                "&secret=" + rtb.getSecret() + "&js_code=" + rtb.getCode() + "&grant_type=" + rtb.getGrant_type();
//
//        ResponseEntity<String> responseEntity = restTemplate.exchange(url, HttpMethod.GET, null, String.class);
//        if (responseEntity != null && responseEntity.getStatusCode() == HttpStatus.OK) {
//            String sessionData = responseEntity.getBody();
//            Gson gson = new Gson();
//            //解析从微信服务器获得的openid和session_key;
//            WeChatSession weChatSession = gson.fromJson(sessionData, WeChatSession.class);
//            //获取用户的唯一标识
//            openid = weChatSession.getOpenid();
//            //获取会话秘钥
//            String session_key = weChatSession.getSession_key();
//            //下面就可以写自己的业务代码了
//            //最后要返回一个自定义的登录态,用来做后续数据传输的验证
//
//            System.out.println("====" + openid + "*****" + session_key);

        WeChatSession weChatSession = gws.weChatSession(code);

        Wxuser user = new Wxuser();

            if(weChatSession.getOpenid()!=null){
                if(wsi.selectByOpenId(weChatSession.getOpenid())==null){

                    Date date = new Date();
                    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
                    user.setOpenid(weChatSession.getOpenid());
                    user.setNickname(nickName);
                    user.setCity(city);
                    user.setCountry(country);
                    user.setGender(gender);
                    user.setProvince(province);
                    user.setAvatarurl(avatarUrl);
                    user.setLanguage(language);
                    user.setCtime(sdf.format(date));
                    wsi.insertSelective(user);
                    return weChatSession;
                }

            }

//        }
        return null;
    }

    @RequestMapping(value = "/getUserInfo",method = RequestMethod.POST)
    public Wxuser getUserInfo(String code, HttpServletRequest req){
        WeChatSession weChatSession = gws.weChatSession(code);
        String openid = null;

        System.out.println(openid);
        if(openid!=null){
            if(wsi.selectByOpenId(openid)!=null){
                return wsi.selectByOpenId(openid);
            }
        }
        return null;
    }

}
