package rimworldmanage.repository;

import rimworldmanage.model.Colonist;
import rimworldmanage.model.Job;
import rimworldmanage.model.Status;

import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

//정착민 관리
public class Repository {
    //크기가 정해지지 않은 배열
    private final List<Colonist> colonists = new ArrayList<>();
    private int nextColID = 1;

    public void save(Colonist colonist){
        colonist.setId(nextColID++);
        colonists.add(colonist);
    }

    public void deleteById(int id) {
        colonists.removeIf(colonist -> colonist.getId() == id);
    }

    public Repository() {
        save(new Colonist("알파", 28, Job.CONSTRUCTION, 12, Status.HEALTHY));
        save(new Colonist("베타", 35, Job.COOKING, 1, Status.MENTAL_BREAK));
        save(new Colonist("감마", 16, Job.CONSTRUCTION, 4, Status.INJURED));
        save(new Colonist("델타", 45, Job.MEDICAL, 7, Status.HEALTHY));
        save(new Colonist("오메가", 7, Job.MINING, 10, Status.SICK));

    }

    public List<Colonist> findAll(){
        return new ArrayList<>(colonists);
    }

    public Colonist findById(int id){
        return colonists.stream()
                .filter(colonist -> colonist.getId() == id)
                .findFirst()
                .orElse(null);
    }

    public List<Colonist> searchByName(String keyword) {
        String lowerKeyword = keyword.trim().toLowerCase();

        return colonists.stream()
                .filter(colonist ->
                        colonist.getName()
                                .toLowerCase()
                                .contains(lowerKeyword)
                )
                .toList();
    }

    public List<Colonist> findByJob(Job job) {
        return colonists.stream()
                .filter(colonist -> colonist.getJob() == job)
                .toList();
    }

    public List<Colonist> findByStatus(Status status) {
        return colonists.stream()
                .filter(colonist -> colonist.getStatus() == status)
                .toList();
    }

    // 전체 인원은 별도의 복사본을 만들지 않고 저장소가 직접 계산한다.
    public int getTotalCount() {
        return colonists.size();
    }

    /*
     * groupingBy()로 직업이 같은 정착민을 묶고,
     * counting()으로 각 직업의 정착민 수를 계산한다.
     */
    public Map<Job, Long> countByJob() {
        return colonists.stream()
                .collect(Collectors.groupingBy(
                        Colonist::getJob,
                        Collectors.counting()
                ));
    }

    // 상태가 같은 정착민을 묶어 상태별 인원을 계산한다.
    public Map<Status, Long> countByStatus() {
        return colonists.stream()
                .collect(Collectors.groupingBy(
                        Colonist::getStatus,
                        Collectors.counting()
                ));
    }

    /*
     * averagingInt()로 모든 정착민의 숙련도 평균을 계산한다.
     * 정착민이 한 명도 없으면 0.0을 반환한다.
     */
    public double getAverageSkillLevel() {
        return colonists.stream()
                .collect(Collectors.averagingInt(
                        Colonist::getSkillLevel
                ));
    }
    
}
