package dao;

import java.util.Scanner;

import dto.BatterDto;
import dto.HumanDto;
import dto.pitcherDto;
import file.DataProc;

public class BaseballDaoImpl implements BaseballDao{

	Scanner	sc = new Scanner(System.in);
	
	private HumanDto human[];
//	private HumanDto human[] = { // 더미데이터
//			new pitcherDto(1001, "홍길동", 24, 172.1, "투수", 10, 3, 0.234),
//			new BatterDto(1002, "성춘향", 16, 157.1, "타자", 15, 5, 0.312),
//			new BatterDto(1003, "일지매", 22, 181.8, "타자", 9, 4, 0.876),
//			new BatterDto(1004, "홍길동", 26, 192.1, "타자", 20, 6, 0.234),
//			new pitcherDto(1005, "홍두깨", 25, 182.1, "투수", 15, 8, 0.834),
//			new pitcherDto(1006, "라일락", 24, 178.1, "투수", 11, 4, 0.634),
//	};
	private int counter;
	
	DataProc dataproc;
	
	public BaseballDaoImpl() {
		human = new HumanDto[10];
		counter = 0;
		
		dataproc = new DataProc("baseball");
		dataproc.createFile();
	}
	
	
	@Override
	public void insert() {
		System.out.println("선수등록입니다.");
		System.out.println("등록할 포지션을 입력해 주세요");
		System.out.print(" 투수(1) / 타자(2) = ");
		int position = sc.nextInt();
		
		System.out.print("번호 = ");
		int number = sc.nextInt();
		
		System.out.print("이름 = ");
		String name = sc.next();
		
		System.out.print("나이 = ");
		int age = sc.nextInt();
		
		System.out.print("신장 = ");
		double height = sc.nextDouble();
		
		HumanDto HumanDto = null;
		if(position == 1) {
			System.out.print("승 = ");
			int win = sc.nextInt();
			
			System.out.print("패 = ");
			int lose = sc.nextInt();
			
			System.out.print("방어율 = ");
			double defence = sc.nextDouble();
			
			HumanDto = new pitcherDto(number, name, age, height, "투수", win, lose, defence);
			
		}else {
			System.out.print("타수 = ");
			int batCount = sc.nextInt();
			
			System.out.print("히트수 = ");
			int hit = sc.nextInt();
			
			System.out.print("타율 = ");
			double hitAvg = sc.nextDouble();
			
			HumanDto = new BatterDto(number, name, age, height, "타자", batCount, hit, hitAvg);
			
		}
		
		human[counter] = HumanDto;
		counter++;
		
		System.out.println("등록되었습니다.");
		
	}

	@Override
	public void delete() {
		System.out.println("선수삭제입니다.");
		System.out.print("삭제한 선수의 이름 = ");
		String name = sc.next();
		
		int index = this.search(name);
		if(index == -1) {
			System.out.println("선수명단에 없습니다.");
			return;
		}
		
		human[index].setNumber(0);
		human[index].setName("");
		human[index].setAge(0);
		human[index].setHeight(0.0);
		
		if(human[index] instanceof pitcherDto) {
			pitcherDto p = (pitcherDto)human[index];
			p.setPosition("");
			p.setWin(0);
			p.setLose(0);
			p.setDefene(0.0);
		}else {
			BatterDto b = (BatterDto)human[index];
			b.setPosition("");
			b.setBatCount(0);
			b.setHit(0);
			b.setHitAvg(0.0);
		}
		System.out.println("삭제되었습니다.");
		
	}

	@Override
	public void select() {
		System.out.println("선수검색입니다.");
		System.out.print("수정할 선수의 이름 = ");
		String name = sc.next();
		
		// 검색된 선수 카운트
		int count = 0;
		for (int i = 0; i < human.length; i++) {
			HumanDto h = human[i];
			
			if( h != null && !h.getName().equals("") ) {
				if(name.equals(h.getName())) {
					count++;
				}
			}
		}
		
		if(count == 0) {
			System.out.println("선수명단이 없습니다.");
			return;
		}
		
		HumanDto findHuman[] = new HumanDto[count];
		int c = 0;
		for (int i = 0; i < human.length; i++) {
			HumanDto h = human[i];
			
			if( h != null && !h.getName().equals("") ) {
				if(name.equals(h.getName())) {
					findHuman[c] = human[i];
					c++;
				}
			}
		}
		
		System.out.println("검색된 선수 명단입니다.");
		for (int i = 0; i < findHuman.length; i++) {
			System.out.println(findHuman[i].info());
		}
		
	}

	@Override
	public void update() {
		System.out.println("선수수정입니다.");
		System.out.print("수정할 선수의 이름 = ");
		String name = sc.next();
		
		int index = this.search(name);
		if(index == -1) {
			System.out.println("선수명단에 없습니다.");
			return;
		}
		
		if(human[index] instanceof pitcherDto) {
			System.out.println("승 = ");
			int win = sc.nextInt();
			
			System.out.println("패 = ");
			int lose = sc.nextInt();
			
			System.out.println("방어율 = ");
			double defence = sc.nextDouble();
			
			pitcherDto p = (pitcherDto)human[index];
			p.setWin(win);
			p.setLose(lose);
			p.setDefene(defence);
			
			
		}else {
			System.out.print("타수 = ");
			int batCount = sc.nextInt();
			
			System.out.print("히트수 = ");
			int hit = sc.nextInt();
			
			System.out.print("타율 = ");
			double hitAvg = sc.nextDouble();
			
			BatterDto b = (BatterDto)human[index];
			b.setBatCount(batCount);
			b.setHit(hit);
			b.setHitAvg(hitAvg);
		}
		
		System.out.println("수정되었습니다.");
	}

