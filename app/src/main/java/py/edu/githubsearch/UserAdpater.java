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

    public class UserAdpater extends ArrayAdapter<User> {
        public UserAdpater(@NonNull Context context, List<User> users) {

            super(context, R.layout.item_users, R.id.textViewItemUserLogin, users);
        }

        @NonNull
        @Override
        public View getView(int position, @Nullable View convertView, @NonNull ViewGroup parent) {
            View view = super.getView(position, convertView, parent);
            TextView urTextView = view.findViewById(R.id.textviewItemUserUrl);
            User u = getItem(position);
            ImageView avatarImageView = view.findViewById(R.id.imageViewItemUserAvatar);

            get().load(u.getAvatar_url()).into(avatarImageView);
            urTextView.setText(u.getHtmlUrl());

            return view;
        }
}
