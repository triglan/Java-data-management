package rimworldmanage.view;

import rimworldmanage.model.Colonist;
import rimworldmanage.model.Job;

import java.util.List;
import java.util.Scanner;

public class View {
    private final Scanner scanner = new Scanner(System.in);

//전체 선택
    public int showMainMenu(){
        System.out.println("\n===== 정착민 관리 시스템 =====");
        System.out.println("1. 정착민 등록");
        System.out.println("2. 전체 정착민 조회");
        System.out.println("3. 번호로 정착민 조회");
        System.out.println("4. 이름으로 정착민 검색");
        System.out.println("5. 직업별 정착민 조회");
        System.out.println("6. 정착민 정보 수정");
        System.out.println("7. 정착민 삭제");
        System.out.println("9. 프로그램 종료");

        return readInt("메뉴 선택: ");
    }
    // 단일 정착민 출력
    public void displayColonist(Colonist colonist) {

        System.out.println(
                colonist.getId() + " | "
                        + colonist.getName() + " | "
                        + colonist.getAge() + "세 | "
                        + colonist.getJob().getDescription() + " | "
                        + "Lv." + colonist.getSkillLevel() + " | "
                        + colonist.getStatus().getDescription()
        );
    }
    //전체 정착민 출력
    public void displayColonists(List<Colonist> colonists) {

        if (colonists.isEmpty()) {
            System.out.println("[안내] 등록된 정착민이 없습니다.");
            return;
        }

        System.out.println("\n===== 정착민 목록 =====");

        for (Colonist colonist : colonists) {
            displayColonist(colonist);
        }

        System.out.println("총 " + colonists.size() + "명");
    }

//직업 선택




// 성공 오류 메시지
    public void displayError(String message) {
        System.out.println("[오류] " + message);
    }
    public void displaySuccess(String message) {
        System.out.println("[완료] " + message);
    }
    public void displayMessage(String message) {
        System.out.println("[안내] " + message);
    }

    //입력
    public Job readJob() {

        Job[] jobs = Job.values();

        while (true) {

            System.out.println("\n===== 직업 선택 =====");

            for (int i = 0; i < jobs.length; i++) {
                System.out.println((i + 1) + ". " + jobs[i].getDescription());
            }

            int selected = readInt("선택: ");

            if (selected >= 1 && selected <= jobs.length) {
                return jobs[selected - 1];
            }

            System.out.println(
                    "[오류] 1 ~ " + jobs.length + " 사이의 번호를 입력해주세요."
            );
        }
    }
    public int readInt(String message){
        while(true){
            System.out.println(message);
            String input = scanner.nextLine();

            try {
                return Integer.parseInt(input);
            }catch (NumberFormatException e){
                System.out.println("[ERROR] 숫자를 입력해 주세요.");
            }
        }
    }
    public String readString(String message) {

        System.out.print(message);

        return scanner.nextLine();
    }
}

