package com.foodConsensus.security.services;

import java.util.Collection;
import java.util.Objects;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import com.fasterxml.jackson.annotation.JsonIgnore;
import com.foodConsensus.model.User;

public class UserDetailsImpl implements UserDetails{
	private static final long serialVersionUID = 1L;
	
	private int id;
	private String name;
	@JsonIgnore
	private String password;
	private boolean isAdmin;
	
	public UserDetailsImpl(int id, String name, String password, boolean isAdmin) {
		this.id = id;
		this.name = name;
		this.password = password;
		this.isAdmin = isAdmin;
	}
	
	public static UserDetailsImpl build(User user) {
		return new UserDetailsImpl(user.getId(), user.getName(), user.getPassword(), user.isAdmin());
	}
	
	public int getId() {
		return id;
	}
	
	@Override
	public String getPassword() {
		return password;
	}
	
	public String getName() {
		return name;
	}
	
	public boolean isAdmin() {
		return isAdmin;
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

	@Override
	public boolean equals(Object o) {
		if (this == o)
			return true;
		if (o == null || getClass() != o.getClass())
			return false;
		UserDetailsImpl user = (UserDetailsImpl) o;
		return Objects.equals(id, user.id);
	}

	@Override
	public Collection<? extends GrantedAuthority> getAuthorities() {
		// TODO Auto-generated method stub
		return null;
	}

	@Override
	public String getUsername() {
		// TODO Auto-generated method stub
		return null;
	}
	
}
