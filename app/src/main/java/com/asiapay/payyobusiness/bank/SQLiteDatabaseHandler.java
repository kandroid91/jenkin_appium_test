package com.asiapay.payyobusiness.bank;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.asiapay.payyobusiness.model.Bank;

import java.util.LinkedList;
import java.util.List;

public class SQLiteDatabaseHandler extends SQLiteOpenHelper {

    private static final int DATABASE_VERSION = 1;
    private static final String DATABASE_NAME = "getBankDataBase";
    private static final String TABLE_NAME = "bankAccountDetails";
    private static final String KEY_ID = "id";
    private static final String KEY_BANK_NAME = "nameBank";
    private static final String KEY_BANK_ACCOUNT_NO = "accountNo";
    private static final String KEY_IFSC_CODE = "ifscCode";
    private static final String[] COLUMNS = { KEY_ID, KEY_BANK_NAME, KEY_BANK_ACCOUNT_NO, KEY_IFSC_CODE };

    public SQLiteDatabaseHandler(Context mContext) {
        super(mContext, DATABASE_NAME, null, DATABASE_VERSION);

    }

    @Override
    public void onCreate(SQLiteDatabase db) {
        String CREATION_TABLE = "CREATE TABLE bankAccountDetails ( "
                + "id INTEGER PRIMARY KEY AUTOINCREMENT, " + "nameBank TEXT, "
                + "accountNo TEXT, " + "ifscCode TEXT )";

        db.execSQL(CREATION_TABLE);
    }

    @Override
    public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {

    }

    public void deleteBankDetails(Bank bank) {
        // Get reference to writable DB
        SQLiteDatabase db = this.getWritableDatabase();
        db.delete(TABLE_NAME, null, null);
        db.close();
    }

    public Bank getBank(int id) {
        SQLiteDatabase db = this.getReadableDatabase();
        Cursor cursor = db.query(TABLE_NAME, // a. table
                COLUMNS, // b. column names
                " id = ?", // c. selections
                new String[]{String.valueOf(id)}, // d. selections args
                null, // e. group by
                null, // f. having
                null, // g. order by
                null); // h. limit

        if (cursor != null)
            cursor.moveToFirst();

        Bank player = new Bank();
        player.setId(Integer.parseInt(cursor.getString(0)));
        player.setBankName(cursor.getString(1));
        player.setAccountNo(cursor.getString(2));
        player.setIfscCode(cursor.getString(3));
        return player;
    }


    public List<Bank> bankDetails() {

        List<Bank> banks = new LinkedList<Bank>();
        String query = "SELECT  * FROM " + TABLE_NAME;
        SQLiteDatabase db = this.getWritableDatabase();
        Cursor cursor = db.rawQuery(query, null);
        Bank bank = null;

        if (cursor.moveToFirst()) {
            do {
                bank = new Bank();
                bank.setId(Integer.parseInt(cursor.getString(0)));
                bank.setBankName(cursor.getString(1));
                bank.setAccountNo(cursor.getString(2));
                bank.setIfscCode(cursor.getString(2));
                banks.add(bank);
            } while (cursor.moveToNext());
        }
        return banks;
    }

    public void addBankDataDetails(Bank bank) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_BANK_NAME, bank.getBankName());
        values.put(KEY_BANK_ACCOUNT_NO, bank.getAccountNo());
        values.put(KEY_IFSC_CODE, bank.getIfscCode());
        // insert
        db.insert(TABLE_NAME, null, values);
        db.close();
    }

    public int updateBankDetails(Bank bank) {
        SQLiteDatabase db = this.getWritableDatabase();
        ContentValues values = new ContentValues();
        values.put(KEY_BANK_NAME, bank.getBankName());
        values.put(KEY_BANK_ACCOUNT_NO, bank.getAccountNo());
        values.put(KEY_IFSC_CODE, bank.getIfscCode());

        int i = db.update(TABLE_NAME, // table
                values, // column/value
                "id = ?", // selections
                new String[]{String.valueOf(bank.getId())});

        db.close();
        return i;
    }

}
