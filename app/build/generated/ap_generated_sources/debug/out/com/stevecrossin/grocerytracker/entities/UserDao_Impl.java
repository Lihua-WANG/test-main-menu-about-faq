package com.stevecrossin.grocerytracker.entities;

import android.database.Cursor;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.List;

@SuppressWarnings({"unchecked", "deprecation"})
public final class UserDao_Impl implements UserDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<User> __insertionAdapterOfUser;

  private final SharedSQLiteStatement __preparedStmtOfUpdateLoginStatus;

  public UserDao_Impl(RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfUser = new EntityInsertionAdapter<User>(__db) {
      @Override
      public String createQuery() {
        return "INSERT OR ABORT INTO `user` (`userID`,`user_name`,`pass_key`,`login_status`,`user_gender`,`age`,`postcode`,`householdAdults`,`householdChildren`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?)";
      }

      @Override
      public void bind(SupportSQLiteStatement stmt, User value) {
        stmt.bindLong(1, value.getUserID());
        if (value.getUserName() == null) {
          stmt.bindNull(2);
        } else {
          stmt.bindString(2, value.getUserName());
        }
        if (value.getPassKey() == null) {
          stmt.bindNull(3);
        } else {
          stmt.bindString(3, value.getPassKey());
        }
        final int _tmp;
        _tmp = value.isLoggedIn() ? 1 : 0;
        stmt.bindLong(4, _tmp);
        if (value.getUserGender() == null) {
          stmt.bindNull(5);
        } else {
          stmt.bindString(5, value.getUserGender());
        }
        stmt.bindLong(6, value.getUserAge());
        stmt.bindLong(7, value.getPostCode());
        if (value.getAdultsInHouse() == null) {
          stmt.bindNull(8);
        } else {
          stmt.bindString(8, value.getAdultsInHouse());
        }
        if (value.getChildrenInHouse() == null) {
          stmt.bindNull(9);
        } else {
          stmt.bindString(9, value.getChildrenInHouse());
        }
      }
    };
    this.__preparedStmtOfUpdateLoginStatus = new SharedSQLiteStatement(__db) {
      @Override
      public String createQuery() {
        final String _query = "UPDATE user SET login_status = ?  WHERE userID = ?;";
        return _query;
      }
    };
  }

  @Override
  public void insertUser(final User user) {
    __db.assertNotSuspendingTransaction();
    __db.beginTransaction();
    try {
      __insertionAdapterOfUser.insert(user);
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
    }
  }

  @Override
  public void updateLoginStatus(final int userId, final boolean isLogin) {
    __db.assertNotSuspendingTransaction();
    final SupportSQLiteStatement _stmt = __preparedStmtOfUpdateLoginStatus.acquire();
    int _argIndex = 1;
    final int _tmp;
    _tmp = isLogin ? 1 : 0;
    _stmt.bindLong(_argIndex, _tmp);
    _argIndex = 2;
    _stmt.bindLong(_argIndex, userId);
    __db.beginTransaction();
    try {
      _stmt.executeUpdateDelete();
      __db.setTransactionSuccessful();
    } finally {
      __db.endTransaction();
      __preparedStmtOfUpdateLoginStatus.release(_stmt);
    }
  }

  @Override
  public List<User> getAllUsers() {
    final String _sql = "SELECT * FROM user;";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUserID = CursorUtil.getColumnIndexOrThrow(_cursor, "userID");
      final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "user_name");
      final int _cursorIndexOfPassKey = CursorUtil.getColumnIndexOrThrow(_cursor, "pass_key");
      final int _cursorIndexOfIsLoggedIn = CursorUtil.getColumnIndexOrThrow(_cursor, "login_status");
      final int _cursorIndexOfUserGender = CursorUtil.getColumnIndexOrThrow(_cursor, "user_gender");
      final int _cursorIndexOfUserAge = CursorUtil.getColumnIndexOrThrow(_cursor, "age");
      final int _cursorIndexOfPostCode = CursorUtil.getColumnIndexOrThrow(_cursor, "postcode");
      final int _cursorIndexOfAdultsInHouse = CursorUtil.getColumnIndexOrThrow(_cursor, "householdAdults");
      final int _cursorIndexOfChildrenInHouse = CursorUtil.getColumnIndexOrThrow(_cursor, "householdChildren");
      final List<User> _result = new ArrayList<User>(_cursor.getCount());
      while(_cursor.moveToNext()) {
        final User _item;
        final int _tmpUserID;
        _tmpUserID = _cursor.getInt(_cursorIndexOfUserID);
        final String _tmpUserName;
        _tmpUserName = _cursor.getString(_cursorIndexOfUserName);
        final String _tmpPassKey;
        _tmpPassKey = _cursor.getString(_cursorIndexOfPassKey);
        final boolean _tmpIsLoggedIn;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsLoggedIn);
        _tmpIsLoggedIn = _tmp != 0;
        final String _tmpUserGender;
        _tmpUserGender = _cursor.getString(_cursorIndexOfUserGender);
        final int _tmpUserAge;
        _tmpUserAge = _cursor.getInt(_cursorIndexOfUserAge);
        final int _tmpPostCode;
        _tmpPostCode = _cursor.getInt(_cursorIndexOfPostCode);
        final String _tmpAdultsInHouse;
        _tmpAdultsInHouse = _cursor.getString(_cursorIndexOfAdultsInHouse);
        final String _tmpChildrenInHouse;
        _tmpChildrenInHouse = _cursor.getString(_cursorIndexOfChildrenInHouse);
        _item = new User(_tmpUserID,_tmpUserName,_tmpPassKey,_tmpIsLoggedIn,_tmpUserGender,_tmpUserAge,_tmpPostCode,_tmpAdultsInHouse,_tmpChildrenInHouse);
        _result.add(_item);
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }

  @Override
  public User getSignInUser() {
    final String _sql = "SELECT * from user WHERE login_status= 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    __db.assertNotSuspendingTransaction();
    final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
    try {
      final int _cursorIndexOfUserID = CursorUtil.getColumnIndexOrThrow(_cursor, "userID");
      final int _cursorIndexOfUserName = CursorUtil.getColumnIndexOrThrow(_cursor, "user_name");
      final int _cursorIndexOfPassKey = CursorUtil.getColumnIndexOrThrow(_cursor, "pass_key");
      final int _cursorIndexOfIsLoggedIn = CursorUtil.getColumnIndexOrThrow(_cursor, "login_status");
      final int _cursorIndexOfUserGender = CursorUtil.getColumnIndexOrThrow(_cursor, "user_gender");
      final int _cursorIndexOfUserAge = CursorUtil.getColumnIndexOrThrow(_cursor, "age");
      final int _cursorIndexOfPostCode = CursorUtil.getColumnIndexOrThrow(_cursor, "postcode");
      final int _cursorIndexOfAdultsInHouse = CursorUtil.getColumnIndexOrThrow(_cursor, "householdAdults");
      final int _cursorIndexOfChildrenInHouse = CursorUtil.getColumnIndexOrThrow(_cursor, "householdChildren");
      final User _result;
      if(_cursor.moveToFirst()) {
        final int _tmpUserID;
        _tmpUserID = _cursor.getInt(_cursorIndexOfUserID);
        final String _tmpUserName;
        _tmpUserName = _cursor.getString(_cursorIndexOfUserName);
        final String _tmpPassKey;
        _tmpPassKey = _cursor.getString(_cursorIndexOfPassKey);
        final boolean _tmpIsLoggedIn;
        final int _tmp;
        _tmp = _cursor.getInt(_cursorIndexOfIsLoggedIn);
        _tmpIsLoggedIn = _tmp != 0;
        final String _tmpUserGender;
        _tmpUserGender = _cursor.getString(_cursorIndexOfUserGender);
        final int _tmpUserAge;
        _tmpUserAge = _cursor.getInt(_cursorIndexOfUserAge);
        final int _tmpPostCode;
        _tmpPostCode = _cursor.getInt(_cursorIndexOfPostCode);
        final String _tmpAdultsInHouse;
        _tmpAdultsInHouse = _cursor.getString(_cursorIndexOfAdultsInHouse);
        final String _tmpChildrenInHouse;
        _tmpChildrenInHouse = _cursor.getString(_cursorIndexOfChildrenInHouse);
        _result = new User(_tmpUserID,_tmpUserName,_tmpPassKey,_tmpIsLoggedIn,_tmpUserGender,_tmpUserAge,_tmpPostCode,_tmpAdultsInHouse,_tmpChildrenInHouse);
      } else {
        _result = null;
      }
      return _result;
    } finally {
      _cursor.close();
      _statement.release();
    }
  }
}
