package com.example.sellclothesapp.dao;

import android.content.ContentValues;
import android.content.Context;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;

import com.example.sellclothesapp.constants.AppConstant;
import com.example.sellclothesapp.database.MySqlHelper;
import com.example.sellclothesapp.model.Bill;
import com.example.sellclothesapp.model.BillInFo;
import com.example.sellclothesapp.model.Bookmark;
import com.example.sellclothesapp.model.Card;
import com.example.sellclothesapp.model.Product;
import com.example.sellclothesapp.model.User;

import java.util.ArrayList;
import java.util.List;

public class Controller {
    private MySqlHelper mySqlHelper;
    private SQLiteDatabase sqLiteDatabase;

    public Controller(Context context) {
        mySqlHelper = new MySqlHelper(context);
    }

    public User getUserLogin(String email, String password) {
        this.sqLiteDatabase = mySqlHelper.getReadableDatabase();
        String sql = "select * from " + AppConstant.TABLE_USER + " where " + AppConstant.USER_EMAIL + "='" + email + "' and " + AppConstant.USER_PASSWORD + "= '" + password + "'";
        Cursor cursor = this.sqLiteDatabase.rawQuery(sql, null);
        User user = new User();
        if (cursor.moveToFirst()) {
            user.setId(Integer.parseInt(cursor.getString(0)));
            user.setName(cursor.getString(1));
            user.setEmail(cursor.getString(2));
            user.setPhone(cursor.getString(3));
            user.setPassword(cursor.getString(4));
            user.setAddress(cursor.getString(5));
        }
        cursor.close();
        this.sqLiteDatabase.close();
        return user;
    }

    public boolean registerUser(User user) {
        this.sqLiteDatabase = mySqlHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(AppConstant.USER_NAME, user.getName());
        contentValues.put(AppConstant.USER_EMAIL, user.getEmail());
        contentValues.put(AppConstant.USER_PHONE, user.getPhone());
        contentValues.put(AppConstant.USER_PASSWORD, user.getPassword());
        contentValues.put(AppConstant.USER_ADDRESS, user.getAddress());
        long result = this.sqLiteDatabase.insert(AppConstant.TABLE_USER, null, contentValues);
        return result > 0;
    }

    public List<Product> getAllListProduct() {
        List<Product> list = new ArrayList<Product>();
        this.sqLiteDatabase = mySqlHelper.getReadableDatabase();
        String sql = "select * from " + AppConstant.TABLE_PRODUCT;
        Cursor cursor = this.sqLiteDatabase.rawQuery(sql, null);
        Product product;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                product = new Product(Integer.parseInt(cursor.getString(0)), cursor.getString(2), Integer.parseInt(cursor.getString(1)) == 1 ? "Biti’s" : Integer.parseInt(cursor.getString(1)) == 2 ? "Ananas" : Integer.parseInt(cursor.getString(1)) == 3 ? "RIENEVAN" : "MIDAZ", cursor.getString(6), cursor.getInt(4), cursor.getInt(3), cursor.getString(5));
                list.add(product);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.sqLiteDatabase.close();
        return list;
    }

    public List<Product> getListProductByCategory(int id) {
        List<Product> list = new ArrayList<Product>();
        this.sqLiteDatabase = mySqlHelper.getReadableDatabase();
        String sql = "select * from " + AppConstant.TABLE_PRODUCT + " where " + AppConstant.PRODUCT_ID_CATEGORY + " ='" + id + "'";
        Cursor cursor = this.sqLiteDatabase.rawQuery(sql, null);
        Product product;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                product = new Product(Integer.parseInt(cursor.getString(0)), cursor.getString(2), Integer.parseInt(cursor.getString(1)) == 1 ? "Biti’s" : Integer.parseInt(cursor.getString(1)) == 2 ? "Ananas" : Integer.parseInt(cursor.getString(1)) == 3 ? "RIENEVAN" : "MIDAZ", cursor.getString(6), cursor.getInt(4), cursor.getInt(3), cursor.getString(5));
                list.add(product);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.sqLiteDatabase.close();
        return list;
    }

    public Product getProductById(int id) {
        this.sqLiteDatabase = mySqlHelper.getReadableDatabase();
        String sql = "select * from " + AppConstant.TABLE_PRODUCT + " where " + AppConstant.PRODUCT_ID + "='" + id + "'";
        Cursor cursor = this.sqLiteDatabase.rawQuery(sql, null);
        Product product = null;
        if (cursor.moveToFirst()) {
            product = new Product(Integer.parseInt(cursor.getString(0)), cursor.getString(2), Integer.parseInt(cursor.getString(1)) == 1 ? "Biti’s" : Integer.parseInt(cursor.getString(1)) == 2 ? "Ananas" : Integer.parseInt(cursor.getString(1)) == 3 ? "RIENEVAN" : "MIDAZ", cursor.getString(6), cursor.getInt(4), cursor.getInt(3), cursor.getString(5));
        }
        cursor.close();
        this.sqLiteDatabase.close();
        return product;
    }

