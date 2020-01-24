package travelingSalesman.project;


import java.io.File;
import java.util.*;
import java.util.stream.Collectors;


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
//            System.out.println("Size of array is equal to: " + array[0].length + " " + array[1].length);
//            int a = 17;
//            int b = 16;
//            System.out.println("Element at index " + a + " " + b + " is equal to:" + array[a][b]);
//            System.out.println("Element at index " + b + " " + a + " is equal to:" + array[b][a]);

        } catch (Exception ex) {
            ex.printStackTrace();
        }
        //--------------------//
        int mutationParam = 5;
        int crossOverParam = 95;
        int tournamentParticipants = 3;
        int iterations = 5000;
        int numberOfSubject = 40;
        //--------------------//
        int maxIndex = array[0].length;
        System.out.println("Wymiar macierzy: " + maxIndex + "x" + maxIndex);
        System.out.println("\n");

        //TODO        potem sa liczone odleglosci - pomiedzy danymi indeksami 2->1->3->0 itd.
        //TODO        od 2->1 = 15, 1->3 = 45 itd. 15+45
        //TODO osobnik[2][1][3][0][5][7][4]   - nie moze sie indeks powtorzyc

        List<List<Integer>> collectionWithSubjects = new ArrayList<>();

        for (int i = 0; i < numberOfSubject; i++) {
            List<Integer> sub = new ArrayList<>();
            sub = sequence(0, maxIndex - 1);
            Collections.shuffle(sub);
            collectionWithSubjects.add(sub);
        }
        //TODO Ocena osobników

        List<Integer> sumsAtStart = new ArrayList<>();

        for (List<Integer> subject : collectionWithSubjects
        ) {
            int judgeResult = judge(subject, array);
            sumsAtStart.add(judgeResult);
                System.out.println(subject + " " + judgeResult);
        }
        int ilosctur = 0;
        for (int tury = 0; tury < iterations; tury++) {
            List<Integer> sums = new ArrayList<>();

            for (List<Integer> subject : collectionWithSubjects
            ) {
                int judgeResult = judge(subject, array);
                sums.add(judgeResult);
//                System.out.println(subject + " " + judgeResult);
            }
//            System.out.println("\n \n \n");

            //TODO Selekcja Turniejowa

            List<List<Integer>> tournamentPopulation = new ArrayList<>();

            for (int i = 0; i < numberOfSubject; i++) {
                List<Integer> integerCollection = new ArrayList<>();
                Map<List<Integer>, Integer> mapa = new HashMap<>();
                for (int j = 0; j < tournamentParticipants; j++) {
                    int rndRange = rndZeroToParam(collectionWithSubjects.size());
                    //z listy osobnikow wybrac tych z tymi indeksami
                    List<Integer> chosenSubject = collectionWithSubjects.get(rndRange);//mam tego osobnika
                    //pobieram jego ocene
                    Integer rating = sums.get(rndRange);

                    //dodaje do mapy osobnika i jego ocene
                    mapa.put(chosenSubject, rating);

//                    System.out.println("Osobnik " + chosenSubject + "ocena: " + rating);

                    integerCollection.add(rating);

                }
//                System.out.println("MAPA" + mapa);
                Integer min = Collections.min(integerCollection);
                List<Integer> theWeaknessSubject = getKey(mapa, min);

                tournamentPopulation.add(theWeaknessSubject);

            }
//            System.out.println("\n \n \n");
//            for (List<Integer> weaknessGroup : tournamentPopulation
//            ) {
//                System.out.println(weaknessGroup);
//
//            }
//            System.out.println("\n");

            //TODO Krzyzowanie
//            System.out.println("KRZYZOWANKO");
//            System.out.println("\n");


            List<List<Integer>> populationAfterCrossOver = new ArrayList<>();
            //biore parami rodzicow (1+2),(3+4) itd
            for (int i = 0; i < tournamentPopulation.size() - 1; i += 2) {
                List<Integer> list = tournamentPopulation.get(0);
                //pobranie rozmiaru jeden z wewnetrznych list
                int innerListSize = list.size();
                int rndRange = rndRange(1, 100);
                int firstPointCut = rndRange(0, innerListSize - 2);
                int secondPointCut = rndRange(firstPointCut + 1, innerListSize - 1);
                ChildDTO childDTO = ChildDTO.crossOverOX(tournamentPopulation.get(i), tournamentPopulation.get(i + 1), firstPointCut, secondPointCut, crossOverParam, rndRange);

                List<Integer> child1 = childDTO.getChild1();
                List<Integer> child2 = childDTO.getChild2();
                //Dostaje do populacji nowych potomków
                populationAfterCrossOver.add(child1);
                populationAfterCrossOver.add(child2);
            }

//            for (List<Integer> crossOverSubjects : populationAfterCrossOver
//            ) {
//                System.out.println(crossOverSubjects);
//            }
            //TODO mutacja i nowa populacja (populationAfterMutation)
//            List<Integer> sums2 = new ArrayList<>();
//
//            for (List<Integer> subject2 : populationAfterCrossOver
//            ) {
//                int judgeResult = judge(subject2, array);
//                sums2.add(judgeResult);
//                System.out.println(subject2 + " " + judgeResult);
//            }

            //Biore losowa ocene z randomowego osobnika z populacji poczatkowej.
            //Biore najmniejsza ocene i tego osobnika dodaje do kolejnej listy(kolekcji). Jezeli jest mniejszy niz osobnik poczatkowy.
            //Mam w kolekcji 1 osobnika.
//        osobnikNajmniejszy = sequence(0,5);
//        populationAfterCrossOver.add(5, osobnikNajmniejszy);

            //TODO Mutacja przez inwersje
//            System.out.println("MUTACJA");
            List<Integer> judgeAfterMutation = new ArrayList<>();
            List<List<Integer>> populationAfterMutation = new ArrayList<>();
            for (List<Integer> subjectAfterMutation : populationAfterCrossOver
            ) {
                int rndRange = rndRange(1, 100);
                List<Integer> mutedSubject = mutation(subjectAfterMutation, mutationParam, rndRange);
                populationAfterMutation.add(mutedSubject);
            }
//            System.out.println("WYSWIETLENIE PO MUTACJI \n");

            for (List<Integer> subject : populationAfterMutation
            ) {
                int judgeResult = judge(subject, array);
                judgeAfterMutation.add(judgeResult);
//                System.out.println(subject + " " + judgeResult);
            }

            int i = rndRange(0, sums.size() - 1);
            int randomObjectFromFirstPopulation = sums.get(i);
            List<Integer> randomObjectFromFirstPop = collectionWithSubjects.get(i);
            int judgeRandomObj = judge(randomObjectFromFirstPop, array);

            int theWeaknessSubAfterMutation = judgeAfterMutation.indexOf(Collections.min(judgeAfterMutation));
            List<Integer> theWeaknessAfterMutation = populationAfterMutation.get(theWeaknessSubAfterMutation);
            int judgeTheWeaknessSub = judge(theWeaknessAfterMutation, array);

            int iterationNumber = tury % numberOfSubject;
            //if(najslabszyOsobnikPoMutacji jest mniejszy niz losowy z populacji poczatkowej to go nadpisz, jezeli nie to przepisz losowego)
            if (judgeTheWeaknessSub < judgeRandomObj) {
                collectionWithSubjects.set(iterationNumber, theWeaknessAfterMutation);
            } else {
                collectionWithSubjects.set(iterationNumber, randomObjectFromFirstPop);
            }

            //System.out.println(theWeaknessAfterMutation + "" + judgeTheWeaknessSub);
            String collect = theWeaknessAfterMutation.stream().map(String::valueOf).collect(Collectors.joining("-"));
            System.out.println(collect + " " + judgeTheWeaknessSub);

            ilosctur++;
        }
        System.out.println("Ilosc tur " + ilosctur);
    }


    public static <K, V> K getKey(Map<K, V> map, V value) {
        return map.keySet().stream().filter(key -> value.equals(map.get(key))).findFirst().get();
    }

    public static List<Integer> mutation(List<Integer> subjectAfterCrossOver, int mutationParam, int randomIfMutation) {
        if (randomIfMutation > 0 && randomIfMutation <= mutationParam) {
            int firstIndex = rndRange(0, subjectAfterCrossOver.size() - 1);
            int secondIndex = rndRange(0, subjectAfterCrossOver.size() - 1);
            if (secondIndex == firstIndex) {
                secondIndex = rndRange(0, subjectAfterCrossOver.size() - 1);
            }
            Integer firstValueAtIndex = subjectAfterCrossOver.get(firstIndex);
            Integer secondValueAtIndex = subjectAfterCrossOver.get(secondIndex);
            subjectAfterCrossOver.set(firstIndex, secondValueAtIndex);
            subjectAfterCrossOver.set(secondIndex, firstValueAtIndex);
        } else {
            return subjectAfterCrossOver;
        }
        return subjectAfterCrossOver;
    }

    public static int judge(List<Integer> subject, int[][] array) {
        int sum = 0;
        for (int i = 0; i < subject.size() - 1; i++) {
            int current = subject.get(i);
            int next = subject.get(i + 1);
            sum += array[current][next];
        }
        int current = subject.get(subject.size() - 1);
        int next = subject.get(0);
        sum += array[current][next];

//        List<Integer> result = new ArrayList<>();
//        result.add(sum);
        return sum;
    }

    public static List<Integer> sequence(int begin, int end) {
        List<Integer> sequenceList = new ArrayList<>(end - begin + 1);
        for (int i = begin; i <= end; i++) {
            sequenceList.add(i);
        }
        return sequenceList;
    }

    public static int rndZeroToParam(int finish) {
        return new Random().nextInt(finish);
    }

    public static int rndRange(int start, int finish) {
        Random rnd = new Random();
        int rndInt = rnd.nextInt(finish + 1 - start) + start;
        return rndInt;
    }


}
