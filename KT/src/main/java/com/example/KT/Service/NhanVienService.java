package com.example.KT.Service;

import com.example.KT.Model.NhanVien;
import com.example.KT.Repository.NhanVienRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@Transactional
public class NhanVienService {
    private final NhanVienRepository nhanVienRepository;

    public List<NhanVien> getallnhanvien(){ return nhanVienRepository.findAll(); }

    public Optional<NhanVien> getbyid(String id) { return nhanVienRepository.findById(id); }

    public NhanVien addNhanVien(NhanVien nhanvien) { return nhanVienRepository.save(nhanvien); }

    public NhanVien updateNhanVien(@NotNull NhanVien nhanVien)
    {
        NhanVien existingNhanVien = nhanVienRepository.findById(nhanVien.getMaNV())
                .orElseThrow(() -> new IllegalStateException("Product with ID " + nhanVien.getMaNV() + " does not exist."));
        existingNhanVien.setTenNV(nhanVien.getTenNV());
        existingNhanVien.setLuong(nhanVien.getLuong());
        existingNhanVien.setGioiTinh(nhanVien.getGioiTinh());
        existingNhanVien.setNoiSinh(nhanVien.getNoiSinh());
        existingNhanVien.setPhongban(nhanVien.getPhongban());
        return nhanVienRepository.save(existingNhanVien);
    }

    public void deleteProductById(String id) {
        if (!nhanVienRepository.existsById(id)) {
            throw new IllegalStateException("Product with ID " + id + " does not exist.");
        }
        nhanVienRepository.deleteById(id);
    }

    public Page<NhanVien> Page(int pageNo, int pageSize)
    {
        Pageable pageable = PageRequest.of(pageNo - 1, pageSize);
        return nhanVienRepository.findAll(pageable);
    }
}
