package pl.javastart.ap.database.manual;

import android.app.Activity;
import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import pl.javastart.ap.R;
import pl.javastart.ap.database.manual.model.Book;
import pl.javastart.ap.database.manual.model.User;

public class ManualDatabaseBookListActivity extends Activity {

    private Button addBookButton;
    private EditText titleEditText;
    private EditText authorEditText;
    private EditText publicationYearEditText;
    private ListView bookListView;
    private BookListAdapter bookListAdapter;

    private List<Book> bookList = new ArrayList<>();

    private ManualDatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_database_book_list);
        databaseHelper = ManualDatabaseHelper.getInstance(getApplicationContext());

        titleEditText = (EditText) findViewById(R.id.title);
        authorEditText = (EditText) findViewById(R.id.author);
        publicationYearEditText = (EditText) findViewById(R.id.publication_year);
        addBookButton = (Button) findViewById(R.id.add_new_book_button);
        bookListView = (ListView) findViewById(R.id.book_list);

        addActionForBookAddButton(addBookButton);

        bookListAdapter = new BookListAdapter();
        updateBookList();
        bookListView.setAdapter(bookListAdapter);

    }

    private void addActionForBookAddButton(Button addBookButton) {
        addBookButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addBook();
            }
        });
    }

    private void addBook() {
        String title = titleEditText.getText().toString();
        String author = authorEditText.getText().toString();
        String publicationYear = publicationYearEditText.getText().toString();

        SQLiteDatabase db = databaseHelper.getWritableDatabase();

        String sql = "insert into book(title, author, publication_year) values (?, ?, ?)";
        db.execSQL(sql, new String[]{title, author, publicationYear});

        db.close();

        updateBookList();
    }

    private void updateBookList() {
        bookList.clear();

        SQLiteDatabase db = databaseHelper.getWritableDatabase();
        Cursor cursor = db.rawQuery("select * from book", null);

        while (cursor.moveToNext()) {
            Book book = new Book();
            book.setId(cursor.getInt(0));
            book.setTitle(cursor.getString(1));
            book.setAuthor(cursor.getString(2));
            book.setYear(cursor.getInt(3));
            bookList.add(book);
        }

        bookListAdapter.notifyDataSetChanged();
    }

    private class BookListAdapter extends BaseAdapter {

        @Override
        public int getCount() {
            return bookList.size();
        }

        @Override
        public Book getItem(int position) {
            return bookList.get(position);
        }

        @Override
        public long getItemId(int position) {
            return getItem(position).getId();
        }

        @Override
        public View getView(int position, View convertView, ViewGroup parent) {

            if (convertView == null) {
                LayoutInflater inflater = LayoutInflater.from(ManualDatabaseBookListActivity.this);
                convertView = inflater.inflate(android.R.layout.simple_list_item_1, parent, false);
            }

            TextView text = (TextView) convertView.findViewById(android.R.id.text1);
            Book book = getItem(position);
            text.setText(book.toString());

            return convertView;
        }
    }

}
