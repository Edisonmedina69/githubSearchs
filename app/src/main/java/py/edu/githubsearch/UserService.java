package py.edu.githubsearch;

import java.util.List;

import retrofit.Callback;
import retrofit.http.GET;
import retrofit.http.Path;

// Interfaz para definir los endpoints de la API de GitHub
public interface UserService {

    // Endpoint para obtener los detalles de un usuario por su login
    @GET("/users/{login}")
    void getUserByLogin(@Path("login") String login, Callback<User> callback);

    // Endpoint para obtener la lista de seguidores de un usuario por su login
    @GET("/users/{login}/followers")
    void getUserFollowers(@Path("login") String login, Callback<List<User>> callback);

    // Endpoint para obtener la lista de seguidos de un usuario por su login
    @GET("/users/{login}/following")
    void getUserFollowing(@Path("login") String login, Callback<List<User>> callback);
}
