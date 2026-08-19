package rimworldmanage.model;

import java.time.LocalDate;

public class Colonist {
        private int id;
        private String name;
        private int age;
        private Job job;
        private int skillLevel;
        private Status status;
        private LocalDate joinedDate;

    public Colonist(String name, int age, Job job, int skillLevel, Status status) {
        this.name = name;
        this.age = age;
        this.job = job;
        this.skillLevel = skillLevel;
        this.status = status;
        this.joinedDate = LocalDate.now();
    }

    public LocalDate getJoinedDate() {
        return joinedDate;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }


    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public Job getJob() {
        return job;
    }

    public void setJob(Job job) {
        this.job = job;
    }

    public int getSkillLevel() {
        return skillLevel;
    }

    public void setSkillLevel(int skillLevel) {
        this.skillLevel = skillLevel;
    }

    public Status getStatus() {
        return status;
    }

    public void setStatus(Status status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "Colonist{" +
                "id=" + id +
                ", name='" + name + '\'' +
                ", age=" + age +
                ", job=" + job +
                ", skillLevel=" + skillLevel +
                ", status=" + status +
                ", joinedDate=" + joinedDate +
                '}';
    }
}
