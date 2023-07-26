// package com.thesis.springboot.vetclinicsystemcore.services;

// import java.util.Collections;

// import org.springframework.security.core.userdetails.UserDetails;
// import org.springframework.security.core.userdetails.UserDetailsService;
// import org.springframework.security.core.userdetails.UsernameNotFoundException;
// import org.springframework.stereotype.Service;

// import com.thesis.springboot.vetclinicsystemcore.exceptions.NotFoundException;
// import com.thesis.springboot.vetclinicsystemcore.models.User;
// import com.thesis.springboot.vetclinicsystemcore.repositories.UserRepository;

// @Service
// public class CustomUserDetailsService implements UserDetailsService{
//     private final UserRepository userRepository;

//     public CustomUserDetailsService(UserRepository userRepository) {
//         this.userRepository = userRepository;
//     }

//     @Override
//     public UserDetails loadUserByUsername(String username) throws UsernameNotFoundException {
//         User user = userRepository.findByUsername(username);
//         if (user == null) {
//             throw new NotFoundException("User not found");
//         }
//         return new org.springframework.security.core.userdetails.User(
//                 user.getUsername(),
//                 user.getPassword(),
//                 Collections.emptyList()
//         );
//     }


// }
