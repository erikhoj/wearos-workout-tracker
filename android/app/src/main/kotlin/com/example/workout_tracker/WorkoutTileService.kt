package com.example.workout_tracker

import android.util.Log
import android.widget.Button
import androidx.wear.tiles.ActionBuilders
import androidx.wear.tiles.LayoutElementBuilders
import androidx.wear.tiles.LayoutElementBuilders.Layout
import androidx.wear.tiles.LayoutElementBuilders.Text
import androidx.wear.tiles.ModifiersBuilders
import androidx.wear.tiles.RequestBuilders.ResourcesRequest
import androidx.wear.tiles.RequestBuilders.TileRequest
import androidx.wear.tiles.ResourceBuilders.Resources
import androidx.wear.tiles.TileBuilders.Tile
import androidx.wear.tiles.TileService
import androidx.wear.tiles.TimelineBuilders.Timeline
import androidx.wear.tiles.TimelineBuilders.TimelineEntry
import com.google.android.gms.auth.api.identity.BeginSignInRequest
import com.google.android.gms.common.SignInButton
import com.google.firebase.auth.ktx.auth
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.guava.future

private val RESOURCES_VERSION = "1"

class WorkoutTileService : TileService() {
    // For coroutines, use a custom scope we can cancel when the service is destroyed
    private val serviceScope = CoroutineScope(Dispatchers.IO)

    override fun onTileRequest(requestParams: TileRequest) = serviceScope.future {
        Tile.Builder()
            .setResourcesVersion(RESOURCES_VERSION)
            .setTimeline(
                Timeline.Builder().addTimelineEntry(
                    TimelineEntry.Builder().setLayout(
                        Layout.Builder().setRoot(
                            Text.Builder().setText("Hello World").setFontStyle(
                                LayoutElementBuilders.FontStyle.Builder().build()
                            ).setModifiers(
                                ModifiersBuilders.Modifiers.Builder()
                                    .setClickable(
                                        ModifiersBuilders.Clickable.Builder()
                                            .setId("TEST_ID")
                                            .setOnClick()
                                            .build()
                                    ).build()
                            ).build()
                        ).build()
                    ).build()
                ).build()
            ).build()
    }

    override fun onCreate() {
        super.onCreate()

        val button = 
    }
        
    override fun onResourcesRequest(requestParams: ResourcesRequest) = serviceScope.future {
        val auth = Firebase.auth
        Log.d("Test", "Test: Current User: " + auth.currentUser?.uid)

        val signInRequest = BeginSignInRequest.Builder()
            .setGoogleIdTokenRequestOptions(
                BeginSignInRequest.GoogleIdTokenRequestOptions.builder()
                    .setSupported(true)
                    .setServerClientId(getString(R.string.default_web_client_id))
                    .setFilterByAuthorizedAccounts(true)
                    .build())
            .build()


        val db = Firebase.firestore
        db.collection("test").add(hashMapOf(
            "first" to "Test",
        ))

        Log.d("Test", "Test Log")

        Resources.Builder()
            .setVersion(RESOURCES_VERSION)
            .build()
    }
}