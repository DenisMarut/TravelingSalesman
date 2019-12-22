package travelingSalesman.project;


import java.io.File;
import java.util.*;



public class MainApp {

    public static void main(String[] args) {
        //Wczytanie pliku
        String fileName = "berlin52.txt";
        File theFile = new File(fileName);

        //Zapisanie do tablicy (wraz ze symetria)
        List<String> result = new ArrayList<>();
        int[][] array = new int[][]{};

        try (Scanner sc = new Scanner(theFile)) {
            while (sc.hasNext()) {
                result.add(sc.nextLine());
            }
            int max = Integer.parseInt(result.get(0).trim());
            array = new int[max][max];
            for (int i = 1; i < result.size(); i++) {
                String[] tmp = result.get(i).trim().split(" ");
                for (int j = 0; j < tmp.length; j++) {
                    array[i - 1][j] = Integer.parseInt(tmp[j]);
                    array[j][i - 1] = array[i - 1][j];
                }
            }

            System.out.println("Size of array is equal to: " + array[0].length + " " + array[1].length);
            int a = 17;
            int b = 15;
            System.out.println("Element at index " + a + " " + b + " is equal to:" + array[a][b]);
            System.out.println("Element at index " + b + " " + a + " is equal to:" + array[b][a]);


        } catch (Exception ex) {
            ex.printStackTrace();
        }

        int maxIndex = array[0].length;
        //TODO
        //TODO        potem sa liczone odleglosci - pomiedzy danymi indeksami 2->1->3->0 itd.
        //TODO        od 2->1 = 15, 1->3 = 45 itd. 15+45
        //TODO osobnik[2][1][3][0][5][7][4]   - nie moze sie indeks powtorzyc

        List<Integer> osobnikZSamymiIndexami = new ArrayList<>();
        List<Integer> sequence = sequence(0, 51);
        Collections.shuffle(sequence);
        System.out.println(sequence);




//        }
//        List<Integer> indexy = new ArrayList<>();
//        for (int z = 0; z < 51; z++){
//            int i = rndRange(maxIndex);
//            numbers[i] = numbers[i -1];
//            maxIndex--;
//            indexy.add(i);
//        }
//
//        System.out.println(indexy);

//        //Wybor 40 osobników (kazdy posiada 52 miasta)
//        List<HashSet<Integer>> listOf40Elements = new ArrayList<>();
//        HashSet<Integer> numbers = new HashSet<>();
//        System.out.println(maxIndex + " Max index");
//        List<Integer> unitMarks = new ArrayList<>();
//
//        for (int i = 0; i < 40; i++) {
//            numbers = new HashSet<>();
//            while (numbers.size() < maxIndex) {
//                int elem = array[rndRange(52)][rndRange(52)];
//                if (elem == 0) {
//                    numbers.add(elem);
//                    numbers.remove(elem);
//                } else {
//                    numbers.add(elem);
//                }
//            }
//            //Zapisanie do listy  kazdego osobnika jako HashSet oraz ocene kazdego osobnika jako druga lista (zawierajacego tylko oceny)
//            Integer integer = numbers.stream().mapToInt(Integer::intValue).sum();
//            unitMarks.add(integer);
//            listOf40Elements.add(numbers);
//        }
//
//        for (HashSet<Integer> hashset : listOf40Elements
//        ) {
//            System.out.println(hashset);
//        }
//
//        for (Integer unitSum: unitMarks
//             ) {
//            System.out.println(unitSum);
//        }

//        System.out.println("\n" + unitMarks);

        //TODO Selekcja
        //TODO Krzyzowanie i mutacja
        
    }
     public static List<Integer> sequence(int begin, int end){
        List<Integer> ret = new ArrayList<>(end-begin+1);
        for(int i = begin; i <= end; i++){
            ret.add(i);
        }
        return ret;
     }

    public static int rndRange(int finish) {
        return new Random().nextInt(finish);
    }


}
