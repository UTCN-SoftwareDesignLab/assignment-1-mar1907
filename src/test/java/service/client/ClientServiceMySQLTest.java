package service.client;

import database.DBConnectionFactory;
import model.Client;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import repository.client.ClientRepositoryMySQL;

import static org.junit.Assert.*;

public class ClientServiceMySQLTest {

    private ClientService clientService;

    @Before
    public void setUp() throws Exception {
        clientService = new ClientServiceMySQL(new ClientRepositoryMySQL(new DBConnectionFactory().getConnectionWrapper(true).getConnection()));
    }

    @Test
    public void getClientsData() {
        assertEquals("1", clientService.getClientsData().split("\n")[0].split(" ")[0]);
    }

    @Test
    public void insertClient() {
        int before = (int) clientService.getClientsData().chars().filter(num -> num == '\n').count();
        clientService.insertClient("Marius", "MS123456", "1234567890123");
        int after = (int) clientService.getClientsData().chars().filter(num -> num == '\n').count();
        assertEquals(before+1, after);
    }
    @Test
    public void updateClient() {
        clientService.updateClient(1L,"Mariuss","MS123456","1234567890123");
        String name = clientService.getClientsData().split(" ")[1];
        assertEquals(name, "Mariuss");
    }
}