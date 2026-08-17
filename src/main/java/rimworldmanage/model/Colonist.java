package rimworldmanage.model;

public class Colonist {
        private int id;
        private String name;
        private int age;
        private Job job;
        private int skillLevel;
        private Status status;

    public Colonist(int id, String name, int age, Job job, int skillLevel, Status status) {
        this.id = id;
        this.name = name;
        this.age = age;
        this.job = job;
        this.skillLevel = skillLevel;
        this.status = status;
    }

    //set id는 없음.
    
    public int getId() {
        return id;
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
}
