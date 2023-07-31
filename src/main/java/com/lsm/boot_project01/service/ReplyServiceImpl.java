package com.lsm.boot_project01.service;

import com.lsm.boot_project01.domain.Reply;
import com.lsm.boot_project01.dto.ReplyDTO;
import com.lsm.boot_project01.repository.ReplyRepository;
import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;
import org.modelmapper.ModelMapper;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
@Log4j2
public class ReplyServiceImpl implements ReplyService{
    private final ReplyRepository replyRepository;
    private final ModelMapper modelMapper;
    @Override
    public Long register(ReplyDTO replyDTO) {
        Reply reply = modelMapper.map(replyDTO, Reply.class);
        Long bno = replyRepository.save(reply).getRno();
        return bno;
    }
}

}
