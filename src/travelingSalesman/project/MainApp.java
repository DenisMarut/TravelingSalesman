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

        List<List<Integer>> collectionWithSubjects = new ArrayList<>();

        for (int i = 0; i < 40; i++) {
            List<Integer> sub = new ArrayList<>();
            sub = sequence(0,51);
            Collections.shuffle(sub);
            collectionWithSubjects.add(sub);
        }
        for (List<Integer> osobniki: collectionWithSubjects
             ) {
            System.out.println(osobniki);
        }

        List<Integer> osobnikZSamymiIndexami = new ArrayList<>();
        List<Integer> sequence = sequence(0, 51);
        Collections.shuffle(sequence);
        System.out.println(sequence);

        List<Integer> ocnaOsobnika = new ArrayList<>();
        for(int i = 0; i < 51; i++){

        }






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
