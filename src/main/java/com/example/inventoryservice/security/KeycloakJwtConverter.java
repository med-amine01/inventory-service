package com.example.inventoryservice.security;

import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.oauth2.jwt.Jwt;
import org.springframework.security.oauth2.server.resource.authentication.JwtAuthenticationConverter;
import org.springframework.security.oauth2.server.resource.authentication.JwtGrantedAuthoritiesConverter;

import java.util.Collection;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

public class KeycloakJwtConverter extends JwtAuthenticationConverter {

	private final JwtGrantedAuthoritiesConverter defaultConverter = new JwtGrantedAuthoritiesConverter();

	public KeycloakJwtConverter() {
		setJwtGrantedAuthoritiesConverter(this::extractAuthorities);
	}

	private Collection<GrantedAuthority> extractAuthorities(Jwt jwt) {
		// Get default authorities (scope-based)
		Collection<GrantedAuthority> authorities = defaultConverter.convert(jwt);

		// Extract realm roles using functional style with type safety
		Collection<GrantedAuthority> realmRoles = Optional.ofNullable(jwt.getClaimAsMap("realm_access"))
			.map(realmAccess -> realmAccess.get("roles"))
			.filter(roles -> roles instanceof List)
			.map(roles -> (List<?>) roles)
			.map(roles -> roles.stream()
				.filter(String.class::isInstance)
				.map(String.class::cast)
				.map(role -> new SimpleGrantedAuthority("ROLE_" + role.toUpperCase()))
				.map(GrantedAuthority.class::cast)
				.collect(Collectors.toList()))
			.orElse(List.of());

		// Combine all authorities
		return Stream.concat(authorities.stream(), realmRoles.stream()).collect(Collectors.toList());
	}

}