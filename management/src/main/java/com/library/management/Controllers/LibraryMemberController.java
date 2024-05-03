package com.library.management.Controllers;
import com.library.management.Dtos.LibraryMemberDto;
import com.library.management.Services.LibraryMemberService;
import com.library.management.Utils.MyResponseGenerator;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@RestController
@RequestMapping("/member")
public class LibraryMemberController
{
    LibraryMemberService libraryMemberService;
    LibraryMemberController(LibraryMemberService libraryMemberService)
    {
        this.libraryMemberService=libraryMemberService;
    }
    @GetMapping("hii")
    public String hii()
    {
        return "Simran is my name";
    }
    @PostMapping("/add")
    public ResponseEntity<Object> addMember(@Valid @RequestBody LibraryMemberDto libraryMemberDto)
    {
        LibraryMemberDto addedLibraryDto= libraryMemberService.addLibraryMember(libraryMemberDto);
        if(addedLibraryDto!=null)
        {
            return MyResponseGenerator.generateResponse(HttpStatus.CREATED,true,"Member is Created",addedLibraryDto);
        }
        else {
            return MyResponseGenerator.generateResponse(HttpStatus.BAD_REQUEST,false,"Something went wrong", addedLibraryDto);
        }
    }
    @GetMapping("/find/{memberId}")
    public ResponseEntity<Object> findMember(@PathVariable long memberId)
    {
        LibraryMemberDto memberTofind =libraryMemberService.searchLibraryMember(memberId);
        if(memberTofind!=null)
        {
            return MyResponseGenerator.generateResponse(HttpStatus.OK,true,"Member is found", memberTofind);
        }
        else {
            return MyResponseGenerator.generateResponse(HttpStatus.BAD_REQUEST,false,"Member is not founc",memberTofind);
        }
    }
    @PutMapping("/update/{memberId}")
    public ResponseEntity<Object> update(@Valid @RequestBody LibraryMemberDto memberDto,@PathVariable long memberId)
    {
        LibraryMemberDto membertoUpdate = libraryMemberService.updateLibraryMember(memberDto,memberId);
        if(membertoUpdate!=null)
        {
            return MyResponseGenerator.generateResponse(HttpStatus.OK,true,"Member is updated",membertoUpdate);
        }
        else {
            return MyResponseGenerator.generateResponse(HttpStatus.BAD_REQUEST,false,"member is not updated",membertoUpdate);
        }
    }
    @PostMapping("/addAll")
    public List<LibraryMemberDto> addAll(@Valid @RequestBody List<LibraryMemberDto> memberDtos)
    {
        return libraryMemberService.addAllLibraryMember(memberDtos);
    }
    @DeleteMapping("/delete{memberId}/")
    public ResponseEntity<Object> deleteBook(@PathVariable long memberId)
    {
        LibraryMemberDto memberDto= libraryMemberService.deleteLibraryMember(memberId);
        if(memberDto!=null)
        {
            return MyResponseGenerator.generateResponse(HttpStatus.OK,true,"member is Deleted Successfully",memberDto);
        }
        else {
            return MyResponseGenerator.generateResponse(HttpStatus.BAD_REQUEST,false,"Member is not Deleted Successfully",memberDto);
        }
    }
    @GetMapping("/findAll")
    public List<LibraryMemberDto> findAll()
    {
        return libraryMemberService.findAllLibraryMember();
    }
}
