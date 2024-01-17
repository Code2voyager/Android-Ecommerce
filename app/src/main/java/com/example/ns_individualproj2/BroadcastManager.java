package com.example.ns_individualproj2;

public class BroadcastManager {
    private static boolean clearBroadcasts = false;

    public static void setClearBroadcasts(boolean clear) {
        clearBroadcasts = clear;
    }

    public static boolean shouldClearBroadcasts() {
        return clearBroadcasts;
    }
}
