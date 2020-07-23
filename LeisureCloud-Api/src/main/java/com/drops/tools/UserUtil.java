package com.drops.tools;

import com.drops.domain.LoginUser;
import com.drops.tools.UserUtil;

import java.net.InetAddress;

import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;


public class UserUtil {
    public static LoginUser getLoginUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null) {
            if (authentication instanceof org.springframework.security.authentication.AnonymousAuthenticationToken) {
                return null;
            }
            if (authentication instanceof org.springframework.security.authentication.UsernamePasswordAuthenticationToken) {
                return (LoginUser) authentication.getPrincipal();
            }
        }
        return null;
    }

    public String getLocalHostName() {
        String hostName;
        try {
            InetAddress addr = InetAddress.getLocalHost();
            hostName = addr.getHostName();
        } catch (Exception ex) {
            hostName = "";
        }
        return hostName;
    }

    public String[] getAllLocalHostIP() {
        String[] ret = null;
        try {
            String hostName = getLocalHostName();
            if (hostName.length() > 0) {
                InetAddress[] addrs = InetAddress.getAllByName(hostName);
                if (addrs.length > 0) {
                    ret = new String[addrs.length];
                    for (int i = 0; i < addrs.length; i++) {
                        ret[i] = addrs[i].getHostAddress();
                    }
                }
            }
        } catch (Exception ex) {
            ret = null;
        }
        return ret;
    }
}