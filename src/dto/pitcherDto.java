package dto;

public class pitcherDto extends HumanDto{
	private String position;
	private int win;
	private int lose;
	private double defence;
	
	public pitcherDto() {
	}

	public pitcherDto(int number, String name, int age, double height, String position, int win, int lose, double defene) {
		super(number, name, age, height); //f3 누르면 상속받은 내용으로 확인가능
		this.position = position;
		this.win = win;
		this.lose = lose;
		this.defence = defene;
	}

	public String getPosition() {
		return position;
	}

	public void setPosition(String position) {
		this.position = position;
	}

	public int getWin() {
		return win;
	}

	public void setWin(int win) {
		this.win = win;
	}

	public int getLose() {
		return lose;
	}

	public void setLose(int lose) {
		this.lose = lose;
	}

	public double getDefene() {
		return defence;
	}

	public void setDefene(double defene) {
		this.defence = defene;
	}

	@Override
	public String toString() {
		return super.toString() +"-"+ position + "-" + win + "-" + lose + "-" + defence;
	}
	
	@Override
	public String info() {
		return super.info() + 
				"포지션 :" + position +" 승 : " + win +" 패 : "  + lose + " 타율 : " + defence;
	}
	
	
}
