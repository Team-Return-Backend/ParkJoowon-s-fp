package com.example.jobis2.domain.user.application;


import com.example.jobis2.domain.user.dao.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class FindAllUserService {

    private final UserRepository userRepository;

    @Value("${key.pick-secret-key}")
    private String secretKey;

    @Transactional
    public List<AllUserResponse> findAllUser(PickSecretRequest request) {

        if (!secretKey.equals(request.getPickSecretKey())) throw InvalidKeyException.EXCEPTION;

        return userRepository.findAll()
                .stream()
                .map(AllUserResponse::new)
                .collect(Collectors.toList());
    }
}
