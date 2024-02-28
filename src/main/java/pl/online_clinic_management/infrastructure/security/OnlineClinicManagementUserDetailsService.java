package pl.online_clinic_management.infrastructure.security;

import lombok.RequiredArgsConstructor;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import pl.online_clinic_management.domain.ClinicUser;
import pl.online_clinic_management.infrastructure.database.repository.mapper.ClinicUserEntityMapper;

import java.util.List;
import java.util.Set;
import java.util.stream.Collectors;

@Service
@RequiredArgsConstructor
public class OnlineClinicManagementUserDetailsService implements UserDetailsService {


    private final ClinicUserRepository clinicUserRepository;
    private final ClinicUserEntityMapper clinicUserEntityMapper;


    @Override
    @Transactional
    public UserDetails loadUserByUsername(String userName) {
        ClinicUserEntity user = clinicUserRepository.findByUserName(userName);
        List<SimpleGrantedAuthority> authorities = getUserAuthority(user.getRoles());
        return buildUserForAuthentication(user, authorities);
    }

    @Transactional
    public ClinicUser findByUserName(String userName) {
        ClinicUserEntity user = clinicUserRepository.findByUserName(userName);
        return clinicUserEntityMapper.mapFromEntity(user);
    }



    private List<SimpleGrantedAuthority> getUserAuthority(Set<RoleEntity> roles) {
        return roles.stream()
                .map(role -> new SimpleGrantedAuthority(role.getRoleName()))
                .distinct()
                .collect(Collectors.toList());
    }

    private UserDetails buildUserForAuthentication(ClinicUserEntity user, List<SimpleGrantedAuthority> authorities) {
        return new org.springframework.security.core.userdetails.User(
                user.getUserName(),
                user.getPassword(),
                user.getActive(),
                true,
                true,
                true,
                authorities
        );
    }
}
