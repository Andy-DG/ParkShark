package com.controlaltinsert.parkshark.member;

import com.controlaltinsert.parkshark.member.api.CreateMemberDTO;
import com.controlaltinsert.parkshark.member.api.MemberDTO;
import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.experimental.FieldDefaults;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

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

}