	@Override
	public void allPrint() {
		for (HumanDto h : human) {
			if( h != null && !(h.getName().equals("")) ) {
				System.out.println(h.info());
			}
		}
		
	}

	@Override
	public void batSort() {
		HumanDto humanB[] = new HumanDto[10];
		// 타자만으로 (배열)구성
		int count = 0;
		for (int i = 0; i < human.length; i++) {
			HumanDto h = human[i];
			if( h != null && h.getName().equals("") == false) {	// 조건 순서 바뀌면 nullpointexception 뜬다
				if(h instanceof BatterDto) {
					humanB[count] = h;
					count++;
				}
			}
		}
				
		// 순위(내림정렬)처리
		HumanDto temp;
		for (int i = 0; i < humanB.length - 1; i++) {
			for (int j = i + 1; j < humanB.length; j++) {
				if(humanB[i] != null && !humanB[i].getName().equals("") 
						&& humanB[j] != null && !humanB[j].getName().equals("")) {
					
					BatterDto b1 = (BatterDto)humanB[i];
					BatterDto b2 = (BatterDto)humanB[j];
					
					if(b1.getHitAvg() < b2.getHitAvg()) { //비교는 타율로 한다. 
						temp = humanB[i];
						humanB[i] = humanB[j];
						humanB[j] = temp;
					}
				}
			}
		}
		
		for (int i = 0; i < humanB.length; i++) {
			if( humanB[i] != null) {
				System.out.println( ( i + 1 ) + "위 "
									+ " 이름 : "+ humanB[i].getName()
									+ ",  타율 : "+ ((BatterDto)humanB[i]).getHitAvg());
			}
		}
	}

	@Override
	public void pitchSort() {
		HumanDto[] humanP = new HumanDto[10];
		
		//투수만 구성
		int count = 0; 
		for (int i = 0; i < human.length; i++) {
			HumanDto h = human[i];
			if(h != null && h.getName().equals("") == false) {
				if(h instanceof pitcherDto) {
					humanP[count] = h ;
					count++;
				}
			}
		}
		
		HumanDto temp;
		for (int i = 0; i < humanP.length; i++) {
			for (int j = 0; j < humanP.length; j++) {
				if(humanP[i] != null && !humanP[i].getName().equals("") 
					&& humanP[j] != null && !humanP[j].getName().equals("")	) {
					
					pitcherDto p1 = (pitcherDto)humanP[i];
					pitcherDto p2 = (pitcherDto)humanP[j];
					
					if(p1.getDefene() > p2.getDefene()) {
						temp = humanP[i];
						humanP[i] = humanP[j];
						humanP[j] = temp;
					}
				}
				
			}
			
		}
		
		for (int i = 0; i < humanP.length; i++) {
			if(humanP[i] != null) {
				System.out.println( ( i + 1 ) + "위 "
						+ " 이름 : "+ humanP[i].getName()
						+ ",  타율 : "+ ((pitcherDto)humanP[i]).getDefene());
			}
			
		}
		
	}

	@Override
	public void save() {
		
		// 몇개의 데이터 수 파악
		int count = 0;
		for (HumanDto h : human) {
			if(h != null && !h.getName().equals("")) {
				count++;
			}
		}
		
		if(count == 0) {
			System.out.println("데이터가 없습니다.");
			return;
		}
		
		// (String)배열 설정
		String saveDatas[] = new String[count]; 
		
		// HumanDto -> String
		int c = 0;
		for (HumanDto h : human) {
			if(h!= null && !h.getName().equals("")) {
				saveDatas[c] = h.toString();
				c++;
			}
		}
		
		// 저장
		dataproc.dataSave(saveDatas);
		System.out.println("저장되었습니다.");
	}

	@Override
	public void load() {
		String datas[] = dataproc.dataLoad();
//		for (String s : datas) { 데이터 확인
//			System.out.println(s);
//		}
		
		for (int i = 0; i < datas.length; i++) {
			String data[] = datas[i].split("-");
			if(data[4].equals("투수")) {
				human[i] = new pitcherDto(	
											Integer.parseInt(data[0]), 
											data[1], 
											Integer.parseInt(data[2]),
											Double.parseDouble(data[3]),
											data[4],
											Integer.parseInt(data[5]),
											Integer.parseInt(data[6]),
											Double.parseDouble(data[7])
										);
			}else if(data[4].equals("타자")) {
				human[i] = new BatterDto(	
											Integer.parseInt(data[0]), 
											data[1], 
											Integer.parseInt(data[2]),
											Double.parseDouble(data[3]),
											data[4],
											Integer.parseInt(data[5]),
											Integer.parseInt(data[6]),
											Double.parseDouble(data[7])
										);
			}
		}
		
		counter = datas.length;
		
		System.out.println("데이터를 로드하였습니다.");
	}
	
	public int search(String name) {
		int index = -1;
		for (int i = 0; i < human.length; i++) {
			HumanDto dto = human[i];
			if(name.equals(dto.getName())) {
				index = i;
				break;
			}
		}
		return index;
	}

}
