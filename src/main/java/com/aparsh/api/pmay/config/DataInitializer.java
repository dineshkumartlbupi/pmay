package com.aparsh.api.pmay.config;

import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.provisioning.JdbcUserDetailsManager;

@Configuration
public class DataInitializer {

    @Bean
    public CommandLineRunner createDefaultUser(JdbcUserDetailsManager users, PasswordEncoder encoder) {
        return args -> {
            if (!users.userExists("ifmisuser")) {
                var user = User.withUsername("ifmisuser")
                        .password(encoder.encode("ifmispassword"))
                        .roles("IFMIS")
                        .build();
                users.createUser(user);
                // also create authority row (JdbcUserDetailsManager does this)
                System.out.println("Created default user 'ifmisuser'");
            } else {
                System.out.println("Default user already exists");
            }
        };
    }
}
