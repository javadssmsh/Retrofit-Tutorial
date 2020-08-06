package developer.company.retrofittutorial;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

public class BookDetailActivity extends AppCompatActivity {

    public static final String ID = "id";

    int id;
    Bundle bundle;

    TextView bookName, bookDescription, bookPrice, bookPublished, bookAuthor, bookGenre;
    ImageView imgBook;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_book_detail);

        bookName = findViewById(R.id.tv_detail_name);
        bookDescription = findViewById(R.id.tv_detail_description);
        bookPrice = findViewById(R.id.tv_detail_price);
        bookPublished = findViewById(R.id.tv_detail_published);
        bookAuthor = findViewById(R.id.tv_detail_author);
        bookGenre = findViewById(R.id.tv_detail_genre);
        imgBook = findViewById(R.id.img_detail_book);

        id = Integer.parseInt(getIntent().getStringExtra(ID));

        bundle = getIntent().getExtras();
        bookName.setText(bundle.getString("name"));
        bookDescription.setText(bundle.getString("description"));
        bookAuthor.setText(new StringBuilder("Author : ") + bundle.getString("author"));
        bookGenre.setText(new StringBuilder("Genre  : ") + bundle.getString("genre"));
        bookPrice.setText(bundle.getString("price"));
        bookPublished.setText(new StringBuilder("Published : ") + bundle.getString("published"));


        Picasso.get().load(bundle.getString("link_img")).into(imgBook);


    }
}