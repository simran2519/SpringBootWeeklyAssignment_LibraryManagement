package com.library.management.Services;

import com.fasterxml.jackson.databind.DeserializationFeature;
import com.fasterxml.jackson.databind.ObjectMapper;
import com.library.management.Dtos.BookDto;
import com.library.management.Dtos.LibraryMemberDto;
import com.library.management.Entities.Book;
import com.library.management.Entities.LibraryMember;
import com.library.management.Exceptions.IdNotFoundException;
import com.library.management.Repositories.LibraryMemberRepository;
import com.library.management.ServicesInterfaces.LibraryMemberServiceInter;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.Arrays;
import java.util.List;

@Service
public class LibraryMemberService implements LibraryMemberServiceInter {
    private LibraryMemberRepository libraryMemberRepository;
    private ObjectMapper objectMapper;
    private PasswordEncoder passwordEncoder;
    public LibraryMemberService(LibraryMemberRepository libraryMemberRepository, ObjectMapper objectMapper,PasswordEncoder passwordEncoder)
    {
        this.libraryMemberRepository=libraryMemberRepository;
        this.objectMapper=objectMapper;
        this.passwordEncoder=passwordEncoder;
    }
    @Override
    public LibraryMemberDto addLibraryMember(LibraryMemberDto libraryMemberDto) {
        LibraryMember newMember=  objectMapper.convertValue(libraryMemberDto, LibraryMember.class);
        newMember.setPassword(passwordEncoder.encode(newMember.getPassword()));
        libraryMemberRepository.save(newMember);
        return objectMapper.convertValue(newMember, LibraryMemberDto.class);
    }

    @Override
    public LibraryMemberDto searchLibraryMember(long libraryMemberId) {
        LibraryMember memberToSearch= libraryMemberRepository.findById(libraryMemberId).orElseThrow(IdNotFoundException::new);
        objectMapper.configure(DeserializationFeature.FAIL_ON_UNKNOWN_PROPERTIES,false);
        return objectMapper.convertValue(memberToSearch,LibraryMemberDto.class);
    }

    @Override
    public LibraryMemberDto updateLibraryMember(LibraryMemberDto libraryMemberDto,long libraryMemberId) {
        LibraryMember memberToMatch = libraryMemberRepository.findById(libraryMemberId).get();
        LibraryMember memberToUpdate = objectMapper.convertValue(libraryMemberDto, LibraryMember.class);
        memberToUpdate.setMemberId(libraryMemberId); //We set by some other value also I am setting it with same id as before
        libraryMemberRepository.save(memberToUpdate);
        return objectMapper.convertValue(memberToMatch, LibraryMemberDto.class);
    }

    @Override
    public LibraryMemberDto deleteLibraryMember(long libraryMemberId) {
        LibraryMember memberToDelete= libraryMemberRepository.findById(libraryMemberId).get();
        libraryMemberRepository.deleteById(libraryMemberId);
        return objectMapper.convertValue(memberToDelete,LibraryMemberDto.class);
    }

    @Override
    public List<LibraryMemberDto> addAllLibraryMember(List<LibraryMemberDto> memberDtos) {
        List<LibraryMember> libraryMemberList= Arrays.asList(objectMapper.convertValue(memberDtos,LibraryMember[].class));
        libraryMemberRepository.saveAll(libraryMemberList);
        return Arrays.asList(objectMapper.convertValue(libraryMemberList,LibraryMemberDto[].class));
    }

    @Override
    public List<LibraryMemberDto> findAllLibraryMember() {
        List<LibraryMember> libraryMemberList= libraryMemberRepository.findAll();
        return Arrays.asList(objectMapper.convertValue(libraryMemberList,LibraryMemberDto[].class));
    }
}
