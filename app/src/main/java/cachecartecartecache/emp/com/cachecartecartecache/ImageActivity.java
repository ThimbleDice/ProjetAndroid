package cachecartecartecache.emp.com.cachecartecartecache;


import android.nfc.Tag;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.ProgressBar;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.List;

public class ImageActivity extends AppCompatActivity {
    private String imageID;

    private DatabaseReference mDatabaseRef;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);


        mDatabaseRef = FirebaseDatabase.getInstance().getReference();

        //Toast.makeText(ImageActivity.this, mDatabaseRef.toString(), Toast.LENGTH_LONG).show();

        //imageID =

        mDatabaseRef.addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                showData(dataSnapshot);
//                Toast.makeText(ImageActivity.this, "TEST", Toast.LENGTH_LONG).show();
//                for (DataSnapshot postSnapshot : dataSnapshot.getChildren()){
//                    Upload upload = postSnapshot.getValue(Upload.class);
//                    mUploads.add(upload);
//                }
//                Toast.makeText(ImageActivity.this, "Télécharger", Toast.LENGTH_LONG).show();
            }

            @Override
            public void onCancelled(DatabaseError databaseError) {
                Toast.makeText(ImageActivity.this, databaseError.getMessage(), Toast.LENGTH_SHORT);
            }
        });
    }

    private void showData(DataSnapshot dataSnapshot) {
        Toast.makeText(ImageActivity.this, "TEST", Toast.LENGTH_LONG).show();
        for (DataSnapshot ds : dataSnapshot.getChildren()){
            ImageInformation iInfo = new ImageInformation();
            iInfo.setLien(ds.child("Image").getValue(ImageInformation.class).getLien());
            Log.d("ankur", "showData: Lien: " + iInfo.getLien());
            ArrayList<String> array = new ArrayList<>();
            array.add(iInfo.getLien());
            ArrayAdapter adapter = new ArrayAdapter(this, android.R.layout.simple_list_item_1, array);

        }
    }
}
