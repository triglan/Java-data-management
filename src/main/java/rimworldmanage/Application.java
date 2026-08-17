package rimworldmanage;

import rimworldmanage.controller.Controller;
import rimworldmanage.model.Colonist;
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
                    controller.showAllColonists();
                    break;
                case 3:
                    controller.showColonistById();
                    break;
                case 9:
                    view.displaySuccess("프로그램을 종료합니다.");
                    return;

                default:
                    view.displayError(
                            "메뉴에 있는 번호를 선택해주세요."
                    );
            }
        }
    }
}