    public boolean addBookmark(Bookmark bookmark) {
        this.sqLiteDatabase = mySqlHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(AppConstant.BOOKMARK_ID_PRODUCT, bookmark.getIdProduct());
        contentValues.put(AppConstant.BOOKMARK_ID_USER, bookmark.getIdUser());
        long result = this.sqLiteDatabase.insert(AppConstant.TABLE_BOOKMARK, null, contentValues);
        return result > 0;
    }

    public boolean deleteBookmark(int id) {
        this.sqLiteDatabase = mySqlHelper.getWritableDatabase();
        return sqLiteDatabase.delete(AppConstant.TABLE_BOOKMARK, AppConstant.BOOKMARK_ID + "=?", new String[]{String.valueOf(id)}) > 0;
    }

    public List<Bookmark> getAllListBookmarkByIdUser(int id) {
        List<Bookmark> list = new ArrayList<Bookmark>();
        this.sqLiteDatabase = mySqlHelper.getReadableDatabase();
        String sql = "select * from " + AppConstant.TABLE_BOOKMARK + " where " + AppConstant.BOOKMARK_ID_USER + " ='" + id + "'";
        Cursor cursor = this.sqLiteDatabase.rawQuery(sql, null);
        Bookmark bookmark;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                bookmark = new Bookmark(cursor.getInt(0), cursor.getInt(2), cursor.getInt(1));
                list.add(bookmark);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.sqLiteDatabase.close();
        return list;
    }

    public List<Product> getAllListProductBookmarkById(int id) {
        List<Product> list = new ArrayList<Product>();
        this.sqLiteDatabase = mySqlHelper.getReadableDatabase();
        String sql = "select * from " + AppConstant.TABLE_BOOKMARK + " where " + AppConstant.BOOKMARK_ID_USER + " ='" + id + "'";
        Cursor cursor = this.sqLiteDatabase.rawQuery(sql, null);
        Product product;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                product = getProductById(cursor.getInt(2));
                list.add(product);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.sqLiteDatabase.close();
        return list;
    }

    public Bookmark getBookmarkByIdUserAndIdProduct(int idProduct, int idUser) {
        this.sqLiteDatabase = mySqlHelper.getReadableDatabase();
        String sql = "select * from " + AppConstant.TABLE_BOOKMARK + " where " + AppConstant.BOOKMARK_ID_PRODUCT + "='" + idProduct + "' and " + AppConstant.BOOKMARK_ID_USER + "='" + idUser + "'";
        Cursor cursor = this.sqLiteDatabase.rawQuery(sql, null);
        Bookmark bookmark = null;
        if (cursor.moveToFirst()) {
            bookmark = new Bookmark(cursor.getInt(0), cursor.getInt(2), cursor.getInt(1));
        } else {
            bookmark = null;
        }
        cursor.close();
        this.sqLiteDatabase.close();
        return bookmark;
    }

