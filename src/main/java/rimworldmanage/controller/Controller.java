package rimworldmanage.controller;

import rimworldmanage.model.Colonist;
import rimworldmanage.model.Job;
import rimworldmanage.model.Status;
import rimworldmanage.repository.Repository;
import rimworldmanage.view.View;

import java.util.List;


public class Controller {
    private final Repository repository;
    private final View view;

    public Controller(Repository repository, View view) {
        this.repository = repository;
        this.view = view;
    }

    //등록
    public void registerColonist() {

        String name = view.readString("이름: ");
        int age = view.readInt("나이: ");
        Job job = view.readJob();
        int skillLevel = view.readInt("숙련도(0 ~ 20): ");

        if (name.isBlank()) {
            view.displayError("이름은 비워둘 수 없습니다.");
            return;
        }

        if (age <= 0) {
            view.displayError("나이는 1 이상이어야 합니다.");
            return;
        }

        if (skillLevel < 0 || skillLevel > 20) {
            view.displayError("숙련도는 0 ~ 20 사이여야 합니다.");
            return;
        }

        Colonist colonist = new Colonist(name, age, job, skillLevel, Status.HEALTHY);

        repository.save(colonist);

        view.displaySuccess(colonist.getId() + "번 정착민 " + colonist.getName() + "이 등록되었습니다.");
    }
    //전체 조회
    public void showAllColonists() {

        List<Colonist> colonists = repository.findAll();

        view.displayColonists(colonists);
    }
    //번호 조회
    public void showColonistById() {

        int id = view.readInt("조회할 정착민 번호: ");

        Colonist colonist = repository.findById(id);

        if (colonist == null) {
            view.displayError("해당 번호의 정착민을 찾을 수 없습니다.");
            return;
        }

        view.displayColonist(colonist);
    }

}
