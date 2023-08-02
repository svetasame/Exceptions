import java.io.FileWriter;
import java.io.IOException;
import java.sql.SQLOutput;
import java.util.Scanner;



// Press Shift twice to open the Search Everywhere dialog and type `show whitespaces`,
// then press Enter. You can now see whitespace characters in your code.
public class Main {
    public static void main(String[] args) {
        try {
            String input = getInput();
            String[] data = input.split(" ");
            checkSizeData(data);
            checkData(data);
            writeFile(data);
            System.out.println("Данные успешно записаны в файл");
        } catch (InvalidDataException e) {
            System.out.println(e.getMessage());
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public static String getInput (){
        Scanner scanner = new Scanner(System.in);
        System.out.println("Введите данные через пробел (ФИО, дата рождения, телефон, пол) в любом порядке: ");
        return scanner.nextLine();
    }

    public static void checkSizeData(String[] data) throws SizeException{
        if (data.length != 6) {
            throw new SizeException(data.length);
        }
    }

    public static void checkData (String[] data) throws InvalidDataException{
        String lastName = data[0];
        String firstName = data[1];
        String middleName = data[2];
        String dateOfBirth = data[3];
        String phone = data[4];
        String gender = data[5];

        if (!dateOfBirth.matches("\\d{2}\\.\\d{2}\\.\\d{4}")){
            throw new InvalidDataException("Неверный формат даты рождения");
        }

        try {
            Long.parseLong(phone);
        } catch (NumberFormatException e) {
            throw new InvalidDataException("Неверный формат номера телефона");
        }
        if (phone.length() < 11) {
            throw new InvalidDataException("Неверный формат номера телефона, некорректное количество цифр");
        }

        if(!gender.equals("m")&&!gender.equals("f")) {
            throw new InvalidDataException("Неверное значение пола");
        }

        if(!checkName(middleName) || !checkName(lastName) || !checkName(firstName)) {
            throw new InvalidDataException("Неверный формат ФИО");
        }

    }

    public static void writeFile (String [] data) throws IOException {
        String lastName = data[0];
        String fileName = lastName +".txt";
        FileWriter wr = new FileWriter(fileName,true);

        try{
            StringBuilder stringBuilder = new StringBuilder();
            stringBuilder.append(data[0]+" ");
            stringBuilder.append(data[1]+" ");
            stringBuilder.append(data[2]+" ");
            stringBuilder.append(data[3]+" ");
            stringBuilder.append(data[4]+" ");
            stringBuilder.append(data[5]+" ");
            stringBuilder.append(System.lineSeparator());

            wr.write(stringBuilder.toString());
        } finally {
            wr.close();
        }
    }

    public static boolean checkName(String str) {
        return str.matches("[a-zA-Z]+");
    }




}