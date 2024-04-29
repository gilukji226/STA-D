package com.klpc.stadspring.global.api;

import com.klpc.stadspring.domain.study.entity.Study;
import com.klpc.stadspring.domain.study.repository.StudyRepository;
import com.klpc.stadspring.global.api.service.ApiService;
import com.klpc.stadspring.global.response.ErrorCode;
import com.klpc.stadspring.global.response.exception.CustomException;
import jakarta.annotation.PostConstruct;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.json.simple.JSONArray;
import org.json.simple.JSONObject;
import org.json.simple.parser.JSONParser;
import org.json.simple.parser.ParseException;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpHeaders;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.util.UriComponents;
import org.springframework.web.util.UriComponentsBuilder;

import java.io.UnsupportedEncodingException;
import java.net.URI;
import java.net.URLEncoder;
import java.util.List;

@Service
@Slf4j
@RequiredArgsConstructor
public class NaverCategoryService {

    @PostConstruct
    public void initialize() {
//        getNaverJoongCategory();
    }
    private static String[] strList = {"가구바퀴","경첩/꺽쇠/자석철물류","기타가구부속품"};
//    private static String[] strList = {"가구바퀴","경첩/꺽쇠/자석철물류","기타가구부속품","나사/못","로프/철망","서랍레일","소켓/옷걸이봉","데코스티커","싱크대","욕실/바스","중문","창문/창호/새시","목재","몰딩","기타바닥재","마루","매트류","온돌마루","인조잔디","장판","필름난방","반제품","기타벽지","띠벽지","뮤럴벽지","실크벽지","포인트벽지","합지벽지","손잡이","단색시트지","무늬목시트지","뮤럴시트지","아이방시트지","유리용시트지","타일시트지","포인트시트지","접착제/보수용품","도기질타일","스텐타일","아트타일","우드타일","유리타일","자기질타일","접착식타일","카페트타일","파벽돌","패널","TV거실장","가죽소파","리클라이너소파","벤치소파","빈백","소파베드","수납소파","인조가죽소파","좌식소파","패브릭소파","흙/돌소파","장식장","거실테이블","사이드테이블","접이식테이블","교구용가구/소품","기타사무/교구용가구","사무용소파","사무용수납가구","사무용의자","사무용책상","사무용책장","캐비닛","파티션","학원/학교책걸상","회의테이블","간이/접이식의자","목받침의자","스툴","안락의자","의자발받침대","인테리어의자","일반의자","좌식의자","하이팩의자","흔들의자","책꽂이","2인용 책상","h형 책상","독서실책상","삼나무/원목책상","스탠딩책상","일자형 책상","좌식책상","책상소품","컴퓨터책상","코너형책상","책장","방석솜","거위털/오리털베개솜","마이크로화이바베개솜","양모베개솜","일반베개솜","견면요솜","목화요솜","거위털/오리털이불솜","마이크로화이바이불솜","양모이불솜","일반이불솜","쿠션솜","CD/DVD장","고가구","공간박스","나비장","선반","소품수납함","수납장","신발장","우산꽂이","잡지꽂이","코너장","행거","기타수예","뜨개질바늘","뜨개질실","완제품","패키지","보석십자수","수예용품/부자재","원단","도안","실/바늘","서랍장","소파","소품가구","아동침실세트","옷장","의자","책상","책상의자","책상의자세트","벙커침대","이단침대","이층침대","일반침대","기타아웃도어가구","야외벤치","야외의자","야외테이블","정원그네","정자","기타장식용품","냉장고자석","도어벨","디자인문패","공예디자인","사진작품","아트포스터","조각","판화","회화","단스탠드","장스탠드","벽시계","스탠드시계","알람/탁상시계","기타아로마/캔들용품","석고방향제만들기","아로마램프/오일","아로마방향제/디퓨저","초/향초","촛대/홀더","캔들만들기","벽걸이액자","액자세트","캐릭터액자","탁상용액자","파티션액자","퍼즐/그림/사진액자","포토보드/집게","앤틱소품","오르골","우체통","워터볼","인터폰박스","인테리어분수","인테리어창문","인테리어파티션","장식미니어처","장식인형","전통공예소품","LED모듈","거실조명","벽등","분수등","샹들리에","야외조명","욕실조명","인테리어조명","전구","주방조명","현관조명","형광등","화병","그릇장/컵보드","기타주방가구","레인지대","레인지대겸용식탁","식탁세트","식탁의자","식탁테이블","아일랜드식탁","아일랜드식탁의자","왜건/카트","주방수납장","기타침구단품","담요","더블/퀸/킹매트커버","더블/퀸/킹침대커버","싱글/슈퍼싱글매트커버","싱글/슈퍼싱글침대커버","무릎담요","계절베개","라텍스베개","메모리폼베개","베개커버","베개커버세트","스프레드","요세트","요커버","이불커버","전기매트커버","차렵이불","토퍼","토퍼커버","더블/퀸/킹패드","싱글/슈퍼싱글패드","홑이불","더블/퀸매트커버세트","슈퍼싱글매트커버세트","싱글매트커버세트","킹매트커버세트","1인용","2/3인용","더블/퀸이불베개세트","슈퍼싱글이불베개세트","싱글이불베개세트","킹이불베개세트","더블/퀸이불패드세트","슈퍼싱글이불패드세트","싱글이불패드세트","킹이불패드세트","더블/퀸침대커버세트","슈퍼싱글침대커버세트","싱글침대커버세트","킹침대커버세트","한실예단세트","벽걸이거울","손거울","전신거울","탁상거울","더블매트리스","슈퍼싱글매트리스","싱글매트리스","퀸매트리스","킹매트리스","부부테이블","드레스룸","붙박이장","장롱","틈새옷장","더블침대","돌침대","모션베드","베드벤치","슈퍼싱글침대","싱글침대","접이식침대","침대프레임","퀸침대","킹침대","패밀리침대","흙침대","침실세트","협탁","일반화장대","좌식화장대","콘솔","화장대의자","화장품정리함","대자리","러그","발매트","왕골자리","면/극세사카페트","원목/우드카페트","파일카페트","쿨매트","대나무발","로만셰이드","롤스크린","바란스","버티컬","블라인드","비즈발","실커튼","자바라","캐노피","커튼/로만세트","거실용커튼","창문용커튼","커튼링/봉","커튼액세서리","콤비블라인드","기타주방데코","러너","식탁매트","식탁보","식탁보세트","앞치마","의자커버","주방장갑","기타커버류","소파커버/패드","에어컨커버","피아노커버","계절방석","기능성방석","기능성쿠션","대쿠션/대방석","등쿠션","목쿠션/아이필로우","바디필로우","방석커버","일반방석","일반쿠션","쿠션/방석기타","쿠션/방석커버세트","쿠션커버","팔꿈치/손목쿠션","헤드쿠션","eBook","가족관계","결혼","부부관계","오디오북","나라별요리","차/술/음료","테마별요리","홈베이킹/베이커리","육아법","육아일기","가구/DIY","생활의 지혜","인테리어","정원가꾸기","임신/출산","태교","독서교육","영어교육","자녀교육일반","좋은부모 되기","건강운동 기타","건강정보/이야기","다이어트/미용","달리기/마라톤","요가/헬스","의학/식품/생활과학","질병치료/예방","정가제free","골프","꽃꽂이","낚시","등산","뜨개질/퀼트/십자수","레저/스포츠기타","바둑/장기","반려동물","비즈/구슬공예","사진/비디오","선물포장/리본공예","옷만들기","취미기타","퍼즐/스도쿠","CEO/비즈니스맨","경영실무","경영일반/이론","경영전략/혁신","창업/장사","경제사상/이론","경제전망","국제경제","금융/재정/화폐","쉽게읽는 경제","한국경제","광고/홍보","마케팅일반","마케팅전략","영업/세일즈","트렌드/미래예측","부동산/경매","재테크일반","주식/증권","EBS 고등","기타","문제집","수능문제집","자습서","국어사전","국어의이해","국어학이론","외국인을 위한 한국어","한국어수험서","기타 국가 사전","기타외국어","독일어","러시아어","스페인어","프랑스어","백과/전문사전","문법/독해/작문","번역/통역","비즈니스영어","수험영어","영어학습일반","유학영어","한영/영한/영영사전","회화/청취","일본어능력시험/JPT","일본어일반","일한/한일사전","HSK","중국어일반","중한/한중사전","한자사전","SF/판타지","공포/추리","교양만화","기타만화","드라마","명랑/코믹만화","성인만화","순정만화","스포츠만화","액션/무협만화","영문판 만화","웹툰/카툰에세이","일본어판 만화","학원만화","교육학의 이해","교육학일반","국방/군사 일반","전쟁","국제법","기타법/법률","민법/형법/상법","법학일반","소송/판례","헌법","노인복지","사회문제일반","사회복지이론","아동복지","여성복지","장애인복지","각국사회/문화","사회이론/사상","사회학일반","신문/방송/출판","언론이론","언론일반","여성학","각국정치","외교/국제관계","정치일반","정책/지방자치","행정일반/관리","행정학일반","세계고전","세계문학","한국고전소설","문학비평/평론","문학사","문학의 이해","문학이론","기타 국가 소설","독일소설","러시아소설","영미소설","일본소설","중국소설","프랑스소설","그리스로마신화","동양","서양","한국","공포/스릴러","라이트노벨","로맨스","무협","전쟁/역사","추리/미스터리","가족/성장문학","드라마극본","어른을 위한 동화/우화","영화/드라마 원작","웹소설","한국소설","희곡/시나리오","고입 검정고시","대입 검정고시","중입 검정고시","물류/유통/품질관리사","투자상담사","감정평가사","공인회계사/세무사","기술고시","노무사","법무사","사법고시","행정/외무고시","10급 기능직","7급 공무원","9급 공무원","경찰직","군무원","기술직","법원직","세무직","소방직","지하철/철도청","공인중개사","주택관리사","교원임용고시","변리사","사회복지사","독학사","간호/조무사","영양사","위생사","의사","LEET","MEET/DEET","PSAT","기타 자격증","상식","적성검사/SSAT","취업전략/면접","워드프로세스","정보처리","컴퓨터활용능력","편입/대학원","그림/사진 에세이","독서 에세이","명사/연예인 에세이","명상 에세이","명언/잠언록","성공 에세이","여행 에세이","연애/사랑 에세이","미술 에세이","미학/예술 에세이","음악 에세이","외국 에세이","외국시","음식/요리 에세이","한국 에세이","한국시","어린이 교양","어린이 문학","어린이 세트","게임으로 배우기","어린이 외국어 기타","영어리딩/읽기","영어학습교재","첫걸음","학습/학습만화","강원/영동","경상도/영남","서울/경기","전국여행","전라도/호남","제주도","충청도","국내지도","지리일반/지리학","해외지도","맛집여행","배낭여행","역사/문학기행","체험학습/가족여행","기타여행","미국/캐나다/중남미","세계여행","유럽여행","인도/아시아여행","일본여행","중국여행","국가별 역사/문화","동양사/문화","서양사/문화","세계사/문화","시대별 역사/문화","문화사","역사의 이해","역사학 이론/비평","주제별 역사/문화","고중세사","근현대사","한국문화","한국사일반","한국역사인물","미디어/광고","건축가","건축이론/건축사","건축이야기","만화 일반","만화 작법/기법","애니메이션 일반","공예/서예","디자인","미술론/미술사","미술실기/기법","사진이론과 실기","사진일반","사진작가/사진집","시나리오/희곡 작법","연극사/연극이론","연극일반","연극제작/비평","영화사/영화이론","영화제작/비평","문화비평","미학/예술철학","예술론/예술사","예술사전/잡지","악기/악보집","음악이론/음악사","장르별음악","가족/생활","건축/예술","경영경제","독일","문학/소설","스페인","외국어/사전","요리","유아청소년/교육","사회/정치/법","종교/명상","철학/심리/역사","일본도서","자연/과학","취미/여행","컴퓨터","프랑스","국내 창작 동화","다른나라 그림책","세계 명작 동화","한국 전래 동화","스티커북/색칠놀이","입체/팝업북","퍼즐/플래시카드","동요/동시","영어/숫자 배우기","한글/말 배우기","사운드북","스티커북/색칠북","교양심리","심리학일반","기호학","언어학","교양인문","독서/글쓰기","인문학이론","신화의 이해","종교비평/비교종교학","종교사","종교학의이해","교양철학","동양철학","서양철학","철학일반","한국철학","대화/협상","성공/처세","시간관리","인간관계","자기능력계발","취업","공학일반/산업공학","과학기본서","쉽게 읽는 과학","기계/전기/전자","농수산/축산","도시/토목/건설","물리학","생물학","수학","천문/지구과학","화학","경제/경영","시사/사회","결혼/육아","리빙/인테리어","교양","문예","출판","방송교재","아동학습","어학","중고학습","행정/고시/정치","여성/남성/패션","레저/스포츠","바둑/낚시/등산","여행","방송/연예","영화/공연","사진","음악/미술","경제/시사","기타잡지","연예/영화/만화","예술/건축/디자인","패션/리빙","요리/건강","기계/자동차","농학/원예","의학/건강","자연과학","종교","게임","그래픽","웹","개신교","기타 종교","불교","종교일반","천주교(가톨릭)","도서","만화","EBS 중등","예비 고1","중1 문제집","중1 자습서","중2 문제집","중2 자습서","중3 문제집","중3 자습서","특목고 대비","논술 가이드","면접 가이드","중고등학교 학습문학","청소년 경제/자기계발","청소년 과학","청소년 문학","청소년 예술","청소년 인문/사회","학습법/진학 가이드","EBS 초등","초등1학년","초등2학년","초등3학년","초등4학년","초등5학년","초등6학년","한글/한자","OS/데이터베이스","3DS/MAX","그래픽일반/자료집","그래픽툴/저작툴","네트워크/보안","MS Excel","MS PowerPoint","MS Word","웹사이트","컴퓨터 입문/활용","컴퓨터공학","프로그래밍 언어","브랜드PC","서버/워크스테이션","조립/베어본PC","CPU","1394케이블","D-SUB케이블","DVI케이블","HDMI케이블","SATA케이블","USB케이블","광케이블","기타케이블","랜케이블","비디오케이블","오디오케이블","전원케이블","젠더","PC케이스","노트북용","데스크탑용","AMD계열","NVIDIA계열","그래픽카드주변기기","기타계열","AMDCPU용","기타메인보드","인텔CPU용","1394카드","RAID카드","SATA카드","SCSI카드","USB카드","기타인터페이스카드","CPU쿨러","HDD쿨러","그래픽카드쿨러","기타쿨러","노트북쿨러","방열판","수냉쿨러","케이스쿨러","기타튜닝용품","방음방진","브라켓","조명기기","팬/온도컨트롤러","팬그릴","팬필터","ATX파워","TFX파워","UPS","mATX파워","기타PC용파워","서버용파워","PC받침대","PC홀더/브라켓","USB라이트","USB보온제품","USB잠금장치","USB청소기","USB토이","USB허브","기타USB액세서리","기타PC액세서리","마우스패드","보안기","손목받침대","케이블타이/정리함","클리너","키보드키스킨/스티커","PC게임","가정용게임기","가방/케이스","기타주변기기","보호필름/스킨","음향기기","조이스틱/컨트롤러","충전기기","게임타이틀","휴대용게임기","USB가습기","가습기필터","가열식가습기","복합식가습기","자연식가습기","초음파식가습기","공기정화기필터","공기청정기","기타부속품","산림욕기","에어워셔","환풍기","냉온풍기","업소용냉풍기","일반용냉풍기","라디에이터","가스보일러","보일러부품","석유보일러","연탄/화목보일러","전기보일러","벽걸이형선풍기","서큘레이터","선풍기부속품","스탠드형선풍기","업소용선풍기","천장형선풍기","타워형선풍기","탁상형선풍기","휴대용선풍기","업소용냉온풍기","에어커튼","멀티형에어컨","벽걸이형에어컨","스탠드형에어컨","시스템에어컨","업소형에어컨","이동식에어컨","창문형에어컨","천장형에어컨","기타액세서리","에어컨 리모콘","에어컨 실외기","가스온수기","전기온수기","온수매트","가스온풍기","돈풍기","석유온풍기","전기온풍기","기타전기매트","옥매트","전기장판","카페트매트","황토매트","전기담요","전기방석","전기요","업소용제습기","일반용제습기","컨벡터","가스히터","석유히터","연탄/화목난로","전기히터","광학용품액세서리","망원경","쌍안경","천체망원경","현미경","AP","KVM스위치","KVM케이블","무선AP","유무선공유기","유선공유기","기타네트워크장비","네트워크모듈","네트워크테스트기","라우터","무선랜카드","유선랜카드","리피터장비","모뎀","무선모뎀","블루투스동글","선택기","스위칭허브","시리얼장비","안테나","컨버터장비","프린터공유기","노트북","기타노트북액세서리","노트북가방/케이스","노트북도난방지","노트북받침대/쿨러","노트북보안기","액정보호필름","전신보호필름","노트북어댑터","노트북용배터리","노트북키스킨","DVR본체","DVR카드","DVR패키지","Divx플레이어","PC마이크","2.1채널","2채널","5.1채널","PC헤드셋","기타사운드카드용품","내장형","레코딩장비","외장형","DMB수신기","PC리모컨","TV카드","디코더","방송보드","엔코더","영상편집","웹캠","휴대용스피커","모니터","기타모니터주변기기","모니터받침대","모니터브라켓","모니터암","모니터어댑터","신발건조기","의류건조기","탈수기","구강세정기","전동칫솔","전동칫솔모","칫솔살균기","건식다리미","스팀다리미","보조키형","주키형","무전기액세서리","생활용무전기","업무용무전기","보풀제거기","드럼세탁기","미니세탁기","세탁기부품","일반세탁기","손소독기","LED스탠드","삼파장스탠드","오파장스탠드","일반스탠드","칠파장스탠드","업소용자외선소독기","연수기","의류관리기","이온수기","자외선소독기","재봉틀","전신건조기","무선전화기","사무용전화기","유무선전화기","유선전화기","전화기주변기기","고압세척기","로봇청소기","무선청소기","물걸레청소기","스팀청소기","업소용청소기","유선청소기","청소기액세서리","침구청소기","해충퇴치기","핸드드라이어","개발툴","그래픽/멀티미디어","번역","보안/백신","사무/회계","운영체제","유틸리티","LCDTV","LEDTV","OLEDTV","PDPTV","QLEDTV","브라운관TV","디지털액자","사이니지","3D입체안경","AV젠더","AV케이블","TV셋톱박스","TV전용키보드","TV카메라","리모컨","방진용품","셀렉터/분배기","스탠드","DVD레코더","DVD콤보","DVD플레이어","블루레이플레이어","퍼스널뷰어","포터블DVD","DLP","LCD","LCoS/기타","기타프로젝터주변기기","프로젝터램프","프로젝터스크린","CD플레이어","DAC","MD플레이어","MP3","기타 MP3/PMP액세서리","배터리","보호필름","충전기","케이스","PMP","노래반주기","데크","라디오","리시버/앰프","마이크주변기기","무선마이크","일반마이크","전문가용마이크","확성기","방송음향기기","블루투스이어폰/이어셋","블루투스헤드폰/헤드셋","블루투스스피커","스피커단품","스피커세트","스피커액세서리","분리형오디오","오디오액세서리","일체형오디오","휴대폰도킹스피커/오디오","오디오믹서","이어폰","거치대","기타이어폰/헤드폰액세서리","연장선/케이블","정리기","캡/솜/팁","케이스/파우치","CD카세트","워크맨","일반카세트","카세트플레이어액세서리","턴테이블","튜너","헤드폰","단일형홈시어터","사운드바시스템","조합형홈시어터","고데기","기타이미용가전","눈썹정리기","두피케어기기","드라이어","매직기","남성용면도기","여성용면도기","기타면도기소모품","면도날/망","세정액","손발톱정리기","에어브러시","이미용가전액세서리","이발기","제모기","코털제거기","피부케어기기","헤어롤/롤셋","HUD","내비게이션","내비게이션액세서리","방청용품","블랙박스","블랙박스액세서리","기타자동차AV용품","노이즈필터","레벨미터","전원보강용품","거치형","기타차량용TV/모니터","인대쉬형","천장형","헤드레스트형","어라운드뷰","이동식카메라감지기","전방감지기","전방카메라","후방감지기","후방카메라","스피커","앰프","우퍼","음향기기기타","무선","유선","차량용GPS","하이패스","하이패스액세서리","핸즈프리","CD/DVD체인저","CD/MP3플레이어","헤드유닛패키지","HDD","NAS","CD-ROM","CD/DVD콤보","CD레코더","DVD-ROM","블루레이","SSD","USB메모리","CD미디어","DVD미디어","기타공미디어","기타공미디어액세서리","미디어보관함","미디어전용라벨","블루레이미디어","기타저장장치","외장HDD","외장SSD","저장장치액세서리","빌트인가스레인지","업소용가스레인지","일반가스레인지","가스레인지후드","거품/반죽기","기타주방가전","기타주방가전부속품","뚜껑형","서랍형","스탠드형","업소용","냉동고","양문형냉장고","업소용냉장고","일반형냉장고","두부두유제조기","믹서기","분쇄기","빙수기","샌드위치제조기","생선그릴","과일야채세척기","식기건조기","식기세척기","업소용식기건조기","업소용식기세척기","식품건조기","아이스크림제조기","업소용거품/반죽기","업소용믹서기","업소용빙수기","업소용음식물처리기","업소용진공포장기","업소용튀김기","에어프라이어","복합형오븐","오븐레인지","오븐조리기","전기오븐","와플제조기","요구르트제조기","음식물처리기","인덕션","전기그릴","압력밥솥","업소용밥솥","일반밥솥","슬로우쿠커","에그마스터","전기냄비","전기찜기","전기팬","라면포트","무선포트","보온포트","유선포트","전기물통","반찬냉장고","쇼케이스","쌀냉장고","와인냉장고","육수냉장고","제빙기","화장품냉장고","전자레인지","기타정수기주변기기","냉온수기","냉온정수기","냉정수기","미니정수기","정수기필터","제빵기","죽제조기","쥬서기/녹즙기","진공포장기","업소용에스프레소머신","에스프레소머신","캡슐/POD머신","커피머신부속품","커피메이커","커피자판기","탄산수제조기","오븐토스터기","팝업토스터기","튀김기","하이라이트","하이브리드","핫플레이트","핸드블렌더","홍삼제조기","무선마우스","유선마우스","트랙볼마우스","펜마우스","프리젠터","3D프린터 소모품","기타복합기/프린터소모품","잉크","토너","잉크젯복합기","컬러레이저복합기","흑백레이저복합기","3D 스캐너","명함스캐너","바코드스캐너","양면스캐너","펜스캐너","평판스캐너","필름스캐너","키보드/마우스세트","무선키보드","유선키보드","키패드","태블릿","태블릿액세서리","3D프린터","도트프린터","라벨프린터","레이저프린터","바코드프린터","영수증프린터","잉크젯프린터","카드프린터","포토프린터","플로터","DSLR카메라","LCD보호커버","LCD보호필름","LCD후드","외부보호필름/스티커","렌즈/필터케이스","렌즈어댑터","렌즈캡","렌즈필터","렌즈후드","텔레컨버터","핀홀/기타렌즈용품","CF메모리","MicroSD메모리","SD메모리","XQD메모리","기타메모리","메모리스틱","미러리스디카","모노포드","삼각대","삼각대가방/스트랩","삼각대액세서리","플레이트/퀵슈","헤드","액션캠","일반디카","즉석카메라","즉석카메라액세서리","즉석필름","배터리그립","범용충전기","범용충전지","전용정품배터리","전용정품충전기","전용호환배터리","전용호환충전기","멀티리더기","메모리어댑터","전용리더기","GPS/무선송수신","기타카메라/캠코더용품","라이트박스","루페","리모컨/릴리즈","소프트버튼","수평계","아이피스","청소용품","촬영보조용품","카메라/캠코더용마이크","카메라보관함","카메라제습용품","파인더","포커싱스크린","핫슈커버","가방용품","기타카메라가방/케이스","방수케이스","배낭형케이스","일반형가방","전용케이스","광각렌즈","망원렌즈","어안렌즈","컨버젼렌즈","표준렌즈","넥스트랩","핸드그립","핸드스트랩","캠코더","라이트","조명/노출용품","플래시","35mm필름","기타필름/관련용품","암실/현상용품","중형필름","SLR카메라","로모카메라","일회용카메라","자동카메라","토이카메라","태블릿PC","스탠드/Dock","터치펜/기타","보이스레코더","어학학습기","전자사전","전자책","보호필름/키스킨","충전기/케이블","학습보조기","공기계/중고폰","자급제폰","해외출시폰","기타휴대폰액세서리","셀카봉","웨어러블 디바이스","웨어러블 디바이스 액세서리","짐벌","핸드셋","휴대폰DMB수신기","휴대폰거치대","휴대폰렌즈","보조배터리","전용배터리","액정코팅제","홈버튼스티커","휴대폰스킨","휴대폰이어캡","휴대폰젠더","휴대폰줄","크래들","휴대폰케이블","가죽케이스","기타케이스","메탈케이스","실리콘케이스","암밴드","플라스틱케이스","휴대폰쿨링패드","교양/다큐멘터리","교육","국내TV드라마","해외TV드라마","뮤직비디오","애니메이션","국내영화","해외영화","유아/어린이","취미/스포츠","건강목걸이","건강팔찌","금연용품","기타건강관리용품","뜸/뜸기구","마스크액세서리","먼지차단마스크","소음방지귀마개","수액시트","수지침","위생마스크","전자담배","전자담배 액세서리","지압기","코건강용품","코클리너","파라핀용품","만보계","신장계","심박계","청진기","체온계","디지털체중계","아날로그체중계","혈압계","대패","샌더","조각기","톱","트리머","홀쏘","배관용품","컷터기","코어드릴","파이프머신","확관기","기타소형기계","모터","발전기","펌프","공구세트","공구함","기타수작업공구","니퍼","도끼","드라이버","렌치","망치","몽키","바이스","복스","스패너","작업대","타카","타카핀","펜치","플라이어","핀셋","기타안전용품","마스크","보안경","비상/구난용품","소방용품","소화기","안전/작업복","안전모","안전장갑","안전표시/신호","기타에어공구","에어건","에어렌치","에어스프레이건","에어타카","에어호스/게이지","유압공구","컴프레서","가스예초기","엔진예초기","예초기부품","충전/전기예초기","용접기","용접봉","용접용품","인두기","인두용품","절단기","해빙기","사다리","지게차","핸드카트/운반기","기타원예공구","농기계","농기구","비료살포기","스프링클러","압축분무기","원예가위","잔디깎이","호스/호스카트","기타 전기용품","멀티탭","일회용 건전지","일회용 리튬건전지","전선/케이블","충전용 건전지/배터리","광택기","기타전동공구","드릴세트","비트세트","송풍기","열풍기","전동공구액세서리","전동드릴","청소기","충전드릴","컷쏘","해머드릴","각도/고속절단기","그라인더","그라인더날","다용도조각기","루터","루터날","직소기","직소기날","글루건","세정제/클리너","스프레이","시멘트/몰탈","실리콘","윤활유","접착제","나사","리벳","못","볼트/너트","스프링","핀","홀딩클램프","기타측정기","레이저측정기","방사능측정기","산소포화도측정기","산업용저울","수평기","염도계","온도계/습도계","워킹자/줄자","음주측정기","조도계","캘리퍼스","테스트기","흡연측정기","락카/스프레이페인트","수성페인트","유성페인트","기타페인트용품","롤러","바니시","방수제","붓","스테인","에폭시","젯소","리본","밴딩기","선물박스","커터","택배박스","택배봉투","포장지","사료","수조장식용품","수조청소용품","수족관/어항","수초","에어/기포","여과기/여과재","조명","히터","구강청정제/가글","마우스피스","치석제거기","치실/치간칫솔","치아미백제","치약","칫솔","칫솔치약세트","틀니관리용품","혀클리너","찜질기","찜질팩","기타렌즈용품","렌즈세정액","렌즈세척기","안대","침/바늘","혈당계","혈당측정지","노트","노트패드","메모지","수첩","점착식메모지","가계부","다이어리","바인더","속지/포켓","스케줄러/플래너","캘린더/달력","데스크정리함","데스크패드","독서대","메모꽂이","명함꽂이","문서보관함/서류함","문진","북엔드","서류꽂이/서류받침대","연필꽂이","저금통","기타문구용품","다이모/엠보서","모양펀치","문구세트","북마크","사무용가위","사무용칼","수정용품","스테이플러/침","자","자석","지우개","집게","클립/핀","펀치","풀/접착제","네온보드","메모판/미니보드","블랙보드","전자칠판","칠판","코르크/게시판","화이트보드","OHP","RFID","공학용계산기","금고","금전등록기","동전계수기","디지털복사기","문서세단기","수표발행기","실물화상기","일반계산기","재단기","전자타자기","접지기","제본기","지폐계수기","천공기","출퇴근기록기","코팅기","팩스","명찰","명패","명함/전단지제작","상패","현수막","도장","스탬프","스탬프패드","인주","네임스티커","마스킹테이프","스티커","테이프","접착식앨범","포켓식앨범","포토박스","폴라로이드앨범","견출지","기타용지","라벨지","복사지","양식/서식지","인화지/포토용지","컬러복사지","코팅지/필름지","팩스용지/전산용지","가랜드","가면/머리띠","고깔모자","데코용품","식기/테이블용품","야광용품","케이크토퍼","태극기","폭죽/불꽃놀이","풍선/풍선용품","기타제도용품","제도용자","제도용지","제도용필기구","제도판","세계지도","지구본","모바일초대장","봉투","엽서","청첩장","카드","편지지","서류철/결재판","파일","파일속지","기타필기도구","네임펜","리필심","만년필","매직","보드마카","볼펜","분필","붓펜","사인펜","샤프","샤프심","연필","연필깎이","펜","필기구세트","필통","형광펜","디스크/관절용품","물리치료용품","부항기","저주파안마기","저주파자극기","저주파패드","적외선조사기","개껌","동결건조 간식","비스킷/스낵","빵/케이크","수제간식","육포/건조간식","음료","캔/파우치","통살/소시지","트릿/스틱","강아지유산균","구강청결제","구강티슈","눈/귀 관리용품","영양제","기저귀/팬티","배변봉투/집게","배변유도제","배변판","배변패드","탈취제/소독제","건식사료","동결건조 사료","분유/우유","소프트사료","수제사료","습식사료","공/원반","노즈워크","자동장난감","장난감/토이","훈련용품","고슴도치용품","캣닢/캣그라스","고양이유산균","구강관리용품","거름망형화장실","매트/발판","분변통/모래삽","응고형모래","자동화장실","평판형화장실","후드형화장실","흡수형모래","낚시/막대","레이저포인터","스크래쳐","터널/주머니","토이/쿠션/공","고양이 캣타워","기타반려동물용품","계단/스텝","넥카라","매트","안전문","울타리","침대/해먹","쿠션/방석","하우스","기타 미용/목욕용품","드라이기/드라이룸","물티슈/크리너","미용가위","발톱/발 관리","브러시/빗","샤워기/욕조","샴푸/린스/비누","에센스/향수/밤","타월/가운","장례용품","파티용품","급수기/물병","사료통/사료스푼","식기/식탁","자동급식기","정수기/필터","가슴줄","리드줄","목걸이/인식표","목줄","유모차","이동장/이동가방","카시트","조류용품","토끼용품","가방","니트/스웨터","레인코트","베스트/조끼","셔츠/블라우스","수영복/구명조끼","스카프/목도리/케이프","신발/양말","안경/모자","올인원","원피스/드레스","재킷/점퍼","커플룩","코스튬","티셔츠/후드","패딩","팬츠/스커트","플리스","한복","헤어핀/주얼리","햄스터용품","건강슬리퍼","다리/발마사지기","발가락교정기","발각질제거기","발냄새제거제","발보호대/패드","발지압/발매트","족욕/족탕기","CCTV","경보기","기타보안용품","도어락/안전고리","모형카메라","자물쇠/열쇠","호신용품","일반생리대","체내형생리대","생활선물세트","난방텐트","단열시트","돗자리/매트","모기장","문풍지","방풍막/방풍비닐","분무기","쇼핑백","신발관리도구","재떨이","핸드카트","호출벨","고농축섬유유연제","시트형섬유유연제","일반섬유유연제","산모용","요실금용","위생매트","환자용","가습기세정제","곰팡이제거제","광택제","배수구세정제","베이킹소다/구연산","변기세정제","빨래비누","살균소독제","세탁보조제","세탁세제","세탁조세정제","손소독제","식기세척기전용세제","에어컨세정제","울세제","유리세정제","일반주방세제","홈드라이","냉장/냉동고탈취제","새집증후군/진드기","섬유탈취스프레이","숯","신발/신발장탈취제","실내용방향제","실내탈취제","옷장탈취제","제습제","겔","끈끈이","리퀴드","매트형","모기채/파리채","모기향","방충제","아로마/로션","에어졸/스프레이","갑티슈","냅킨","롤화장지","세정티슈","여행티슈","점보롤","키친타월","페이퍼타월","화장지세트","화장지케이스","다림판","다림풀","이동접이식","천장부착식","빨래바구니","빨래삶통","빨래집게","빨래판","빨랫줄","세탁망","세탁볼","세탁솔","세탁잡화","마네킹","바구니","벽걸이선반/진열대","스탠드선반/진열대","이동식선반/진열대","코너선반/진열대","선풍기커버","소품걸이","압축팩","바지걸이","일반옷걸이","옷커버","부직포정리함","소품정리함","속옷정리함","신발정리대","종이정리함","LP","다트","마술도구","보드게임","공예품","다이캐스트","모형","밀리터리","보관용품","프라모델","프라모델용품","피규어","RC소품","드론","보트/배","비행기","자동차","중장비","탱크","헬기","건(GUN)","보호장비","서바이벌용품","서예/글씨","기타굿즈","문구사무용품","응원봉","텀블러/물병","포토/브로마이드","휴대폰용품","씰","우표","이색수집품","소품","의상","블록","퍼즐","포스터","주화","지폐","돋보기","목욕보조용품","배변보조용품","보청기","보행보조용품","보행차/실버카","욕창방지용품","장례용품/수의","스틸휠체어","알루미늄휠체어","전동휠체어","멜로디언","실로폰","아코디언","오르간","키보드/신시사이저","관악기소품/부품","리코더","바순","색소폰","오보에","오카리나","유포니움","클라리넷","튜바","트럼펫","트롬본","팬플루트","플룻","피콜로","호른","휘슬","가야금","거문고","국악기/소품/부품","기타국악기","꽹과리","단소","대금","북","소금","장구","징","해금","베이스기타","어쿠스틱기타","오베이션기타","우쿨렐레","일렉기타","클래식기타","기타교육용악기","탬버린","하모니카","핸드벨","메트로놈","보면대","이펙터","튜너기","파워믹서","기타타악기","드럼","스틱/스탠드","심벌즈","전자드럼","타악기소품/부품","그랜드피아노","디지털피아노","일반피아노","피아노용품","기타현악기","만돌린","바이올린","베이스","비올라","전자바이올린","첼로","하프","현악기소품/부품","활","기타안마용품","렌탈안마의자","안마기","안마매트","안마의자","쿠션안마기","면도기","면도기날","기계식비데","렌탈비데","비데필터","전자식비데","때타월/때장갑","목욕바구니","바디브러시","샤워가운","샤워볼","샤워용품","샤워커튼/커튼봉","녹물제거/샤워기필터","배수구캡","샤워기","샤워호스","수도/수전용품","욕실부속품","바스/비치타월","세면타월","주방수건","미끄럼방지매트","욕실발판/매트","디스펜서","비눗갑/홀더/받침","수건걸이","양치컵","치약짜개","칫솔통/걸이","휴지걸이","바가지","변기커버","세숫대야","여행용세트","욕실세트","욕실의자","욕실화","욕실선반","욕실수납장","뚫어뻥","변기솔","욕실청소도구","반신욕조","욕조트레이/소품","월풀욕조/매립욕조","이동식욕조","세면대","양변기","공기정화식물","관엽식물","금꽃","넝쿨식물","다육식물","미니화분","보존화","분재","비누꽃","수경식물","숯부작","식용식물","씨앗/모종","조화","종이꽃","천연잔디","테라리엄","토피어리","플라워소품만들기","허브식물","국내앨범","해외앨범","거즈/붕대/솜류","구급/응급용품","석션기/네블라이저","의료용가구","핀셋/포셉/겸자류","DIY전구","LED소자","도색용품","레진","차량용공구","차량용보호필름","차량용스위치","차량용시트지","차량용방향제","차량용탈취제","기타램프용품","방향지시등","안개등","전조등","차량용실내등","테일램프","기타배터리용품","배터리충전기/점프스타터","점프선","차량용배터리","터미널","김서림방지제","도장용광택/코팅제","세차도구세트","세차물통","세차스펀지","세차장갑","세차타월","세차패키지","스티커/타르제거제","실내세정제","엔진룸세척","유리복원제","유리코팅제","유막제거제","정전기방지","차량용먼지떨이개","차량용브러시","차량용커버","차량용컴프레서","카샴푸","컴파운드","타이어세정광택제","호스","휠세정/광택제","CD바이저","논슬립패드","선글라스클립","차량용옷걸이/후크/행거","차량용우산걸이","차량용재떨이","차량용테이블","차량용포켓","차량용홀더","차량용휴지통","콘솔박스","트렁크정리함","기타 오일/소모품","미션오일","미션오일첨가제","부동액","브레이크오일","에어컨필터","에어필터","엔진오일","엔진오일첨가제","연료첨가제","연료필터","오일세트","오일필터","와이퍼","요소수","워셔액","가드","도어스커프","루프박스","번호판볼트","사이드미러","사이드스텝","선루프","선바이저","안테나볼","에어로파츠","엠블럼","차량용스티커","차량용폴대","캐리어","후드핀","후사경","기어용품","내부몰딩","대시보드커버","룸미러","룸미러커버","바닥매트","방음/방진/흡음","보조룸미러","시트커버","실내원단","썬팅필름","안전벨트용품","주차번호판","차량용나침반","차량용등쿠션","차량용목쿠션","차량용방석","차량용사진액자","차량용시계","차량용장식소품","차량용팔/틈새쿠션","차량용햇빛가리개","페달용품","핸들","핸들커버","기타전기용품","릴레이","소켓/시거잭","인버터","전압안정기","점화케이블","점화플러그","차량용AC어댑터","차량용케이블","차량용태양열충전기","컨버터","휴즈","스마트키","스킨","스타트버튼","시동경보기","키노브","키케이스","폴딩키","스노우체인","타이어","타이어용품","휠","휠용품","게이지","계기판","냉각","레이싱용품","배기/흡기튜닝","브레이크","서스펜션","엔진튠업","혼","차량용가습기","차량용공기청정기","차량용냉/온장고","차량용면도기","차량용보온컵","차량용선풍기","차량용온풍기","차량용청소기","차량용휴대폰거치대","차량용휴대폰충전기","기타재활운동용품","목보호대","상반신보조기","의료용압박스타킹","자세교정용품","재활운동기구","하반신보조기","물조리개","수반","원예부자재","자갈/모래/흙","정원데코","정원부자재","조경수/묘목","화분","화분대","화분받침","화분영양제/비료","기독교용품","불교용품","천주교용품","좌욕기","좌훈기","교자상","다과상","다용도상","상커버","곰솥/들통","냄비부자재","냄비세트","돌솥","뚝배기","압력솥","양수냄비","전골냄비","전자레인지/오븐 용기","직화냄비","찜기","편수냄비","도마","기타보관용기","김치통","도시락통/찬합","도자기/유리용기","물병","물통","법랑용기","보온/보냉병","비닐접착기","스텐용기","시리얼디스펜서","쌀통","아이스트레이","양념통/설탕프림기","플라스틱용기","항아리","공기","공기대접세트","단반상기","대접","디저트세트","면기","반상기","볼","소스기","수저","수저받침","수저통","식판","양식기","어린이식기","접시","찬기","티스푼","티스푼/티포크세트","티포크","홈세트","디캔터","스토퍼/세이버","와인랙","와인액세서리세트","와인오프너","와인쿨러","다기/주기","머그","샴페인잔","스텐컵","와인잔","유리컵","찻잔","텀블러","플라스틱컵","계량스푼","계량저울","계량컵","돌림판","모양깍지/짤주머니","밀대","빵칼","빵틀","스패츌러/주걱","식힘망","와플팬","유산지/베이킹컵","작업판/매트","제빵소도구","쿠키커터/모양틀","쿠키팬","쿠킹타이머","핸드믹서기","돗자리","병풍","위패","유기제기","제기","제기세트","제기함","향/향로","휴대용제기","거품기","계란찜기/계란틀","국자","기타조리기구","김밥/주먹밥틀","깔때기","다시망/차거름망","달고나세트","뒤집개","믹싱볼","솔","스퀴저","오프너","제면기","조리도구세트","주걱","채반","퐁듀세트","식기건조대","싱크선반","주방정리소품","고무장갑","기타주방잡화","냄비받침","랩","롤백","비닐장갑","수세미","쓰레기봉투","야채탈수기","일회용도시락","일회용빨대","일회용식기","일회용젓가락","쟁반","종이컵","종이컵디스펜서","지퍼백","커피스틱","컵받침/홀더","쿠킹호일","행주","주전자","티세트","티포트","강판","다지기","절구","주방가위","채칼/필러","칼","칼갈이","칼꽂이/블럭","칼세트","드립포트","스팀피쳐/밀크저그","여과지","우유거품기","커피분쇄기","커피추출기","템퍼","구이팬","궁중팬/튀김팬","양면팬","일반프라이팬","프라이팬세트","걸레","기타청소용품","매직블럭","먼지떨이/먼지제거기","밀대/패드","빗자루/쓰레받기","유리닦이용품","테이프클리너","회전청소기","다용도휴지통","분리수거함","압축휴지통","음식물쓰레기통","동양화물감","수채화물감","아크릴물감","유화물감","포스터컬러","붓통/케이스","아트백","화구통/박스","목각인형","보조제/접착제/정착제","아크릴판","이젤","컬러가이드","토시/앞치마/장갑","팔레트","구성붓/세필붓","서예/동양화붓","수채화붓","아크릴붓","유화붓","먹","먹물","멍석","벼루","연적","전각재료","마카","목탄/콘테","수채색연필","오일파스텔","유성색연필","크레파스","파스텔","만화원고지","스크린톤","펜촉/펜대","우드락/폼보드","간지","골판지","미술용지","색종이/종이접기","스케치북/크로키북","캔버스/판넬","한지/공예용지","화선지","조소용품","찰흙/점토","클레이","판화용품","염료","페인트","검도보호용품","기타검도용품","도복","죽도","죽도집/부속품","타격대","골프공","골프백세트","골프파우치","보스턴백","캐디백","하프백","항공커버","스윙용품","실외용품","퍼팅용품","니트","바지","스커트","원피스","재킷","점퍼","조끼","티셔츠","모자","벨트","슈즈백","스타킹/레깅스","스파이크","양말","장갑","아이언","연습용클럽","웨지","주니어용클럽","치퍼","퍼터","페어웨이우드","풀세트","피팅헤드/샤프트","하이브리드/유틸리티","거리측정기","골프티","그립","기타필드용품","우산","헤드커버","골프화","로스트볼","파크골프공","파크골프용품","파크골프클럽","글러브","미트","샌드백","핸드랩","기타스포츠용품","가위/라인커터/핀온릴","기타낚시공구","낚시칼","도래","립그립/고기집게","바늘결속기","포셉/바늘빼기","플라이어/니퍼","베이트릴","스피닝릴","전동릴","플라이릴","가프","기포기","낚시의자","낚시텐트","두레박","뜰채/망/꿰미","밑밥주걱","밑밥통/살림통","봉돌/싱커","어군탐지기","케미/집어등","쿨백","태클박스","낚시복","낚시신발","낚시장갑","낚시장화","낚시조끼","힙커버/힙가드","민물낚싯대","민물루어낚싯대","바다루어낚싯대","바다민장대","바다선상낚싯대","바다원투낚싯대","바다찌낚싯대","플라이낚싯대","모노라인","카본라인","플라이라인","합사라인","루어가방","루어낚시세트","루어로드케이스","바늘","소프트베이트","하드베이트","떡밥","민물낚시가방","민물낚시세트","민물낚싯바늘","받침대/받침틀/뒤꽂이","얼음낚시","찌","찌케이스","밑밥","바다낚시가방","바다낚시세트","바다낚싯바늘","기타농구용품","농구공","농구공가방","농구대","농구의류","농구화","당구용품","댄스복","댄스소품","기타등산장비","등산가방","기능성언더웨어","긴팔티셔츠","반팔티셔츠","세트","GPS","나침반","로프","손난로","스틱","스패츠","아이젠","카라비너","핫팩","등산화","마라톤용품","기타무술용품","목검/가검","봉/곤/창","기타배구용품","배구공","배구네트","배구의류","배구화","기타배드민턴용품","배드민턴가방","배드민턴네트","배드민턴라켓","배드민턴의류","배드민턴화","셔틀콕","스트링","연습용품","급소보호대","다리보호대","머리보호대","몸통보호대","무릎보호대","발목보호대","배보호대","손목보호대","어깨보호대","테이핑요법","팔보호대","허리보호대","볼링가방","볼링공","볼링용품","볼링의류","볼링화","아대","격파용품","기타수련용품","띠/벨트","무도복","수련화","남성사각","남성삼각","반신수영복","전신수영복","상의","상하세트","커플비치웨어","팬츠","구명조끼","귀마개","기타수영용품","방수팩","보트","수경","수영가방","수영모","스노클/마스크","오리발","조립식수영장","튜브","모노키니","비키니","원피스수영복","롤러슈즈","스네이크보드","스케이트보드","스케이트보드용품","아이스스케이트","킥보드","트라이스키","핑거보드","기타스쿼시용품","스쿼시공","스쿼시라켓","하의","후드티","바인딩","부츠","스노보드세트","반다나","비니","고글","보드가방","보수장비","보호대","스티커용품","시즌권케이스","헬멧","스키세트","폴","플레이트","기어백","기타스킨스쿠버용품","납","다이빙슈트","다이빙칼","레귤레이터","리트렉터","부력재킷","수중전등","수중총/작살","옥토퍼스","볼캐리어","스코어보드/작전판","스포츠넥워머","스포츠마스크","스포츠선글라스","스포츠토시","스포츠헤어밴드","아이스머플러/스카프","호각/호루라기","기타야구용품","야구가방","야구공","야구배트","야구보호대","야구양말","야구의류","야구장갑","야구화","포수장비","일반스쿠터","전동스쿠터","오토바이","기타오토바이부품","오일","잠금장치","튜닝용품","기타오토바이용품","바이크장갑","전동휠","기타요가용품","요가링/필라테스링","요가매트","필라테스","성인용","인라인용품","주니어","MTB","미니벨로","사이클","유사MTB","일반자전거","전기자전거","특수자전거","기타자전거부품","바엔드/그립","변속기","시트포스트","안장","체인","클릿/페달","타이어/튜브","프레임/포크","핸들바","휠셋/림/스프라켓","공구","기타자전거용품","바테이프","백미러","벨","보조바퀴","세척제","속도계","자물쇠","자전거가방","자전거라이트","짐받이","케이지","흙받이","두건/밴드","배낭","신발","기타족구용품","족구공","족구네트","족구의류","족구화","기타축구용품","필드용","축구공","축구보호대","축구양말","축구연습용품","축구의류","축구장갑","축구화","풋살화","기타캠핑용품","랜턴걸이/스탠드","손전등","실내등","헤드랜턴","아이스박스","천막","기타취사용품","다용도칼","더치오븐","바비큐그릴/화로대","버너","부탄가스","설거지용품","수저/커트러리","연료","캠핑주전자","캠핑컵","코펠","휴대용가스레인지","침낭","야전침대","캠핑박스","캠핑의자","캠핑테이블","테이블세트","파라솔","해먹","캠핑매트","캠핑왜건","타프","기타텐트/타프용품","방수포/그라운드시트","스트링/로프","폴대","1-2인용","2-3인용","3-4인용","4-5인용","5-6인용","6-7인용","7-8인용","9인용 이상","기타탁구용품","탁구공","탁구대","탁구라켓","탁구의류","탁구화","기타테니스용품","테니스가방","테니스공","테니스라켓","테니스의류","테니스화","거꾸리","고정식","접이식","로잉머신","벨트마사지","복근운동기구","스텝퍼","승마운동기","아령/덤벨","바벨","벤치프레스","복합헬스머신","봉/바","스쿼트머신","싯업벤치","웨이트용품","일립티컬","줄넘기","진동운동기","케틀벨","트램펄린","트위스트","헬스사이클","기타헬스소품","모래주머니","손목근력기","악력기","완력기","짐볼/돔볼","폼롤러","푸시업바","헬스장갑","훌라후프","기타분말가루","밀가루","부침가루","빵가루","쌀가루","아몬드가루","오트밀","찹쌀가루","콩가루","튀김가루","건강분말","기타건강즙/과일즙","배즙","블루베리즙","비트즙","사과즙","석류즙","아로니아즙","양배추즙","양파즙","칡즙","토마토즙","포도즙","호박즙","건강환/정","꿀","기타비타민","멀티비타민","비오틴","비타민A","비타민B","비타민C","비타민D","비타민E","MSM","감마리놀렌산","글루코사민","기타건강보조식품","로얄제리","루테인","마그네슘","밀크씨슬","보스웰리아","셀레늄","스쿠알렌","스피루리나","쏘팔메토","아연","알로에정/겔","엽산","오메가3","철분","칼륨","칼슘","코큐텐","크릴오일","클로렐라","키토산","폴리코사놀","프로바이오틱스","프로폴리스","프리바이오틱스","효모","인삼","가시오가피","감초","계피","구기자","기타한방재료","녹용","산수유","오미자","헛개나무","황기","기타홍삼제품","정과/절편","캡슐/환","혼합세트","홍삼액","환자식/영양보충식","가공안주류","강정","기타과자","껌","떡","빵","사탕","스낵","시리얼","빙수/빙수재료","아이스크림","엿","전병","젤리","초콜릿","캐러멜","케이크","쿠키","팝콘/강냉이류","푸딩","한과","화과자","갓김치","겉절이","깍두기","나박김치","동치미","묵은지","백김치","별미김치","열무김치","오이소박이","절임배추","총각김치","파김치","포기김치","기타냉동/간편조리식품","누룽지","도시락","딤섬","떡볶이","만두","맛살/게살","샐러드","스프","어묵","죽","즉석국/즉석탕","즉석밥","채식푸드","카레/짜장","튀김류","피자","함박/미트볼","핫도그","햄버거","감말랭이","건망고","건바나나","건자두","건포도","곶감","기타건과류","기타견과류","대추","땅콩","마카다미아","밤","아몬드","은행","잣","캐슈너트","피스타치오","피칸","해바라기씨","호두","호박씨","혼합견과/견과류세트","감","감귤","과일세트","기타과일","딸기","레드향","레몬","망고","매실","멜론","무화과","바나나","배","복분자","복숭아","블루베리","사과","석류","수박","아보카도","오렌지","자두","자몽","참외","천혜향","체리","키위/참다래","토마토","파인애플","포도","한라봉","기능성쌀","백미","찹쌀","현미","흑미","귀리","기장","기타잡곡","녹두","들깨","메밀","보리","수수","율무","참깨","콩","팥","혼합곡","가지","감자","건나물","고구마","고추","기타채소류","깻잎","냉이","단호박/밤호박","당근","대파","더덕","도라지","두부","마","마늘","무","미나리","배추","버섯","부추","브로콜리","비트/레드비트","산나물","삶은나물","상추","새싹채소","생강","숙주나물","시금치","쌈채소","아스파라거스","알로에","애호박","양배추","양파","연근","열무","오이","옥수수","우엉","쪽파","콩나물","피망/파프리카","CLA","가르시니아","곤약쌀","곤약젤리","기타곤약가공품","기타다이어트식품","다이어트바","다이어트차","기타단백질보충제","단백질바","단백질스낵","단백질음료","단백질츄어블/정","단백질파우더","레몬밤","뷰티푸드","시네트롤","시서스","식이섬유","와일드망고","잔티젠","카테킨","콜라겐","히알루론산","봉지라면","컵라면","국수면","냉면","당면","소면","소바","스파게티면","쌀국수면","우동","쫄면","칼국수면","간식/디저트","구이","면/파스타","밥/죽","볶음/튀김","세트요리","조림/찜","찌개/국","기타반찬류","단무지","반찬세트","볶음류","장아찌","장조림","절임류","조림류","굴소스","기타소스/드레싱","돈가스소스","마요네즈","머스타드소스","발사믹드레싱","스테이크/바베큐소스","스파게티/파스타소스","오리엔탈드레싱","칠리/핫소스","케첩","건새우","과메기","기타건어물","노가리","멸치","문어","오징어","쥐포","진미채","한치","황태","감태","기타해초","김","다시마","매생이","미역","해초샐러드","갈치","고등어","굴비","기타생선","동태","삼치","생선회","연어/훈제연어","옥돔","장어","조기","참치","초밥재료","홍어","해물볶음류","해물찜류","해물탕류","해물튀김류","게장","기타젓갈","낙지젓","대하장","명란젓","새우젓","연어장","오징어젓","젓갈선물세트","조개젓","창난젓","토하젓","가리비","골뱅이","굴","기타해산물","꼬막","꽃게","낙지","날치알","대게/홍게","멍게","바닷가재","새우","소라","전복","조개","주꾸미","킹크랩","해삼","홍합","기타기름","대두유","들기름","아보카도오일","올리브유","참기름","카놀라유","포도씨유","해바라기씨유","마가린","버터","생크림","연유","기타치즈","리코타치즈","스트링치즈","슬라이스치즈","크림치즈","파마산치즈","피자치즈","휘핑크림","기타건강/기능성음료","숙취해소음료","에너지음료","두유","생수","요구르트","우유","곡물음료","기타전통/차음료","녹차/홍차음료","식혜/수정과","감귤주스","기타과즙음료","레몬주스","망고주스","매실주스","사과주스","오렌지주스","자몽주스","타트체리주스","토마토주스","포도주스","국화차","기타차","꽃차","녹차","대추차","루이보스티","바나바잎차","보리차","보이차","생강차","쌍화차","옥수수차","우롱차","우엉차","유자차","율무차","작두콩차","콤부차","허브차","호박팥차","홍차","과즙탄산음료","기타탄산음료","무알콜음료","사이다","아이스티음료","에이드음료","이온음료","콜라","더치커피","드립백/티백","원두/생두","캡슐커피","커피믹스/인스턴트커피","커피음료","프림","코코아","탄산수","파우더/스무디","간장","고추장","기타장류","낫토","된장","메주","쌈장","양념장","청국장","기타잼/시럽","딸기잼","땅콩잼","메이플시럽","사과잼","초코시럽","초코잼","커피시럽","기타제과/제빵재료","베이킹파우더","제과/제빵믹스","호떡믹스","겨자","고추냉이","고춧가루","기타조미료","물엿/올리고당","설탕","식초","액젓","천연감미료","후추","국산와인/과일주","기타전통주","담금주","리큐르주","막걸리/탁주","복분자주","소주","약주","일반증류주","전통주선물세트","기타육류","닭가공품","닭가슴살","닭양념육","부위별닭고기","생닭","국내산돼지고기","돈육훈제","돼지양념육","수입산돼지고기","국내산육우","쇠고기양념육","수입산쇠고기","한우","기타새알","달걀","메추리알","훈제란","양고기","생오리","오리가공품","오리양념육","오리훈제","갈비찜","곱창/막창","기타육가공","돈가스","떡갈비","베이컨","삼계탕","소시지","순대","육포","족발","햄","골뱅이/번데기","기타통조림/캔","꽁치/고등어","옥수수/콩","참치/연어","피클/올리브","황도/과일","단기렌터카","장기렌터카","국악/전통예술","뮤지컬","발레/무용","스포츠","연극","전시/행사","콘서트","클래식/오페라","국내숙박","국내패키지/기타","기타레저이용권","수상레저/래프팅","스키/보드/눈썰매","아쿠아리움","워터파크/스파","체험","테마/놀이동산","패러글라이딩","게임/E머니","기타 푸드/음료","데이터/통화","도서/문화상품권","백화점/마트","뷰티/헬스","생활/서비스","아이스크림/빙수","영화/뮤직/컨텐츠","외식","치킨","카페/베이커리","편의점","피자/햄버거","꽃배달","케이크배달","사진/영상 촬영","VOD","기타 콘텐츠","기타상품권","기프트카드","백화점/마트상품권","영화관람권","외식상품권","골프레슨","구기 스포츠","댄스/무용","무예/무도","미술","보컬/악기","서핑/다이빙","스키/보드","피트니스/PT","필라테스/요가","기타클래스","가죽공예","기타수공예","도자기","드로잉","뜨개질","목공예","비누","자수/수예","주얼리","캔들/방향제/향수","플라워","기타쿠킹","베이킹","공예","뷰티","영어","취업/자격증/기타","커피/요리","와이파이/USIM","공항 픽업/샌딩","교통패스","기타/이동서비스","해외렌터카","기타입장권","박물관/미술관","역사/관광명소","전망대","테마파크","해외숙박","해외패키지/기타","가전/가구 설치","이사/청소","비디오/DVD","기타교구","미술교구","수학교구","영어교구","자연/과학교구","한글교구","학습보드게임","기타구강청결용품","손가락빨기방지용품","유아구강세정제","유아구강청결티슈","유아치약","유아칫솔","국내기저귀","기저귀밴드","기저귀커버","땅콩기저귀","수영장기저귀","천기저귀","수입기저귀","기타목욕용품","욕실정리망/정리함","유아목욕가운","유아목욕의자","유아목욕장갑/스펀지","유아목욕장난감","유아목욕타월","유아샴푸캡","유아세면대/수도꼭지","유아욕조","유아욕탕온도계","비데용","손소독용","리필형","물티슈워머/물티슈캡","캡형","코인티슈/업소용","휴대용","국내분유","수입분유","특수분유","장난감소독기","젖병건조대","젖병세정제","젖병소독기","젖병솔","젖병집게","남아수영복","수경/수모/귀마개","수영가방/비치백","여아수영복","기타수유용품","노리개젖꼭지","모유보관비닐팩","보틀워머","분유케이스","수유가리개","수유쿠션/시트","수유패드","유두케어/젖몸살용품","유축기","젖꼭지","젖병","기저귀크림","기타스킨/바디용품","색조화장품","어린이네일케어","유아로션","유아립케어","유아바스/샴푸","유아비누","유아선크림","유아오일","유아입욕제","유아크림","유아파우더","가제손수건","레그/스패츠","바디슈트/롬퍼","배냇저고리","손/발싸개","신생아모자/보닛","우주복","유아과자","유아두유","유아유제품","유아음료","디딤대","모기밴드/퇴치용품","모서리보호대","문닫힘방지","안전잠금장치","콘센트안전커버","기타감각발달완구","도형끼우기","링쌓기/컵쌓기","멜로디완구","비눗방울","아기전화기","헝겊완구","그네/그네봉","놀이집","다기능놀이터","미끄럼틀","볼텐트/놀이텐트","놀이방매트","목튜브/암링","물총","보행기튜브","비치볼","스노클링","풀장","그림판","기타미술놀이","물감","색연필","색칠공부","스케치북","오리기","종이접기","크레욜라","플레이콘/매직콘","기타블록","레고","빅블록","사각/십자블록","옥스포드","원목블록","자석블록","종이/소프트블록","공/소프트볼","기타스포츠완구","볼링","스카이콩콩","스키/눈썰매","야구/캐치볼","축구대","트램펄린/덤블링","펀치백/권투","헬스기구","호핑볼/점핑볼","기타승용완구","붕붕카","스프링카","시소","왜건","전동차","지붕차","킥보드/씽씽이","트레일러","흔들말","기타신생아/영유아완구","딸랑이","모빌","베이비룸","신생아완구세트","아기걸음마","아기체육관","오뚝이","치아발육기","플레이매트","기타언어/학습완구","낱말카드/학습카드","디지털학습완구","유아용컴퓨터","자석놀이","학습벽보","학습보드판","가게놀이","가사놀이","공구놀이","기타역할놀이/소꿉놀이","낚시놀이","모래놀이","병원놀이","소꿉놀이","인형유모차","주방놀이","쿠킹토이","화장놀이","가베","도미노","도형쌓기","롤러코스터","셈놀이/숫자놀이판","실꿰기/구슬꿰기","원목큐브","하노이탑","다기능자전거","밸런스자전거","보조바퀴체인자전거","세발자전거","구슬퍼즐","그림퍼즐","기타유아동퍼즐","도형퍼즐/칠교놀이","숫자퍼즐","원목/꼭지퍼즐","종이/입체퍼즐","직소퍼즐/액자","큐브/소마큐브","기타음악/악기놀이","나팔","마이크/노래방","멜로디언/키보드","악기세트","타악기","피아노","곤충학습","공룡","과학상자","기초로봇","동물학습","물로켓/고무동력기","식물학습","기차","기타작동완구","동물작동완구","로봇","로봇강아지","메카드시리즈","장난감총","팽이","캐릭터카드/딱지","기저귀가방","기타외출용품","망토/워머","미아방지용품","슬링","아기띠","아기띠침받이","아기띠쿨러/패드","포대기/처네","힙시트","기저귀휴지통","분통/파우더통","유아마스크","유아면봉","유아변기/커버","유아손세정제","유아손톱가위/손톱깎이","유아이발용품","유아항균팩","제균스프레이","콧물흡입기","키재기","투약기","해열시트","쌍둥이용","유모차/카시트 세트","기타유모차용품","유모차걸이","유모차목쿠션/블랭킷","유모차보호대/안전바","유모차시트","유모차장난감","유모차정리망/정리함","유모차커버","유모차풋머프","유모차햇빛가리개","유모차홀더","절충형/디럭스형","초경량/휴대용","기저귀정리함","기타유아가구","유아공부상","유아소파","유아옷걸이","유아의자","유아이유식의자","유아책상","유아침대","장난감정리함","귀걸이","목걸이","순금돌반지","순금주얼리","주얼리세트","팔찌","펜던트","내의/내복","러닝","러닝팬티세트","배변훈련팬티","브라","브라팬티세트","속치마/속바지","수면조끼","잠옷/홈웨어","팬티","공주드레스","교복","드레스","레깅스","발레복/댄스복","블라우스","셔츠/남방","스키복","점프슈트","정장","청바지","카디건","코스튬의상","코트","트레이닝복","패밀리룩","미아방지가방","백팩","신발주머니/보조가방","캐리어백","크로스백","토트백/숄더백","기타유아동잡화","넥타이","목도리","벨트/멜빵","선글라스","스카프","구두","보행기신발","부츠/털신","샌들","슬리퍼","실내화","아쿠아슈즈","운동화","장화","젤리슈즈","앞치마/토시/두건","지갑","타이즈","헤어끈","헤어밴드","헤어핀","바운서/흔들침대","보행기","쏘서","점퍼루","유아세탁비누","유아세탁세제","유아유연제","유아표백제/얼룩제거제","DIY 아기용품","겉싸개","기타유아침구","낮잠이불","방수요","보낭/슬리핑백","속싸개","아기이불/요/패드","유아베개","가공이유식","수제이유식","이유식재료","과즙망","기타이유식용품","연습용젓가락","유아스푼/포크","유아식기","유아컵","조리기","턱받이","기타인형","봉제인형","캐릭터/패션인형","수유복","스타킹","수유브라","임부내복","임부러닝","임부팬티","임산부복대","임부용수영복","트레이닝복/요가복","기타임산부용품","모유수유차","산모방석","임산부바디필로우","임산부보호대","임산부화장품","전자파차단앞치마","태교용구","태교음반/DVD","기저귀케이크","기타출산/돌기념품","답례품","돌잔치소품","상대여","행사용스티커","성장앨범/액자","성장카드","촬영소품","촬영의상","손발도장","유치보관함","탯줄도장","탯줄보관함","바구니형","분리형","일체형","차량안전벨트","카시트기타용품","햇빛가리개","보정상의","보정하의","복대","내복","모시메리","유니폼/단체복","정장세트","코디세트","러닝/캐미솔","거들","기타보정속옷","니퍼/복대","보정속옷세트","올인원/바디쉐이퍼","코르셋","슬립","힙워머","가터벨트","기타언더웨어소품","땀패드","브라끈","브라패드","블라우스/셔츠","파티복","브리프케이스","숄더백","에코백","클러치백","토트백","힙색","댄스화","작업화/안전화","컴포트화","한복신발","모카신/털신","보트슈즈","스니커즈","슬립온","러닝화","보드화","워킹화","캔버스화","하이탑","워커","웰트화","군모","귀달이모자","두건/반다나","방울털모자","베레모","사파리모자","선캡","메시캡","일반캡","페도라","헌팅캡","정장벨트","캐주얼벨트","멜빵","여성벨트","선글라스 케이스/소품","안경소품","안경줄","안경케이스","안경테","골드바","순금기념품","실버바","시계보관함","시계줄","시계수리용품","아동시계","커플시계","가죽밴드시계","금시계","기타밴드시계","메탈밴드시계","뱅글/팔찌형시계","스톱워치","젤리/우레탄밴드시계","회중시계","구둣주걱","기타신발용품","보호쿠션/패드","부츠키퍼","슈즈커버","신발깔창","신발끈","발가락양말","발목양말","수면양말","스포츠양말","중목/장목양말","니삭스","덧신","레그워머","가방걸이","가방끈","파우치","다이어트화","유니폼화","로퍼","옥스퍼드화","플랫","롱부츠","미들부츠","앵클/숏부츠","부티","글래디에이터샌들","뮬/블로퍼","스트랩샌들","슬링백샌들","아쿠아샌들","웨지힐샌들","젤리샌들","워커힐","플랫워커","가보시힐","스트랩힐","슬링백힐","웨딩슈즈","웨지힐","토오픈힐","통굽힐","펌프스","하이힐","기내용 캐리어","네임태그","보스턴가방","슈트케이스","여권지갑/케이스","여행소품케이스","이민/유학용 가방","잠금벨트/자물쇠","중대형 캐리어","캐리어소품","캐리어커버","남성장갑","암워머/토시","여성장갑","14K귀걸이","18K귀걸이","귀걸이만들기","다이아몬드 귀걸이","순금귀걸이","유색보석귀걸이","은귀걸이","이니셜귀걸이","진주귀걸이","패션귀걸이","피어싱","14K목걸이","18K목걸이","다이아몬드목걸이","목걸이만들기","순금목걸이","유색보석목걸이","은목걸이","이니셜목걸이","진주목걸이","패션목걸이","14K반지","18K반지","다이아몬드반지","반지만들기","순금반지","유색보석반지","은반지","이니셜반지","진주반지","패션반지","순금발찌","패션발찌","14K세트","18K세트","다이아몬드세트","순금주얼리세트","유색보석세트","은세트","진주세트","패션주얼리세트","기타주얼리소품","보관함/케이스","세척용품","진열대","금팔찌","순금팔찌","은팔찌","팔찌만들기","패션팔찌","금펜던트","순금펜던트","유색보석펜던트","은펜던트","패션펜던트","반지갑","장지갑","중지갑","동전지갑","머니클립","열쇠지갑","지갑기타세트","카드/명함지갑","통장지갑","기타패션소품","넥워머","넥케이프","기본넥타이","나비넥타이","넥타이핀","기타담배용품","담배케이스","라이터","머플러","부채","보석브로치","카네이션브로치","패션브로치","남성용","여성용","숄","롱","쁘띠/미니","발목스타킹","밴드스타킹","스프레이스타킹","압박보정스타킹","판타롱스타킹","팬티스타킹","수동양산","자동양산","와펜","수동우산","자동우산","커프스","코르사주","키홀더","한복소품","행커치프","남성가발","여성가발","헤어액세서리소품","남성청결제","남성화장품세트","로션","마스크/팩","메이크업","선크림","쉐이빙폼","스크럽/필링","아이케어","에센스","크림","클렌징","네일리무버","네일아트","네일영양제","네일케어도구","네일케어세트","매니큐어","마사지크림/젤","마스크/팩세트","마스크시트","수면팩","워시오프팩","코팩","필오프팩","데오드란트","목욕비누","바디로션","바디미스트","바디스크럽","바디슬리밍","바디오일","바디케어세트","바디크림","바디클렌저","바디파우더","샤워코롱","아로마테라피","여성청결제","입욕제","제모제","풋케어","핸드케어","BB크림","CC크림","메이크업베이스","베이스메이크업세트","컨실러","트윈케이크","루스파우더","팩트파우더","리퀴드형","스틱형","쿠션형","크림형","프라이머","DIY화장품재료","립브러시","브러시세트","아이브러시","페이스브러시","바디소품","기타아이소품","눈썹가위","눈썹칼","뷰러","샤프너","속눈썹/속눈썹펌제","쌍꺼풀","족집게","타투","기름종이","기타페이스소품","마사지도구","면봉","세안도구","퍼프","피지/각질제거기","화장솜","기타헤어소품","세안밴드","헤어롤","헤어브러시","헤어캡","화장품케이스","립글로스","립라이너","립스틱","립케어","립틴트","마스카라","블러셔","색조메이크업세트","속눈썹영양제","젤형","펜슬형","아이브로","아이섀도","하이라이터/쉐이딩","선스틱","선스프레이","선케어세트","선파우더/쿠션","애프터선","태닝스프레이","태닝오일","태닝크림","태닝티슈","넥케어","미스트","스킨/토너","톤업크림","페이스오일","화장품세트","립앤아이리무버","클렌징로션","클렌징비누","클렌징세트","클렌징오일","클렌징워터","클렌징젤","클렌징크림","클렌징티슈","클렌징파우더","클렌징폼","남녀공용향수","남성향수","여성향수","향수세트","염색약","스트레이트","웨이브","헤어글레이즈","헤어무스","헤어스프레이","헤어왁스","헤어젤","두피케어","린스","샴푸","탈모케어","트리트먼트","헤어미스트","헤어에센스","헤어케어세트","헤어팩"};

