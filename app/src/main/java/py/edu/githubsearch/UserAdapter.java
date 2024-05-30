package py.edu.githubsearch;

import static com.squareup.picasso.Picasso.get;

import android.content.Context;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ArrayAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;

import java.util.List;

// Clase adaptador para mostrar los usuarios en una ListView
public class UserAdapter extends ArrayAdapter<User> {

    // Constructor del adaptador, inicializa con el contexto y la lista de usuarios
    public UserAdapter(@NonNull Context context, List<User> users) {
        // Llama al constructor de ArrayAdapter con el layout del item y la lista de usuarios
        super(context, R.layout.item_users, R.id.textViewItemUserLogin, users);
    }

    // Método para obtener y configurar la vista de cada item en la lista
    @NonNull
    @Override
    public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
        // Obtiene la vista del item usando el método getView del ArrayAdapter
        View view = super.getView(position, convertView, parent);

        // Encuentra el TextView para mostrar la URL del usuario
        TextView urTextView = view.findViewById(R.id.textviewItemUserUrl);

        // Obtiene el usuario en la posición actual de la lista
        User u = getItem(position);

        // Encuentra el ImageView para mostrar el avatar del usuario
        ImageView avatarImageView = view.findViewById(R.id.imageViewItemUserAvatar);

        // Utiliza Picasso para cargar la imagen del avatar en el ImageView
        get().load(u.getAvatar_url()).into(avatarImageView);

        // Establece la URL del usuario en el TextView
        urTextView.setText(u.getHtmlUrl());

        // Devuelve la vista configurada del item
        return view;
    }
}
