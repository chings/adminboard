package adminboard.webapp.component;

import adminboard.core.AdminService;
import adminboard.core.User;
import org.apache.dubbo.config.annotation.DubboReference;
import org.apache.shiro.authc.*;
import org.apache.shiro.authz.AuthorizationInfo;
import org.apache.shiro.authz.SimpleAuthorizationInfo;
import org.apache.shiro.realm.AuthorizingRealm;
import org.apache.shiro.subject.PrincipalCollection;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.HashSet;
import java.util.Set;
import java.util.stream.Collectors;

@Component("authorizer")
public class AdminRealm extends AuthorizingRealm {

    @Autowired(required = false)
    @DubboReference
    AdminService adminService;

    @Override
    protected AuthorizationInfo doGetAuthorizationInfo(PrincipalCollection principals) {
        String username = (String)getAvailablePrincipal(principals);
        User user = adminService.getUser(username);
        if(user == null) throw new UnknownAccountException();

        SimpleAuthorizationInfo result = new SimpleAuthorizationInfo();
        result.setStringPermissions(adminService.findUserPermissions(user.getId()).stream().collect(Collectors.toSet()));

        Set<String> roles = new HashSet<>();
        roles.addAll(adminService.findUserRoles(user.getId()).stream().map(role -> role.getName()).collect(Collectors.toSet()));
        result.setRoles(roles);

        return result;
    }

    @Override
    protected AuthenticationInfo doGetAuthenticationInfo(AuthenticationToken token) throws AuthenticationException {
        String username = (String)token.getPrincipal();
        User user = adminService.getUser(username);
        if(user == null) throw new UnknownAccountException();

        String password = new String((char[])token.getCredentials());
        user = adminService.getUser(username, password);
        if(user == null) throw new IncorrectCredentialsException();

        return new SimpleAuthenticationInfo(username, password, getName());
    }

}