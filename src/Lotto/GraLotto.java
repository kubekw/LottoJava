package Lotto;

import java.util.Random;
import java.util.Scanner;
import java.util.Set;
import java.util.TreeSet;

public class GraLotto {
    private Random losowanie = new Random();
    private Scanner odczyt = new Scanner(System.in);
    private Set<Integer> liczbyUzytkownika = new TreeSet<>();
    private Set<Integer> wylosowaneLiczby = new TreeSet<>();
    private int liczbaLosowan;
    private double cenaKuponu;
    private double kosztKuponow;

    public void start() {
        pobierzAktualnaCeneKuponu();
        pobierzLiczbyUzytkownika();
        losujLiczbyAzWylosujeszLiczbyUzytkownika();
        kosztKuponow = obliczCeneKuponow();
        wyswietlKomunikat();
    }


    private void pobierzAktualnaCeneKuponu() {
        boolean cenaJestPrawidlowa = false;
        do {
            System.out.println("Wprowadź aktualną cene kuponu w złotówkach");
            String temp = odczyt.next();
            try {
                cenaKuponu = Double.parseDouble(temp);
                cenaJestPrawidlowa = czyCenaJestDodatnia();
            } catch (Exception e) {
                System.out.println("Wprowadzono nieprawidłową wartość. " + e);
            }
        }
        while (!cenaJestPrawidlowa);
    }

    private boolean czyCenaJestDodatnia() {
        if(cenaKuponu<=0){
            System.out.println("Cena musi być dodatnia. Podaj prawidłową cenę");
            return false;
        }
        else return true;
    }

    private void pobierzLiczbyUzytkownika() {
        do {
            System.out.println("Podaj liczbę całkowitą z zakresu od 1 do 49: ");
            String temp= odczyt.next();
            int podanaLiczba;
            try{
                podanaLiczba=Integer.parseInt(temp);
                if(czyLiczbaJestPrawidlowaINieZostalaJuzPodana(podanaLiczba)){
                    liczbyUzytkownika.add(podanaLiczba);
                }
            }
            catch (Exception e){
                System.out.println("Wprowadzono nieprawidłową wartość. "+e);
            }
        }
        while (liczbyUzytkownika.size() < 6);
    }

    private  boolean czyLiczbaJestPrawidlowaINieZostalaJuzPodana(int tempInt) {
        if(liczbyUzytkownika.contains(tempInt)){
            System.out.println("Wprowadzona liczba została już przez Ciebie podana");
            return false;
        }
        else if(tempInt <=0 || tempInt >49) {
            System.out.println("Wprowadzona wartość wykracza poza zakres. Sprubój ponownie.");
            return false;
        }
        else{
            return true;
        }
    }

    private void losujLiczbyAzWylosujeszLiczbyUzytkownika() {
        System.out.println("losowanie.....");
        while (!(liczbyUzytkownika.equals(wylosowaneLiczby))) {
            wylosowaneLiczby.clear();
            do {
                wylosowaneLiczby.add(losowanie.nextInt(49) + 1);
            }
            while (wylosowaneLiczby.size() < 6);
            liczbaLosowan++;
        }
    }

    private double obliczCeneKuponow(){
        return liczbaLosowan*cenaKuponu;
    }

    private void wyswietlKomunikat() {
        System.lineSeparator();
        System.out.printf("Twoje liczby "+liczbyUzytkownika+" wylosowano po %,d losowaniach.\n", liczbaLosowan);
        System.out.printf("Aby wygrać główną nagrodę należało wydać %,.2f złotych.", kosztKuponow);
    }
}

