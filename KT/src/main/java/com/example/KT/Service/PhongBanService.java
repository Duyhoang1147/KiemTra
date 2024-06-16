package com.example.KT.Service;

import com.example.KT.Model.PhongBan;
import com.example.KT.Repository.PhongBanRepository;
import jakarta.transaction.Transactional;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

@Service
@RequiredArgsConstructor
@AllArgsConstructor
@Transactional
public class PhongBanService {
    @Autowired
    private PhongBanRepository phongBanRepository;

    public List<PhongBan> getAllPhongBan() { return phongBanRepository.findAll(); }

    public Optional<PhongBan> getPhongBanById(String id) { return phongBanRepository.findById(id); }

    public void addPhongBan(PhongBan phongBan) { phongBanRepository.save(phongBan); }

    public void updatePhongban(@NotNull PhongBan phongBan) {
        PhongBan existingPhongBan = phongBanRepository.findById(
                phongBan.getMaPB()).orElseThrow(
                () -> new IllegalStateException(
                        "Category with ID " + phongBan.getMaPB() + " does not exist."));
        existingPhongBan.setTenPB(phongBan.getTenPB());
        phongBanRepository.save(existingPhongBan);
    }

    public void deletePhongBanById(String id) {
        if (!phongBanRepository.existsById(id)) {
            throw new IllegalStateException("Category with ID " + id + " does not exist.");
        }
        phongBanRepository.deleteById(id);
    }
}
