package be.ucll.backend.campusapi.controller;

import be.ucll.backend.campusapi.model.Campus;
import be.ucll.backend.campusapi.service.CampusService;
import be.ucll.backend.campusapi.service.RoomService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.webtestclient.autoconfigure.AutoConfigureWebTestClient;
import org.springframework.test.web.reactive.server.WebTestClient;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;


@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
@AutoConfigureWebTestClient
public class CampusControllerTest {

    @Autowired
    private WebTestClient client;
    @Autowired
    private CampusService campusService;
    @Autowired
    private RoomService roomService;

//    @BeforeEach
//    public void prepare() {
//        client = mock(WebTestClient.class);
//        campusService = mock(CampusService.class);
//        roomService = mock(RoomService.class);
//    }

    @Test
    public void addCampusSucces() {
        client.post()
                .uri("/campus")
                .header("Content-Type", "application/json")
                .bodyValue("{\"campusName\":\"Proximus\", \"address\":\"Heverlee\",\"numberOfParkingSpaces\":250}")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .json("{\"campusName\":\"Proximus\", \"address\":\"Heverlee\",\"numberOfParkingSpaces\":250}");

        assertEquals("Proximus", campusService.getCampusById("Proximus").getCampusName());
    }

    @Test
    public void addRoomToCampusSucces() {
        client.post()
                .uri("/campus")
                .header("Content-Type", "application/json")
                .bodyValue("{\"campusName\":\"Heraeus\", \"address\":\"Heverlee\",\"numberOfParkingSpaces\":250}")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .json("{\"campusName\":\"Heraeus\", \"address\":\"Heverlee\",\"numberOfParkingSpaces\":250}");

        client.post()
                .uri("/campus/Heraeus/rooms")
                .header("Content-Type", "application/json")
                .bodyValue("{\"name\":\"IT-Kamer\", \"type\":\"Computer lokaal\", \"capacity\": 20,\"floor\":\"1\", \"firstName\":\"IT\", \"lastName\":\"lokaal\"}")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .json("{\"name\":\"IT-Kamer\", \"type\":\"Computer lokaal\", \"capacity\": 20,\"floor\":\"1\", \"firstName\":\"IT\", \"lastName\": \"lokaal\"}");

        assertEquals(20, roomService.getRoom("Heraeus", "IT-Kamer").getCapacity());
    }

    @Test
    public void addCampussesWithTheSameName() {
        client.post()
                .uri("/campus")
                .header("Content-Type", "application/json")
                .bodyValue("{\"campusName\":\"Gebouw\", \"address\":\"Heverlee\",\"numberOfParkingSpaces\":250}")
                .exchange()
                .expectStatus().is2xxSuccessful()
                .expectBody()
                .json("{\"campusName\":\"Gebouw\", \"address\":\"Heverlee\",\"numberOfParkingSpaces\":250}");


        client.post()
                .uri("/campus")
                .header("Content-Type", "application/json")
                .bodyValue("{\"campusName\":\"Gebouw\", \"address\":\"Heverlee\",\"numberOfParkingSpaces\":250}")
                .exchange()
                .expectStatus().isBadRequest()
                .expectBody()
                .json("{\"field\": \"campus\", \"message\": \"campus name needs to be unique\"}");
    }
}
