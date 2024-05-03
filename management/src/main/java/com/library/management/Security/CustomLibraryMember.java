package com.library.management.Security;

import com.library.management.Entities.LibraryMember;
import com.library.management.Repositories.LibraryMemberRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import java.util.Optional;
@Service
public class CustomLibraryMember  implements UserDetailsService {
    @Autowired
    LibraryMemberRepository libraryMemberRepository;
    @Override
    public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
        Optional<LibraryMember> libraryMember=libraryMemberRepository.findByUsername(username);
        if(libraryMember.isPresent())
        {
            var userObj=libraryMember.get();
            return User.builder()
                    .username(userObj.getUsername())
                    .password(userObj.getPassword())
                    .roles(getRoles(userObj))
                    .build();
        }
        else {
            throw new UsernameNotFoundException(username);
        }
    }
    private String[] getRoles(LibraryMember libraryMember)
    {
        if(libraryMember.getRole()==null)
        {
            return new String[]{"USER"};
        }
        return libraryMember.getRole().split(",");
    }
}
