package com.example.bsm.serviceimpl;

import com.example.bsm.entity.Admin;
import com.example.bsm.entity.Hospital;
import com.example.bsm.entity.User;
import com.example.bsm.enums.Role;
import com.example.bsm.exception.HospitalNotFoundByIdException;
import com.example.bsm.exception.UserNotFoundByIdException;
import com.example.bsm.repository.AdminRepository;
import com.example.bsm.repository.HospitalRepository;
import com.example.bsm.repository.UserRepository;
import com.example.bsm.response.AdminResponse;
import com.example.bsm.service.AdminService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class AdminServiceImpl implements AdminService {

    @Autowired
    private AdminRepository adminRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private HospitalRepository hospitalRepository;

    private AdminResponse getAdminResponse(Admin admin) {
        return AdminResponse.builder()
                .adminId(admin.getAdminId())
//                .hospital(admin.getHospital())
                .user(admin.getUser())
                .build();
    }

    @Override
    public AdminResponse addAdmin(int userId) {

        Optional<User> useroptional = userRepository.findById(userId);
        if (useroptional.isEmpty())
            throw new UserNotFoundByIdException("Fail to update user with ID: " );

        User exUser = useroptional.get();
        Admin admin = Admin.builder()
                .user(exUser)
                .build();

        exUser.setRole(Role.valueOf("ADMIN"));

        adminRepository.save(admin);
        userRepository.save(exUser);

        AdminResponse adminResponse = this.getAdminResponse(admin);

        return adminResponse;
    }
}
