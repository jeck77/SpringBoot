package com.edu.springboot;

import com.edu.AppConfig;
import com.edu.member.Grade;
import com.edu.member.Member;
import com.edu.member.MemberService;
import com.edu.member.MemberServiceImpl;

public class MemberApp {
    public static void main(String[] args) {

        // AppConfig에서 가져오는 방식
        AppConfig appConfig = new AppConfig();
        MemberService memberService = appConfig.memberService();

        //MemberService memberService = new MemberServiceImpl();
        Member member = new Member(1L, "memberA", Grade.VIP);
        memberService.join(member);

        Member findMember = memberService.findMember(1L);
        System.out.println("member = " + member.getName());
        System.out.println("findMember = " + findMember.getName());
    }
}
