package developer.company.retrofittutorial;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.squareup.picasso.Picasso;

import java.util.List;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

public class BooksAdapter extends RecyclerView.Adapter<BooksAdapter.MyViewHolder> {

    Context context;
    List<Books> data;

    public BooksAdapter(Context context, List<Books> data) {
        this.context = context;
        this.data = data;
    }

    @NonNull
    @Override
    public MyViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(context).inflate(R.layout.item_books, parent, false);
        return new MyViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull MyViewHolder holder, int position) {

        holder.txtBookName.setText(data.get(position).getName());
        holder.txtBookAuthor.setText(data.get(position).getAuthor());
        holder.txtBookPrice.setText(data.get(position).getPrice());

        Picasso.get().load(data.get(position).getLink_img()).into(holder.imgBook);

    }

    @Override
    public int getItemCount() {
        return data.size();
    }

    public class MyViewHolder extends RecyclerView.ViewHolder implements View.OnClickListener {

        TextView txtBookName,txtBookAuthor,txtBookPrice;
        ImageView imgBook;

        public MyViewHolder(@NonNull View itemView) {
            super(itemView);

            txtBookName = itemView.findViewById(R.id.name_book);
            txtBookAuthor = itemView.findViewById(R.id.author_book);
            txtBookPrice = itemView.findViewById(R.id.txt_price);
            imgBook = itemView.findViewById(R.id.img_books);

            itemView.setOnClickListener(this);
        }


        @Override
        public void onClick(View view) {

            Intent intent = new Intent(context,BookDetailActivity.class);
            intent.putExtra(BookDetailActivity.ID,data.get(getAdapterPosition()).getId());
            intent.putExtra("name",data.get(getAdapterPosition()).getName());
            intent.putExtra("author",data.get(getAdapterPosition()).getAuthor());
            intent.putExtra("link_img",data.get(getAdapterPosition()).getLink_img());
            intent.putExtra("genre",data.get(getAdapterPosition()).getGenre());
            intent.putExtra("published",data.get(getAdapterPosition()).getPublished());
            intent.putExtra("description",data.get(getAdapterPosition()).getDescription());
            intent.putExtra("price",data.get(getAdapterPosition()).getPrice());
            intent.setFlags(Intent.FLAG_ACTIVITY_NEW_TASK);
            context.startActivity(intent);
        }
    }

}
