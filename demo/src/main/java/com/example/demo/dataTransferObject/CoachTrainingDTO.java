package com.example.demo.dataTransferObject;
import java.util.Date;

public class CoachTrainingDTO {
    private Long id;

    private String name;

    private Integer salary;

    private String sport;

    private Date birthDate;

    private String gender;

    private String phone;

    private Long trainingCount;

    public CoachTrainingDTO() {
    }


    public CoachTrainingDTO(Long id, String name, Integer salary, String sport,Date birthDate, String gender, String phone, Long trainingCount) {
        this.id = id;
        this.name = name;
        this.salary = salary;
        this.sport = sport;
        this.birthDate = birthDate;
        this.gender = gender;
        this.phone = phone;
        this.trainingCount = trainingCount;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Integer getSalary() {
        return salary;
    }

    public void setSalary(Integer salary) {
        this.salary = salary;
    }

    public Date getBirthDate() {
        return birthDate;
    }

    public void setBirthDate(Date birthDate) {
        this.birthDate = birthDate;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Long getTrainingCount() {
        return trainingCount;
    }

    public void setTrainingCount(Long trainingCount) {
        this.trainingCount = trainingCount;
    }

    public String getSport() {
        return sport;
    }

    public void setSport(String sport) {
        this.sport = sport;
    }

    @Override
    public String toString() {
        return "CoachTrainingDTO{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", salary=" + salary +
                ", sport=" + sport + '\''+
                ", birthDate=" + birthDate +
                ", gender='" + gender + '\'' +
                ", phone='" + phone + '\'' +
                ", trainingCount=" + trainingCount +
                '}';
    }
}
