package com.edu.member;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

// 빈 등록 자동화
@Component
public class MemberServiceImpl implements MemberService{

    private final MemberRepository memberRepository;

    // 마치 ac.getBean(MemberRepository.class)를 넣어주듯이
    @Autowired  // 이걸 생성자에 붙여주면 MemberRepository 타입에 맞는 빈을 알아서 의존성을 주입해준다
    public MemberServiceImpl(MemberRepository memberRepository) {
        this.memberRepository = memberRepository;
    }

    @Override
    public void join(Member member) {
        memberRepository.save(member);
    }

    @Override
    public Member findMember(Long memberId) {
        return memberRepository.findById(memberId);
    }

    //테스트 용도
    public  MemberRepository getMemberRepository(){
        return memberRepository;
    }
}
