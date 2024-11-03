package trantrongtin_iuh.baitap4_payara.frontend.models;

import com.fasterxml.jackson.core.type.TypeReference;
import com.fasterxml.jackson.databind.ObjectMapper;
import jakarta.ws.rs.client.Client;
import jakarta.ws.rs.client.ClientBuilder;
import jakarta.ws.rs.client.Entity;
import jakarta.ws.rs.client.WebTarget;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Request;
import jakarta.ws.rs.core.Response;
import trantrongtin_iuh.baitap4_payara.backend.dtos.UserDTO;

import java.util.List;

public class UserModel {
    private static final String BASED_URI = "http://localhost:8080/BaiTap4_Payara-1.0-SNAPSHOT/api/users";
    private final ObjectMapper mapper = new ObjectMapper();

    private <T> T executeRequest(String path, Class<T> responseType) {
        return executeRequestInternal(path, json -> mapper.readValue(json, responseType));
    }

    private <T> T executeRequest(String path, TypeReference<T> typeReference) {
        return executeRequestInternal(path, json -> mapper.readValue(json, typeReference));
    }



    private <T> T executeRequestInternal(String path, ThrowingFunction<String, T> jsonParser) {
        WebTarget target = null;
        try (Client client = ClientBuilder.newClient()) {
            target = client.target(BASED_URI).path(path);
            String json = target
                    .request(MediaType.APPLICATION_JSON)
                    .get(String.class);
            return jsonParser.apply(json);
        } catch (Exception e) {
            String errorMessage = "Error fetching data from path: " + e;
            System.out.println(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }



    public List<UserDTO> getAllUsers() {
        return executeRequest("", new TypeReference<List<UserDTO>>() {});
    }

    public  UserDTO getUserById(long id) {
        return executeRequest("/" + id, UserDTO.class);
    }

    public Request addUser(UserDTO userDTO) {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(BASED_URI).path("");
            return target
                    .request(MediaType.APPLICATION_JSON)
                    .post(Entity.entity(userDTO, MediaType.APPLICATION_JSON), Request.class);
        } catch (Exception e) {
            String errorMessage = "Error adding user: " + e;
            System.out.println(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }

    public Response updateUser(UserDTO userDTO) {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(BASED_URI).path("/" + userDTO.getId());
            Response response = target
                    .request(MediaType.APPLICATION_JSON)
                    .put(Entity.entity(userDTO, MediaType.APPLICATION_JSON));

            if(response.getStatus() != 200 || response.getStatus() != 204) {
                String errorMessage = "Lỗi khi cập nhật: " + response.getStatus();
                System.out.println(errorMessage);
                return Response.status(response.getStatus()).entity(errorMessage).build();
            }
            else {
                return response;
            }
        } catch (Exception e) {
            String errorMessage = "Error updating user: " + e;
            System.out.println(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }

    public Response deleteUser(long id) {
        try (Client client = ClientBuilder.newClient()) {
            WebTarget target = client.target(BASED_URI).path("/" + id);
            return target.request(MediaType.APPLICATION_JSON).delete();
        } catch (Exception e) {
            String errorMessage = "Error deleting user: " + e;
            System.out.println(errorMessage);
            throw new RuntimeException(errorMessage, e);
        }
    }



    @FunctionalInterface
    public interface ThrowingFunction<T, R> {
        R apply(T t) throws Exception;
    }
}