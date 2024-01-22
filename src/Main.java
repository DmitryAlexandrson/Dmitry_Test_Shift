import java.io.*;
import java.nio.file.Path;
import java.util.ArrayList;

public class Main {

    public static boolean isNumeric(String s) {
        try {
            Long.parseLong(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static boolean isDouble(String s) {
        try {
            Double.parseDouble(s);
            return true;
        } catch (NumberFormatException e) {
            return false;
        }
    }

    public static long maxLong(ArrayList<Object> arrayList) {
        long max = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            if (Long.parseLong(arrayList.get(i).toString()) > max) max = Long.parseLong(arrayList.get(i).toString());
        }
        return max;
    }

    public static long minLong(ArrayList<Object> arrayList) {
        long min = Long.parseLong(arrayList.get(0).toString());
        for (int i = 0; i < arrayList.size(); i++) {
            if (Long.parseLong(arrayList.get(i).toString()) < min) min = Long.parseLong(arrayList.get(i).toString());
        }
        return min;
    }

    public static long sumLong(ArrayList<Object> arrayList) {
        long sum = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            sum += Long.parseLong(arrayList.get(i).toString());
        }
        return sum;
    }

    public static long averageLong(ArrayList<Object> arrayList) {
        long average = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            average = sumLong(arrayList) / arrayList.size();
        }
        return average;
    }

    public static double maxDouble(ArrayList<Object> arrayList) {
        double maxD = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            if (Double.parseDouble(arrayList.get(i).toString()) > maxD)
                maxD = Double.parseDouble(arrayList.get(i).toString());
        }
        return maxD;
    }

    public static double minDouble(ArrayList<Object> arrayList) {
        double minD = Double.parseDouble(arrayList.get(0).toString());
        for (int i = 0; i < arrayList.size(); i++) {
            if (Double.parseDouble(arrayList.get(i).toString()) < minD)
                minD = Double.parseDouble(arrayList.get(i).toString());
        }
        return minD;
    }

    public static double sumDouble(ArrayList<Object> arrayList) {
        double sumD = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            sumD += Double.parseDouble(arrayList.get(i).toString());
        }
        return sumD;
    }

    public static double averageDouble(ArrayList<Object> arrayList) {
        double average = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            average = sumDouble(arrayList) / arrayList.size();
        }
        return average;
    }

    public static int longestString(ArrayList<Object> arrayList) {
        int longestString = 0;
        for (int i = 0; i < arrayList.size(); i++) {
            if (String.valueOf(arrayList.get(i)).length() > longestString)
                longestString = String.valueOf(arrayList.get(i)).length();
        }
        return longestString;
    }

    public static int shortestString(ArrayList<Object> arrayList) {
        int shortestString = String.valueOf(arrayList.get(0)).length();
        for (int i = 0; i < arrayList.size(); i++) {
            if (String.valueOf(arrayList.get(i)).length() < shortestString)
                shortestString = String.valueOf(arrayList.get(i)).length();
        }
        return shortestString;
    }

    public static BufferedWriter writerToFile(String nameFile, String[] array, ArrayList<Object> list) throws IOException {
        BufferedWriter bufferedWriter;
        String argsStr = "";
        for (String s : array) argsStr += s + " ";
        if (argsStr.contains("a")) {
            bufferedWriter = new BufferedWriter(new FileWriter(nameFile, true));
        } else {
            bufferedWriter = new BufferedWriter(new FileWriter(nameFile));
        }
        for (Object o : list) bufferedWriter.write(o + "\n");
        bufferedWriter.close();
        return bufferedWriter;
    }

    public static void main(String[] args) {
        ArrayList<Object> ints = new ArrayList<>();
        ArrayList<Object> floats = new ArrayList<>();
        ArrayList<Object> strings = new ArrayList<>();
        ArrayList<String> fileNames = new ArrayList<>();

        String prefix = "";
        String path = "";
        for (int i = 0; i < args.length; i++) {
            if (args[i].contains(".txt")) fileNames.add(args[i]);
            if (args[i].contains("-p")) prefix = args[i + 1];
        }
        if (fileNames.size() == 0) System.out.println("Вы не ввели ни одного файла");
        for (int i = 0; i < args.length; i++) {
            if (args[i].contains("-o")) path = args[i + 1];
            else {
                if (fileNames.size() != 0) {
                    Path file = Path.of(fileNames.get(0));
                    String pathFile = file.toAbsolutePath().toString();
                    int index = pathFile.lastIndexOf("\\");
                    path = pathFile.substring(0, index + 1);
                }
            }
        }
        String line;
        int intCount = 0;
        int stringCount = 0;
        int floatCount = 0;
        try {
            for (String s : fileNames) {
                BufferedReader readerFromFile = new BufferedReader(new FileReader(s));
                while ((line = readerFromFile.readLine()) != null) {
                    if (isNumeric(line)) {
                        ints.add(Long.parseLong(line));
                        intCount++;
                    } else if (isDouble(line)) {
                        floats.add(Double.parseDouble(line));
                        floatCount++;
                    } else {
                        strings.add(line);
                        stringCount++;
                    }
                }
                readerFromFile.close();
            }
            writerToFile(path + prefix + "integers.txt", args, ints);
            writerToFile(path + prefix + "floats.txt", args, floats);
            writerToFile(path + prefix + "strings.txt", args, strings);
        } catch (FileNotFoundException e) {
            System.out.println("Синтаксическая ошибка в имени файла, имени папки или метке тома. Пожалуйста, попробуйте еще раз");
        } catch (IOException e) {
            e.printStackTrace();
        }
        for (String argument : args) {
            if (argument.equals("-s")) {
                System.out.println("Количество целых чисел: " + intCount);
                System.out.println("Количество вещественных чисел: " + floatCount);
                System.out.println("Количество строк: " + stringCount);
            }
            if (argument.equals("-f")) {
                System.out.println("СТАТИСТИКА ПО ЦЕЛЫМ ЧИСЛАМ:" + "\n" +
                        "Всего чисел: " + intCount + "\n" +
                        "Максимальное значение: " + maxLong(ints) + "\n" +
                        "Минимильное значение: " + minLong(ints) + "\n" +
                        "Сумма: " + sumLong(ints) + "\n" +
                        "Среднее значение: " + averageLong(ints) + "\n");
                System.out.println("СТАТИСТИКА ПО ВЕЩЕСТВЕННЫМ ЧИСЛАМ:" + "\n" +
                        "Всего чисел: " + floatCount + "\n" +
                        "Максимальное значение: " + maxDouble(floats) + "\n" +
                        "Минимильное значение: " + minDouble(floats) + "\n" +
                        "Сумма: " + sumDouble(floats) + "\n" +
                        "Среднее значение: " + averageDouble(floats) + "\n");
                System.out.println("СТАТИСТИКА ПО СТРОКАМ:" + "\n" +
                        "Всего строк :" + stringCount + "\n" +
                        "Размер самой длинной строки: " + longestString(strings) + "\n" +
                        "Размер самой короткой строки: " + shortestString(strings) + "\n");
            }
        }
    }
}