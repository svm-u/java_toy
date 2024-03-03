import java.util.PriorityQueue;
import java.io.FileWriter;
import java.io.IOException;

public class store {
    public static void main(String[] args) {
        PriorityQueue<Toy> toys = new PriorityQueue<>((t1, t2) -> t2.getUseToy() - t1.getUseToy());
        addToy(toys, "1 2 конструктор");
        addToy(toys, "2 2 робот");
        addToy(toys, "3 6 кукла");
        try {
            FileWriter writer = new FileWriter("file.txt");
            for (int i = 0; i < 10; i++) {
                int toy_id = getToy(toys);
                writer.write(Integer.toString(toy_id) + "\n");
                System.out.println(toy_id);
            }
            writer.close();
            System.out.println("Результаты сохранены");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
    public static void addToy(PriorityQueue<Toy> toys, String toyString) {
        String[] parts = toyString.split(" ");
        int id = Integer.parseInt(parts[0]);
        int useToy = Integer.parseInt(parts[1]);
        String name = parts[2];
        Toy toy = new Toy(id, name, useToy);
        toys.add(toy);
    }
    public static int getToy(PriorityQueue<Toy> toys) {
        int UseToys = toys.stream().mapToInt(Toy::getUseToy).sum();
        int randomNum = (int) (Math.random() * UseToys) + 1;
        int sum = 0;
        for (Toy toy : toys) {
            sum += toy.getUseToy();
            if (randomNum <= sum) {
                return toy.getId();
            }
        }
        return -1; 
    }
}
class Toy {
    int id;
    String name;
    int useToy;

    public Toy(int id, String name, int useToy) {
        this.id = id;
        this.name = name;
        this.useToy = useToy;
    }
    public int getId() {
        return id;
    }
    public String getName() {
        return name;
    }
    public int getUseToy() {
        return useToy;
    }
}