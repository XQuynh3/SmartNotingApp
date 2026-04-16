package com.example.notingapp.viewmodel;

@kotlin.Metadata(mv = {1, 9, 0}, k = 1, xi = 48, d1 = {"\u0000>\n\u0002\u0018\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\b\u0002\n\u0002\u0018\u0002\n\u0000\n\u0002\u0018\u0002\n\u0002\u0010 \n\u0002\u0018\u0002\n\u0002\b\u0003\n\u0002\u0018\u0002\n\u0000\n\u0002\u0010\u0002\n\u0002\b\u0003\n\u0002\u0010\u000e\n\u0002\b\u0006\u0018\u00002\u00020\u0001B\r\u0012\u0006\u0010\u0002\u001a\u00020\u0003\u00a2\u0006\u0002\u0010\u0004J\u000e\u0010\u000f\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\nJ\u001a\u0010\u0012\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u0013\u001a\u00020\u0014J\u000e\u0010\u0015\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\nJ\u001a\u0010\u0016\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b2\u0006\u0010\u0017\u001a\u00020\u0014J\u0006\u0010\u0018\u001a\u00020\u0010J\u000e\u0010\u0019\u001a\u00020\u00102\u0006\u0010\u0011\u001a\u00020\nR\u000e\u0010\u0005\u001a\u00020\u0006X\u0082\u0004\u00a2\u0006\u0002\n\u0000R\u001d\u0010\u0007\u001a\u000e\u0012\n\u0012\b\u0012\u0004\u0012\u00020\n0\t0\b\u00a2\u0006\b\n\u0000\u001a\u0004\b\u000b\u0010\fR\u000e\u0010\r\u001a\u00020\u000eX\u0082\u0004\u00a2\u0006\u0002\n\u0000\u00a8\u0006\u001a"}, d2 = {"Lcom/example/notingapp/viewmodel/NoteViewModel;", "Landroidx/lifecycle/AndroidViewModel;", "application", "Landroid/app/Application;", "(Landroid/app/Application;)V", "dao", "Lcom/example/notingapp/data/NoteDao;", "notes", "Landroidx/lifecycle/LiveData;", "", "Lcom/example/notingapp/model/Note;", "getNotes", "()Landroidx/lifecycle/LiveData;", "repo", "Lcom/example/notingapp/repository/NoteRepository;", "delete", "", "note", "filterTag", "tag", "", "insert", "search", "query", "syncFromServer", "update", "app_debug"})
public final class NoteViewModel extends androidx.lifecycle.AndroidViewModel {
    @org.jetbrains.annotations.NotNull()
    private final com.example.notingapp.data.NoteDao dao = null;
    @org.jetbrains.annotations.NotNull()
    private final com.example.notingapp.repository.NoteRepository repo = null;
    @org.jetbrains.annotations.NotNull()
    private final androidx.lifecycle.LiveData<java.util.List<com.example.notingapp.model.Note>> notes = null;
    
    public NoteViewModel(@org.jetbrains.annotations.NotNull()
    android.app.Application application) {
        super(null);
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.example.notingapp.model.Note>> getNotes() {
        return null;
    }
    
    public final void insert(@org.jetbrains.annotations.NotNull()
    com.example.notingapp.model.Note note) {
    }
    
    public final void update(@org.jetbrains.annotations.NotNull()
    com.example.notingapp.model.Note note) {
    }
    
    public final void delete(@org.jetbrains.annotations.NotNull()
    com.example.notingapp.model.Note note) {
    }
    
    public final void syncFromServer() {
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.example.notingapp.model.Note>> search(@org.jetbrains.annotations.NotNull()
    java.lang.String query) {
        return null;
    }
    
    @org.jetbrains.annotations.NotNull()
    public final androidx.lifecycle.LiveData<java.util.List<com.example.notingapp.model.Note>> filterTag(@org.jetbrains.annotations.NotNull()
    java.lang.String tag) {
        return null;
    }
}