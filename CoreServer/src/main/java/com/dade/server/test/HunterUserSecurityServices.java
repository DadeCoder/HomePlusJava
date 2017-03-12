package com.dade.server.test;

import com.dade.common.domain.HunterUser.HunterUser;
import com.dade.common.utils.LogUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.web.client.RestTemplate;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Dade on 2017/3/12.
 */
public class HunterUserSecurityServices implements UserDetailsService {

    @Autowired
    RestTemplate restTemplate;

    private final HunterUserRepository hunterUserRepository;

    public HunterUserSecurityServices(HunterUserRepository hunterUserRepository){
        this.hunterUserRepository = hunterUserRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String phoneNumber) throws UsernameNotFoundException {

//        final String uri = "http://mongo-server/api/hunter/find/" + phoneNumber;
//        HunterUser hunterUser = restTemplate.getForEntity(uri, HunterUser.class).getBody();
        HunterUser hunterUser = hunterUserRepository.findByPhoneNumber(phoneNumber);

        LogUtil.info(hunterUser.toString());

        if (hunterUser != null){
            List<GrantedAuthority> authorities = new ArrayList<GrantedAuthority>();
            authorities.add(new SimpleGrantedAuthority("ROLE_" + hunterUser.getRole()));

            LogUtil.info("ROLE_" + hunterUser.getRole());

            return new org.springframework.security.core.userdetails.User(hunterUser.getPhoneNumber(), hunterUser.getPassword(), authorities);
        }

        throw new UsernameNotFoundException("User " + phoneNumber + "Not Found!");

    }

}
