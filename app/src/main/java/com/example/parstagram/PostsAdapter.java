package com.example.parstagram;

import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.example.parstagram.fragments.FeedFragment;
import com.example.parstagram.fragments.PostDetailsFragment;
import com.parse.ParseFile;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

public class PostsAdapter extends RecyclerView.Adapter<PostsAdapter.ViewHolder> {

    private Context context;
    private List<Post> posts;
    ClickListener listener;
    private String TAG = "POST ADAPTER";

    public interface ClickListener {

        void onPositionClicked(int position);

        void onLongClicked(int position);
    }

    public PostsAdapter(Context context, List<Post> posts) {
        this.context = context;
        this.posts = posts;
    }

    // Clean all elements of the recycler
    public void clear() {
        posts.clear();
        notifyDataSetChanged();
    }

    // Add a list of items -- change to type used
    public void addAll(List<Post> list) {
        posts.addAll(list);
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public PostsAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_post, parent, false);
        return new ViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        Post post = posts.get(position);
        holder.bind(post);
    }

    @Override
    public int getItemCount() {
        return posts.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        private TextView tvUsername1;
        private TextView tvUsername2;
        private ImageView ivImage;
        private TextView tvDescription;
        private TextView tvTimeStamp;
        private ImageView ivProfileImage;

        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            tvUsername1 = itemView.findViewById(R.id.tvUsername1);
            tvUsername2 = itemView.findViewById(R.id.tvUsername2);
            ivImage = itemView.findViewById(R.id.ivImage);
            tvDescription = itemView.findViewById(R.id.tvDescription);
            tvTimeStamp = itemView.findViewById(R.id.tvTimeStamp);
            ivProfileImage = itemView.findViewById(R.id.ivProfileImage);

            itemView.setOnClickListener((View.OnClickListener) this);

        }




        public void bind(Post post) {
            // bind the post data to the view elements
            tvDescription.setText(post.getDescription());
            tvUsername1.setText(post.getUser().getUsername());
            tvUsername2.setText(post.getUser().getUsername());
            tvTimeStamp.setText(Post.calculateTimeAgo(post.getCreatedAt()));
            ParseFile image = post.getImage();
            if (image != null) {
                Glide.with(context).load(image.getUrl()).into(ivImage);
            }
            ParseFile profilePicture = (ParseFile) post.getUser().get("profilePic");
            if (profilePicture != null) {
                Glide.with(context).load(profilePicture.getUrl()).into(ivProfileImage);
            }
        }

        @Override
        public void onClick(View view) {
            int position = getAdapterPosition();
            Log.i(TAG, String.valueOf(position));
            String image = posts.get(position).getImage().getUrl();

            Date date = posts.get(position).getCreatedAt();
            String dateString = Post.calculateTimeAgo(date);

            MainActivity.setUsername(posts.get(position).getUser().getUsername());
            MainActivity.setDescription(posts.get(position).getDescription());
            MainActivity.setTimeStamp(dateString);
            MainActivity.setImage(image);

//            FeedFragment.launchDetails();

            AppCompatActivity activity = (AppCompatActivity) view.getContext();
            Fragment myFragment = new PostDetailsFragment();
            activity.getSupportFragmentManager().beginTransaction().replace(R.id.flContainer, myFragment).addToBackStack(null).commit();



        }


    }


}
