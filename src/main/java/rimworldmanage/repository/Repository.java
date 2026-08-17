package rimworldmanage.repository;

import rimworldmanage.model.Colonist;
import rimworldmanage.model.Job;
import rimworldmanage.model.Status;

import java.util.ArrayList;
import java.util.List;

public class Repository {
    //크기가 정해지지 않은 배열
    private final List<Colonist> colonists = new ArrayList<>();
    private int nextColID = 1;

    public void save(Colonist colonist){
        colonist.setId(nextColID++);
        colonists.add(colonist);
    }

    public Repository() {
        save(new Colonist("알파", 28, Job.CONSTRUCTION, 12, Status.HEALTHY));
        save(new Colonist("베타", 35, Job.COOKING, 1, Status.INJURED));
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



    
}
