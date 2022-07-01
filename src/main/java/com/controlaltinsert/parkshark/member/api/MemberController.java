package com.controlaltinsert.parkshark.member.api;

import com.controlaltinsert.parkshark.member.service.MemberService;
import com.controlaltinsert.parkshark.member.api.dto.CreateMemberDTO;
import com.controlaltinsert.parkshark.member.api.dto.MemberDTO;
import com.controlaltinsert.parkshark.parkinglot.api.dto.ParkingLotDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@AllArgsConstructor
@FieldDefaults(level = AccessLevel.PRIVATE)
@RequestMapping("members")
public class MemberController {
    MemberService memberService;

    @PostMapping(consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE, path = "add")
    MemberDTO createMember(@RequestBody CreateMemberDTO createMemberDTO){
        return this.memberService.createMember(createMemberDTO);
    }

    @GetMapping(path = "/{id}", produces = "application/json")
    @ResponseStatus(HttpStatus.OK)
    public MemberDTO getMember(@PathVariable int id){
        return this.memberService.getMemberById(id);
    }

    @GetMapping(produces = MediaType.APPLICATION_JSON_VALUE)
    List<MemberDTO> getAllMembers(){
        return this.memberService.getAllMembers();
    }

}
