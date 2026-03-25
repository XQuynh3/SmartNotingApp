package com.example.notingapp.location;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000@\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u000e\n\u0000\n\u0002\u0010\u0006\n\u0002\b\u0002\n\u0002\u0010 \n\u0000\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u001e\u0010\u0005\u001a\u00020\u00062\u0006\u0010\u0007\u001a\u00020\b2\u0006\u0010\t\u001a\u00020\n2\u0006\u0010\u000b\u001a\u00020\nJ\u001a\u0010\f\u001a\b\u0012\u0004\u0012\u00020\u00060\r2\f\u0010\u000e\u001a\b\u0012\u0004\u0012\u00020\u000f0\rJ\u000e\u0010\u0010\u001a\u00020\u00112\u0006\u0010\u0012\u001a\u00020\u0006J\u0014\u0010\u0010\u001a\u00020\u00112\f\u0010\u0013\u001a\b\u0012\u0004\u0012\u00020\u00060\rJ\u0006\u0010\u0014\u001a\u00020\u0015R\u000e\u0010\u0002\u001a\u00020\u0003X\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u0016"}, d2 = {"Lcom/example/notingapp/location/GeofenceHelper;", "", "context", "Landroid/content/Context;", "(Landroid/content/Context;)V", "getGeofence", "Lcom/google/android/gms/location/Geofence;", "id", "", "lat", "", "lng", "getGeofenceList", "", "notes", "Lcom/example/notingapp/model/Note;", "getGeofencingRequest", "Lcom/google/android/gms/location/GeofencingRequest;", "geofence", "geofenceList", "getPendingIntent", "Landroid/app/PendingIntent;", "app_debug"})
public final class GeofenceHelper {
    @org.jetbrains.annotations.NotNull()
    private final android.content.Context context = null;
    
    public GeofenceHelper(@org.jetbrains.annotations.NotNull()
    android.content.Context context) {
        super();
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gms.location.Geofence getGeofence(@org.jetbrains.annotations.NotNull()
    java.lang.String id, double lat, double lng) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final java.util.List<com.google.android.gms.location.Geofence> getGeofenceList(@org.jetbrains.annotations.NotNull()
    java.util.List<com.example.notingapp.model.Note> notes) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gms.location.GeofencingRequest getGeofencingRequest(@org.jetbrains.annotations.NotNull()
    com.google.android.gms.location.Geofence geofence) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final com.google.android.gms.location.GeofencingRequest getGeofencingRequest(@org.jetbrains.annotations.NotNull()
    java.util.List<? extends com.google.android.gms.location.Geofence> geofenceList) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final android.app.PendingIntent getPendingIntent() {
        return null;
    }
}