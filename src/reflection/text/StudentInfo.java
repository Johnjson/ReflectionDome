package reflection.text;

public class StudentInfo {

	public StudentInfo(String name) {
		this.name = name;
	}

	public StudentInfo(String name, int age) {
		this.name = name;
		this.age = age;
	}

	private String name;
	public int age;
	private String sex;

	public StudentInfo() {
		// TODO Auto-generated constructor stub
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
		System.out.println(name);
	}

	public int getAge() {
		return age;
	}

	public void setAge(int age) {
		this.age = age;
	}

	public String getSex() {
		return sex;
	}

	public void setSex(String sex) {
		this.sex = sex;
	}

	@Override
	public String toString() {
		// TODO Auto-generated method stub
		return this.getClass().getName();
	}
}
