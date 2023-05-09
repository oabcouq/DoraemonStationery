package com.example.sellclothesapp.database;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

import com.example.sellclothesapp.constants.AppConstant;

public class MySqlHelper extends SQLiteOpenHelper {
    public MySqlHelper(Context context) {
        super(context, "database3.db", null, 1);
    }

    @Override
    public void onCreate(SQLiteDatabase sqLiteDatabase) {
        String sql = "create table " + AppConstant.TABLE_USER + "(" + AppConstant.USER_ID + " integer primary key AUTOINCREMENT, " + AppConstant.USER_NAME + " text not null," + AppConstant.USER_EMAIL + " text not null," + AppConstant.USER_PHONE + " text not null," + AppConstant.USER_PASSWORD + " text not null," + AppConstant.USER_ADDRESS + " text not null" + ")";
        sqLiteDatabase.execSQL(sql);

        sql = "create table " + AppConstant.TABLE_PRODUCT + "(" + AppConstant.PRODUCT_ID + " integer primary key AUTOINCREMENT, " + AppConstant.PRODUCT_ID_CATEGORY + " integer not null, " + AppConstant.PRODUCT_NAME + " text not null, " + AppConstant.PRODUCT_COUNT_FEEDBACK + " integer not null, " + AppConstant.PRODUCT_PRICE + " integer not null, " + AppConstant.PRODUCT_MORE + " text not null , " + AppConstant.PRODUCT_IMAGE + " text not null" + ")";
        sqLiteDatabase.execSQL(sql);

        sql = "create table " + AppConstant.TABLE_BOOKMARK + "(" + AppConstant.BOOKMARK_ID + " integer primary key AUTOINCREMENT, " + AppConstant.BOOKMARK_ID_USER + " integer not null, " + AppConstant.BOOKMARK_ID_PRODUCT + " integer not null" + ")";
        sqLiteDatabase.execSQL(sql);

        sql = "create table " + AppConstant.TABLE_CARD + "(" + AppConstant.CARD_ID + " integer primary key AUTOINCREMENT, " + AppConstant.CARD_ID_USER + " integer not null, " + AppConstant.CARD_ID_PRODUCT + " integer not null," + AppConstant.CARD_SIZE + " integer not null," + AppConstant.CARD_QUALITY + " integer not null" + ")";
        sqLiteDatabase.execSQL(sql);

        sql = "create table " + AppConstant.TABLE_BILL + "(" + AppConstant.BILL_ID + " integer primary key, " + AppConstant.BILL_ID_USER + " integer not null, " + AppConstant.BILL_NAME_USER + " text not null," + AppConstant.BILL_PHONE_USER + " text not null," + AppConstant.BILL_ADDRESS_USER + " text not null," + AppConstant.BILL_PAYMENT + " integer not null," + AppConstant.BILL_PRICE + " integer not null" + ")";
        sqLiteDatabase.execSQL(sql);

        sql = "create table " + AppConstant.TABLE_BILL_INFO + "(" + AppConstant.BILL_INFO_ID + " integer primary key AUTOINCREMENT, " + AppConstant.BILL_INFO_ID_BILL + " integer not null, " + AppConstant.BILL_INFO_ID_USER + " integer not null," + AppConstant.BILL_INFO_ID_PRODUCT + " integer not null," + AppConstant.BILL_INFO_COUNT_PRODUCT + " integer not null" + ")";
        sqLiteDatabase.execSQL(sql);

// váy là id là 3
        // áo là 1
        // quần là 2
        //đầm là 4

        //Notebook
        sql = "INSERT INTO " + AppConstant.TABLE_PRODUCT + " VALUES ( null ,3,'Sổ caro 21x33 - 336 trang (unit: Cuốn)' ,23 , 59000, ' Là sản phẩm sổ tập dạng sổ bìa cứng với khổ giấy 21x33cm, giấy caro dày 344 trang. Sản phẩm thích hợp cho việc ghi chú xuất nhập hàng hóa tại kho bãi, lịch công tác, quản lý ra vào cổng công ty' , 'https://www.anlocviet.vn/upload/product/so-caro-dac-biet-5967_240x240.png' )";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO " + AppConstant.TABLE_PRODUCT + " VALUES ( null ,3,'Sổ lò xo A6 - 200 trang (unit: Cuốn)' ,34 , 13000, 'Là sản phẩm sổ tập thương hiệu LAX dạng ghi chú khổ A6, giấy kẻ ngang trắng đẹp ĐL 70gsm, dày 200 trang có lò xo bên mép trái thuận tiện cho việc ghi chép hoặc xé rời các trang cần thiết phục vụ cho công việc văn phòng. ' , 'https://www.anlocviet.vn/upload/product/so-lo-xo-a6-6843_240x240.jpg' )";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO " + AppConstant.TABLE_PRODUCT + " VALUES ( null ,3,'Sổ lò xo A4 không dòng kẻ (unit: Cuốn)' ,44 , 63000, 'Sổ lò xo A4 không dòng kẻ có bìa sổ và ruột giấy được làm bằng giấy fort cao cấp, dày dặn, không bị lem nhòe khi viết.' , 'https://www.anlocviet.vn/upload/product/so-lo-xo-khong-dong-ke-3783_240x240.jpg' )";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO " + AppConstant.TABLE_PRODUCT + " VALUES ( null ,3,'Sổ da A4 CK10 - 108 trang (unit: Cuốn)' ,13 , 38000, 'Là sản phẩm sổ tập dạng sổ bìa da cứng màu đen với các góc bo tròn viền vàng trau chuốt, giấy kẻ ngang dày 100 trang. Sản phẩm thích hợp cho việc ghi chú nội dung quan trọng tại các cuộc họp tại văn phòng công ty. ' , 'https://www.anlocviet.vn/upload/product/so-da-ck10-mong-5378_240x240.jpg' )";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO " + AppConstant.TABLE_PRODUCT + " VALUES ( null ,3,'Sổ lò xo A7 - 200 trang (unit: Cuốn)' ,54 , 8000, 'Đây là sổ tập thương hiệu LAX dạng ghi chú khổ A7, giấy kẻ ngang trắng đẹp ĐL 70gsm, dày 200 trang có lò xo bên mép trái thuận tiện cho việc ghi chép hoặc xé rời các trang cần thiết phục vụ cho công việc văn phòng.' , 'https://www.anlocviet.vn/upload/product/so-lo-xo-a7-674_240x240.jpg' )";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO " + AppConstant.TABLE_PRODUCT + " VALUES ( null ,3,'Tập 100 trang Dân gian (unit: Cuốn)' ,57 , 4000, 'Là sản phẩm tập vở nhãn hiệu Dân gian, chất lượng cao cấp được nhiều khách hàng ưa chuộng tin dùng' , 'https://www.anlocviet.vn/upload/product/91c8004d60d14afca8c170491841c331-4712_180x240.jpg' )";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO " + AppConstant.TABLE_PRODUCT + " VALUES ( null ,3,'Tập 120 trang ngang khổ lớn Campus (unit: Cuốn)' ,24 , 15000, 'Là sản phẩm tập vở nhãn hiệu Campus, chất lượng cao cấp được nhiều khách hàng ưa chuộng tin dùng' , 'https://www.anlocviet.vn/upload/product/nbadfl962-2905_240x240.jpg' )";
        sqLiteDatabase.execSQL(sql);

        // School supplies

        sql = "INSERT INTO " + AppConstant.TABLE_PRODUCT + " VALUES ( null ,4,'Chì màu học sinh T8123 (unit: Hộp)' ,23 , 16000, 'Màu sắc chì màu học sinh T8123 tươi sáng đúng chuẩn màu mỹ thuật. Tô mịn, ít bụi, màu phủ đều, bền màu.' , 'https://www.anlocviet.vn/upload/product/untitled-1294_240x193.63636363636.png' )";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO " + AppConstant.TABLE_PRODUCT + " VALUES ( null ,4,'Bóp ví viết học sinh 2853A (unit: Cái)' ,34 , 65000, 'Tiện lợi và bền bỉ, chống thấm nước hiệu quả' , 'https://www.anlocviet.vn/upload/product/bop-vi-viet-hoc-sinh-2853a-8225_240x240.jpg' )";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO " + AppConstant.TABLE_PRODUCT + " VALUES ( null ,4,'Hộp bút học sinh 8808 (unit: Cái)' ,44 , 29000, 'Kích thước của hộp là: 22x9x4cm nhé, tha hồ mình để thước kẻ này, bút chì này, bút máy này, kéo này, nhiều thứ lắm nha à, mà bạn nào chưa đi học lớp 1 thì mình còn để được cả phiếu bé ngoan nữa cơ' ,'https://www.anlocviet.vn/upload/product/untitled-2024_240x183.77142857143.png')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO " + AppConstant.TABLE_PRODUCT + " VALUES ( null ,4,'Đất nặn cánh cam to (unit: Hộp)' ,13 , 22000, 'Chất liệu an toàn, dễ dàng nặn và tạo kiểu theo ý muôn. Tránh ánh nắng và môi trường ẩm ướt' , 'https://www.anlocviet.vn/upload/product/1e-2509_184.81178396072x240.png' )";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO " + AppConstant.TABLE_PRODUCT + " VALUES ( null ,4,'Cặp tài liệu học sinh C1 (unit: Cái)' ,54 , 43000, 'Chất liệu an toàn, bền bỉ, chống thấm nước. Kích thước nhỏ gọn, tiện lợi' , 'https://www.anlocviet.vn/upload/product/untitled-1874_240x215.56886227545.png' )";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO " + AppConstant.TABLE_PRODUCT + " VALUES ( null ,4,'Bóp ví viết học sinh 8185 Cococolo (unit: Cái)' ,57 , 26000, 'Tiện lợi và nhỏ gọn là những ưu điểm không thể phủ nhận. Ngoài ra, bóp ví viết còn có thể chống thấm nước, chống bụi hiệu quả.' , 'https://www.anlocviet.vn/upload/product/bop-vi-viet-hoc-sinh-cococolo-6548_240x240.jpg' )";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO " + AppConstant.TABLE_PRODUCT + " VALUES ( null ,4,'Bảng từ học sinh size nhỏ (unit: Cái)' ,24 , 26000, 'Chất liệu an toà, bền bỉ. Tiện lợi mang theo và dễ dàng học tập mọi lúc. Hạn chế sử dụng ở những nơi ẩm ướt và cường độ ánh sáng mạnh' , 'https://www.anlocviet.vn/upload/product/3e-1281_240x184.61538461538.png' )";
        sqLiteDatabase.execSQL(sql);


        // Paper of all kinds

        sql = "INSERT INTO " + AppConstant.TABLE_PRODUCT + " VALUES ( null ,1,'Giấy A4 Uni Plus 80 gsm màng co (unit: Ram)' ,24 , 70000, 'Xuất xứ Việt Nam, an toàn khi sử dụng' , 'https://www.anlocviet.vn/upload/product/uni-plus-mang-co-80gsm-1164_240x240.jpg')";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO " + AppConstant.TABLE_PRODUCT + " VALUES ( null ,1,'Giấy A4 Uni Plus 72 gsm màng co (unit: Ram)' ,5 , 68000, 'Xuất xứ Việt Nam, an toàn khi sử dụng' , 'https://www.anlocviet.vn/upload/product/uni-plus-co-mang-co-363_240x240.jpg' )";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO " + AppConstant.TABLE_PRODUCT + " VALUES ( null ,1,'Giấy A4 Viva 70 gsmt (unit: Ram)' ,54 , 69000, 'Xuất xứ Việt Nam, an toàn khi sử dụng. Số lượng đủ 500 tờ.' , 'https://www.anlocviet.vn/upload/product/giayvivapaper-2944_240x240.jpg' )";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO " + AppConstant.TABLE_PRODUCT + " VALUES ( null ,1,'Giấy A4 PaperOne 70 gsm (unit: Ram)' ,22 , 76000, 'Xuất xứ Indonesia, đã qua kiểm định an toàn' , 'https://www.anlocviet.vn/upload/product/giay-paperone-a4-70-gsm-4878_240x240.jpg' )";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO " + AppConstant.TABLE_PRODUCT + " VALUES ( null ,1,'Giấy A4 Delight 70 gsm (unit: Ram)' ,12 , 69000, 'Xuất xứ Indonesia, đã qua kiểm định an toàn' , 'https://www.anlocviet.vn/upload/product/giaydelighta470gsmgiare2-8177_240x240.jpg' )";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO " + AppConstant.TABLE_PRODUCT + " VALUES ( null ,1,'Giấy A4 Quality 70 gsm (unit: Ram)' ,34 , 7100, 'Giấy A4 Quality 70Gsm với kích thước A4, thân thiện với môi trường và thích hợp với hầu hết các loại máy in phun, máy in laser, máy fax laser, máy photocopy.' , 'https://www.anlocviet.vn/upload/product/giayquality-9533_240x215.57046979866.jpg' )";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO " + AppConstant.TABLE_PRODUCT + " VALUES ( null ,1,'Giấy A4 Double A 80 gsm (unit: Ram)' ,87 , 93000, 'Khổ giấy A4 là khổ giấy thông dụng trong văn phòng hành chính sự nghiệp, văn phòng công ty, trường đại học, ..với kích thước (21*29,7cm) là một cuốn sổ rất vừa cầm trên tay, dễ mang theo, lưu lượng văn bản vừa đủ không quá ngắn như A5 và không quá lớn như khổ A3. Đây cũng là khổ giấy chính trong các loại giấy in photo được sử dụng nhiều nhất hiện nay.' , 'https://www.anlocviet.vn/upload/product/giay-a4-double-a-70-gsm-7840_240x240.jpg' )";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO " + AppConstant.TABLE_PRODUCT + " VALUES ( null ,1,'Giấy A4 Supreme 70 gsm (unit: Ram)' ,45 , 73000, ' Đây là loại giấy in photo có độ trắng sáng cao, thường sử dụng để in, đóng thành cuốn, dùng làm phiếu thu, chi, đặt đơn hàng...' , 'https://www.anlocviet.vn/upload/product/giayinsupremea470gsm500tovmaxmin-3674_240x240.jpg' )";
        sqLiteDatabase.execSQL(sql);

        //Pen-ink

        sql = "INSERT INTO " + AppConstant.TABLE_PRODUCT + " VALUES ( null ,2,'Bút xóa nước Thiên Long FO-CP-01 (unit: Cái)' ,24 , 18000, 'An toàn khi sử dụng. Thời gian khô mực nhanh chóng' , 'https://www.anlocviet.vn/upload/product/image20211011113530-3362_240x240.png' )";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO " + AppConstant.TABLE_PRODUCT + " VALUES ( null ,2,'Bút chì chuốt Gstar P333 4B (unit: Cây)' ,24 , 2000, 'Dễ cầm, chì an toàn và viết rất mịn' , 'https://www.anlocviet.vn/upload/product/but-chi-chuot-gstar-p333-3b-4b-5b-6b-8686_240x87.918593894542.png' )";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO " + AppConstant.TABLE_PRODUCT + " VALUES ( null ,2,'Bút bi Pentel BK250 (unit: Cây)' ,24 , 40000, 'Bút bi bấm có màu mực đen, ra đều, đẹp và không phai màu sau thời gian dài sử dụng. Chất liệu vỏ được làm từ cao su mềm mại, giúp bạn dễ dàng điều khiển và cho nét chữ thật đẹp' , 'https://www.anlocviet.vn/upload/product/h3-3815_180x240.jpg' )";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO " + AppConstant.TABLE_PRODUCT + " VALUES ( null ,2,'Bút sáp màu Thiên Long CR-C05/DO 16 màu (unit: Hộp)' ,24 , 27000, 'Màu sắc tươi sáng đúng chuẩn màu mỹ thuật. Tô êm, ít bụi.3' , 'https://www.anlocviet.vn/upload/product/but-sap-mau-16-mau-7623_240x240.jpg' )";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO " + AppConstant.TABLE_PRODUCT + " VALUES ( null ,2,'Bút lông bảng Thiên Long WB-02 (unit: Cây)' ,24 , 7000, 'Là bút lông bảng 1 đầu có nét viết có màu ổn định trong suốt quá trình sử dụng, lưu trữ, bảo quản' , 'https://www.anlocviet.vn/upload/product/dsc09478-3093_240x240.jpg' )";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO " + AppConstant.TABLE_PRODUCT + " VALUES ( null ,2,'Bút dạ quang Thiên Long HL-03 (unit: Cây)' ,24 , 8000, 'Một trong những loại bút dạ quang được rất hiều người sử dụng, nhờ thiết kệ nhỏ gọn vừa tiện dụng vừa đẹp mắt. HL - 03 là sự lựa chọn tuyệt vời của nhiều khách hàng từ các ngành nghề khác nhau, đặc biệt cho nhân viên văn phòng và sinh viên' , 'https://www.anlocviet.vn/upload/product/butdaquangthienlonghl03camvmax1-7619_240x152.21052631579.png' )";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO " + AppConstant.TABLE_PRODUCT + " VALUES ( null ,2,'Bút bi Thiên Long 093 (unit: Cây)' ,24 , 4000, 'Bút bi Thiên Long 093 là dạng bút đùn là dạng bút không ruột, mực được bơm thẳng vào vỏ bút (hay nói cách khác là ruột bút cũng chính là thân bút).' , 'https://www.anlocviet.vn/upload/product/butbithienlongtl9320large-696_240x240.jpg' )";
        sqLiteDatabase.execSQL(sql);
        sql = "INSERT INTO " + AppConstant.TABLE_PRODUCT + " VALUES ( null ,2,'Bút bi Thiên Long TL-08 (unit: Cây)' ,24 , 5000, 'Bút bi dạng đậy nắp, kiểu dáng truyền thống và sử dụng đầu bút mới có adapter to màu đồng thau - nét chữ viết ra hơi đậm nét' , 'https://www.anlocviet.vn/upload/product/butbithienlongtl0803large-6621_240x240.jpg' )";
        sqLiteDatabase.execSQL(sql);
    }

    @Override
    public void onUpgrade(SQLiteDatabase sqLiteDatabase, int i, int i1) {
        sqLiteDatabase.execSQL("DROP TABLE IF EXISTS " + AppConstant.TABLE_USER);
        onCreate(sqLiteDatabase);
    }
}
