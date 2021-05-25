package com.work.model.service;

import com.work.model.dto.StudentScore;

/**
 * <pre>
 * 학생 성적관련 서비스 제공 클래스
 * 1. 등록 기능 : + addStudentScore //addDomianName 등록(학번, 이름, 성적) : 결과값이 안궁금하면 출력할거면 void지만 
 * 2. 조회 기능
 * 		>> 전체학생성적조회 가능 : + printStudentScore() : void 등록한인원수만큼출력
 * 		>> 학번의 해당학생 상세조회 가능
 * 3. 총점 계산 기능
 * 4. 평균 계산 기능
 * </pre>
 * 
 * @author 한나
 * @version ver.1.0
 * @since jdk.1.8
 * @see com.work.model.dto.StudentScore
 * @see com.work.model.dto.StudentScore#printInfo()
 */
public class StudentScoreService {
	/**학생들의 성적정보 객체를 그룹으로 관리하기 위한 자료 저장구조 : 필요시에 확장하도록 처리*/
	//멤버변수로 만들어두면 1~4까지 다 쓸수잇음
	//public StudentScore[] students = new StudentScore[3];
	/**
	 * import 컨시프트 눌러도되고 도움말써도됨
	 * new StudentScore[3]=배열의크기=\=현재 등록된 학생수=0
	 * 신규등록학생의 index 번호는 0부터 시작
	 * 현재 배열의 크기 (students.length)와 현재등록된인원(count)를 비교해서 
	 * 작으면 등록, 같으면 확장 시킴 (호텔 방 부족)
	 * 확장시키는 로직
	 * >>기존의 크기보다 더큰 배열객체(몇개?? 10개)를 생성해서 
	 *   기존 배열에 저장된 객체를 더 큰 배열로 순서대로 이동시키고 
	 *   새로운 객체를 추가된 배열요소에 등록 
	 *   멤버변수 배열객체를 기존 배열요소객체 대신에 새로이 생성한 배열객체 요소를 참조할당
	 */
	
	
	/**학생들의 성적정보 객체를 그룹으로 관리하기 위한 자료 저장구조 멤버변수 선언 : 배열객체 생성은 생성자 위임처리
	 *-- 접근 권한 : public -> private 변경(외부에서 함부로 변경할 수 없음: 보안문제 해결) 
	 * */
	private StudentScore[] students;
	
	/** 등록된 학생의 숫자 정보 및 등록해야하는 배열요소의 인덱스번호 정보
	 * -- 접근 권한 : public -> private 변경(외부에서 함부로 변경할 수 없음: 보안문제 해결) 
	 * -- 변경 불가
	 * -- 등록 학생수 조회 제공 : getter()
	 *  */
	private int count;
	
	/** 기본생성자 : 배열의 크기를 기본 10개 초기화 */
	public StudentScoreService() {
		//students = new StudentScore[10]; 아래가 더 간단
		this(10);
	}
	
	/**
	 *  배열의 크기를 전달받아서 배열생성 할당 초기화 생성자
	 *  @param length
	 */
	public StudentScoreService(int length) {
		students = new StudentScore[length];
	}
	
	/**실습 : 현재 등록한 학생성적의 숫자조회 메서드
	 * 
	 */
	public int getCount() {
		return count;
	}
	
	
	//메서드 선언 실습
	//1. 등록 기능 : + addStudentScore (학번, 이름, 성적) : void 
	/**
	 * <pre>
	 * 학생성적 등록 메서드
	 * -- 등록결과 메세지 출력
	 * -- 성공 : 등록 성공
	 * -- 실패 : 등록 실패
	 * 
	 * # 처리 수행 절차
	 * 1.StudentScore 객체 생성
	 * 2.아규먼트로 전달받은 정보로 생성 객체 정보 설정
	 * 3.생성된 객체를 배열의 count 요소 위치에 등록
	 * 4.count 등록인원을 1 증가
	 * 
	 * </pre>
	 * @param StudentNo 학번
	 * @param name 이름
	 * @param scores 성적
	 */
	
	/**
	 * 등록메서드 중복 정의
	 * @param StudentNo
	 * @param name
	 * @param scores
	 */
	public void addStudentScore(String StudentNo,String name, int scores) {
		if (count == students.length) {
			extendStudents(10);
			System.out.println("학생들의 자료저장구조를 추가 확장하였습니다");
		}
		//StudentScore 객체 생성
		//아규먼트로 전달받은 정보로 생성 객체 정보 설정
		StudentScore dto = new StudentScore();
		dto.studentNo = StudentNo;
		dto.name = name;
		dto.score = scores;
		
		//생성된 객체를 배열의 count 요소 위치에 등록
		//count 등록인원을 1 증가
		
	//	students[count++] = dto; //'++' = 3과4 동일한 역할
		addStudentScore(dto);
	}

		
		
		
	
	// 실습 : 등록메서드 중복정의 : StudentScore 매개변수 전달받음
	public void addStudentScore(StudentScore dto) {
		if (count == students.length) {
			extendStudents(10);
			System.out.println("학생들의 자료저장구조를 추가 확장하였습니다");
		}
		students[count++] = dto; 
	}
	/**
	 * <pre>
	 * 학생들의 성적 자료 저장구조를 전달받은 크기로 확장한 후
	 * 확장한 새로운 배열요소에 기존 학생들을 변경 저장한 후
	 * 학생들의 배열요소를 새로이 확장한 배열객체로 변경 설정
	 * </pre>
	 * @param Length
	 */
	public void extendStudents(int length) {
		StudentScore[] studentsTemp = new StudentScore[students.length + length];
		
		for (int index = 0; index < students.length; index++) {
			studentsTemp[index] = students[index];
		}
		
		students = studentsTemp;
	}
	
	/**
	 * 전체학생 성적 조회 메서드
	 */
	public void printStudentScore() {
		for (int index = 0; index < count; index++)
			students[index].printInfo();
	}
}


