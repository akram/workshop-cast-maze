package fr.xebia.workshop.cast.maze;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.support.v7.media.MediaRouteSelector;
import android.support.v7.media.MediaRouter;
import android.util.Log;
import android.view.Menu;
import butterknife.ButterKnife;
import com.google.android.gms.cast.Cast;
import com.google.android.gms.cast.CastDevice;
import com.google.android.gms.common.ConnectionResult;
import com.google.android.gms.common.api.GoogleApiClient;


public class MazeActivity extends ActionBarActivity {

    private static final String TAG = MazeActivity.class.getSimpleName();

    /**
     * MediaRouter allows applications to control the routing of media channels and streams
     * from the current device to external speakers and destination devices
     */
    private MediaRouter mMediaRouter;

    /**
     * Describes the capabilities of routes that applications would like to discover and use.
     */
    private MediaRouteSelector mMediaRouteSelector;

    /**
     * An object representing a Cast receiver device
     */
    private CastDevice mSelectedDevice;

    /**
     * Client api to interact with play services
     */
    private GoogleApiClient mApiClient;

    /**
     * Channel to receive messages from a receiver
     */
    // TODO channel to create

    private boolean mApplicationStarted;
    private boolean mWaitingForReconnect;

    /**
     * Session id generated by the receiver, when connected
     */
    private String mSessionId;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_maze);

        ButterKnife.inject(this);

        //TODO Configure Cast device discovery
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        super.onCreateOptionsMenu(menu);
        //TODO Add menu with chromecast logo
        return true;
    }

    @Override
    protected void onResume() {
        super.onResume();
        //TODO Start media router discovery
    }

    /**
     * Callback for MediaRouter events
     */
    // TODO Implement MediaRouterCallback

    /**
     * Start the receiver app
     */
    // TODO Add launchReceiver method here

    /**
     * Google Play services callbacks
     */
    private class ConnectionCallbacks implements GoogleApiClient.ConnectionCallbacks {
        @Override
        public void onConnected(Bundle connectionHint) {
            Log.d(TAG, "onConnected");

            if (mApiClient == null) {
                // We got disconnected while this runnable was pending
                // execution.
                return;
            }

            try {
                if (mWaitingForReconnect) {
                    mWaitingForReconnect = false;

                    // Check if the receiver app is still running
                    if ((connectionHint != null) && connectionHint.getBoolean(Cast.EXTRA_APP_NO_LONGER_RUNNING)) {
                        Log.d(TAG, "App  is no longer running");
                        // TODO Call teardown method when implemented
                    } else {
                        // Re-create the custom message channel -> Not part of the workshop but it's a hint for later
                        //try {
                        // Cast.CastApi.setMessageReceivedCallbacks(
                        //         mApiClient,
                        //         mGameChannel.getNamespace(),
                        //         mGameChannel);
                        //} catch (IOException e) {
                        // Log.e(TAG, "Exception while creating channel", e);
                        //}
                    }
                } else {
                    //TODO Launch the receiver app
                }
            } catch (Exception e) {
                Log.e(TAG, "Failed to launch application", e);
            }
        }

        @Override
        public void onConnectionSuspended(int cause) {
            Log.d(TAG, "onConnectionSuspended");
            mWaitingForReconnect = true;
        }
    }

    /**
     * Google Play services callbacks
     */
    private class ConnectionFailedListener implements GoogleApiClient.OnConnectionFailedListener {
        @Override
        public void onConnectionFailed(ConnectionResult result) {
            Log.e(TAG, "onConnectionFailed ");
            // TODO Call teardown method when implemented
        }
    }

    // TODO Implement ResultCallback


    // TODO Implement Cast.MessageReceivedCallback (GameChannel)

    public static int hex2Rgb(String colorStr) {
        return Color.rgb(Integer.valueOf(colorStr.substring(1, 3), 16),
                Integer.valueOf(colorStr.substring(3, 5), 16),
                Integer.valueOf(colorStr.substring(5, 7), 16));
    }


    // TODO Map image to correct action

    /**
     * Send a text message to the receiver
     *
     * @param message
     */
    private void sendMessage(String message) {
        // TODO Implement send message
    }

    @Override
    protected void onStop() {
        super.onStop();
        // TODO Call teardown method when implemented
    }


    /**
     * Tear down the connection to the receiver
     */
    // TODO Implement teardown method
}