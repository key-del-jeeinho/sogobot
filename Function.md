# Sogo Auth Bot 기능명세서
## Summary | 요약
- **소마고 연합 본인인증용 디스코드 봇** 입니다.
- 학교 이메일을 통해서 소프트웨어 마이스터고 재학생임을 인증하실 수 있습니다.
- 이후 소마고 연합 디스코드방 관련 봇들에서 `OAuth` 역할을 할 수 있도록 구축할 예정입니다
## Function | 기능
### 이메일 인증

소프트웨어 마이스터고등학교 학생에게는 각자 학교 전용 이메일이 발급됩니다

- 광주 SW 마이스터고 : XXX@gsm.hs.kr
- 대덕 SW 마이스터고 : XXX@dsm.hs.kr
- 대구 SW 마이스터고 : XXX@dgsw.hs.kr
- 부산 SW 마이스터고 : XXX@bssm.hs.kr

이를 이용하여, `이메일주소`를 입력받아 
1. `도메인`을 인증하고
2. `JMS` 를 통해 본인인증을 거쳐서
소마고의 학생임을 인증한다
   
### 마이스터고 고유 도메인 등록
`configuration` 파일을 통해서, 각 **SW 마이스터고등학교 별 도메인**을 설정할 수 있게 한다

`configuration` 파일은 `resources` 의 `bot.yml` 에 저장하고 이를 읽어내는 방식으로 한다.
### 유저 정보 저장
본인인증을 마친 유저는 `유저 정보`를 DB에 저장한다.

> **유저정보**
> - 이름 ([5글자](https://blog.daum.net/mojjustice/8708084) 이하)
> - 이메일 주소 (XXX@YYY.hs.kr)
> - 소속 (Gwangju | Daedeok | Daegu | Busan)
> - [디스코드 유저 id](https://support.discord.com/hc/ko/articles/206346498-%EC%82%AC%EC%9A%A9%EC%9E%90-%EC%84%9C%EB%B2%84-%EB%A9%94%EC%8B%9C%EC%A7%80-ID%EB%8A%94-%EC%96%B4%EB%94%94%EC%84%9C-%ED%99%95%EC%9D%B8%ED%95%98%EB%82%98%EC%9A%94-)

## Develop | 개발
> 해당 개발은 진화적 프로토타입 개발방법론을 채용하여개발한다
> 하나의 기능별로 Sprint 를 생성하여 다음과 같은 순서로 진행한다
> 1. Function-details (세부명세서) 를 작성한다
> 2. 해당 기능에 대한 `feature branch` 를 생성한다
> 3. 세부명세서에 나온 기능들을 나누어
> 
>       1. Test 코드 작성
>       2. Test 를 통과하는 서비스 로직 작성
> 
>   순으로 개발을 진행한다
> 4. 개발 완료시, `develop branch` 에 `PullRequest` 를 진행하고 `feature branch` 를 삭제한데