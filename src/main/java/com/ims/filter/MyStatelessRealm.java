package com.ims.filter;
import com.ims.entity.Login;
import com.ims.util.JWT;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class MyStatelessRealm extends AuthorizingRealm {
    Logger logger = LoggerFactory.getLogger(MyStatelessRealm.class);

    public boolean supports(AuthenticationToken token) {
        return token instanceof UsernamePasswordToken;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken authenticationToken) throws AuthenticationException {
        logger.info("Realm处理登录验证");
        UsernamePasswordToken usernamePasswordToken = (UsernamePasswordToken) authenticationToken;
        String token = usernamePasswordToken.getUsername();
        return new SimpleAuthenticationInfo(token, token, getName());


    }

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principalCollection) {
        logger.info("Realm处理授权");
        String token = (String) principalCollection.getPrimaryPrincipal();
        logger.info("realm授权获取token:"+token);
        SimpleAuthorizationInfo authorizationInfo = new SimpleAuthorizationInfo();
        authorizationInfo.addRole("user");
        return authorizationInfo;
    }

}