    private final ApiService<String> apiService;
    private final StudyRepository studyRepository;

    @Value("${api.naver.id}")
    private String apiId;

    @Value("${api.naver.secret}")
    private String secret;

    /**
     * Naver api 요청 시, header에 id와 secret 값을 넣어 보내야함
     * id와 secret은 @Value로 application-secret.yml에 저장 되어 있음.
     * @return
     */
    private HttpHeaders setHttpHeaders() {
        HttpHeaders headers = new HttpHeaders();
        headers.set("X-Naver-Client-Id",apiId);
        headers.set("X-Naver-Client-Secret",secret);
        return headers;
    }

    public void getNaverJoongCategory() {
        URI uri;
        JSONArray list = new JSONArray();
        try {
            for(int i = 0; i<strList.length; i++){
                UriComponents uriComponents = UriComponentsBuilder
                        .fromHttpUrl("https://openapi.naver.com/v1/search/shop.json")
                        .queryParam("query", URLEncoder.encode(strList[i], "UTF-8"))
                        .queryParam("display", 100)
                        .queryParam("sort", "sim")
                        .queryParam("exclude", "used:rental:cbshop")
                        .build(true);
                log.info("i : "+i+" url : "+uriComponents.toUri());
                ResponseEntity<String> response = apiService.get(uriComponents.toUri(), setHttpHeaders(), String.class);

                List<Study> studies = Study.jsonToO(response);
                for (Study study : studies) {
                    studyRepository.save(study);
                }
            }
        } catch (CustomException e){
            throw new CustomException(ErrorCode.URI_SYNTAX_ERROR);
        } catch (UnsupportedEncodingException e) {
            throw new CustomException(ErrorCode.ENCODING_UTF_8);
        }
    }
}