# README.md

# 💊 MEDIFLIX Admin Dashboard Backend 💊

## 프로젝트 기간 : 2023. 03. 04 ~ 2023. 03. 24

## Team: C조

## 프로젝트 소개

정량적인 데이터 분석을 통한 마케팅 인사이트를 얻기 위해, 
<br/>유저 통계와 콘텐츠 통계를 종합적으로 볼 수 있는 **어드민 페이지 대시보드**를 기획하였습니다! 

<img width="500" alt="Untitled (4)" src="https://user-images.githubusercontent.com/86006389/227492020-940cbc5e-812d-46c7-b700-d95156ef2036.png"> <img width="500" alt="Untitled (5)" src="https://user-images.githubusercontent.com/86006389/227492077-2720c5fa-e863-4cc5-b8c2-cd18217bee9d.png">



### 대시보드의 필요성

[`메디플릭스`](https://www.mediflix.co.kr) 는, </br>
대시보드 자체의 부재로 인해 유저 트래픽을 활용한 마케팅 인사이트를 얻고자 하는 목표를 이루기 어려운 상황입니다.  특히나 사이트 형태가 기존 종합 포털 형태가 아닌 OTT 서비스를 제공하고 있다는 점에서 볼 때, 세부 콘텐츠 데이터 지표 분석이 중요한 상황입니다.  이러한 점을 종합적으로 미루어 봤을때, **유저 통계와 콘텐츠 통계를 종합적으로 볼 수 있는 대시보드** 는 필수불가결했습니다. 

## 서비스 기능


### 간단요약

<img width="1624" alt="Untitled (4)" src="https://user-images.githubusercontent.com/86006389/227492918-bb3d9a24-16e3-4749-abf1-1bd343a5e332.png">


<details>
<summary>DAU/WAU/MAU, 사용자 통계 지표</summary>

![ezgif com-video-to-gif (1) (1)](https://user-images.githubusercontent.com/86006389/227496924-ef4a50a2-9a30-4076-80cd-d2a4e2b073bd.gif)
- 차트를 통해 DAU, WAU, MAU와 일간, 주간, 월간 신규 가입자를 확인할 수 있습니다.  
- 오늘의 방문자 수, 페이지 뷰 수, 신규 가입자 수, 회원 이탈율을 확인할 수 있고,
전날 대비 증감 비율을 확인할 수 있습니다.

</details> 
    
 
<details>
<summary> 지역별 트래픽</summary>

<img src="https://user-images.githubusercontent.com/86006389/227497813-b5f80ed2-8a20-49af-a464-5dbfd3c93c49.gif" height="400">

- 지역별 일일 방문자 및 신규 가입자 수를 지도 차트로 보여줍니다.

</details>     
   

<details>
<summary> 진료과별 유저 수 </summary>

<img src="https://user-images.githubusercontent.com/86006389/227498148-61937468-9d0d-4c21-bee1-dd74bcad63cc.gif" height="400">

- 진료과별 유저 수 통계 파이차트
  - ‘내과, 정형외과, 내분비대사내과, 신경과, 그 외’로 분리하여 전체 가입 회원 중 해당 과의 회원 비율을 나타냄
  - 마우스 오버 시, 상세 정보 노출 ( 총 유저 수 )

</details>     
     
<details>
<summary> 업로드 예정 콘텐츠 </summary>

![업로드예정](https://user-images.githubusercontent.com/86006389/227498896-b568aad1-019e-43e6-b49b-3b26762b168f.gif)

- 드롭다운 메뉴: 카테고리 지정
- 카테고리 별로 썸네일 이미지, 제목, 전공, 업로드 예정 날짜 보여줌

</details>     
<details>
<summary> 세부 콘텐츠 분석 </summary>

![ezgif com-video-to-gif (1) (2)](https://user-images.githubusercontent.com/86006389/227499179-fa31db21-0357-4b40-bb03-b86440e37842.gif)

- 총 조회수: 필터링 후 동영상에 대한 총 조회수
  - 기간 설정 : 달력을 통해 범위 설정 (default: 2023-03-01~2023-04-10)
  - <details> <summary> 드롭다운 메뉴 : 카테고리 지정</summary>
  
    - 지정된 카테고리 별 가져오는 정보
    
        ```
        ❒ ORIGINAL
        - thumbnailUrl
        - seriesName _episodeNum
        - uploadDate | major
        - commentNum / likeNum / reviewNum
        - viewsNum
        
        ❒ VOD
        - thumbnailUrl
        - title
        - uploadDate |  major
        - vodId
        - viewsNum
        
        ❒ Live
        - title
        - uploadDate 
        - applicantNum
        - applicableNum
        - viewsNum
        
        ❒ LIFE
        - title
        - uploadDate_videoCategory
        - commentNum / likeNum
        - viewsNum
        ```
        </details>
        
    - <details> <summary> 정렬 설정 : 카테고리별로 정렬 기준 불러옴 </summary>
  
        ```jsx
            const original = {
              "view/desc": "조회수 높은 순",
              "view/asc": "조회수 낮은 순",
              comment: "댓글 많은 순",
              like: "좋아요 많은 순",
              review: "리뷰 많은 순",
            };

            const vod = {
              "view/desc": "조회수 높은 순",
              "view/asc": "조회수 낮은 순",
            };
            const live = {
              "view/desc": "조회수 높은 순",
              "view/asc": "조회수 낮은 순",
              comment: "댓글 많은 순",
              applicant: "신청인원 많은 순",
            };

            export const life = {
              "view/desc": "조회수 높은 순",
              "view/asc": "조회수 낮은 순",
              comment: "댓글 많은 순",
              like: "좋아요 많은 순",
            };
        ```
            
      </details>
    

</details>   
<details>
<summary> 콘텐츠 별 유입률 </summary>

<img src="https://user-images.githubusercontent.com/86006389/227501624-b785c4bf-a964-435a-ac6f-45901c057441.gif" height="500">

- 파이 차트 마우스 오버시 각 콘텐츠 종류별 유입률 확인 가능
- 콘텐츠 종류: original, vod, live, life

</details>            

<details>
<summary> 로그인 헤더 </summary>

![로그인 헤더](https://user-images.githubusercontent.com/86006389/227502751-27a3f6fc-fbb3-43cf-849a-ec188250f7e3.gif)

- 활동중인 관리자 : 로그인 중인 관리자를 확인할 수 있습니다.
  - 3명 이상의 관리자가 활동중일 때에는, 회색 동그라미에 나머지 관리자 수가 표시됩니다.
- 메모를 열고 닫을 수 있습니다.

</details>   

<details>
<summary> 메모 </summary>

- <details> <summary> 메모 생성하기 </summary>
        
  <img src="https://user-images.githubusercontent.com/86006389/227502860-ebc8f7eb-8a19-4f98-85c3-ec709e8573d8.gif" height="400">
        
   </details>
        
- <details> <summary> 메모 수정/삭제하기 </summary> 
        
  <img src="https://user-images.githubusercontent.com/86006389/227502909-b14c9909-550b-4ed5-bc0d-d0be9c41df43.gif" height="400">
  <img src="https://user-images.githubusercontent.com/86006389/227502942-6c1461fa-8d91-4cdd-931f-ebf7cf46226f.gif" height="400">
        
  </details>
        
- <details> <summary> 댓글 작성/확인하기 </summary>

  <img src="ehttps://user-images.githubusercontent.com/86006389/227502986-71b45080-93fc-4a2d-a22f-875afea3f3fa.gif" height="400">
        
  </details>

</details>  
         

## 기술 스택

### 프론트엔드

<img src="https://user-images.githubusercontent.com/86006389/227504015-0a3c72d3-c9e6-46cb-86e9-d28f34629fe2.png" height="180">

### 백엔드

<img src="https://user-images.githubusercontent.com/86006389/227504103-f35b4350-619d-43fc-8276-9d0ea6afdbf3.png" height="300">

### Infra

<img src="https://user-images.githubusercontent.com/86006389/227504144-0cdc2e3e-6292-419f-8aef-4944e282d372.png" height="150">

### CI/CD

<img src="https://user-images.githubusercontent.com/86006389/227504183-188e82ba-5b4e-4913-aa94-7339a0b56a7c.png" height="150">

### DB

<img src="https://user-images.githubusercontent.com/86006389/227504208-31e60f6a-caa2-429b-8cdc-67e1611edb67.png" height="150">


## ERD 구조

<img src="https://user-images.githubusercontent.com/86006389/227504260-281e3a3c-16db-417b-b002-4d5ff3d4f7c1.png" height="500">


## 시스템 아키텍쳐


<img src="https://user-images.githubusercontent.com/86006389/227504299-bb736c51-8dee-42e5-8916-28898a51fb48.png" height="500">

## CI/CD

![Untitled (12)](https://user-images.githubusercontent.com/86006389/227504331-d255b27d-e8be-45c3-9cb5-fc5387b0538a.png)


## 커밋 컨벤션
|commit 명|commit 뜻|
|:-:|:---:|
|Feat|새로운 기능 추가|
|Fix|버그 수정|
|Docs|문서 수정|
|Style|코드 포맷팅, 세미콜론 누락, 코드 변경이 없는 경우|
|Refactor|코드 리펙토링|
|Test|테스트 코드, 리펙토링 테스트 코드 추가|
|Chore|빌드 업무 수정, 패키지 매니저 추가|


## 팀원 소개

### 기획팀
|송예지|문서현|
|:-:|:---:|
||<img src="https://user-images.githubusercontent.com/86006389/227505490-2175228a-346e-42f8-98eb-02af4a6996dd.png" height="100">|

### 디자인팀
|김혜림|
|:-:|

### 프론트엔드팀
|이안진|신향아|
|:-:|:---:|
|<img src="https://user-images.githubusercontent.com/86006389/227494696-227a5c47-3d6a-4719-9d82-6b2468cb9cad.png" width="100" height="100">|<img src="https://user-images.githubusercontent.com/86006389/227495034-2a92f1d2-2aa8-4399-94ca-82337cd101f2.png" width="100" height="100">|
|[anjiniii](https://github.com/anjiniii)|[Stillssi](https://github.com/Stillssi)|


### 백엔드팀
|김민수|장진우|
|:-:|:---:|
|<img src="https://avatars.githubusercontent.com/u/86006389?v=4" alt="minsu20" width="100" height="100">|<img src="https://user-images.githubusercontent.com/86006389/227494127-35ffc867-155f-4ab2-9683-fd4eae8941f5.png" alt=JINU-CHANG width="100" height="100">|
|[minsu20](https://github.com/minsu20)|[JINU-CHANG](https://github.com/JINU-CHANG)|
