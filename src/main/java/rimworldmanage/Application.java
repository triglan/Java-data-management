package rimworldmanage;

import rimworldmanage.controller.Controller;
import rimworldmanage.repository.Repository;
import rimworldmanage.view.View;

public class Application {
    public static void main(String[] args) {
        Repository repository = new Repository();
        View view = new View();

        Controller controller = new Controller(repository, view);

        
        while (true) {
            int menu = view.showMainMenu();
            switch (menu) {
                case 1:
                    controller.registerColonist();
                    break;
                case 2:
                    searchMenu(view, controller);
                    break;
                case 3:
                    controller.updateColonist();
                    break;
                case 4:
                    controller.deleteColonist();
                    break;
                case 5:
                    controller.showSettlementStatistics();
                    break;
                case 9:
                    view.displaySuccess("프로그램을 종료합니다.");
                    view.close();
                    return;

                default:
                    view.displayError(
                            "메뉴에 있는 번호를 선택해주세요."
                    );
            }
        }
    }

    /*
     * 조회 메뉴를 반복해서 보여준다.
     * 9번을 선택하면 이 메소드만 종료하여 메인 메뉴로 돌아간다.
     */
    private static void searchMenu(View view, Controller controller) {
        while (true) {
            int menu = view.showSearchMenu();

            switch (menu) {
                case 1:
                    controller.showAllColonists();
                    break;
                case 2:
                    controller.showColonistById();
                    break;
                case 3:
                    controller.searchColonistsByName();
                    break;
                case 4:
                    controller.showColonistsByJob();
                    break;
                case 5:
                    controller.showColonistsByStatus();
                    break;
                case 9:
                    return;
                default:
                    view.displayError("조회 메뉴에 있는 번호를 선택해주세요.");
            }
        }
    }
}
