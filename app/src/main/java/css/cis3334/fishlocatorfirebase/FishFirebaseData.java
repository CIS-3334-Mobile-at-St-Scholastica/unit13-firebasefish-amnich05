package css.cis3334.fishlocatorfirebase;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by cssuser on 4/20/2017.
 * Modified by Amanda Nichols on 4/22/2018
 */

public class FishFirebaseData {

    DatabaseReference myFishDbRef;
    public static final String FishDataTag = "Fish Data";

    public DatabaseReference open()  {
        // Get an instance of the database and a reference to the fish data in it

        // Write a message to the database
        FirebaseDatabase database = FirebaseDatabase.getInstance();
        myFishDbRef = database.getReference(FishDataTag);

        return myFishDbRef;
    }

    public void close() {

    }

    public Fish createFish( String species, String weightInOz, String dateCaught) {           //Added String rating as a parameter
        // ---- Get a new database key for the vote
        String key = myFishDbRef.child(FishDataTag).push().getKey();
        // ---- set up the fish object
        Fish newFish = new Fish(key, species, weightInOz, dateCaught);
        // ---- write the vote to Firebase
        myFishDbRef.child(key).setValue(newFish);

        return newFish;
    }

    public Fish createFish( String species, String weightInOz, String dateCaught, String locationLatitude, String locationLongitude) {           //Added String rating as a parameter
        // ---- Get a new database key for the vote
        String key = myFishDbRef.child(FishDataTag).push().getKey();
        // ---- set up the fish object
        Fish newFish = new Fish(key, species, weightInOz, dateCaught, locationLatitude, locationLongitude);
        // ---- write the vote to Firebase
        myFishDbRef.child(key).setValue(newFish);

        return newFish;
    }

    public void deleteFish(Fish fish) {
        String key = fish.getKey();

        myFishDbRef.child(key).removeValue();
    }

    public List<Fish> getAllFish(DataSnapshot dataSnapshot) {
        List<Fish> fishList = new ArrayList<Fish>();

        for (DataSnapshot data : dataSnapshot.getChildren()) {
            Fish fish = data.getValue(Fish.class);
            fishList.add(fish);
        }

        return fishList;
    }

}
