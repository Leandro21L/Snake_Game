import co.edu.uptc.model.Food;
import co.edu.uptc.view.MainFrame;

public class Main {
    public static void main(String[] args) {
        //MainFrame mainFrame = new MainFrame();
        Food food = new Food(3, 0, 0);
        Thread thread = new Thread(food);
        food.setPlaying(true);

        thread.start();
    }
}
