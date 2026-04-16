package com.example.notingapp.data;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.example.notingapp.model.Note;
import java.lang.Class;
import java.lang.Double;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class NoteDao_Impl implements NoteDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Note> __insertionAdapterOfNote;

  private final EntityDeletionOrUpdateAdapter<Note> __deletionAdapterOfNote;

  private final EntityDeletionOrUpdateAdapter<Note> __updateAdapterOfNote;

  public NoteDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfNote = new EntityInsertionAdapter<Note>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR ABORT INTO `Note` (`id`,`title`,`content`,`tag`,`color`,`pinned`,`textSize`,`bold`,`italic`,`lastModified`,`latitude`,`longitude`,`locationName`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Note entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getTitle());
        }
        if (entity.getContent() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getContent());
        }
        if (entity.getTag() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getTag());
        }
        statement.bindLong(5, entity.getColor());
        final int _tmp = entity.getPinned() ? 1 : 0;
        statement.bindLong(6, _tmp);
        statement.bindDouble(7, entity.getTextSize());
        final int _tmp_1 = entity.getBold() ? 1 : 0;
        statement.bindLong(8, _tmp_1);
        final int _tmp_2 = entity.getItalic() ? 1 : 0;
        statement.bindLong(9, _tmp_2);
        statement.bindLong(10, entity.getLastModified());
        if (entity.getLatitude() == null) {
          statement.bindNull(11);
        } else {
          statement.bindDouble(11, entity.getLatitude());
        }
        if (entity.getLongitude() == null) {
          statement.bindNull(12);
        } else {
          statement.bindDouble(12, entity.getLongitude());
        }
        if (entity.getLocationName() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getLocationName());
        }
      }
    };
    this.__deletionAdapterOfNote = new EntityDeletionOrUpdateAdapter<Note>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `Note` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Note entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfNote = new EntityDeletionOrUpdateAdapter<Note>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `Note` SET `id` = ?,`title` = ?,`content` = ?,`tag` = ?,`color` = ?,`pinned` = ?,`textSize` = ?,`bold` = ?,`italic` = ?,`lastModified` = ?,`latitude` = ?,`longitude` = ?,`locationName` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Note entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getTitle() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getTitle());
        }
        if (entity.getContent() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getContent());
        }
        if (entity.getTag() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getTag());
        }
        statement.bindLong(5, entity.getColor());
        final int _tmp = entity.getPinned() ? 1 : 0;
        statement.bindLong(6, _tmp);
        statement.bindDouble(7, entity.getTextSize());
        final int _tmp_1 = entity.getBold() ? 1 : 0;
        statement.bindLong(8, _tmp_1);
        final int _tmp_2 = entity.getItalic() ? 1 : 0;
        statement.bindLong(9, _tmp_2);
        statement.bindLong(10, entity.getLastModified());
        if (entity.getLatitude() == null) {
          statement.bindNull(11);
        } else {
          statement.bindDouble(11, entity.getLatitude());
        }
        if (entity.getLongitude() == null) {
          statement.bindNull(12);
        } else {
          statement.bindDouble(12, entity.getLongitude());
        }
        if (entity.getLocationName() == null) {
          statement.bindNull(13);
        } else {
          statement.bindString(13, entity.getLocationName());
        }
        statement.bindLong(14, entity.getId());
      }
    };
  }

  @Override
  public Object insert(final Note note, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfNote.insert(note);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final Note note, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfNote.handle(note);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final Note note, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfNote.handle(note);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<Note>> getAllNotes() {
    final String _sql = "SELECT * FROM Note ORDER BY pinned DESC, lastModified DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"Note"}, false, new Callable<List<Note>>() {
      @Override
      @Nullable
      public List<Note> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfTag = CursorUtil.getColumnIndexOrThrow(_cursor, "tag");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfPinned = CursorUtil.getColumnIndexOrThrow(_cursor, "pinned");
          final int _cursorIndexOfTextSize = CursorUtil.getColumnIndexOrThrow(_cursor, "textSize");
          final int _cursorIndexOfBold = CursorUtil.getColumnIndexOrThrow(_cursor, "bold");
          final int _cursorIndexOfItalic = CursorUtil.getColumnIndexOrThrow(_cursor, "italic");
          final int _cursorIndexOfLastModified = CursorUtil.getColumnIndexOrThrow(_cursor, "lastModified");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfLocationName = CursorUtil.getColumnIndexOrThrow(_cursor, "locationName");
          final List<Note> _result = new ArrayList<Note>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Note _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            final String _tmpTag;
            if (_cursor.isNull(_cursorIndexOfTag)) {
              _tmpTag = null;
            } else {
              _tmpTag = _cursor.getString(_cursorIndexOfTag);
            }
            final int _tmpColor;
            _tmpColor = _cursor.getInt(_cursorIndexOfColor);
            final boolean _tmpPinned;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfPinned);
            _tmpPinned = _tmp != 0;
            final float _tmpTextSize;
            _tmpTextSize = _cursor.getFloat(_cursorIndexOfTextSize);
            final boolean _tmpBold;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfBold);
            _tmpBold = _tmp_1 != 0;
            final boolean _tmpItalic;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfItalic);
            _tmpItalic = _tmp_2 != 0;
            final long _tmpLastModified;
            _tmpLastModified = _cursor.getLong(_cursorIndexOfLastModified);
            final Double _tmpLatitude;
            if (_cursor.isNull(_cursorIndexOfLatitude)) {
              _tmpLatitude = null;
            } else {
              _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            }
            final Double _tmpLongitude;
            if (_cursor.isNull(_cursorIndexOfLongitude)) {
              _tmpLongitude = null;
            } else {
              _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            }
            final String _tmpLocationName;
            if (_cursor.isNull(_cursorIndexOfLocationName)) {
              _tmpLocationName = null;
            } else {
              _tmpLocationName = _cursor.getString(_cursorIndexOfLocationName);
            }
            _item = new Note(_tmpId,_tmpTitle,_tmpContent,_tmpTag,_tmpColor,_tmpPinned,_tmpTextSize,_tmpBold,_tmpItalic,_tmpLastModified,_tmpLatitude,_tmpLongitude,_tmpLocationName);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getNoteById(final int id, final Continuation<? super Note> $completion) {
    final String _sql = "SELECT * FROM Note WHERE id=?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindLong(_argIndex, id);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Note>() {
      @Override
      @NonNull
      public Note call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfTag = CursorUtil.getColumnIndexOrThrow(_cursor, "tag");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfPinned = CursorUtil.getColumnIndexOrThrow(_cursor, "pinned");
          final int _cursorIndexOfTextSize = CursorUtil.getColumnIndexOrThrow(_cursor, "textSize");
          final int _cursorIndexOfBold = CursorUtil.getColumnIndexOrThrow(_cursor, "bold");
          final int _cursorIndexOfItalic = CursorUtil.getColumnIndexOrThrow(_cursor, "italic");
          final int _cursorIndexOfLastModified = CursorUtil.getColumnIndexOrThrow(_cursor, "lastModified");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfLocationName = CursorUtil.getColumnIndexOrThrow(_cursor, "locationName");
          final Note _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            final String _tmpTag;
            if (_cursor.isNull(_cursorIndexOfTag)) {
              _tmpTag = null;
            } else {
              _tmpTag = _cursor.getString(_cursorIndexOfTag);
            }
            final int _tmpColor;
            _tmpColor = _cursor.getInt(_cursorIndexOfColor);
            final boolean _tmpPinned;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfPinned);
            _tmpPinned = _tmp != 0;
            final float _tmpTextSize;
            _tmpTextSize = _cursor.getFloat(_cursorIndexOfTextSize);
            final boolean _tmpBold;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfBold);
            _tmpBold = _tmp_1 != 0;
            final boolean _tmpItalic;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfItalic);
            _tmpItalic = _tmp_2 != 0;
            final long _tmpLastModified;
            _tmpLastModified = _cursor.getLong(_cursorIndexOfLastModified);
            final Double _tmpLatitude;
            if (_cursor.isNull(_cursorIndexOfLatitude)) {
              _tmpLatitude = null;
            } else {
              _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            }
            final Double _tmpLongitude;
            if (_cursor.isNull(_cursorIndexOfLongitude)) {
              _tmpLongitude = null;
            } else {
              _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            }
            final String _tmpLocationName;
            if (_cursor.isNull(_cursorIndexOfLocationName)) {
              _tmpLocationName = null;
            } else {
              _tmpLocationName = _cursor.getString(_cursorIndexOfLocationName);
            }
            _result = new Note(_tmpId,_tmpTitle,_tmpContent,_tmpTag,_tmpColor,_tmpPinned,_tmpTextSize,_tmpBold,_tmpItalic,_tmpLastModified,_tmpLatitude,_tmpLongitude,_tmpLocationName);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<Note>> search(final String query) {
    final String _sql = "\n"
            + "        SELECT * FROM Note \n"
            + "        WHERE title LIKE '%' || ? || '%' \n"
            + "        OR content LIKE '%' || ? || '%'\n"
            + "        ORDER BY pinned DESC, lastModified DESC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (query == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, query);
    }
    _argIndex = 2;
    if (query == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, query);
    }
    return __db.getInvalidationTracker().createLiveData(new String[] {"Note"}, false, new Callable<List<Note>>() {
      @Override
      @Nullable
      public List<Note> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfTag = CursorUtil.getColumnIndexOrThrow(_cursor, "tag");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfPinned = CursorUtil.getColumnIndexOrThrow(_cursor, "pinned");
          final int _cursorIndexOfTextSize = CursorUtil.getColumnIndexOrThrow(_cursor, "textSize");
          final int _cursorIndexOfBold = CursorUtil.getColumnIndexOrThrow(_cursor, "bold");
          final int _cursorIndexOfItalic = CursorUtil.getColumnIndexOrThrow(_cursor, "italic");
          final int _cursorIndexOfLastModified = CursorUtil.getColumnIndexOrThrow(_cursor, "lastModified");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfLocationName = CursorUtil.getColumnIndexOrThrow(_cursor, "locationName");
          final List<Note> _result = new ArrayList<Note>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Note _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            final String _tmpTag;
            if (_cursor.isNull(_cursorIndexOfTag)) {
              _tmpTag = null;
            } else {
              _tmpTag = _cursor.getString(_cursorIndexOfTag);
            }
            final int _tmpColor;
            _tmpColor = _cursor.getInt(_cursorIndexOfColor);
            final boolean _tmpPinned;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfPinned);
            _tmpPinned = _tmp != 0;
            final float _tmpTextSize;
            _tmpTextSize = _cursor.getFloat(_cursorIndexOfTextSize);
            final boolean _tmpBold;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfBold);
            _tmpBold = _tmp_1 != 0;
            final boolean _tmpItalic;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfItalic);
            _tmpItalic = _tmp_2 != 0;
            final long _tmpLastModified;
            _tmpLastModified = _cursor.getLong(_cursorIndexOfLastModified);
            final Double _tmpLatitude;
            if (_cursor.isNull(_cursorIndexOfLatitude)) {
              _tmpLatitude = null;
            } else {
              _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            }
            final Double _tmpLongitude;
            if (_cursor.isNull(_cursorIndexOfLongitude)) {
              _tmpLongitude = null;
            } else {
              _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            }
            final String _tmpLocationName;
            if (_cursor.isNull(_cursorIndexOfLocationName)) {
              _tmpLocationName = null;
            } else {
              _tmpLocationName = _cursor.getString(_cursorIndexOfLocationName);
            }
            _item = new Note(_tmpId,_tmpTitle,_tmpContent,_tmpTag,_tmpColor,_tmpPinned,_tmpTextSize,_tmpBold,_tmpItalic,_tmpLastModified,_tmpLatitude,_tmpLongitude,_tmpLocationName);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public LiveData<List<Note>> filterTag(final String tag) {
    final String _sql = "\n"
            + "        SELECT * FROM Note \n"
            + "        WHERE tag=? \n"
            + "        ORDER BY pinned DESC, lastModified DESC\n"
            + "    ";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (tag == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, tag);
    }
    return __db.getInvalidationTracker().createLiveData(new String[] {"Note"}, false, new Callable<List<Note>>() {
      @Override
      @Nullable
      public List<Note> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfTag = CursorUtil.getColumnIndexOrThrow(_cursor, "tag");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfPinned = CursorUtil.getColumnIndexOrThrow(_cursor, "pinned");
          final int _cursorIndexOfTextSize = CursorUtil.getColumnIndexOrThrow(_cursor, "textSize");
          final int _cursorIndexOfBold = CursorUtil.getColumnIndexOrThrow(_cursor, "bold");
          final int _cursorIndexOfItalic = CursorUtil.getColumnIndexOrThrow(_cursor, "italic");
          final int _cursorIndexOfLastModified = CursorUtil.getColumnIndexOrThrow(_cursor, "lastModified");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfLocationName = CursorUtil.getColumnIndexOrThrow(_cursor, "locationName");
          final List<Note> _result = new ArrayList<Note>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Note _item;
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            final String _tmpTag;
            if (_cursor.isNull(_cursorIndexOfTag)) {
              _tmpTag = null;
            } else {
              _tmpTag = _cursor.getString(_cursorIndexOfTag);
            }
            final int _tmpColor;
            _tmpColor = _cursor.getInt(_cursorIndexOfColor);
            final boolean _tmpPinned;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfPinned);
            _tmpPinned = _tmp != 0;
            final float _tmpTextSize;
            _tmpTextSize = _cursor.getFloat(_cursorIndexOfTextSize);
            final boolean _tmpBold;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfBold);
            _tmpBold = _tmp_1 != 0;
            final boolean _tmpItalic;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfItalic);
            _tmpItalic = _tmp_2 != 0;
            final long _tmpLastModified;
            _tmpLastModified = _cursor.getLong(_cursorIndexOfLastModified);
            final Double _tmpLatitude;
            if (_cursor.isNull(_cursorIndexOfLatitude)) {
              _tmpLatitude = null;
            } else {
              _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            }
            final Double _tmpLongitude;
            if (_cursor.isNull(_cursorIndexOfLongitude)) {
              _tmpLongitude = null;
            } else {
              _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            }
            final String _tmpLocationName;
            if (_cursor.isNull(_cursorIndexOfLocationName)) {
              _tmpLocationName = null;
            } else {
              _tmpLocationName = _cursor.getString(_cursorIndexOfLocationName);
            }
            _item = new Note(_tmpId,_tmpTitle,_tmpContent,_tmpTag,_tmpColor,_tmpPinned,_tmpTextSize,_tmpBold,_tmpItalic,_tmpLastModified,_tmpLatitude,_tmpLongitude,_tmpLocationName);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object findByTitleAndContent(final String title, final String content,
      final Continuation<? super Note> $completion) {
    final String _sql = "SELECT * FROM Note WHERE title = ? AND content = ? LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 2);
    int _argIndex = 1;
    if (title == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, title);
    }
    _argIndex = 2;
    if (content == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, content);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Note>() {
      @Override
      @Nullable
      public Note call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfTitle = CursorUtil.getColumnIndexOrThrow(_cursor, "title");
          final int _cursorIndexOfContent = CursorUtil.getColumnIndexOrThrow(_cursor, "content");
          final int _cursorIndexOfTag = CursorUtil.getColumnIndexOrThrow(_cursor, "tag");
          final int _cursorIndexOfColor = CursorUtil.getColumnIndexOrThrow(_cursor, "color");
          final int _cursorIndexOfPinned = CursorUtil.getColumnIndexOrThrow(_cursor, "pinned");
          final int _cursorIndexOfTextSize = CursorUtil.getColumnIndexOrThrow(_cursor, "textSize");
          final int _cursorIndexOfBold = CursorUtil.getColumnIndexOrThrow(_cursor, "bold");
          final int _cursorIndexOfItalic = CursorUtil.getColumnIndexOrThrow(_cursor, "italic");
          final int _cursorIndexOfLastModified = CursorUtil.getColumnIndexOrThrow(_cursor, "lastModified");
          final int _cursorIndexOfLatitude = CursorUtil.getColumnIndexOrThrow(_cursor, "latitude");
          final int _cursorIndexOfLongitude = CursorUtil.getColumnIndexOrThrow(_cursor, "longitude");
          final int _cursorIndexOfLocationName = CursorUtil.getColumnIndexOrThrow(_cursor, "locationName");
          final Note _result;
          if (_cursor.moveToFirst()) {
            final int _tmpId;
            _tmpId = _cursor.getInt(_cursorIndexOfId);
            final String _tmpTitle;
            if (_cursor.isNull(_cursorIndexOfTitle)) {
              _tmpTitle = null;
            } else {
              _tmpTitle = _cursor.getString(_cursorIndexOfTitle);
            }
            final String _tmpContent;
            if (_cursor.isNull(_cursorIndexOfContent)) {
              _tmpContent = null;
            } else {
              _tmpContent = _cursor.getString(_cursorIndexOfContent);
            }
            final String _tmpTag;
            if (_cursor.isNull(_cursorIndexOfTag)) {
              _tmpTag = null;
            } else {
              _tmpTag = _cursor.getString(_cursorIndexOfTag);
            }
            final int _tmpColor;
            _tmpColor = _cursor.getInt(_cursorIndexOfColor);
            final boolean _tmpPinned;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfPinned);
            _tmpPinned = _tmp != 0;
            final float _tmpTextSize;
            _tmpTextSize = _cursor.getFloat(_cursorIndexOfTextSize);
            final boolean _tmpBold;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfBold);
            _tmpBold = _tmp_1 != 0;
            final boolean _tmpItalic;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfItalic);
            _tmpItalic = _tmp_2 != 0;
            final long _tmpLastModified;
            _tmpLastModified = _cursor.getLong(_cursorIndexOfLastModified);
            final Double _tmpLatitude;
            if (_cursor.isNull(_cursorIndexOfLatitude)) {
              _tmpLatitude = null;
            } else {
              _tmpLatitude = _cursor.getDouble(_cursorIndexOfLatitude);
            }
            final Double _tmpLongitude;
            if (_cursor.isNull(_cursorIndexOfLongitude)) {
              _tmpLongitude = null;
            } else {
              _tmpLongitude = _cursor.getDouble(_cursorIndexOfLongitude);
            }
            final String _tmpLocationName;
            if (_cursor.isNull(_cursorIndexOfLocationName)) {
              _tmpLocationName = null;
            } else {
              _tmpLocationName = _cursor.getString(_cursorIndexOfLocationName);
            }
            _result = new Note(_tmpId,_tmpTitle,_tmpContent,_tmpTag,_tmpColor,_tmpPinned,_tmpTextSize,_tmpBold,_tmpItalic,_tmpLastModified,_tmpLatitude,_tmpLongitude,_tmpLocationName);
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