    public Card getCardByUserId(int idProduct, int idUser) {
        this.sqLiteDatabase = mySqlHelper.getReadableDatabase();
        String sql = "select * from " + AppConstant.TABLE_CARD + " where " + AppConstant.CARD_ID_PRODUCT + "='" + idProduct + "' and " + AppConstant.CARD_ID_USER + "='" + idUser + "'";
        Cursor cursor = this.sqLiteDatabase.rawQuery(sql, null);
        Card card = null;
        if (cursor.moveToFirst()) {
            card = new Card(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4));
        }
        cursor.close();
        this.sqLiteDatabase.close();
        return card;
    }

    public boolean addToCard(Card card) {
        this.sqLiteDatabase = mySqlHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(AppConstant.CARD_ID_USER, card.getIdUser());
        contentValues.put(AppConstant.CARD_ID_PRODUCT, card.getIdProduct());
        contentValues.put(AppConstant.CARD_SIZE, card.getSize());
        contentValues.put(AppConstant.CARD_QUALITY, card.getQuality());
        long result = this.sqLiteDatabase.insert(AppConstant.TABLE_CARD, null, contentValues);
        return result > 0;
    }

    public boolean deleteCard(int id) {
        this.sqLiteDatabase = mySqlHelper.getWritableDatabase();
        return sqLiteDatabase.delete(AppConstant.TABLE_CARD, AppConstant.CARD_ID + "=?", new String[]{String.valueOf(id)}) > 0;
    }

    //int id, int idUser, int idProduct, int size, int quality
    public List<Card> getAllListCardByUserId(int id) {
        List<Card> list = new ArrayList<>();
        this.sqLiteDatabase = mySqlHelper.getReadableDatabase();
        String sql = "select * from " + AppConstant.TABLE_CARD + " where " + AppConstant.CARD_ID_USER + " ='" + id + "'";
        Cursor cursor = this.sqLiteDatabase.rawQuery(sql, null);
        Card card;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                card = new Card(cursor.getInt(0), cursor.getInt(1), cursor.getInt(2), cursor.getInt(3), cursor.getInt(4));
                list.add(card);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.sqLiteDatabase.close();
        return list;
    }

    public List<Product> getListProductByCardUserId(int id) {
        List<Product> list = new ArrayList<>();
        this.sqLiteDatabase = mySqlHelper.getReadableDatabase();
        String sql = "select * from " + AppConstant.TABLE_CARD + " where " + AppConstant.CARD_ID_USER + " ='" + id + "'";
        Cursor cursor = this.sqLiteDatabase.rawQuery(sql, null);
        Product product;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                product = getProductById(cursor.getInt(2));
                product.setSize(cursor.getInt(3));
                list.add(product);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.sqLiteDatabase.close();
        return list;
    }

    public boolean addBill(Bill bill) {
        this.sqLiteDatabase = mySqlHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(AppConstant.BILL_ID, bill.getId());
        contentValues.put(AppConstant.BILL_ID_USER, bill.getIdUser());
        contentValues.put(AppConstant.BILL_NAME_USER, bill.getNameUser());
        contentValues.put(AppConstant.BILL_PHONE_USER, bill.getPhoneUser());
        contentValues.put(AppConstant.BILL_ADDRESS_USER, bill.getAddress());
        contentValues.put(AppConstant.BILL_PAYMENT, bill.getPayment());
        contentValues.put(AppConstant.BILL_PRICE, bill.getSum());
        long result = this.sqLiteDatabase.insert(AppConstant.TABLE_BILL, null, contentValues);
        return result > 0;
    }

    public boolean addBillInfo(BillInFo billInFo) {
        this.sqLiteDatabase = mySqlHelper.getWritableDatabase();
        ContentValues contentValues = new ContentValues();
        contentValues.put(AppConstant.BILL_INFO_ID_BILL, billInFo.getIdBill());
        contentValues.put(AppConstant.BILL_INFO_ID_USER, billInFo.getIdUser());
        contentValues.put(AppConstant.BILL_INFO_ID_PRODUCT, billInFo.getIdProduct());
        contentValues.put(AppConstant.BILL_INFO_COUNT_PRODUCT, billInFo.getCountProduct());
        long result = this.sqLiteDatabase.insert(AppConstant.TABLE_BILL_INFO, null, contentValues);
        return result > 0;
    }

    public List<Bill> getBillByIdUser(int id) {
        List<Bill> list = new ArrayList<>();
        this.sqLiteDatabase = mySqlHelper.getReadableDatabase();
        String sql = "select * from " + AppConstant.TABLE_BILL + " where " + AppConstant.BILL_ID_USER + " ='" + id + "'";
        Cursor cursor = this.sqLiteDatabase.rawQuery(sql, null);
        Bill bill;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                bill = new Bill(cursor.getInt(0), cursor.getInt(1), cursor.getString(2), cursor.getString(3), cursor.getString(4), cursor.getInt(5), cursor.getInt(6));
                list.add(bill);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.sqLiteDatabase.close();
        return list;
    }

    public List<Product> getListProductByListBillAndIdUser(int idUser, int idBill) {
        List<Product> list = new ArrayList<>();
        this.sqLiteDatabase = mySqlHelper.getReadableDatabase();
        String sql = "select * from " + AppConstant.TABLE_BILL_INFO + " where " + AppConstant.BILL_INFO_ID_USER + " ='" + idUser + "' and " + AppConstant.BILL_INFO_ID_BILL + "='" + idBill + "'";
        Cursor cursor = this.sqLiteDatabase.rawQuery(sql, null);
        Product product;
        if (cursor.moveToFirst()) {
            while (!cursor.isAfterLast()) {
                product = getProductById(cursor.getInt(3));
                product.setCountProduct(cursor.getInt(4));
                list.add(product);
                cursor.moveToNext();
            }
        }
        cursor.close();
        this.sqLiteDatabase.close();
        return list;
    }
}
