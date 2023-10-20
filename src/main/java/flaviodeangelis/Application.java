package flaviodeangelis;

import flaviodeangelis.entities.*;
import flaviodeangelis.exception.*;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import java.time.LocalDate;
import java.util.List;
import java.util.Scanner;

import static flaviodeangelis.utils.JpaUtil.getEntityManagerFactory;

public class Application {

    private static final EntityManagerFactory emf = getEntityManagerFactory();

    public static void main(String[] args) {
        System.out.println("Hello World!");
        EntityManager em = emf.createEntityManager();
        ElementDAO eDAO = new ElementDAO(em);
        UtenteDAO uDAO = new UtenteDAO(em);
        PrestitoDAO pDAO = new PrestitoDAO(em);
        Magazine test1 = new Magazine("L'infinito", 2019, 154, Periodicità.MENSILE);
        //eDAO.save(test1);
        //List<Element> archivio = new ArrayList<>();


        try {
            Scanner input = new Scanner(System.in);
            int inputChoose = 0;
            do {

                try {
                    System.out.println("Inserisci 1 per aggiungere un elemento all'archivio, 2 per rimuovere un elemento tramite ISBN,3 per cercare un elemento tramite ISBN,4 per cercare un elemento con l'anno");
                    System.out.println("Inserisci 5 per cercare un libro con il suo autore,6 per cercare un elemento con il titolo,7 per creare o eliminare un untente");
                    inputChoose = Integer.parseInt(input.nextLine());

                    switch (inputChoose) {
                        case 0:
                            break;
                        case 1:
                            try {
                                System.out.println("Inserisci book per aggiunere un libro, magazine per aggiungere una rivista nel DB");
                                String inputCreazione = input.nextLine();
                                if (inputCreazione.toLowerCase().trim().equals("book")) {
                                    System.out.println("Come si chiama il tuo libro : ");
                                    String inputTitle = input.nextLine();
                                    System.out.println("Qual'è l'anno di publicazione : ");
                                    int inputYear = Integer.parseInt(input.nextLine());
                                    if (inputYear > 2023) {
                                        throw new InputAnnoException();
                                    }
                                    System.out.println("Quante pagine ha : ");
                                    int inputPage = Integer.parseInt(input.nextLine());
                                    System.out.println("Chi è l'autore : ");
                                    String inputAuthor = input.nextLine();
                                    System.out.println("Di che genere è : ");
                                    String inputType = input.nextLine();
                                    Books book = new Books(inputTitle, inputYear, inputPage, inputAuthor, inputType);
                                    eDAO.save(book);
                                } else if (inputCreazione.toLowerCase().trim().equals("magazine")) {
                                    System.out.println("Come si chiama la tua rivista : ");
                                    String inputTitle = input.nextLine();
                                    System.out.println("Qual'è l'anno di publicazione : ");
                                    int inputYear = Integer.parseInt(input.nextLine());
                                    if (inputYear > 2023) {
                                        throw new InputAnnoException();
                                    }
                                    System.out.println("Quante pagine ha : ");
                                    int inputPage = Integer.parseInt(input.nextLine());
                                    System.out.println("Qual'è la sua periodicità (periodicità disponibili settimanale,mensile,semestrale) : ");
                                    String inputPeriod = input.nextLine();
                                    if (inputPeriod.toLowerCase().trim().equals("settimanale")) {
                                        Magazine magazine = new Magazine(inputTitle, inputYear, inputPage, Periodicità.SETTIMANALE);
                                        eDAO.save(magazine);
                                    } else if (inputPeriod.toLowerCase().trim().equals("mensile")) {
                                        Magazine magazine = new Magazine(inputTitle, inputYear, inputPage, Periodicità.MENSILE);
                                        eDAO.save(magazine);
                                    } else if (inputPeriod.toLowerCase().trim().equals("semestrale")) {
                                        Magazine magazine = new Magazine(inputTitle, inputYear, inputPage, Periodicità.SEMESTRALE);
                                        eDAO.save(magazine);
                                        ;
                                    }
                                } else {
                                    throw new ElementException();
                                }
                            } catch (ElementException e) {
                                System.out.println("ERRORE : " + e.getMessage());
                            } catch (InputAnnoException e) {
                                System.err.println("ERRORE : " + e.getMessage());
                            }

                            break;
                        case 2:
                            System.out.println("Inserisci un codice ISBN per rimuovere quell'elemento dall'DB");
                            long inputISBN = Integer.parseInt(input.nextLine());
                            eDAO.delate(inputISBN);
                            break;
                        case 3:
                            try {
                                System.out.println("Inserisci un codice ISBN per cercare un elemento nel DB");
                                long inputISBNCerca = Integer.parseInt(input.nextLine());
                                Element outputISBN = eDAO.getByIsbn(inputISBNCerca);
                                if (outputISBN == null) {
                                    throw new ISBNCercaException();
                                } else {
                                    System.out.println(outputISBN);
                                }
                            } catch (ISBNCercaException e) {
                                System.err.println("ERRORE : " + e.getMessage());
                            }

                            break;
                        case 4:
                            try {
                                System.out.println("Inserisci un anno per cercare tutti gli elementi usciti in quell'anno nel DB");
                                int inputAnnoCerca = Integer.parseInt(input.nextLine());
                                List<Element> outputAnno = eDAO.getByYearOfPublication(inputAnnoCerca);
                                if (outputAnno.isEmpty()) {
                                    throw new AnnoException();
                                } else {
                                    System.out.println(outputAnno);
                                }
                            } catch (AnnoException e) {
                                System.err.println("ERRORE : " + e.getMessage());
                            }

                            break;
                        case 5:
                            try {
                                System.out.println("Inserisci un autore per cercare tutti i libri di quest'ultimo nel DB");
                                String inputAutoreCerca = input.nextLine();

                                List<Books> outputAutore = eDAO.getByAuthor(inputAutoreCerca);
                                if (outputAutore.isEmpty()) {
                                    throw new AutoreException();
                                } else {
                                    outputAutore.forEach(System.out::println);
                                }
                            } catch (AutoreException e) {
                                System.err.println("ERRORE : " + e.getMessage());
                            }


                            break;
                        case 6:
                            try {
                                System.out.println("Inserisci un titolo per cercare un elemento nel di nel DB");
                                String inputTitle = input.nextLine();

                                List<Element> outputTitolo = eDAO.getByTitle(inputTitle);
                                if (outputTitolo.isEmpty()) {
                                    throw new AutoreException();
                                } else {
                                    outputTitolo.forEach(System.out::println);
                                }
                            } catch (AutoreException e) {
                                System.err.println("ERRORE : " + e.getMessage());
                            }
                            break;
                        case 7:
                            try {
                                System.out.println("Inserisci 1 per creare un Utente, 2 per eliminarlo tramite numero tessera");
                                inputChoose = Integer.parseInt(input.nextLine());
                                switch (inputChoose) {
                                    case 1:
                                        System.out.println("Inserisci nome utente : ");
                                        String inputNome = input.nextLine();
                                        System.out.println("Inserisci cogome utente : ");
                                        String inputCognome = input.nextLine();
                                        System.out.println("Inserisci anno di nascita : ");
                                        int inputAnno = Integer.parseInt(input.nextLine());
                                        System.out.println("Inserisci mese di nascita : ");
                                        int inputMese = Integer.parseInt(input.nextLine());
                                        System.out.println("Inserisci giorno di nascita : ");
                                        int inputgiorno = Integer.parseInt(input.nextLine());
                                        Utente testUtente = new Utente(inputNome, inputCognome, LocalDate.of(inputAnno, inputMese, inputgiorno));
                                        uDAO.save(testUtente);
                                        break;
                                    case 2:
                                        System.out.println("Inserisci numero tessera utente per eliminarlo dal DB : ");
                                        int inputNumeroTessera = Integer.parseInt(input.nextLine());
                                        uDAO.delate(inputNumeroTessera);
                                    default:
                                        throw new ChooseInputException();
                                }

                            } catch (Exception e) {
                                System.err.println(e.getMessage());

                            }
                            break;
                        case 8:
                            Utente utentePrestito = uDAO.getByNumeroTessera(99019003);
                            Element elementPrestito = eDAO.getByIsbn(82724707);
                            Prestito testPrestito = new Prestito(LocalDate.of(2022, 10, 20), null, utentePrestito, elementPrestito);
                            pDAO.save(testPrestito);
                            List<Prestito> listaPrestitiUtente = pDAO.getPrestitoByNumeroTesseraUtente(99019003);
                            listaPrestitiUtente.forEach(System.out::println);
                            List<Prestito> listaPrestitiScaduti = pDAO.getPrestitoScaduto();
                            listaPrestitiScaduti.forEach(System.out::println);
                            break;
                        default:
                            throw new ChooseInputException();
                    }
                } catch (ChooseInputException e) {
                    System.err.println("ERRORE : " + e.getMessage());
                } catch (Exception e) {
                    System.err.println("ERRORE : " + e.getMessage());
                }
            } while (inputChoose != 0);

            input.close();
        } catch (Exception e) {
            System.err.println(e.getMessage());
        }

    }
}







