package com.library.management.Services;

import com.library.management.Dtos.MagazineDto;
import com.library.management.ServicesInterfaces.MagazineServiceInter;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class MagazineService implements MagazineServiceInter {
    @Override
    public MagazineDto addMagazine() {
        return null;
    }

    @Override
    public MagazineDto searchMagazine(long magazineId) {
        return null;
    }

    @Override
    public MagazineDto deleteMagazine(long magazineId) {
        return null;
    }

    @Override
    public MagazineDto updateMagazine(MagazineDto magazineDto, long magazineId) {
        return null;
    }

    @Override
    public List<MagazineDto> addAllMagazines() {
        return List.of();
    }

    @Override
    public List<MagazineDto> findAllMagazines() {
        return List.of();
    }
}
