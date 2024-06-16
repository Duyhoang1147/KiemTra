package com.example.KT.Controller;

import com.example.KT.Model.NhanVien;
import com.example.KT.Service.NhanVienService;
import com.example.KT.Service.PhongBanService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

@Controller
@RequestMapping("/nhanviens")
@RequiredArgsConstructor
public class NhanVienController {
    @Autowired
    private NhanVienService nhanVienService;
    @Autowired
    private PhongBanService phongBanService;

    @GetMapping("")
    public String showProductList(Model model, @RequestParam(defaultValue = "1") int page) {
        int pageSize = 5;
        Page<NhanVien> nhanVienPage = nhanVienService.Page(page, pageSize);
        model.addAttribute("nhanvien", nhanVienService.getallnhanvien());
        model.addAttribute("currentPage", page);
        model.addAttribute("totalPages", nhanVienPage.getTotalPages());
        model.addAttribute("totalItems", nhanVienPage.getTotalElements());
        return "/Nhanvien/nhanvien-from";
    }

    @GetMapping("/add")
    public String showAddForm(Model model) {
        model.addAttribute("nhanvien", new NhanVien());
        model.addAttribute("phongban", phongBanService.getAllPhongBan());
        return "/Nhanvien/add-nhanvien";
    }

    @PostMapping("/add")
    public String addProduct(@Valid NhanVien nhanVien, BindingResult result) {
        if (result.hasErrors())
            return "/nhanvien/add-nhanvien";
        nhanVienService.addNhanVien(nhanVien);
        return "redirect:/nhanviens";
    }
    @GetMapping("/edit/{id}")
    public String showEditForm (@PathVariable("id") String id, Model model){
        NhanVien nhanvien = nhanVienService.getbyid(id).orElseThrow(() -> new IllegalArgumentException("Invalid product Id:" + id));
        model.addAttribute("nhanvien", nhanvien);
        model.addAttribute("phongban", phongBanService.getAllPhongBan());
        return "/Nhanvien/update-nhanvien";
    }

    @PostMapping("/update/{id}")
    public String updateProduct (@PathVariable("id") String id, @Valid NhanVien nhanVien, BindingResult result){
        if (result.hasErrors()) {
            nhanVien.setMaNV(id);
            return "/Nhanvien/update-nhanvien";
        }
        nhanVienService.updateNhanVien(nhanVien);
        return "redirect:/nhanviens";
    }

    @GetMapping("/delete/{id}")
    public String deleteProduct (@PathVariable String id){
        nhanVienService.deleteProductById(id);
        return "redirect:/nhanviens";
    }
}
