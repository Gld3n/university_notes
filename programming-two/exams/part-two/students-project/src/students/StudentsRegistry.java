package students;

import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.time.Period;
import java.time.ZoneId;
import java.util.Arrays;
import java.util.Comparator;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class StudentsRegistry {
	
	public static Student[] students = new Student[128];
	public static Integer studentsCount = 0;
	public static final Date CURRENT_DATE = new Date();
	
	// UTILITY functions
	public static void clearScreen() {
	    if (System.getProperty("os.name").toLowerCase().contains("windows")) {
	        System.out.println("\f");
	    } else {
	        System.out.println("\033[H\033[2J");
	    }
	}

	public static void waitForUserInput(Scanner scanner) {
	    System.out.println("\nPress enter to continue");
	    scanner.nextLine();
	    clearScreen();
	}
	
	public static Integer calculateAge(Date birthDate) {
        LocalDate localBirthDate = birthDate.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        LocalDate localCurrentDate = CURRENT_DATE.toInstant().atZone(ZoneId.systemDefault()).toLocalDate();
        Period period = Period.between(localBirthDate, localCurrentDate);
        return period.getYears();
    }
	
	public static Double calculateAverage(Double sumOfGrades, Integer numberOfGrades) {
		return sumOfGrades / numberOfGrades;
	}
	
	public static Map<String, Integer> calculateMenWomen() {
		Map<String, Integer> menAndWomen = new HashMap<>();
		Integer men = 0, women = 0;
		
		for (Integer i = 0; i < studentsCount; i++) {
			Student currentStudent = students[i];
			
			if (currentStudent.sex.equals("M")) {
				men++;
			} else {
				women++;
			}
		}
		
		menAndWomen.put("M", men);
		menAndWomen.put("F", women);
		
		return menAndWomen;
	}
	
	public static Map<String, Integer> calculateApprovedFailed() {
		Map<String, Integer> approvedAndFalied = new HashMap<>();
		Integer approved = 0, failed = 0;
		
		for (Integer i = 0; i < studentsCount; i++) {
			Student currentStudent = students[i];
			
			if (currentStudent.average >= 9.5) {
				approved++;
			} else {
				failed++;
			}
		}
		
		approvedAndFalied.put("approved", approved);
		approvedAndFalied.put("failed", failed);
		
		return approvedAndFalied;
	}
	
	public static Map<String, Integer> calculateMajorsMinors() {
		Map<String, Integer> majorsMinors = new HashMap<>();
		Integer majors = 0, minors = 0;
		
		for (Integer i = 0; i < studentsCount; i++) {
			Student currentStudent = students[i];
			
			if (currentStudent.age >= 18) {
				majors++;
			} else {
				minors++;
			}
		}
		
		majorsMinors.put("majors", majors);
		majorsMinors.put("minors", minors);
		
		return majorsMinors;
	}

    public static Student[] calculateHighestAverages() {
        Student[] sortedStudents = new Student[studentsCount];
        
        for (Integer i = 0; i < studentsCount; i++) {
        	sortedStudents[i] = students[i];
        }
        
        Arrays.sort(sortedStudents, Comparator.comparingDouble(Student::getAverage).reversed());
        
        Student[] slicedStudents = Arrays.copyOfRange(sortedStudents, 0, 2);
        
        return slicedStudents;
	}
    
    public static Student[] calculateLowestAverages() {
        Student[] sortedStudents = new Student[studentsCount];
        
        for (Integer i = 0; i < studentsCount; i++) {
        	sortedStudents[i] = students[i];
        }
        
        Arrays.sort(sortedStudents, Comparator.comparingDouble(Student::getAverage));
        
        Student[] slicedStudents = Arrays.copyOfRange(sortedStudents, 0, 2);
        
        return slicedStudents;
	}
    
    public static Student[] calculateYoungest() {
        Student[] sortedStudents = new Student[studentsCount];
        
        for (Integer i = 0; i < studentsCount; i++) {
        	sortedStudents[i] = students[i];
        }
        
        Arrays.sort(sortedStudents, Comparator.comparingDouble(Student::getAge).reversed());
        
        Student[] slicedStudents = Arrays.copyOfRange(sortedStudents, 0, 2);
        
        return slicedStudents;
	}
    
    public static Student[] calculateOldest() {
        Student[] sortedStudents = new Student[studentsCount];
        
        for (Integer i = 0; i < studentsCount; i++) {
        	sortedStudents[i] = students[i];
        }
        
        Arrays.sort(sortedStudents, Comparator.comparingDouble(Student::getAge));
        
        Student[] slicedStudents = Arrays.copyOfRange(sortedStudents, 0, 2);
        
        return slicedStudents;
	}
	
	// MENU functions
	public static void addStudent() {
		Scanner addScanner = new Scanner(System.in);
		
		System.out.println("=== ADD STUDENT =====================");
		
		System.out.print("How many students would you like to add?\nAmount: ");
		Integer studentsToAdd = addScanner.nextInt();
		addScanner.nextLine();
		
		if (studentsToAdd <= 0) {
			return;
		}
		
		for (Integer i = 0; i < studentsToAdd; i++) {
			System.out.println("=== [STUDENT #" + (i+1) + "] ====================");
		
			// NAME
			System.out.print("Name: ");
			String studentName = addScanner.nextLine();
			
			// BIRTHDATE
			System.out.print("Birthdate (dd-mm-yyyy): ");
			Date studentBirthdate;
			Integer studentAge;
			while (true) {
				
				String birthdateInput = addScanner.nextLine();
				SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy");
		        dateFormat.setLenient(false);

		        try {
		            Date userDate = dateFormat.parse(birthdateInput);

		            if (!userDate.after(CURRENT_DATE)) {
		            	studentBirthdate = userDate;
		            	studentAge = calculateAge(studentBirthdate);
		            	break;
		            }
		            System.out.print("[Invalid date. Date must be after the current date]\n"
		            		+ "Try again: ");
		            
		        } catch (ParseException e) {
		            System.out.print("[Invalid date format. Date must be in the format dd-mm-yyyy]\n"
		            		+ "Try again: ");
		        }
			}
			
			// SEX
			System.out.print("Sex (M/F): ");
			String studentSex;
			while (true) {
				
				String sexInput = addScanner.nextLine().substring(0);
				if (sexInput.equalsIgnoreCase("M") || sexInput.equalsIgnoreCase("F")) {
					studentSex = sexInput;
					break;
				}
				System.out.print("[Invalid sex. Must be M or F]\n"
						+ "Try again: ");
			}
			
			// GRADES
			System.out.print("Grades to add: ");
			Integer numOfGrades = addScanner.nextInt();
			addScanner.nextLine();
			
			Double totalOfGrades = 0.0;
			Double[] studentGrades = new Double[numOfGrades];
			for (Integer j = 0; j < numOfGrades; j++) {
				while (true) {
					
					System.out.print("[GRADE #" + (j+1) + "]: ");
					Double grade = addScanner.nextDouble();
					addScanner.nextLine();
					if (grade >= 0 && grade <= 20) {
						studentGrades[j] = grade;
						totalOfGrades += grade;
						break;
					}
					System.out.print("[Invalid grade. Must be between 0 and 20]\n"
							+ "Try again:");
				}
			}
			Double studentAverage = calculateAverage(totalOfGrades, numOfGrades);
			
			// ID
			System.out.print("ID (e.g: 30123890): ");
			String studentId = "";
			while (true) {
				
				String idInput = addScanner.nextLine();
				Integer idLength = idInput.length();
				if (idLength >= 5 && idLength <= 8) {
					studentId = idInput;
					break;
				}
				
				System.out.print("[Invalid ID. ID's must have 5 to 8 numbers]\n"
						+ "Try again: ");
			}
			
			
			students[studentsCount] = new Student(
						studentName,
						studentBirthdate,
						studentSex,
						studentGrades,
						studentId,
						studentAge,
						studentAverage
			);
			
			System.out.println(students[studentsCount].toString());
			studentsCount++;
		}
	}

	public static void showStudentInfo() {
		if (studentsCount <= 0) {
			System.out.println("[No students to show]");
			return;
		}
		
		Scanner detailScanner = new Scanner(System.in);
		
		System.out.println("=== STUDENT DETAILS =================");
		System.out.print("Provide an ID to search for: ");
		String tempId = detailScanner.nextLine().trim();
		Integer index = -1;
		
		for (Integer i = 0; i < studentsCount; i++) {
			if (students[i].id.trim().equals(tempId)) {
				index = i;
				break;
			}
		}
		
		if (index == -1) {
			System.out.println("[ERROR 404. Student not found]");
			return;
		}
		
		Student currentStudent = students[index];
		
		System.out.println("- Name: " + currentStudent.name + "\n"
				+ "- ID: " + currentStudent.id + "\n"
				+ "- Birthday: " + currentStudent.birthdate + "\n"
				+ "- Average: " + currentStudent.average + "\n"
				+ "- Sex: " + currentStudent.sex
		);	
	}
	
	public static void listStudents() {
		System.out.println("=== STUDENT LIST ====================");
		
		if (studentsCount <= 0) {
			System.out.println("[No students to list]");
			return;
		}
		
		for (Integer i = 0; i < studentsCount; i++) {
			Student currentStudent = students[i];
			System.out.println("[V-" + currentStudent.id + "] - " + currentStudent.name);
		}
	}
	
	public static void editStudentInfo() {
		if (studentsCount <= 0) {
			System.out.println("[No students to edit]");
			return;
		}
		
		Scanner editScanner = new Scanner(System.in);
		Scanner optScanner = new Scanner(System.in);
		Integer index = -1;
		
		System.out.println("=== EDIT STUDENT ====================");
		for (Integer i = 0; i < studentsCount; i++) {
			Student currentStudent = students[i];
			
			System.out.println(currentStudent.id + " | " + currentStudent.name);
		}
		
		System.out.print("Provide the ID of the student you want to edit: ");
		String selectedID = editScanner.nextLine();
		for (Integer i = 0; i < studentsCount; i++) {
			Student currentStudent = students[i];
			
			if (currentStudent.id.equals(selectedID)) {
				index = i;
				break;
			}
		}
		
		if (index == -1) {
			System.out.println("[Item not found or incorrect ID]");
			return;
		}
		
		Student currentStudent = students[index];
		
		System.out.print("Select the property to edit:\n"
				+ "1. Name\n"
				+ "2. Sex\n"
				+ "3. Grades\n"
				+ "4. Exit\n"
				+ "Option: ");
		Integer opt = optScanner.nextInt();
		
		Scanner switchScanner = new Scanner(System.in);
		
		switch (opt) {
			case 1:
				while (true) {
					System.out.print("New name: ");
					currentStudent.name = switchScanner.nextLine();					
					break;
				}
				break;
			case 2:
				System.out.print("New sex: ");
				switchScanner.nextLine();
				System.out.println("[You must be kidding, right? No trans students allowed]");
				break;
			case 3:
				System.out.println("Select the grade you want to change: ");
				
				for (Integer i = 0; i < currentStudent.grades.length; i++) {
					System.out.println("[Grade #" + (i+1) + "]: " + currentStudent.grades[i]);
				}
				
				System.out.print("Option: ");
				Integer gradeIndex = 0;
				while (true) {
					gradeIndex = switchScanner.nextInt()-1;
					if (gradeIndex < 0 || gradeIndex > currentStudent.grades.length-1) {
						System.out.print("[Invalid option. Select an index in range]\n"
								+ "Try again:");
					}
					
					break;
				}
					
				System.out.print("New grade: ");
				while (true) {
					Double grade = switchScanner.nextDouble();
					switchScanner.nextLine();
					if (grade < 0 || grade > 20) {
						System.out.print("[Invalid grade. Grades must be in the range of 0 and 20]\n"
								+ "Try again:");
						continue;
					}
					
					currentStudent.grades[gradeIndex] = grade;
					break;
				}
			
				Double totalOfGrades = 0.0;
				for (Integer i = 0; i < currentStudent.grades.length; i++) {
					totalOfGrades += currentStudent.grades[i];
				}
				currentStudent.average = calculateAverage(totalOfGrades, currentStudent.grades.length);
				break;
			case 4:
				System.out.println("[Exiting...]");
				break;
			default:
				System.out.println("[Option not allowed]");
				break;
		}
	}
	
	public static void showStatistics() {
		if (studentsCount <= 0) {
			System.out.println("[No statistics available]");
			return;
		}		
		
		Map<String, Integer> amountOfMenWomen = calculateMenWomen();
		Map<String, Integer> amountOfApprovedFailed = calculateApprovedFailed();
		Map<String, Integer> amountOfMajorsMinors = calculateMajorsMinors();
		Student[] highestAverages = calculateHighestAverages();
		Student[] lowestAverages = calculateLowestAverages();
		Student[] youngest = calculateYoungest();
		Student[] oldest = calculateOldest();
		
		if (studentsCount > 2) {
			System.out.println("=== STATISTICS ======================\n"
					+ "- Amount of students registered: " + studentsCount + "\n"
					+ "- Amount of Men (M): " + amountOfMenWomen.get("M") + " | Women (F): " + amountOfMenWomen.get("F")  + "\n"
					+ "- Amount of Approved: " + amountOfApprovedFailed.get("approved") + " | Failed: " + amountOfApprovedFailed.get("failed") + "\n"
					+ "- Amount of Majors: " + amountOfMajorsMinors.get("majors") + " | Minors: " + amountOfMajorsMinors.get("minors") + "\n"
					+ "- Highest average students: " + highestAverages[0].name + " - " + highestAverages[0].average
						+ " | " + highestAverages[1].name + " - " + highestAverages[1].average + "\n"
					+ "- Lowest average students: " + lowestAverages[0].name + " - " + lowestAverages[0].average
						+ " | " + lowestAverages[1].name + " - " + lowestAverages[1].average + "\n"
					+ "- Oldest students: " + oldest[0].name + " - " + oldest[0].average
						+ " | " + oldest[1].name + " - " + oldest[1].average + "\n"
					+ "- Youngest students: " + youngest[0].name + " - " + youngest[0].average
						+ " | " + youngest[1].name + " - " + youngest[1].average	
			);
		}
		
		System.out.println("=== STATISTICS ======================\n"
				+ "- Amount of students registered: " + studentsCount + "\n"
				+ "- Amount of Men (M): " + amountOfMenWomen.get("M") + " | Women (F): " + amountOfMenWomen.get("F")  + "\n"
				+ "- Amount of Approved: " + amountOfApprovedFailed.get("approved") + " | Failed: " + amountOfApprovedFailed.get("failed") + "\n"
				+ "- Amount of Majors: " + amountOfMajorsMinors.get("majors") + " | Minors: " + amountOfMajorsMinors.get("minors") + "\n"
				+ "- Highest average student: " + highestAverages[0].name + " - " + highestAverages[0].average + "\n"
				+ "- Lowest average student: " + lowestAverages[0].name + " - " + lowestAverages[0].average + "\n"
				+ "- Oldest student: " + oldest[0].name + " - " + oldest[0].average + "\n"
				+ "- Youngest student: " + youngest[0].name + " - " + youngest[0].average
		);
	}
	
	public static void main(String[] args) {
		Boolean flag = true;
		Scanner mainScanner = new Scanner(System.in);
		
        do {
        	SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy hh:mm");
    		String formattedDate = dateFormat.format(new Date());
        	
            System.out.print("=== MENU ============================\n"
            		+ "[Current date: " + formattedDate + "]\n"
                    + "1. Enter X new students\n"
                    + "2. Show student info\n"
                    + "3. Student list\n"
                    + "4. Edit student info\n"
                    + "5. Statistics\n"
                    + "6. [Exit]\n"
                    + "Option: ");
            Integer opt = mainScanner.nextInt();

            switch (opt) {
                case 1: 
                    addStudent();
                    waitForUserInput(mainScanner);
                    break;
                    
                case 2:
                    showStudentInfo();
                    waitForUserInput(mainScanner);
                    break;
                
                case 3: 
                	listStudents();
                	waitForUserInput(mainScanner);
                    break;
                
                case 4: 
                    editStudentInfo();
                    waitForUserInput(mainScanner);
                    break;
                
                case 5: 
                    showStatistics();
                    waitForUserInput(mainScanner);
                    break;
                
                case 6: 
                    System.out.println("[Exiting...]");
                    waitForUserInput(mainScanner);
                    flag = false;
                    break;
                
                default:
                    System.out.println("[Option not allowed]");
                    waitForUserInput(mainScanner);
                    break;
            }
            
            mainScanner.nextLine();
            
        } while (flag);

        mainScanner.close();
    }
}