package com.example.KT.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.Length;

@Setter
@Getter
@RequiredArgsConstructor
@AllArgsConstructor
@Entity
@Table(name = "NhanVien")
public class NhanVien {
    @Id
    private String MaNV;

    @NotBlank(message = "Tên không được bỏ trống")
    @Length(min = 1, max = 50, message = "Tên không được dài quá 50 ký tự")
    private String TenNV;

    private String GioiTinh;
    private String NoiSinh;
    private double Luong;

    @ManyToOne
    @JoinColumn(name = "phongban_MaPB")
    private PhongBan phongban;

}
