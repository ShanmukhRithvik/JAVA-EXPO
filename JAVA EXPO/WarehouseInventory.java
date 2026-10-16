import java.util.*;
import java.nio.file.*;
import java.util.stream.*;

class WarehouseItem {
    private String name;
    private int stock;

    WarehouseItem(String name, int stock) {
        this.name = name;
        this.stock = stock;
    }

    public String getName() { return name; }
    public int getStock() { return stock; }

    public void reorder(int qty) {
        if (stock < 15) stock += qty;
    }

    public String toString() {
        return name + ":" + stock;
    }
}

public class WarehouseInventory {
    // Recursion example
    static int fact(int n) {
        return (n <= 1) ? 1 : n * fact(n - 1);
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);
        List<WarehouseItem> list = new ArrayList<>();
        Map<String, Integer> map = new HashMap<>();

        try {
            // Module 1 & 2: Input + Loop + Switch
            System.out.print("How many items? ");
            int n = sc.nextInt();
            sc.nextLine();

            for (int i = 0; i < n; i++) {
                System.out.print("Name stock: ");
                String line = sc.nextLine();
                String[] p = line.split(" ");
                String name = p[0];
                int stock = Integer.parseInt(p[1]);

                list.add(new WarehouseItem(name, stock));
                map.put(name, stock);
            }

            // Module 3: Array + for-each + method
            System.out.println("\nAll items:");
            for (WarehouseItem i : list) {
                System.out.println(i);
                i.reorder(20);          // reorder if low
            }

            // Module 6: Stream – filter low stock
            System.out.println("\nLow stock:");
            list.stream()
                .filter(i -> i.getStock() < 15)
                .forEach(System.out::println);

            // Module 5: Sort + File I/O
            list.sort(Comparator.comparing(WarehouseItem::getStock));
            Path file = Paths.get("inventory.txt");
            Files.write(file, list.stream()
                                  .map(WarehouseItem::toString)
                                  .collect(Collectors.toList()));

            System.out.println("\nSaved to file. Fact(5) = " + fact(5));

        } catch (Exception e) {
            System.out.println("Error: " + e.getMessage());
        } finally {
            sc.close();
            System.out.println("Done.");
        }
    }
}

