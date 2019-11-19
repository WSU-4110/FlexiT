package com.example.flexit;

import android.content.Context;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.viewpager.widget.PagerAdapter;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.like.LikeButton;
import com.like.OnLikeListener;

import java.util.List;

public class ModelAdapter extends PagerAdapter {

    private static final String TAG = "ModelAdapter";

    private List<Model> models;
    private LayoutInflater layoutInflater;
    private Context context;
    private FirebaseAuth mAuth;
    private FirebaseUser user;
    private FirebaseDatabase myFireBaseDB;
    private DatabaseReference myRef;
    private DatabaseReference likeRef;
    private DatabaseReference disLikeRef;


    public ModelAdapter(List<Model> models, Context context) {
        this.models = models;
        this.context = context;
    }

    @Override
    public int getCount() {
        return models.size();
    }

    @Override
    public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
        return view.equals(object);
    }

    @NonNull
    @Override
    public Object instantiateItem(@NonNull ViewGroup container, int position) {
        layoutInflater = LayoutInflater.from(context);
        View view = layoutInflater.inflate(R.layout.item, container, false);

        mAuth = FirebaseAuth.getInstance();
        myFireBaseDB = FirebaseDatabase.getInstance();
        user = mAuth.getCurrentUser();

        myRef = myFireBaseDB.getReference("WorkoutRating");
        final String userID = user.getUid();

        likeRef = myRef.child(userID).child("Likes");
        disLikeRef = myRef.child(userID).child("Dislikes");


        ImageView imageView;
        final TextView title;
        final TextView description;
        LikeButton likeButton;

        imageView = view.findViewById(R.id.image);
        title = view.findViewById(R.id.title);
        description = view.findViewById(R.id.desc);

        /*https://github.com/jd-alexander/LikeButton#like-event-listener*/
        likeButton = view.findViewById(R.id.heart_button);
        likeButton.setOnLikeListener(new OnLikeListener() {
            @Override
            public void liked(LikeButton likeButton) {
                Toast.makeText(context, "Liked!", Toast.LENGTH_SHORT).show();
                disLikeRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(title.getText().toString())){
                            disLikeRef.child(title.getText().toString()).removeValue();
                        }
                        likeRef.child(title.getText().toString()).setValue(description.getText().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e(TAG, "onCancelled", databaseError.toException());
                    }
                });

            }

            @Override
            public void unLiked(LikeButton likeButton) {
                Toast.makeText(context, "Disliked!", Toast.LENGTH_SHORT).show();
                likeRef.addListenerForSingleValueEvent(new ValueEventListener() {
                    @Override
                    public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                        if (dataSnapshot.hasChild(title.getText().toString())){
                            likeRef.child(title.getText().toString()).removeValue();
                        }
                        disLikeRef.child(title.getText().toString()).setValue(description.getText().toString());
                    }

                    @Override
                    public void onCancelled(@NonNull DatabaseError databaseError) {
                        Log.e(TAG, "onCancelled", databaseError.toException());
                    }
                });
            }
        });

        imageView.setImageResource(models.get(position).getImage());
        title.setText(models.get(position).getTitle());
        description.setText(models.get(position).getDescription());

        container.addView(view);
        return view;
    }

    @Override
    public void destroyItem(@NonNull ViewGroup container, int position, @NonNull Object object) {
        container.removeView((View) object);
    }
}
