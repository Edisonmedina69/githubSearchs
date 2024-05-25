package py.edu.githubsearch;

import static py.edu.githubsearch.R.id.textViewUrl;

import android.annotation.SuppressLint;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ListView;
import android.widget.ProgressBar;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import com.squareup.picasso.Picasso;

import java.util.List;

import retrofit.Callback;
import retrofit.RestAdapter;
import retrofit.RetrofitError;
import retrofit.client.Response;

public class MainActivity extends AppCompatActivity implements Callback<User> {

    SearchView loginSearchView;
    ImageView avatarImageView;
    TextView nameTextView, urlTextView, locationTextView, errorTextView;

    LinearLayout successLayout, emptyLayout;
    ProgressBar progressBar, progressBarUser;
    ListView usersListView;

    Button followersButton, followingsButton;
    UserService service;

    User user;

    Callback<List<User>> callback;

    @SuppressLint("MissingInflatedId")
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        // Encontrar las vistas por ID
        loginSearchView = findViewById(R.id.searchViewLogin);
        avatarImageView = findViewById(R.id.imageviewAvatar);
        progressBar = findViewById(R.id.progressbar);
        urlTextView = findViewById(textViewUrl);
        nameTextView = findViewById(R.id.textViewName);
        locationTextView = findViewById(R.id.textviewLocation);
        errorTextView = findViewById(R.id.textviewError);
        successLayout = findViewById(R.id.layout_success);
        emptyLayout = findViewById(R.id.layoutEmpty);
        progressBarUser =findViewById(R.id.progressbar);
        usersListView = findViewById(R.id.listviewUsers);
        followersButton = findViewById(R.id.buttonFollowers);
        followingsButton = findViewById(R.id.buttonFollowing);

        usersListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                User u = (User) parent.getAdapter().getItem(position);
                loginSearchView.setQuery(u.getLogin(), true);
            }
        });
        // now to finish implement custom adapter to users ListView

        // Crear el servicio para la API de GitHub
        service = new RestAdapter
                .Builder()
                .setEndpoint("https://api.github.com")
                .build()
                .create(UserService.class);

        // Configurar el listener para el SearchView
        loginSearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                Log.d("MainActivity", "Query submitted: " + query);
                service.getUserByLogin(query, MainActivity.this);
                progressBar.setVisibility(View.VISIBLE);
                emptyLayout.setVisibility(View.GONE);
                successLayout.setVisibility(View.GONE);
                return false;
            }


            @Override
            public boolean onQueryTextChange(String newText) {

                return false;
            }
        });

        callback = new Callback<List<User>>() {
            @Override
            public void success(List<User> users, Response response) {
                usersListView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                //set a user listView adapter
                //ArrayAdapter<User> adapter = new ArrayAdapter<>(MainActivity.this, android.R.layout.simple_list_item_1,users);
                UserAdpater adapter = new UserAdpater(MainActivity.this,users);
                usersListView.setAdapter(adapter);
            }

            @Override
            public void failure(RetrofitError error) {
                usersListView.setVisibility(View.GONE);
                progressBar.setVisibility(View.GONE);

                Toast.makeText(MainActivity.this,error.getLocalizedMessage(), Toast.LENGTH_SHORT).show();
            }
        };

        // Hacer una consulta inicial
        service.getUserByLogin("leodufer", new Callback<User>() {
            @Override
            public void success(User user, Response response) {
                Log.i("result", user.toString());
            }

            @Override
            public void failure(RetrofitError error) {
                Log.e("result", error.getLocalizedMessage());
            }
        });
    }

    @Override
    public void success(User user, Response response) {
        Log.d("MainActivity", "User received: " + user.getName());
        nameTextView.setText(user.getName());
        urlTextView.setText(user.getHtmlUrl());
        locationTextView.setText(user.getLocation());
        Picasso.get().load(user.getAvatar_url()).into(avatarImageView);
        successLayout.setVisibility(View.VISIBLE);
        progressBar.setVisibility(View.GONE);
        errorTextView.setVisibility(View.GONE);
        followersButton.setText(user.getFollowers() + " followers");
        followingsButton.setText(user.getFollowings() + " followings");

        this.user = user;

        progressBarUser.setVisibility(View.VISIBLE);
        service.getUserFollowers(user.getLogin(), callback);
    }

    @Override
    public void failure(RetrofitError error) {
        Log.e("MainActivity", "Search failed: " + error.getLocalizedMessage());
        successLayout.setVisibility(View.GONE);
        progressBar.setVisibility(View.GONE);
        errorTextView.setVisibility(View.VISIBLE);
        errorTextView.setText(error.getLocalizedMessage());
    }

    public void getFollowers(View view) {
        Log.d("MainActivity", "Getting followers for user: " + user.getLogin());
        progressBarUser.setVisibility(View.VISIBLE);
        usersListView.setVisibility(View.GONE);
        service.getUserFollowers(user.getLogin(), callback);
    }

    public void getFollowings(View view) {
        Log.d("MainActivity", "Getting followings for user: " + user.getLogin());
        progressBarUser.setVisibility(View.VISIBLE);
        usersListView.setVisibility(View.GONE);
        service.getUserFollowing(user.getLogin(), callback);
    }
}
