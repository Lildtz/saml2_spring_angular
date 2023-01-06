package com.all.saml2test.security;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.security.config.Customizer;
import org.springframework.security.config.annotation.web.builders.HttpSecurity;
import org.springframework.security.config.annotation.web.configuration.EnableWebSecurity;
import org.springframework.security.saml2.provider.service.metadata.OpenSamlMetadataResolver;
import org.springframework.security.saml2.provider.service.registration.RelyingPartyRegistrationRepository;
import org.springframework.security.saml2.provider.service.web.DefaultRelyingPartyRegistrationResolver;
import org.springframework.security.saml2.provider.service.web.RelyingPartyRegistrationResolver;
import org.springframework.security.saml2.provider.service.web.Saml2MetadataFilter;
import org.springframework.security.saml2.provider.service.web.authentication.OpenSaml4AuthenticationRequestResolver;
import org.springframework.security.saml2.provider.service.web.authentication.Saml2AuthenticationRequestResolver;
import org.springframework.security.saml2.provider.service.web.authentication.Saml2WebSsoAuthenticationFilter;
import org.springframework.security.web.SecurityFilterChain;

@Configuration
@EnableWebSecurity
public class SecurityConfiguration {
    @Bean
    SecurityFilterChain securityFilterChain(HttpSecurity http,
                                            RelyingPartyRegistrationRepository relyingPartyRegistrationRepository) throws Exception {

        DefaultRelyingPartyRegistrationResolver relyingPartyRegistrationResolver =
                new DefaultRelyingPartyRegistrationResolver(relyingPartyRegistrationRepository);
        Saml2MetadataFilter filter = new Saml2MetadataFilter(
                relyingPartyRegistrationResolver,
                new OpenSamlMetadataResolver());

//        RelyingPartyRegistrationResolver relyingPartyRegistrationResolver = new DefaultRelyingPartyRegistrationResolver(
//                relyingPartyRegistrationRepository);
//        Saml2MetadataFilter metadataFilter = new Saml2MetadataFilter(relyingPartyRegistrationResolver,
//                new OpenSamlMetadataResolver());

        // @formatter:off
        http
                .authorizeHttpRequests((authorize) -> authorize
//                        .requestMatchers("/api/**").permitAll()
                        .anyRequest().authenticated()
                )
                .saml2Login(Customizer.withDefaults())
                .saml2Logout(Customizer.withDefaults())
                .addFilterBefore(filter, Saml2WebSsoAuthenticationFilter.class);
        // @formatter:on
        return http.build();
    }

//    @Bean
//    Saml2AuthenticationRequestResolver authenticationRequestResolver(RelyingPartyRegistrationRepository registrations) {
//        RelyingPartyRegistrationResolver registrationResolver =
//                new DefaultRelyingPartyRegistrationResolver(registrations);
//        OpenSaml4AuthenticationRequestResolver authenticationRequestResolver =
//                new OpenSaml4AuthenticationRequestResolver(registrationResolver);
//        authenticationRequestResolver.setAuthnRequestCustomizer((context) ->
//                context.getRelyingPartyRegistration().
//                context
//                .getAuthnRequest().setForceAuthn(true));
//        return authenticationRequestResolver;
//    }
}
