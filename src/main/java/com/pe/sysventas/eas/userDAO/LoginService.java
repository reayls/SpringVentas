/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package com.pe.sysventas.eas.userDAO;

import com.pe.sysventas.eas.Entidades.Rol;
import com.pe.sysventas.eas.Entidades.Usuario;
import com.pe.sysventas.eas.Interfaces.IUser;
import java.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 *
 * @author Deary
 */
@Service("userDetailsService")
public class LoginService implements UserDetailsService{

    @Autowired
    private IUser iuser;
    
    @Override
    @Transactional(readOnly = true)
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Usuario usuario=iuser.findByEmail(username);
        if(usuario==null){
            throw new  UsernameNotFoundException(username);
        }
        else{
            List<GrantedAuthority> roles= new ArrayList<GrantedAuthority>();
            for(Rol ro:usuario.getRoles()){
                roles.add(new SimpleGrantedAuthority(ro.getNombre()));
            }
            return new User(usuario.getEmail(),usuario.getPassword(),roles);
        }
    }
    
}
