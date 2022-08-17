# PostProject
sparta 3주차 개인 과제입니다.


## 클래스 설명
```
Controller
-PostController: post의 생성, 조회, 삭제, 수정 등의 기능을 제어하는 컨트롤러

Service
-PostService: Postcontroller에게서 받은 요청을 수행한 후 결과를 넘겨준다.

Domain
-Post: 게시물 객체
-Timestamped: 생성일자, 수정일자를 자동으로 갱신하고 만들어준다.

Dto
-PostResponseDto: post목록을 반환할 때의 정보를 담고있는 dto
-ResponseDto: post의 정보를 전달하는 dto
-PostRequestDto: request 정보를 담아 전달하는 dto

Repository
- PostRepository: DB에서 Post를 관리하는 repository. post 리스트를 최신순으로 정렬하여 반환하는 역할도 있음
```

```
통일된 형식을 위한 클래스

-ExceptionController: ID관련 오류 발생 시 가로채서 설정한 에러메시지를 반환하도록 한다.

-ResponseService: Controller가 전달해준 에러 메시지를 형식에 맞게 담아서 반환한다.

-CommonResponse: Response 형식을 정의한 객체.
-Exception: ID가 없는 오류 발생시 에러메시지를 설정하는 enum

-ErrorDto: 설정해둔 에러메시지인 code와 message를 담고있는 dto
```

## 배포 ip
13.209.88.187

## 유스케이스 및 API 명세서
https://yonykk.tistory.com/20
