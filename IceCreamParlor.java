import java.io.*;
import java.util.ArrayList;
import java.util.List;
import java.util.stream.IntStream;
import java.util.stream.Stream;

import static java.util.stream.Collectors.joining;
import static java.util.stream.Collectors.toList;

class Result {

    /*
     * Complete the 'icecreamParlor' function below.
     *
     * The function is expected to return an INTEGER_ARRAY.
     * The function accepts following parameters:
     *  1. INTEGER m
     *  2. INTEGER_ARRAY arr
     */

    public static List<Integer> icecreamParlor(int m, List<Integer> arr) {
        int arrLen = arr.size();
        List<Integer> result1 = new ArrayList<Integer>();
        List<Integer> result2 = new ArrayList<Integer>();


        List<Integer> keep = icecreamParlor(m, arr);
        result1.addAll(keep);
        List<Integer> skip = icecreamParlor(m, arr.subList(1, arrLen));
        result2.addAll(skip);


        if (result1.size() == 3 && result2.size() == 3) {
            int sum1 = 0;
            int sum2 = 0;
            for (int i = 0; i < 3; i++) {
                sum1 = sum1 + result1.get(i);
                sum2 = sum2 + result2.get(i);
            }
            if (sum1 > sum2) {
                return result1;
            } else {
                return result2;
            }
        } else if (result1.size() == 3) {
            return result1;
        } else if (result2.size() == 3) {
            return result2;
        } else {
            return result1;
        }

        return result;

    }

    public static List<Integer> icecreamHelper(int m, List<Integer> arr) {
        int val = arr.get(0);
        int remainder = m - val;
        if (remainder < 0) {
            return icecreamHelper(m, arr.subList(1, arr.size()));
        }
        return arr;
    }

}

public class Solution {
    public static void main(String[] args) throws IOException {
        BufferedReader bufferedReader = new BufferedReader(new InputStreamReader(System.in));
        BufferedWriter bufferedWriter = new BufferedWriter(new FileWriter(System.getenv("OUTPUT_PATH")));

        int t = Integer.parseInt(bufferedReader.readLine().trim());

        IntStream.range(0, t).forEach(tItr -> {
            try {
                int m = Integer.parseInt(bufferedReader.readLine().trim());

                int n = Integer.parseInt(bufferedReader.readLine().trim());

                List<Integer> arr = Stream.of(bufferedReader.readLine().replaceAll("\\s+$", "").split(" "))
                        .map(Integer::parseInt)
                        .collect(toList());

                List<Integer> result = Result.icecreamParlor(m, arr);

                bufferedWriter.write(
                        result.stream()
                                .map(Object::toString)
                                .collect(joining(" "))
                                + "\n"
                );
            } catch (IOException ex) {
                throw new RuntimeException(ex);
            }
        });

        bufferedReader.close();
        bufferedWriter.close();
    }
}
