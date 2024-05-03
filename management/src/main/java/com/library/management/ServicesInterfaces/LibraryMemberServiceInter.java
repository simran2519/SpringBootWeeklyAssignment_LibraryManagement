package com.library.management.ServicesInterfaces;

import com.library.management.Dtos.LibraryMemberDto;

import java.util.List;

public interface LibraryMemberServiceInter
{
    public LibraryMemberDto addLibraryMember(LibraryMemberDto libraryMemberDto);
    public LibraryMemberDto searchLibraryMember(long libraryMemberId);
    public LibraryMemberDto updateLibraryMember(LibraryMemberDto libraryMemberDto,long libraryMemberId);
    public LibraryMemberDto deleteLibraryMember(long libraryMemberId);
    public List<LibraryMemberDto> addAllLibraryMember(List<LibraryMemberDto> memberDtos);
    public List<LibraryMemberDto> findAllLibraryMember();
}
