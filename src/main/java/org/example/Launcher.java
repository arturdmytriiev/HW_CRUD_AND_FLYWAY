package org.example;

import org.example.models.Client;
import org.example.service.ClientService;

public class Launcher {
    public static void main(String[] args) {
        /*ClientService clientService = new ClientService();
        System.out.println(clientService.create("Oleh"));*/
        /*System.out.println("--------------------------------------------");
        ClientService clientService = new ClientService();
        System.out.println(clientService.getById(4));
        System.out.println("----------------------------------------------");
        clientService.setName(6,"MicroStep");
        System.out.println("----------------------------------------------");*/
        ClientService clientService = new ClientService();
        //clientService.deleteById(6);
        System.out.println("-----------------------------------------------");
        for(Client client:clientService.getAll()) {
            System.out.println(client.getId()+" "+client.getName());
        }
    }
}
