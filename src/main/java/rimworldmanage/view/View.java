package rimworldmanage.view;

import rimworldmanage.model.Colonist;
import rimworldmanage.model.Job;
import rimworldmanage.model.Status;

import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class View {
    private final Scanner scanner = new Scanner(System.in);

    // 메인 메뉴에는 등록, 조회, 수정, 삭제와 같은 큰 기능만 표시한다.
    public int showMainMenu() {
        System.out.println();
        System.out.println("===== 정착민 관리 시스템 =====");
        System.out.println("1. 정착민 등록");
        System.out.println("2. 정착민 조회");
        System.out.println("3. 정착민 정보 수정");
        System.out.println("4. 정착민 삭제");
        System.out.println("5. 정착지 통계");
        System.out.println("9. 프로그램 종료");

        return readInt("메뉴 선택: ");
    }

    /*
     * 조회 기능을 하위 메뉴로 묶어 메인 메뉴가 길어지지 않게 한다.
     * 이 메뉴의 9번은 프로그램 종료가 아니라 이전 메뉴로 돌아가기이다.
     */
    public int showSearchMenu() {
        System.out.println();
        System.out.println("===== 정착민 조회 =====");
        System.out.println("1. 전체 정착민 조회");
        System.out.println("2. 번호로 정착민 조회");
        System.out.println("3. 이름으로 정착민 검색");
        System.out.println("4. 직업별 정착민 조회");
        System.out.println("5. 상태별 정착민 조회");
        System.out.println("9. 이전 메뉴로");

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
            System.out.println("[안내] 조회된 정착민이 없습니다.");
            return;
        }

        System.out.println("\n===== 정착민 목록 =====");

        for (Colonist colonist : colonists) {
            displayColonist(colonist);
        }

        System.out.println("총 " + colonists.size() + "명");
    }

    /*
     * Repository가 계산한 통계 결과를 출력한다.
     * enum의 모든 값을 순회하여 인원이 0명인 직업과 상태도 표시한다.
     */
    public void displayStatistics(
            int totalCount,
            Map<Job, Long> jobCounts,
            Map<Status, Long> statusCounts,
            double averageSkillLevel
    ) {
        System.out.println();
        System.out.println("===== 정착지 통계 =====");
        System.out.println("전체 정착민 : " + totalCount + "명");

        System.out.println();
        System.out.println("직업별 인원");
        for (Job job : Job.values()) {
            System.out.println(
                    job.getDescription() + " : "
                            + jobCounts.getOrDefault(job, 0L) + "명"
            );
        }

        System.out.println();
        System.out.println("상태별 인원");
        for (Status status : Status.values()) {
            System.out.println(
                    status.getDescription() + " : "
                            + statusCounts.getOrDefault(status, 0L) + "명"
            );
        }

        System.out.printf("평균 숙련도 : %.1f%n", averageSkillLevel);
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
        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (!input.isBlank()) {
                return input;
            }

            displayError("한 글자 이상 입력해주세요.");
        }
    }
    public boolean readConfirm(String message) {

        while (true) {
            System.out.print(message);
            String input = scanner.nextLine().trim();

            if (input.equalsIgnoreCase("y")) {
                return true;
            }

            if (input.equalsIgnoreCase("n")) {
                return false;
            }

            displayError("y 또는 n을 입력해주세요.");
        }
    }

    public Status readStatus() {
        Status[] statuses = Status.values();

        while (true) {
            System.out.println();
            System.out.println("===== 상태 선택 =====");

            for (int i = 0; i < statuses.length; i++) {
                System.out.println(
                        (i + 1) + ". " + statuses[i].getDescription()
                );
            }

            int selected = readInt("선택: ");

            /*
             * 사용자 메뉴 번호는 1부터 시작하고
             * 배열 인덱스는 0부터 시작하므로 1을 뺀다.
             */
            if (selected >= 1 && selected <= statuses.length) {
                return statuses[selected - 1];
            }

            displayError(
                    "1 ~ " + statuses.length
                            + " 사이의 번호를 입력해주세요."
            );
        }
    }
    //종료시 Scanner 닫기
    public void close() {
        scanner.close();
    }
}

