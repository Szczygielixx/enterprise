package lab.app;

import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.core.GenericType;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;
import lab.dto.ComplaintDTO;

import java.util.List;

public class Main {

    private static final String BASE_URL = "http://localhost:8080/Server-1.0-SNAPSHOT/api/complaints";

    public static void main(String[] args) {
        Client client = ClientBuilder.newClient();

        System.out.println("--- Ćwiczenie 3, Krok 4: Pobieranie statusu skargi ---");
        // Zakładamy, że istnieje skarga o ID 1. Zmień w razie potrzeby.
        try {
            String status = client.target(BASE_URL + "/1/status")
                    .request(MediaType.TEXT_PLAIN)
                    .get(String.class);
            System.out.println("Status skargi o ID 1: " + status);
        } catch (Exception e) {
            System.out.println("Nie udało się pobrać statusu dla skargi o ID 1. Upewnij się, że serwer działa i zawiera dane.");
            e.printStackTrace();
        }

        System.out.println("\n--- Ćwiczenie 3, Krok 7a: Pobieranie wszystkich skarg ---");
        List<ComplaintDTO> allComplaints = client.target(BASE_URL)
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<ComplaintDTO>>() {});
        System.out.println("Wszystkie skargi:");
        allComplaints.forEach(System.out::println);


        System.out.println("\n--- Ćwiczenie 3, Krok 7b: Pobieranie jednej z otwartych skarg ---");
        List<ComplaintDTO> openComplaints = client.target(BASE_URL)
                .queryParam("status", "open")
                .request(MediaType.APPLICATION_JSON)
                .get(new GenericType<List<ComplaintDTO>>() {});

        if (openComplaints.isEmpty()) {
            System.out.println("Nie znaleziono otwartych skarg. Testy modyfikacji i ponownego pobierania zostaną pominięte.");
        } else {
            ComplaintDTO complaintToUpdate = openComplaints.get(0);
            System.out.println("Pobrana otwarta skarga do modyfikacji: " + complaintToUpdate);

            System.out.println("\n--- Ćwiczenie 3, Krok 7c: Modyfikacja skargi (zmiana statusu na 'closed') ---");
            complaintToUpdate.setStatus("closed");
            Response updateResponse = client.target(BASE_URL + "/" + complaintToUpdate.getId())
                    .request()
                    .put(Entity.entity(complaintToUpdate, MediaType.APPLICATION_JSON));

            System.out.println("Odpowiedź serwera na modyfikację: " + updateResponse.getStatus());
            updateResponse.close();

            System.out.println("\n--- Ćwiczenie 3, Krok 7d: Pobranie i wyświetlenie wszystkich otwartych skarg ---");
            List<ComplaintDTO> openComplaintsAfterUpdate = client.target(BASE_URL)
                    .queryParam("status", "open")
                    .request(MediaType.APPLICATION_JSON)
                    .get(new GenericType<List<ComplaintDTO>>() {});
            System.out.println("Otwarte skargi po modyfikacji:");
            openComplaintsAfterUpdate.forEach(System.out::println);
        }

        client.close();
    }
} 