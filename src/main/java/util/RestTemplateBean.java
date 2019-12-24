package util;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

@Component
public class RestTemplateBean {

    @Value("wxd7b8a3f7d86df740")
    private String appid;

    private String code;

    @Value("ae36a6331a4438d7a87dcd6fd68718a1")
    private String secret;

    @Value("authorization_code")
    private String grant_type;


    public String getAppid() {
        return appid;
    }

    public void setAppid(String appid) {
        this.appid = appid;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public String getSecret() {
        return secret;
    }

    public void setSecret(String secret) {
        this.secret = secret;
    }

    public String getGrant_type() {
        return grant_type;
    }

    public void setGrant_type(String grant_type) {
        this.grant_type = grant_type;
    }

    @Bean
    public RestTemplate restTemplate(){
       return new RestTemplate();
   }

    @Override
    public String toString() {
        return "RestTemplateBean{" +
                "appid='" + appid + '\'' +
                ", code='" + code + '\'' +
                ", secret='" + secret + '\'' +
                ", grant_type='" + grant_type + '\'' +
                '}';
    }
}
