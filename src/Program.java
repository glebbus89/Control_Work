import java.text.ParseException;
import java.util.Scanner;

public class Program {
    public static void main(String[] args) throws ParseException {

        AccountingSystem accountingSystem = new AccountingSystem();
        Counter counter = new Counter();
        try (Scanner scanner = new Scanner(System.in)) {
            String option = "";

            while (true) {
                System.out.println(
                        "\nЗдравствуйте, введите команду:\n1. Завести нового животного \n2. Список животных питомника \n3. Команды животного \n4. Обучить командам\n5. Выход\n");
                System.out.print("Введите цифру (от 1 до 5): ");
                option = scanner.nextLine();

                switch (option) {
                    case "1":
                        try {
                            System.out.print("Введите вид животного(например, Cat,Dog,Donkey,Horse,Camel,Humster): ");
                            String type = scanner.nextLine().toLowerCase();
                            System.out.print("Введите имя(кличку) животного: ");
                            String name = scanner.nextLine();
                            System.out.print("Введите дату рождения животного (в формате дд.мм.гггг): ");
                            String strDate = scanner.nextLine();
                            System.out.print("Введите команды, которые умеет делать животное, через запятую(например, Walk, Run, Jump): ");
                            String commands = scanner.nextLine().toLowerCase().replaceAll("\\s", "");
                            if (type.isEmpty() || name.isEmpty() || strDate.isEmpty() || commands.isEmpty()) {
                                throw new Exception("Ошибка, животное не занесено в базу! Попробуйте заново");
                            }
                            accountingSystem.getAnimal(type, name, strDate, commands);
                            counter.add();
                            counter.printCounter();
                            System.out.println("Животное успешно занесено в базу питомника.");
                        } catch (Exception e) {
                            System.out.println(e.getMessage());
                        }

                        break;
                    case "2":
                        System.out.println("Список животных питомника:");
                        accountingSystem.showAllAnimals();
                        break;
                    case "3":
                        System.out.print("Введите id животного: ");
                        int id = Integer.parseInt(scanner.nextLine());
                        System.out.println("Команды, которые исполняет животное:");
                        accountingSystem.showCommands(id);
                        break;
                    case "4":
                        System.out.print("Введите id животного: ");
                        int id_2 = Integer.parseInt(scanner.nextLine());
                        System.out.print("Введите команды, которым обучили, через запятую (например, Walk, Run, Jump): ");
                        String command = scanner.nextLine().toLowerCase().replaceAll("\\s", "");
                        accountingSystem.learnCommand(id_2, command);
                        System.out.println("Животное успешно обучено командам.");
                        break;
                    case "5":
                        accountingSystem.saveToJSON();
                        System.out.println("Данные сохранены в файл JSON.\n");
                        System.exit(1);
                        break;
                    default:
                        System.out.println("Введите команду от 1 до 5.");
                        break;
                }
            }
        } catch (NumberFormatException e) {
            e.printStackTrace();
        }
    }
}