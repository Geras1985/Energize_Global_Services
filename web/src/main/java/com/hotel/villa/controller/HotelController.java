package com.hotel.villa.controller;

import com.hotel.villa.entity.Hotel;
import com.hotel.villa.service.HotelService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.config.annotation.EnableWebMvc;

import javax.servlet.http.HttpSession;
import java.util.List;

@Controller
@EnableWebMvc
public class HotelController {

    public HotelController(HotelService hotelService) {
        this.hotelService = hotelService;
    }

    private final HotelService hotelService;

    @GetMapping("/")
    public String home(Model model) {
        List<Hotel> allHotel = hotelService.getAllHotel();
        model.addAttribute("hotel", allHotel);
        return "index";
    }

    @GetMapping("add_hotel.html")
    public String addHotel() {
        return "add_hotel";
    }

    @GetMapping("login.html")
    public String login() {
        return "login";
    }

    @PostMapping("/register")
    public String hotelRegister(@ModelAttribute Hotel hotel, HttpSession session) {
        hotelService.createHotel(hotel);
        session.setAttribute("msg", "Hotel was added successfully...");
        return "redirect:/";
    }

    @GetMapping("/edit/{id}")
    public String editHotel(@PathVariable Long id, Model model) {
        Hotel hotel = hotelService.editHotelById(id);
        model.addAttribute("hotel", hotel);
        return "edit";
    }

    @GetMapping("/delete/{id}")
    public String deleteHotel(@PathVariable Long id, HttpSession session) {
        hotelService.deleteHotelById(id);
        session.setAttribute("msg", "Hotel was deleted successfully... ");
        return "redirect:/";
    }

}
