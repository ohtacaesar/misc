package com.ohtacaesar.misc.spring_boot_sandbox;

import java.util.Arrays;
import java.util.Collection;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import lombok.Getter;
import lombok.Setter;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

@Entity
@Getter
@Setter
public class Account implements UserDetails {

  @Id
  @GeneratedValue
  private int id;

  @Column(unique = true)
  private String username;

  @Column
  private String password;

  @Override
  public Collection<? extends GrantedAuthority> getAuthorities() {
    if (username.equals("root")) {
      return Arrays.asList(new SimpleGrantedAuthority("ROLE_ADMIN"));
    }
    return Arrays.asList(new SimpleGrantedAuthority("ROLE_USER"));
  }

  @Override
  public boolean isAccountNonExpired() {
    return true;
  }

  @Override
  public boolean isAccountNonLocked() {
    return true;
  }

  @Override
  public boolean isCredentialsNonExpired() {
    return true;
  }

  @Override
  public boolean isEnabled() {
    return true;
  }
}
