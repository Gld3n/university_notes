package students;

import java.util.Arrays;
import java.util.Date;

public class Student {

	public String name;
	public Date birthdate;
	public String sex;
	public Double[] grades;
	public String id;
	public Integer age;
	public Double average;
	
	public Student(String name, Date birthdate, String sex, Double[] grades, String id, Integer age, Double average) {
		this.name = name;
	    this.birthdate = birthdate;
	    this.sex = sex;
	    this.grades = grades;
	    this.id = id;
	    this.age = age;
	    this.average = average;
	}
	
	public Double getAverage() {
		return average;
	}
	
	public Integer getAge() {
		return age;
	}
	
	@Override
    public String toString() {
        return "Student [name=" + name + ", birthdate=" + birthdate + ", sex=" + sex +
                ", grades=" + Arrays.toString(grades) + ", id=" + id + ", age=" + age +
                ", average=" + average + "]";
	}
}