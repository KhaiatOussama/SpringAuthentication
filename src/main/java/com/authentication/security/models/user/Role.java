package com.authentication.security.models.user;

import lombok.Getter;
import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.Collections;
import java.util.List;
import java.util.Set;

import static com.authentication.security.models.user.Permission.*;

@RequiredArgsConstructor
public enum Role {
    USER(
            Set.of(
                    USER_UPDATE,
                    USER_CREATE,
                    USER_DELETE,
                    USER_READ
            )
    ),
    ADMIN(Collections.emptySet()),
    DOCTOR(
            Set.of(
                    USER_UPDATE,
                    USER_CREATE,
                    USER_DELETE,
                    USER_READ,
                    DOCTOR_CREATE,
                    DOCTOR_DELETE,
                    DOCTOR_UPDATE,
                    DOCTOR_READ
            )
    ),

    ;

    @Getter
    private final Set<Permission> permissions;

    public List<SimpleGrantedAuthority> getAuthorities(){
        var authorities = getPermissions()
                .stream()
                .map(permission -> new SimpleGrantedAuthority(permission.name()))
                .toList();
        authorities.add(new SimpleGrantedAuthority("ROLE_" + this.name()));
        return authorities;
    }
}
