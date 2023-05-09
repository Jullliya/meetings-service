package dev.jullliya.meetingsservice.controller;

import dev.jullliya.meetingsservice.dto.MembersDto;
import dev.jullliya.meetingsservice.entity.Members;
import dev.jullliya.meetingsservice.myexceptions.CreateMemberExceptions;
import dev.jullliya.meetingsservice.service.MembersService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

@RestController
@RequestMapping("/members")
public class MembersController {

    private final MembersService membersService;

    @Autowired
    public MembersController(MembersService membersService) {
        this.membersService = membersService;
    }

    @RequestMapping(method = RequestMethod.POST)
    public ResponseEntity<?> createMember(@RequestBody MembersDto membersDto) throws CreateMemberExceptions {
        //проверка занятости времени у добавляемого пользователя
        try{
            List<Members> members = membersService.getAllMembers(membersDto.getUserId());
            LocalDateTime StartNewMeet = LocalDateTime.of(membersDto.getMeet().getMeetStartDate(), membersDto.getMeet().getMeetStartTime());
            LocalDateTime FinishNewMeet = LocalDateTime.of(membersDto.getMeet().getMeetFinishDate(), membersDto.getMeet().getMeetFinishTime());

            for (int i = 0; i < members.size(); i++) {

                LocalDateTime StartExistMeet = LocalDateTime.of(members.get(i).getMeet().getMeetStartDate(), members.get(i).getMeet().getMeetStartTime());
                LocalDateTime FinishExistMeet = LocalDateTime.of(members.get(i).getMeet().getMeetFinishDate(), members.get(i).getMeet().getMeetFinishTime());

                if ((StartNewMeet.compareTo(StartExistMeet) >= 0 && (StartNewMeet.compareTo(FinishExistMeet) <= 0))
                        || (FinishNewMeet.compareTo(StartExistMeet) >= 0) && FinishNewMeet.compareTo(FinishExistMeet) <= 0) {
                    {
                        throw new CreateMemberExceptions("This user already has a meeting in this datetime!");
                    }
                }
            }
        }
        catch (CreateMemberExceptions e){
            return ResponseEntity.ok(e.getMessage());
        }
        Members members = membersService.createMember(membersDto.getMeet(), membersDto.getUserId());
        membersDto.setMemberId(members.getMemberId());
        return ResponseEntity.ok(membersDto);
    };

    @RequestMapping(value = "{id}", method = RequestMethod.GET)
    public ResponseEntity<MembersDto> getMemberById(@PathVariable("id") Long id) {
        Members member = membersService.getMemberById(id);
        MembersDto membersDto = new MembersDto();
        membersDto = createDto(membersDto, member);
        return ResponseEntity.ok(membersDto);
    };

    @RequestMapping(value = "{id}", method = RequestMethod.DELETE)
    public void deleteMember(@PathVariable("id") Long id) {
        membersService.deleteMemberById(id);
    };

    @RequestMapping(value = "all", method = RequestMethod.GET)
    public ResponseEntity<List<MembersDto>> getAllMembers(Long id){
        MembersDto membersDto = new MembersDto();
        List<Members> members = membersService.getAllMembers(id);
        List<MembersDto> membersDtos = new ArrayList<MembersDto>();
        for (Members member: members){
            membersDtos.add(createDto(membersDto, member));
        };
        return ResponseEntity.ok(membersDtos);
    };

    private MembersDto createDto(MembersDto membersDto, Members member){
        membersDto.setMemberId(member.getMemberId());
        membersDto.setMeet(member.getMeet());
        membersDto.setUserId(member.getUserId());
        return membersDto;
    }
}
