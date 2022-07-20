package com.hotel.villa;

import com.hotel.villa.entity.Address;
import com.hotel.villa.entity.User;
import com.hotel.villa.enums.Status;

import javax.persistence.EntityManager;
import javax.persistence.TypedQuery;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class MyUserService {
    protected EntityManager em;

    public MyUserService(EntityManager em) {
        this.em = em;
    }

    public User createUser(String firstName, String lastName, String username, String password, String email) {
        User user = new User();
        user.setLastName(lastName);
        user.setFirstName(firstName);
        user.setUsername(username);
        user.setPassword(password);
        user.setEmail(email);
        user.setStatus(Status.ACTIVE);
        user.setCreated(LocalDateTime.now());
        user.setValidDate(LocalDateTime.now().plusYears(3));
        em.persist(user);
        return user;
    }

    public void removeUser(Long id) {
        User user = findUser(id);
        if (user!=null){
            em.remove(user);
        }
    }
    public User findUser(Long id){
        return em.find(User.class, id);
    }

    public User editUserName(Long id, String name){
        User user = findUser(id);
        if (user!=null){
            user.setLastName(user.getLastName()+name);
            user.setUpdated(LocalDateTime.now());
            em.persist(user);
        }
        return user;
    }

    public static void main(String[] args) {


    Address  address = new Address();
    }
}
