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

    public void updateColonist() {
        int id = view.readInt("수정할 정착민 번호: ");

        Colonist colonist = repository.findById(id);

        if (colonist == null) {
            view.displayError("해당 번호의 정착민을 찾을 수 없습니다.");
            return;
        }

        String name = view.readString("새 이름: ");
        int age = view.readInt("새 나이: ");
        Job job = view.readJob();
        int skillLevel = view.readInt("새 숙련도(0 ~ 20): ");

        /*
         * 모든 입력값을 검증한 뒤 setter를 호출한다.
         * 검증 전에 일부 값을 수정하면 오류 발생 시 데이터가 일부만 변경될 수 있다.
         */
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

        /*
         * findById()로 가져온 객체는 Repository의 리스트에 저장된 객체와 같다.
         * 따라서 setter로 값을 변경하면 Repository에 저장된 정보도 변경된다.
         *
         * ID와 상태는 이번 수정 대상에 포함하지 않는다.
         */
        colonist.setName(name);
        colonist.setAge(age);
        colonist.setJob(job);
        colonist.setSkillLevel(skillLevel);

        view.displaySuccess(
                id + "번 정착민 정보가 수정되었습니다."
        );
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
    public void searchColonistsByName() {
        String keyword = view.readString("검색할 이름: ");

        if (keyword.isBlank()) {
            view.displayError("검색어는 비워둘 수 없습니다.");
            return;
        }

        List<Colonist> colonists = repository.searchByName(keyword);

        view.displayColonists(colonists);
    }
    public void showColonistsByJob() {
        Job job = view.readJob();

        List<Colonist> colonists = repository.findByJob(job);

        view.displayColonists(colonists);
    }


}
