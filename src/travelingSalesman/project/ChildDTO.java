package travelingSalesman.project;


import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;
import java.util.stream.Stream;


public class ChildDTO {
    private List<Integer> child1;
    private List<Integer> child2;

    public static ChildDTO crossOverOX(List<Integer> parent1, List<Integer> parent2, int firstPointCut, int secondPointCut, int crossoverParam, int randomIfCross) {
        if (randomIfCross > 0 && randomIfCross <= crossoverParam) {
            ChildDTO childDTO = new ChildDTO();
            //Srodki rodziców
            List<Integer> firstParentMiddle = parent1.subList(firstPointCut, secondPointCut);
            List<Integer> secondParentMiddle = parent2.subList(firstPointCut, secondPointCut);

            //Cały rodzic, ale zaczynajacy sie od 2punktu przeciecia
            List<Integer> firstParentEntire = new ArrayList<>();
            List<Integer> parent1AfterSecondPointCut = parent1.subList(secondPointCut, parent1.size());
            List<Integer> parent1FromStartToSecondPointCut = parent1.subList(0, secondPointCut);
            List<Integer> parent1FromStartToFirstPointCut = parent1.subList(0, firstPointCut);
            firstParentEntire.addAll(parent1AfterSecondPointCut);
            firstParentEntire.addAll(parent1FromStartToSecondPointCut);

            //Cały rodzic, ale zaczynajacy się od 2punkty przeciecia
            List<Integer> secondParentEntire = new ArrayList<>();
            List<Integer> parent2AfterSecondPointCut = parent2.subList(secondPointCut, parent2.size());
            List<Integer> parent2FromStartToSecondPointCut = parent2.subList(0, secondPointCut);
            List<Integer> parent2FromStartToFirstPointCut = parent2.subList(0, firstPointCut);
            secondParentEntire.addAll(parent2AfterSecondPointCut);
            secondParentEntire.addAll(parent2FromStartToSecondPointCut);

            //Rozmiar wycietego elementu od startu do PPP
            int sizeOfFirstPart = parent1FromStartToFirstPointCut.size();
            //Rozmiar wycietego elementu od DPP to konca
            int sizeOfLastPart = parent1AfterSecondPointCut.size();

            List<Integer> copyOfFirstParentEntire = new ArrayList<>(firstParentEntire);
            List<Integer> copyOfSecondParentEntire = new ArrayList<>(secondParentEntire);

            //Usuniecie z calego rodzica, powtarzajacych się elementow ze śrdoka drugiego rodzica
            copyOfFirstParentEntire.removeAll(secondParentMiddle);
            copyOfSecondParentEntire.removeAll(firstParentMiddle);

            //Pobranie do dziecka pierwszego i ostatniego elemntu (na podstawie ilosci brakujacych miejsc)
            List<Integer> children1FirstPart = copyOfFirstParentEntire.subList(0, sizeOfFirstPart);
            List<Integer> children1LastPart = copyOfFirstParentEntire.subList(copyOfFirstParentEntire.size() - sizeOfLastPart, copyOfFirstParentEntire.size());

            //Pobranie do dziecka pierwszego i ostatniego elemntu (na podstawie ilosci brakujacych miejsc)
            List<Integer> children2FirstPart = copyOfSecondParentEntire.subList(0, sizeOfFirstPart);
            List<Integer> children2LastPart = copyOfSecondParentEntire.subList(copyOfSecondParentEntire.size() - sizeOfLastPart, copyOfSecondParentEntire.size());

            List<Integer> children1;
            List<Integer> children2;

            //Połączenie potomków:
            children1 = Stream.of(children1FirstPart, secondParentMiddle, children1LastPart).flatMap(Collection::stream).collect(Collectors.toList());
            children2 = Stream.of(children2FirstPart, firstParentMiddle, children2LastPart).flatMap(Collection::stream).collect(Collectors.toList());

            childDTO.setChild1(children1);
            childDTO.setChild2(children2);
            return childDTO;
        }
        //Jezeli random nie jest w zasięgu parametru krzyzowania
        else {
            ChildDTO dto = new ChildDTO();
            dto.setChild1(parent1);
            dto.setChild2(parent2);
            return dto;
        }
    }

    public ChildDTO() {
    }

    public ChildDTO(List<Integer> child1, List<Integer> child2) {
        this.child1 = child1;
        this.child2 = child2;
    }

    public List<Integer> getChild1() {
        return child1;
    }

    public void setChild1(List<Integer> child1) {
        this.child1 = child1;
    }

    public List<Integer> getChild2() {
        return child2;
    }

    public void setChild2(List<Integer> child2) {
        this.child2 = child2;
    }
}
