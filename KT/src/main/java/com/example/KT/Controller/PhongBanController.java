package com.example.KT.Controller;

import com.example.KT.Model.PhongBan;
import com.example.KT.Service.PhongBanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.List;


@Controller
@RequestMapping("phongbans")
@RequiredArgsConstructor
public class PhongBanController {
    @Autowired
    private final PhongBanService phongBanService;

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("phongban", new PhongBan());
        return "/phongban/add-phongban";
    }

    @PostMapping("/add")
    public String addCategory(@Valid PhongBan phongBan, BindingResult result) {
        if (result.hasErrors()) {
            return "/phongban/add-phongban";
        }
        phongBanService.addPhongBan(phongBan);
        return "redirect:/phongbans";
    }

    // Hiển thị danh sách danh mục
    @GetMapping("")
    public String listphongban(Model model) {
        List<PhongBan> listphongBan = phongBanService.getAllPhongBan();
        model.addAttribute("phongban", listphongBan);
        return "/Phongban/phongban-form";
    }

    @GetMapping("edit/{id}")
    public String showUpdateForm(@PathVariable("id") String id, Model model) {
        PhongBan phongBan = phongBanService.getPhongBanById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id:" + id));
        model.addAttribute("phongBan", phongBan);
        return "Phongban/update-phongban";
    }
    // POST request to update category
    @PostMapping("/update/{id}")
    public String updateCategory(@PathVariable("id") String id, @Valid PhongBan phongBan,
                                 BindingResult result, Model model) {
        if (result.hasErrors()) {
            phongBan.setMaPB(id);
            return "Phongban/update-phongban";
        }
        phongBanService.updatePhongban(phongBan);
        model.addAttribute("phongbans", phongBanService.getAllPhongBan());
        return "redirect:/phongbans";
    }
    // GET request for deleting category
    @GetMapping("/delete/{id}")
    public String deleteCategory(@PathVariable("id") String id, Model model) {
        PhongBan category = phongBanService.getPhongBanById(id)
                .orElseThrow(() -> new IllegalArgumentException("Invalid category Id:"
                        + id));
        phongBanService.deletePhongBanById(id);
        model.addAttribute("phongbans", phongBanService.getAllPhongBan());
        return "redirect:/phongbans";
    }
}
