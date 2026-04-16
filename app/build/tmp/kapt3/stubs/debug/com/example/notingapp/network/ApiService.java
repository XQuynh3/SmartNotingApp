package com.example.notingapp.network;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u00004\n\u0002\u0018\u0002\n\u0002\u0010\u0000\n\u0000\n\u0002\u0010\u0002\n\u0000\n\u0002\u0010$\n\u0002\u0010\u000e\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0010 \n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0002\b\u0005\bf\u0018\u00002\u00020\u0001J$\u0010\u0002\u001a\u00020\u00032\u0014\b\u0001\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u0018\u0010\b\u001a\u00020\u00032\b\b\u0001\u0010\t\u001a\u00020\nH\u00a7@\u00a2\u0006\u0002\u0010\u000bJ\u001e\u0010\f\u001a\b\u0012\u0004\u0012\u00020\n0\r2\b\b\u0001\u0010\u000e\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u000fJ\u001e\u0010\u0010\u001a\b\u0012\u0004\u0012\u00020\u00110\r2\b\b\u0001\u0010\u000e\u001a\u00020\u0006H\u00a7@\u00a2\u0006\u0002\u0010\u000fJ$\u0010\u0012\u001a\u00020\u00032\u0014\b\u0001\u0010\u0004\u001a\u000e\u0012\u0004\u0012\u00020\u0006\u0012\u0004\u0012\u00020\u00060\u0005H\u00a7@\u00a2\u0006\u0002\u0010\u0007J\u0018\u0010\u0013\u001a\u00020\u00032\b\b\u0001\u0010\u0014\u001a\u00020\u0011H\u00a7@\u00a2\u0006\u0002\u0010\u0015\u00a8\u0006\u0016"}, d2 = {"Lcom/example/notingapp/network/ApiService;", "", "acceptRequest", "", "body", "", "", "(Ljava/util/Map;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "createNote", "note", "Lcom/example/notingapp/model/NoteDTO;", "(Lcom/example/notingapp/model/NoteDTO;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getNotes", "", "userId", "(Ljava/lang/String;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "getRequests", "Lcom/example/notingapp/model/ShareRequestDTO;", "rejectRequest", "sendShareRequest", "request", "(Lcom/example/notingapp/model/ShareRequestDTO;Lkotlin/coroutines/Continuation;)Ljava/lang/Object;", "app_debug"})
public abstract interface ApiService {
    
    @retrofit2.http.POST(value = "notes")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object createNote(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.example.notingapp.model.NoteDTO note, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @retrofit2.http.GET(value = "notes")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getNotes(@retrofit2.http.Query(value = "userId")
    @org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.notingapp.model.NoteDTO>> $completion);
    
    @retrofit2.http.POST(value = "share-request")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object sendShareRequest(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    com.example.notingapp.model.ShareRequestDTO request, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @retrofit2.http.GET(value = "share-request")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object getRequests(@retrofit2.http.Query(value = "userId")
    @org.jetbrains.annotations.NotNull()
    java.lang.String userId, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super java.util.List<com.example.notingapp.model.ShareRequestDTO>> $completion);
    
    @retrofit2.http.POST(value = "share-request/accept")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object acceptRequest(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.String> body, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
    
    @retrofit2.http.POST(value = "share-request/reject")
    @org.jetbrains.annotations.Nullable()
    public abstract java.lang.Object rejectRequest(@retrofit2.http.Body()
    @org.jetbrains.annotations.NotNull()
    java.util.Map<java.lang.String, java.lang.String> body, @org.jetbrains.annotations.NotNull()
    kotlin.coroutines.Continuation<? super kotlin.Unit> $completion);
}