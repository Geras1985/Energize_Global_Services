package com.hotel.villa.service.impl;

import com.hotel.villa.entity.Bank;
import com.hotel.villa.entity.Role;
import com.hotel.villa.entity.User;
import com.hotel.villa.repository.RoleRepo;
import com.hotel.villa.service.RoleService;
import lombok.extern.slf4j.Slf4j;
import org.hibernate.jpa.boot.spi.Bootstrap;
import org.springframework.stereotype.Service;

import javax.management.Query;
import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.persistence.TypedQuery;
import java.util.List;
import java.util.concurrent.ConcurrentHashMap;
import java.util.stream.Stream;

@Service
@Slf4j
public class RoleServiceImpl implements RoleService{

    private final RoleRepo roleRepo;

    public RoleServiceImpl(RoleRepo roleRepo) {

        this.roleRepo = roleRepo;
    }
//Employee emp = em.find(Employee.class, 158);
//emp.setSalary(emp.getSalary() + 1000);

//    @Override
//    public List<Role> getAllRoles(){
//        return roleRepo.getAllRoles();
//    }
}
