package com.sxau.master.utils;

import com.google.gson.Gson;
import com.nimbusds.jose.*;
import com.nimbusds.jose.crypto.MACSigner;
import com.nimbusds.jose.crypto.MACVerifier;
import com.sxau.master.common.ErrorException;
import com.sxau.master.common.ResultEnum;
import com.sxau.master.vo.token.TokenValidResultVO;
import com.sxau.master.vo.token.UserTokenInfoVO;
import com.sxau.master.common.ErrorException;
import com.sxau.master.common.ResultEnum;
import com.sxau.master.vo.token.TokenValidResultVO;
import net.minidev.json.JSONObject;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import javax.annotation.PostConstruct;
import java.text.ParseException;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

@Component
public class TokenUtil {

    @Value("${token.secret}")
    private String secret;

    private JWSVerifier jwsVerifier;

    private JWSSigner jwsSigner;

    public static final int PASS = 0;
    public static final int FAIL = 1;
    public static final int EXPIRE = 2;
    private static Gson gson = new Gson();
    @PostConstruct
    public void init(){
        jwsVerifier = getJWSVerifier();

        jwsSigner = getJWSSigner();
    }


    //生成一个token
    public <T>String  createToken(T payloadMap)  {

        //3.先建立一个头部Header
        /*
         * JWSHeader参数：1.加密算法法则,2.类型，3.。。。。。。。
         * 一般只需要传入加密算法法则就可以。
         * 这里则采用HS256
         *
         * JWSAlgorithm类里面有所有的加密算法法则，直接调用。
         */
        JWSHeader jwsHeader = new JWSHeader(JWSAlgorithm.HS256);

        //建立一个载荷Payload
        Payload payload = new Payload(new JSONObject(payloadMap==null? new HashMap<String, Object>() :gson.fromJson(gson.toJson(payloadMap), Map.class)));

        //将头部和载荷结合在一起
        JWSObject jwsObject = new JWSObject(jwsHeader, payload);

        //建立一个密匙

        sign(jwsObject);
        //生成token
        return jwsObject.serialize();
    }

    private void sign(JWSObject jwsObject){
        try {
            //签名
            jwsObject.sign(jwsSigner);
        }catch (JOSEException e){
            throw new ErrorException(ResultEnum.PARAM_ERROR, "签名失败",e);
        }
    }

    private JWSSigner getJWSSigner(){
        try {
            return new MACSigner(secret.getBytes());
        }catch (KeyLengthException e){
            throw new ErrorException(ResultEnum.PARAM_ERROR,"签名秘钥初始化失败",e);
        }
    }

    private JWSObject getJWSObjectFromToken(String token){
        try {
            return JWSObject.parse(token);
        }catch (ParseException e){
            throw new ErrorException(ResultEnum.PARAM_ERROR,"token不合法",e);
        }
    }

    private JWSVerifier getJWSVerifier(){
        try {
            return new MACVerifier(secret);
        }catch (JOSEException e){
            throw new ErrorException(ResultEnum.PARAM_ERROR,"生成秘钥失败",e);
        }
    }

    private boolean verifyJWSObject(JWSObject jwsObject){
        try {
            return jwsObject.verify(jwsVerifier);
        }catch (JOSEException e){
            throw new ErrorException(ResultEnum.PARAM_ERROR,"验证token失败",e);
        }
    }

    /**
     * 校验token
     * @param <T> bean类型
     * @param token token内容
     * @param clz bean Class
     * @return 校验结果
     */
    public <T> TokenValidResultVO<UserTokenInfoVO> valid(String token, Class<T> clz) {
        //解析token
        JWSObject jwsObject = getJWSObjectFromToken(token);

        //获取到载荷
        Payload payload=jwsObject.getPayload();

        TokenValidResultVO tokenValidResultVO = new TokenValidResultVO();

        //判断token
        if (verifyJWSObject(jwsObject)) {
            tokenValidResultVO.setState(PASS);
            //载荷的数据解析成json对象。
            tokenValidResultVO.setData(gson.fromJson(payload.toJSONObject().toJSONString(),clz));

            //判断token是否过期
            if(tokenValidResultVO.getExp() != 0){
                if (new Date().getTime() > tokenValidResultVO.getExp()) {
                    //已经过期
                    tokenValidResultVO.setState(EXPIRE);
                }
            }
        }else {
            tokenValidResultVO.setState(FAIL);
        }
        return tokenValidResultVO;
    }
}
