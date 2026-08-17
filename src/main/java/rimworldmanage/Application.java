package rimworldmanage;

import rimworldmanage.model.Colonist;
import rimworldmanage.repository.Repository;

public class Application {
    public static void main(String[] args) {
        Repository repository = new Repository();

        System.out.println(repository.findAll().size());

        Colonist colonist = repository.findById(3);

        System.out.println(colonist.getName());
    }
}
