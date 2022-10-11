package com.example.library;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.view.ContextMenu;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {

    public static final int MENU_ID_ADD = 1;
    public static final int MENU_ID_UPDATE = 2;
    public static final int MENU_ID_DELETE = 3;
    private ArrayList<String> mainStringSet;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        RecyclerView recyclerViewMain=findViewById(R.id.recycler_view);

        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(this);
        linearLayoutManager.setOrientation(LinearLayoutManager.VERTICAL);
        recyclerViewMain.setLayoutManager(linearLayoutManager);

        mainStringSet = new ArrayList<>();
        mainStringSet.add("信息安全数学基础");
        mainStringSet.add("软件项目管理案例教程");
        mainStringSet.add("创新工程实践");

       // for(int i=1;i<4;++i)
        //{
            //mainStringSet.add("item "+i);
        //}
        MainRecycleViewAdapter mainRecycleViewAdapter=new MainRecycleViewAdapter(mainStringSet);
        recyclerViewMain.setAdapter(mainRecycleViewAdapter);



    }

    @Override
    public boolean onContextItemSelected(@NonNull MenuItem item) {
        switch(item.getItemId())
        {
            case MENU_ID_ADD:
                Toast.makeText(this,"item add" +item.getOrder()+" clicked!",Toast.LENGTH_LONG)
                        .show();
                break;
            case MENU_ID_UPDATE:
                Toast.makeText(this,"item update " +item.getOrder()+" clicked!",Toast.LENGTH_LONG)
                        .show();
                break;
            case MENU_ID_DELETE:
                Toast.makeText(this,"item delete " +item.getOrder()+" clicked!",Toast.LENGTH_LONG)
                        .show();
                break;
        }
        return super.onContextItemSelected(item);
    }

    public class MainRecycleViewAdapter extends RecyclerView.Adapter<MainRecycleViewAdapter.ViewHolder> {

        private ArrayList<String> localDataSet;

        /**
         * Provide a reference to the type of views that you are using
         * (custom ViewHolder).
         */
        public class ViewHolder extends RecyclerView.ViewHolder implements View.OnCreateContextMenuListener {
            private final TextView textView;
            private final ImageView imageViewImage;

            public ViewHolder(View view) {
                super(view);
                // Define click listener for the ViewHolder's View

                imageViewImage = view.findViewById(R.id.image_view);
                textView = view.findViewById(R.id.textview);

                view.setOnCreateContextMenuListener(this);
            }

            public TextView getTextView() {
                return textView;
            }

            public ImageView getImageViewImage() {
                return imageViewImage;
            }

            @Override
            public void onCreateContextMenu(ContextMenu contextMenu, View view, ContextMenu.ContextMenuInfo contextMenuInfo) {
                contextMenu.add(0,MENU_ID_ADD,getAdapterPosition(),"Add "+getAdapterPosition());
                contextMenu.add(0,MENU_ID_UPDATE,getAdapterPosition(),"Update "+getAdapterPosition());
                contextMenu.add(0,MENU_ID_DELETE,getAdapterPosition(),"Delete "+getAdapterPosition());
            }
        }

        /**
         * Initialize the dataset of the Adapter.
         *
         * @param dataSet String[] containing the data to populate views to be used
         * by RecyclerView.
         */
        public MainRecycleViewAdapter(ArrayList<String> dataSet) {
            localDataSet = dataSet;
        }

        // Create new views (invoked by the layout manager)
        @Override
        @NonNull
        public ViewHolder onCreateViewHolder(ViewGroup viewGroup, int viewType) {
            // Create a new view, which defines the UI of the list item
            View view = LayoutInflater.from(viewGroup.getContext())
                    .inflate(R.layout.item_list, viewGroup, false);

            return new ViewHolder(view);
        }

        // Replace the contents of a view (invoked by the layout manager)
        @Override
        public void onBindViewHolder(ViewHolder viewHolder, final int position) {

            // Get element from your dataset at this position and replace the
            // contents of the view with that element
            viewHolder.getTextView().setText(localDataSet.get(position));
            if(position==0)
                viewHolder.getImageViewImage().setImageResource(R.drawable.book_1);
            else if(position==1)
                viewHolder.getImageViewImage().setImageResource(R.drawable.book_2);
            else if(position==2)
                viewHolder.getImageViewImage().setImageResource(R.drawable.book_no_name);
           // viewHolder.getImageViewImage().setImageResource(position%2==1?R.drawable.jmz:R.drawable.jmz);
        }

        // Return the size of your dataset (invoked by the layout manager)
        @Override
        public int getItemCount() {
            return localDataSet.size();
        }
    }
}








