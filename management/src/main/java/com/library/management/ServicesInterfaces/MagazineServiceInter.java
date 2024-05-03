package com.library.management.ServicesInterfaces;

import com.library.management.Dtos.BookDto;
import com.library.management.Dtos.MagazineDto;

import java.util.List;

public interface MagazineServiceInter
{
    public MagazineDto addMagazine();
    public MagazineDto searchMagazine(long magazineId);
    public MagazineDto deleteMagazine(long magazineId);
    public MagazineDto updateMagazine(MagazineDto magazineDto,long magazineId);
    public List<MagazineDto> addAllMagazines();
    public List<MagazineDto> findAllMagazines();
}
