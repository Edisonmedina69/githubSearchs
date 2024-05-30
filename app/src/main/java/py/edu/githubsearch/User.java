package py.edu.githubsearch;

import androidx.annotation.NonNull;

import com.google.gson.annotations.SerializedName;

// Clase que representa un usuario de GitHub
public class User {

    // Nombre de usuario
    public String login;

    // URL del avatar del usuario
    public String avatar_url;

    // URL del perfil del usuario (utiliza SerializedName para mapear el nombre JSON a un nombre de campo diferente)
    @SerializedName("html_url")
    public String htmlUrl;

    // Nombre completo del usuario
    public String name;

    // Ubicación del usuario
    public String location;

    // Número de repositorios públicos del usuario
    public String public_repos;

    // Número de seguidores del usuario
    public String followers;

    // Número de personas que el usuario está siguiendo (utiliza SerializedName para mapear)
    @SerializedName("following")
    public String followings;

    // Métodos getter y setter para cada campo

    public String getHtmlUrl() {
        return htmlUrl;
    }

    public void setHtmlUrl(String htmlUrl) {
        this.htmlUrl = htmlUrl;
    }

    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    public String getAvatar_url() {
        return avatar_url;
    }

    public void setAvatar_url(String avatar_url) {
        this.avatar_url = avatar_url;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getLocation() {
        return location;
    }

    public void setLocation(String location) {
        this.location = location;
    }

    public String getPublic_repos() {
        return public_repos;
    }

    public void setPublic_repos(String public_repos) {
        this.public_repos = public_repos;
    }

    public String getFollowers() {
        return followers;
    }

    public void setFollowers(String followers) {
        this.followers = followers;
    }

    public String getFollowings() {
        return followings;
    }

    public void setFollowings(String followings) {
        this.followings = followings;
    }

    // Sobrescribe el método toString para devolver el nombre de usuario
    @NonNull
    @Override
    public String toString() {
        return this.login;
    }
}
